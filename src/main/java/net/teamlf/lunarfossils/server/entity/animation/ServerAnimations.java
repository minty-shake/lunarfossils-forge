package net.teamlf.lunarfossils.server.entity.animation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.obj.LunarEntity;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public final class ServerAnimations {
    @Nullable
    public static JsonObject fromClientFile(LunarEntity<?> entity) {
        String resource = "/assets/" + LunarFossils.MOD_ID + "/animations/entity/" + entity.getEntityName() + ".animation.json";
        JsonObject client = read(resource);
        if (client == null) {
            LunarFossils.LOGGER.error("No client animation file {} for {}", resource, entity.getEntityName());
            return null;
        }
        return build(entity, client);
    }

    public static JsonObject build(LunarEntity<?> entity, JsonObject client) {
        String prefix = "animation." + entity.getEntityName() + ".";
        JsonObject animations = new JsonObject();

        for (Map.Entry<String, JsonElement> entry : client.getAsJsonObject("animations").entrySet()) {
            JsonObject source = entry.getValue().getAsJsonObject();
            JsonObject animation = new JsonObject();
            if (source.has("loop") && source.get("loop").isJsonPrimitive()
                    && source.get("loop").getAsJsonPrimitive().isBoolean() && source.get("loop").getAsBoolean()) {
                animation.addProperty("loop", true);
            }
            if (source.has("animation_length")) {
                animation.add("animation_length", source.get("animation_length"));
            }

            String shortName = entry.getKey().startsWith(prefix) ? entry.getKey().substring(prefix.length()) : entry.getKey();
            List<Double> delays = entity.getActionDelays().get(shortName);
            if (delays != null) {
                JsonArray array = new JsonArray();
                delays.forEach(array::add);
                animation.add("action_delay", array);
            }
            animations.add(entry.getKey(), animation);
        }

        for (String shortName : entity.getActionDelays().keySet()) {
            if (!animations.has(prefix + shortName)) {
                LunarFossils.LOGGER.warn("actionDelay for '{}' on {}: no animation {}{}", shortName, entity.getEntityName(), prefix, shortName);
            }
        }

        JsonObject root = new JsonObject();
        root.addProperty("format_version", "1.8.0");
        root.add("animations", animations);
        root.addProperty("geckolib_format_version", 2);
        return root;
    }

    @Nullable
    private static JsonObject read(String resource) {
        InputStream stream = ServerAnimations.class.getResourceAsStream(resource);
        if (stream == null) {
            return null;
        }
        try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException | RuntimeException e) {
            LunarFossils.LOGGER.error("Couldn't read {}", resource, e);
            return null;
        }
    }
}

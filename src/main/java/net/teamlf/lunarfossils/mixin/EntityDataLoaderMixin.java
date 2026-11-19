package net.teamlf.lunarfossils.mixin;

import com.github.teamfossilsarcheology.fossil.entity.data.EntityDataLoader;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import net.teamlf.lunarfossils.server.entity.obj.LunarEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashMap;
import java.util.Map;

@Mixin(EntityDataLoader.class)
public abstract class EntityDataLoaderMixin {

    @ModifyVariable(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
            at = @At("HEAD"), argsOnly = true)
    private Map<ResourceLocation, JsonElement> expanded$applyBuilderScale(Map<ResourceLocation, JsonElement> files) {
        Map<ResourceLocation, JsonElement> result = new HashMap<>(files);
        for (LunarEntity<?> entity : LunarEntities.all()) {
            ResourceLocation key = ResourceLocation.fromNamespaceAndPath("fossil", entity.getEntityName());
            if (!(result.get(key) instanceof JsonObject original)) {
                LunarFossils.LOGGER.error("No data/fossil/entity_info/{}.json for {}", entity.getEntityName(), entity.getEntityName());
                continue;
            }
            JsonObject json = original.deepCopy();
            json.addProperty("scaleBase", entity.getScaleBase());
            json.addProperty("scaleMax", entity.getScaleMax());
            if (entity.getEggScale() != null) {
                json.addProperty("eggScale", entity.getEggScale());
            }
            result.put(key, json);
        }
        return result;
    }
}

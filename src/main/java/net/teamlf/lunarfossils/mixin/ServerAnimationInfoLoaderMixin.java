package net.teamlf.lunarfossils.mixin;

import com.github.teamfossilsarcheology.fossil.entity.animation.ServerAnimationInfoLoader;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import net.teamlf.lunarfossils.server.entity.animation.ServerAnimations;
import net.teamlf.lunarfossils.server.entity.obj.LunarEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashMap;
import java.util.Map;

@Mixin(ServerAnimationInfoLoader.class)
public abstract class ServerAnimationInfoLoaderMixin {

    /**
     * Make sure it loads the right files (so action-delay ect, works properly)
     */
    @ModifyVariable(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
            at = @At("HEAD"), argsOnly = true)
    private Map<ResourceLocation, JsonElement> expanded$addServerAnimations(Map<ResourceLocation, JsonElement> files) {
        Map<ResourceLocation, JsonElement> result = new HashMap<>(files);
        int added = 0;
        for (LunarEntity<?> entity : LunarEntities.all()) {
            ResourceLocation key = LunarFossils.id("entity/" + entity.getEntityName() + ".animation");
            if (result.containsKey(key)) {
                continue;
            }
            JsonObject json = ServerAnimations.fromClientFile(entity);
            if (json != null) {
                result.put(key, json);
                added++;
            }
        }
        // We can remove this, just have it here as a "making sure it works"
        LunarFossils.LOGGER.info("Built server animations for {} entities from client animation files", added);
        return result;
    }
}

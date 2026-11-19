package net.teamlf.lunarfossils.client.renderer;

import com.github.teamfossilsarcheology.fossil.client.renderer.entity.PrehistoricGeoRenderer;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.Prehistoric;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import net.teamlf.lunarfossils.server.entity.obj.LunarEntity;

import java.util.function.Function;

public final class LunarEntityRenderers {
    public static void register() {
        for (LunarEntity<?> expandedEntity : LunarEntities.all()) {
            registerDino(expandedEntity.getType(), expandedEntity.getEntityName());
        }
    }

    private static <T extends Prehistoric> void registerDino(RegistrySupplier<EntityType<T>> type, String name) {
        registerDino(type, name, RenderType::entityCutoutNoCull);
    }

    private static <T extends Prehistoric> void registerDino(RegistrySupplier<EntityType<T>> type, String name, Function<ResourceLocation, RenderType> renderType) {
        String id = LunarFossils.id(name).toString();
        EntityRendererRegistry.register(type, context -> new PrehistoricGeoRenderer<>(context, id, renderType));
    }
}

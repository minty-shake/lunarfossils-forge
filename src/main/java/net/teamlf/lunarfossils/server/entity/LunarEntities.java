package net.teamlf.lunarfossils.server.entity;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricMobType;
import com.github.teamfossilsarcheology.fossil.util.TimePeriod;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.obj.ExampleEntity;
import net.teamlf.lunarfossils.server.entity.obj.LunarEntity;

import java.util.List;
import java.util.Optional;

public final class LunarEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTER = DeferredRegister.create(LunarFossils.MOD_ID, Registries.ENTITY_TYPE);
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(LunarFossils.MOD_ID, Registries.ITEM);

    public static final LunarEntity<ExampleEntity> EXAMPLE_ENTITY = LunarEntity
            .<ExampleEntity>builder("example_entity", ExampleEntity::new)
            .size(0.8F, 0.9F)
            .scale(0.2F, 0.7F)
            .eggScale(0.5F)
            .attributes(ExampleEntity::createAttributes)
            .spawnEgg(0x6b5a3e, 0xc9a66b)
            .mobType(PrehistoricMobType.DINOSAUR)
            .timePeriod(TimePeriod.MESOZOIC)
            .actionDelay("attack", 0.25)
            .build();

    public static List<LunarEntity<?>> all() {
        return LunarEntity.all();
    }

    public static Optional<LunarEntity<?>> byName(String entityName) {
        for (LunarEntity<?> entity : all()) {
            if (entity.getEntityName().equals(entityName)) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    public static void init() {
        ENTITY_TYPE_REGISTER.register();
        ITEM_REGISTER.register();
        LunarEntity.all().forEach(LunarEntity::registerExtras);
    }
}

package net.teamlf.lunarfossils.server.entity;

import java.util.List;
import java.util.Optional;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricMobType;
import com.github.teamfossilsarcheology.fossil.util.TimePeriod;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.obj.LunarEntity;
import net.teamlf.lunarfossils.server.entity.obj.Maraapunisaurus;
import net.teamlf.lunarfossils.server.entity.obj.Pantylus;
import net.teamlf.lunarfossils.server.entity.obj.Pycnonemosaurus;
import net.teamlf.lunarfossils.server.entity.obj.Vasuki;

public final class LunarEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTER = DeferredRegister.create(LunarFossils.MOD_ID, Registries.ENTITY_TYPE);
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(LunarFossils.MOD_ID, Registries.ITEM);

    public static final LunarEntity<Maraapunisaurus> MARAAPUNISAURUS = LunarEntity
            .<Maraapunisaurus>builder("maraapunisaurus", Maraapunisaurus::new)
            .size(1.2F, 2.0F)
            .scale(0.2F, 4.2F)
            .eggScale(1.4F)
            .attributes(Maraapunisaurus::createAttributes)
            .spawnEgg(0xd6ad6b, 0x783d1f)
            .mobType(PrehistoricMobType.DINOSAUR)
            .timePeriod(TimePeriod.MESOZOIC)
            .actionDelay("attack", 0.25)
            .build();

    public static final LunarEntity<Pantylus> PANTYLUS = LunarEntity
            .<Pantylus>builder("pantylus", Pantylus::new)
            .size(0.8F, 0.9F)
            .scale(0.1F, 0.3F)
            .eggScale(0.2F)
            .attributes(Pantylus::createAttributes)
            .spawnEgg(0xd1a23d, 0xded82c)
            .mobType(PrehistoricMobType.DINOSAUR)
            .timePeriod(TimePeriod.PALEOZOIC)
            .actionDelay("attack", 0.25)
            .build();

    public static final LunarEntity<Pycnonemosaurus> PYCNONEMOSAURUS = LunarEntity
            .<Pycnonemosaurus>builder("pycnonemosaurus", Pycnonemosaurus::new)
            .size(2.6F, 1.0F)
            .scale(0.3F, 1.9F)
            .eggScale(0.9F)
            .attributes(Pycnonemosaurus::createAttributes)
            .spawnEgg(0x2e1305, 0xb84309)
            .mobType(PrehistoricMobType.DINOSAUR)
            .timePeriod(TimePeriod.MESOZOIC)
            .actionDelay("attack", 0.25)
            .build();

    public static final LunarEntity<Vasuki> VASUKI = LunarEntity
            .<Vasuki>builder("vasuki", Vasuki::new)
            .size(2.0F, 0.3F)
            .scale(0.25F, 1.3F)
            .eggScale(0.9F)
            .attributes(Vasuki::createAttributes)
            .spawnEgg(0x661f35, 0x170803)
            .mobType(PrehistoricMobType.DINOSAUR)
            .timePeriod(TimePeriod.CENOZOIC)
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

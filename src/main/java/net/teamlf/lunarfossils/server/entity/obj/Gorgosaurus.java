package net.teamlf.lunarfossils.server.entity.obj;

import org.jetbrains.annotations.Nullable;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.Prehistoric;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricEntityInfo;
import com.github.teamfossilsarcheology.fossil.item.ModItems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import net.teamlf.lunarfossils.sound.LunarSounds;

public class Gorgosaurus extends Prehistoric {

    public Gorgosaurus(EntityType<? extends Prehistoric> entityType, Level level, ResourceLocation animationLocation) {
        super(entityType, level, animationLocation);
    }

    public Gorgosaurus(EntityType<? extends Prehistoric> entityType, Level level) {
        super(entityType, level, LunarFossils.id("animations/entity/" + EntityType.getKey(entityType).getPath() + ".animation.json"));
    }

    @Override
    public PrehistoricEntityInfo info() {
        return LunarEntities.GORGOSAURUS.getInfo();
    }

    @Override
    public Item getOrderItem() {
        return ModItems.SKULL_STICK.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return LunarSounds.PYCNO_BASS.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return LunarSounds.PYCNO_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return LunarSounds.PYCNO_DEATH.get();
    }
}
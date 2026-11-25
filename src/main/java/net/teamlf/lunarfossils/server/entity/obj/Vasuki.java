package net.teamlf.lunarfossils.server.entity.obj;

import org.jetbrains.annotations.Nullable;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricEntityInfo;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricSwimming;
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

public class Vasuki extends PrehistoricSwimming {

    public Vasuki(EntityType<? extends PrehistoricSwimming> entityType, Level level, ResourceLocation animationLocation) {
        super(entityType, level, animationLocation);
    }

    public Vasuki(EntityType<? extends PrehistoricSwimming> entityType, Level level) {
        super(entityType, level, LunarFossils.id("animations/entity/" + EntityType.getKey(entityType).getPath() + ".animation.json"));
    }

    @Override
    public PrehistoricEntityInfo info() {
        return LunarEntities.VASUKI.getInfo();
    }

    @Override
    public Item getOrderItem() {
        return ModItems.SKULL_STICK.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return LunarSounds.VASUKI_HISS_1.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return LunarSounds.VASUKI_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return LunarSounds.VASUKI_DEATH.get();
    }
}
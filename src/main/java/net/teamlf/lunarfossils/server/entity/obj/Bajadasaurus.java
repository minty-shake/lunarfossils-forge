package net.teamlf.lunarfossils.server.entity.obj;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.Prehistoric;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricEntityInfo;
import org.jetbrains.annotations.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import net.teamlf.lunarfossils.sound.LunarSounds;

public class Bajadasaurus extends Prehistoric {

    public Bajadasaurus(EntityType<? extends Prehistoric> entityType, Level level, ResourceLocation animationLocation) {
        super(entityType, level, animationLocation);
    }

    public Bajadasaurus(EntityType<? extends Prehistoric> entityType, Level level) {
        super(entityType, level, LunarFossils.id("animations/entity/" + EntityType.getKey(entityType).getPath() + ".animation.json"));
    }

    @Override
    public PrehistoricEntityInfo info() {
        return LunarEntities.BAJADASAURUS.getInfo();
    }

    @Override
    public Item getOrderItem() {
        return Items.STICK;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return LunarSounds.MARAA_CALL.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return LunarSounds.MARAA_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return LunarSounds.MARAA_DEATH.get();
    }
}
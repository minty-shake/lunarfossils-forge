package net.teamlf.lunarfossils.sound;

import net.teamlf.lunarfossils.LunarFossils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LunarSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LunarFossils.MOD_ID);

    public static final RegistryObject<SoundEvent> MARAA_ROAR = registerSoundEvents("maraaroar");
    public static final RegistryObject<SoundEvent> MARAA_CALL = registerSoundEvents("maraacall");
    public static final RegistryObject<SoundEvent> MARAA_RUMBLE = registerSoundEvents("maraarumble");
    public static final RegistryObject<SoundEvent> MARAA_HURT = registerSoundEvents("maraahurt");
    public static final RegistryObject<SoundEvent> MARAA_DEATH = registerSoundEvents("maraadeath");

    public static final ForgeSoundType MARAA_SOUNDS = new ForgeSoundType(1f, 1f,
            LunarSounds.MARAA_ROAR, LunarSounds.MARAA_CALL, LunarSounds.MARAA_RUMBLE,
            LunarSounds.MARAA_HURT, LunarSounds.MARAA_DEATH);

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LunarFossils.MOD_ID, name)));
    }

    public static void init(IEventBus eventBus) {
    SOUND_EVENTS.register(eventBus);
    }
}
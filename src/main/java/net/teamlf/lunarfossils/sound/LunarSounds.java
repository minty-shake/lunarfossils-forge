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

    public static final RegistryObject<SoundEvent> PANTY_CALL_1 = registerSoundEvents("pantycall1");
    public static final RegistryObject<SoundEvent> PANTY_CALL_2 = registerSoundEvents("pantycall2");
    public static final RegistryObject<SoundEvent> PANTY_SQUEAK_1 = registerSoundEvents("pantysqueak1");
    public static final RegistryObject<SoundEvent> PANTY_SQUEAK_2 = registerSoundEvents("pantysqueak2");
    public static final RegistryObject<SoundEvent> PANTY_SQUEAK_3 = registerSoundEvents("pantysqueak3");
    public static final RegistryObject<SoundEvent> PANTY_HURT = registerSoundEvents("pantyhurt");
    public static final RegistryObject<SoundEvent> PANTY_DEATH = registerSoundEvents("pantydeath");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LunarFossils.MOD_ID, name)));
    }

    public static void init(IEventBus eventBus) {
    SOUND_EVENTS.register(eventBus);
    }
}
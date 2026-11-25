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

    public static final RegistryObject<SoundEvent> MARAA_CALL = registerSoundEvents("maraacall");
    public static final RegistryObject<SoundEvent> MARAA_HURT = registerSoundEvents("maraahurt");
    public static final RegistryObject<SoundEvent> MARAA_DEATH = registerSoundEvents("maraadeath");

    public static final RegistryObject<SoundEvent> PANTY_SQUEAK_2 = registerSoundEvents("pantysqueak2");
    public static final RegistryObject<SoundEvent> PANTY_HURT = registerSoundEvents("pantyhurt");
    public static final RegistryObject<SoundEvent> PANTY_DEATH = registerSoundEvents("pantydeath");

    public static final RegistryObject<SoundEvent> PYCNO_BASS = registerSoundEvents("pycnobass");
    public static final RegistryObject<SoundEvent> PYCNO_HURT = registerSoundEvents("pycnohurt");
    public static final RegistryObject<SoundEvent> PYCNO_DEATH = registerSoundEvents("pycnodeath");

    public static final RegistryObject<SoundEvent> VASUKI_HISS_1 = registerSoundEvents("vasukihiss");
    public static final RegistryObject<SoundEvent> VASUKI_HURT = registerSoundEvents("vasukihurt");
    public static final RegistryObject<SoundEvent> VASUKI_DEATH = registerSoundEvents("vasukideath");

    public static final RegistryObject<SoundEvent> BAJA_CALL_1 = registerSoundEvents("bajacall");
    public static final RegistryObject<SoundEvent> BAJA_HURT = registerSoundEvents("bajahurt");
    public static final RegistryObject<SoundEvent> BAJA_DEATH = registerSoundEvents("bajadeath");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LunarFossils.MOD_ID, name)));
    }

    public static void init(IEventBus eventBus) {
    SOUND_EVENTS.register(eventBus);
    }
}
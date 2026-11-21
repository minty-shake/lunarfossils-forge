package net.teamlf.lunarfossils.sounds;

import net.teamlf.lunarfossils.LunarFossils;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LunarSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LunarFossils.MOD_ID);

    

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    public static void init() {
        LunarSounds.register(modEventBus);
    }
}

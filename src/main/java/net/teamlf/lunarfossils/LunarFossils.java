package net.teamlf.lunarfossils;

import com.mojang.logging.LogUtils;
import dev.architectury.platform.forge.EventBuses;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.teamlf.lunarfossils.client.LunarFossilsClient;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import org.slf4j.Logger;
import net.teamlf.lunarfossils.sound.LunarSounds;


@Mod(LunarFossils.MOD_ID)
public final class LunarFossils {
    public static final String MOD_ID = "lunarfossils";
    public static final Logger LOGGER = LogUtils.getLogger();

    public LunarFossils() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EventBuses.registerModEventBus(MOD_ID, modEventBus);

        LunarEntities.init();

        LunarSounds.init(modEventBus);

        EnvExecutor.runInEnv(Env.CLIENT, () -> LunarFossilsClient::init);
    }

    public static ResourceLocation id(String entityName) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, entityName);
    }
}

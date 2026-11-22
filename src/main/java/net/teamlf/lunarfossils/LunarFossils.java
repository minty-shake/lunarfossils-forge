package net.teamlf.lunarfossils;

import com.mojang.logging.LogUtils;
import dev.architectury.platform.forge.EventBuses;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.teamlf.lunarfossils.client.LunarFossilsClient;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import org.slf4j.Logger;

@Mod(LunarFossils.MOD_ID)
public final class LunarFossils {
    public static final String MOD_ID = "lunarfossils";
    public static final Logger LOGGER = LogUtils.getLogger();

    public LunarFossils() {
        EventBuses.registerModEventBus(MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        LunarEntities.init();

        EnvExecutor.runInEnv(Env.CLIENT, () -> LunarFossilsClient::init);
    }

    public static ResourceLocation id(String entityName) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, entityName);
    }
}

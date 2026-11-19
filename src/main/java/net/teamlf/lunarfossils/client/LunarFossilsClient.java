package net.teamlf.lunarfossils.client;


import net.teamlf.lunarfossils.client.renderer.LunarEntityRenderers;

public final class LunarFossilsClient {
    public static void init() {
        LunarEntityRenderers.register();
    }
}

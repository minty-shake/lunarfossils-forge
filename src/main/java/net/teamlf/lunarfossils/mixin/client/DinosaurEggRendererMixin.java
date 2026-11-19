package net.teamlf.lunarfossils.mixin.client;

import com.github.teamfossilsarcheology.fossil.client.renderer.entity.DinosaurEggRenderer;
import net.minecraft.resources.ResourceLocation;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DinosaurEggRenderer.class)
public abstract class DinosaurEggRendererMixin {

    /**
     * All this does is make sure whenever there is a Lunar Entity present it'll make sure it uses our egg textures
     */
    @Inject(method = "lambda$getTextureLocation$0", at = @At("HEAD"), cancellable = true, remap = false)
    private static void expanded$eggTexture(String name, CallbackInfoReturnable<ResourceLocation> cir) {
        if (LunarEntities.byName(name).isPresent()) {
            cir.setReturnValue(LunarFossils.id("textures/egg/" + name + "_egg.png"));
        }
    }
}

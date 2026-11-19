package net.teamlf.lunarfossils.mixin;

import com.github.teamfossilsarcheology.fossil.FossilMod;
import com.github.teamfossilsarcheology.fossil.entity.animation.ClientAnimationInfoLoader;
import com.github.teamfossilsarcheology.fossil.entity.animation.ServerAnimationInfoLoader;
import net.minecraft.resources.ResourceLocation;
import net.teamlf.lunarfossils.LunarFossils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ServerAnimationInfoLoader.class, ClientAnimationInfoLoader.class})
public abstract class AnimationInfoLoaderMixin {
    @Unique
    private String expanded$namespace;

    /**
     * Loads the server animation data from our folder aswel
     */
    @Redirect(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"))
    private boolean expanded$allowNamespace(String namespace, Object fossilNamespace) {
        this.expanded$namespace = namespace;
        return namespace.equals(fossilNamespace) || LunarFossils.MOD_ID.equals(namespace);
    }

    /**
     * Same thing as above but client side (has some other params)
     */
    @Redirect(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At(value = "INVOKE",
            target = "Lcom/github/teamfossilsarcheology/fossil/FossilMod;location(Ljava/lang/String;)Lnet/minecraft/resources/ResourceLocation;"))
    private ResourceLocation expanded$keepNamespace(String path) {
        if (LunarFossils.MOD_ID.equals(this.expanded$namespace)) {
            return LunarFossils.id(path);
        }
        return FossilMod.location(path);
    }
}

package net.teamlf.lunarfossils.mixin;

import com.github.teamfossilsarcheology.fossil.FossilMod;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.Prehistoric;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Prehistoric.class)
public abstract class PrehistoricMixin {

    /**
     * Make sure the textures refresh into the right place
     */
    @Redirect(method = "refreshTexturePath", at = @At(value = "INVOKE",
            target = "Lcom/github/teamfossilsarcheology/fossil/FossilMod;location(Ljava/lang/String;)Lnet/minecraft/resources/ResourceLocation;"))
    private ResourceLocation expanded$textureNamespace(String path) {
        ResourceLocation typeId = EntityType.getKey(((Prehistoric) (Object) this).getType());
        if (LunarFossils.MOD_ID.equals(typeId.getNamespace())) {
            return LunarFossils.id(path);
        }
        return FossilMod.location(path);
    }

    /**
     * Makes sure the animations refresh into the right place
     */
    @Redirect(method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V", at = @At(value = "INVOKE",
            target = "Lcom/github/teamfossilsarcheology/fossil/FossilMod;location(Ljava/lang/String;)Lnet/minecraft/resources/ResourceLocation;"))
    private static ResourceLocation expanded$animationNamespace(String path) {
        if (path.startsWith("animations/entity/") && path.endsWith(".animation.json")) {
            String name = path.substring("animations/entity/".length(), path.length() - ".animation.json".length());
            if (LunarEntities.byName(name).isPresent()) {
                return LunarFossils.id(path);
            }
        }
        return FossilMod.location(path);
    }
}

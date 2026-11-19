package net.teamlf.lunarfossils.mixin.client;

import com.github.teamfossilsarcheology.fossil.FossilMod;
import com.github.teamfossilsarcheology.fossil.client.model.PrehistoricGeoModel;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PrehistoricGeoModel.class)
public abstract class PrehistoricGeoModelMixin {

    /**
     * Makes sure it looks for the model, textures ect. inside lunar instead of f&a
     */
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/github/teamfossilsarcheology/fossil/FossilMod;location(Ljava/lang/String;)Lnet/minecraft/resources/ResourceLocation;"))
    private static ResourceLocation expanded$allowNamespacedName(String name) {
        if (name.indexOf(':') >= 0) {
            return ResourceLocation.bySeparator(name, ':');
        }
        return FossilMod.location(name);
    }
}

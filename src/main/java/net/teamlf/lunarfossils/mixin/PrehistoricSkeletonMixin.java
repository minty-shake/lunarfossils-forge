package net.teamlf.lunarfossils.mixin;

import com.github.teamfossilsarcheology.fossil.entity.PrehistoricSkeleton;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricEntityInfo;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import net.teamlf.lunarfossils.server.entity.obj.LunarEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PrehistoricSkeleton.class, remap = false)
public abstract class PrehistoricSkeletonMixin {

    /**
     * Makes sure the texture path of the skeleton is in the right place
     */
    @Inject(method = "refreshTexturePath", at = @At("TAIL"))
    private void expanded$fixSkeletonNamespace(CallbackInfo ci) {
        PrehistoricSkeleton self = (PrehistoricSkeleton) (Object) this;
        if (!self.level().isClientSide) {
            return;
        }
        PrehistoricEntityInfo info = self.info();
        for (LunarEntity<?> entity : LunarEntities.all()) {
            if (entity.getInfo() == info) {
                String name = info.resourceName;
                self.textureLocation = LunarFossils.id("textures/entity/" + name + "/" + name + "_skeleton.png");
                self.modelLocation = LunarFossils.id("geo/entity/" + name + ".geo.json");
                return;
            }
        }
    }
}

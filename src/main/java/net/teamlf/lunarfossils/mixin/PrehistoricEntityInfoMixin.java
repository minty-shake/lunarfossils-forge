package net.teamlf.lunarfossils.mixin;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricEntityInfo;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import net.teamlf.lunarfossils.server.entity.obj.LunarEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(PrehistoricEntityInfo.class)
public abstract class PrehistoricEntityInfoMixin {

    @Shadow
    @Final
    @Mutable
    private static PrehistoricEntityInfo[] $VALUES;

    /**
     * Adds the entries to the actual EntityProperties
     */
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void expanded$addEntries(CallbackInfo ci) {
        List<PrehistoricEntityInfo> values = new ArrayList<>(Arrays.asList($VALUES));
        int vanillaCount = values.size();

        for (LunarEntity<?> entity : LunarEntities.all()) {
            PrehistoricEntityInfo info = PrehistoricEntityInfoAccessor.expanded$create(
                    entity.enumName(),
                    values.size(),
                    entity.getType(),
                    entity.getMobType(),
                    entity.getTimePeriod(),
                    entity.getEggPrimary(),
                    entity.getEggSecondary());
            values.add(info);
            entity.setInfo(info);
        }

        $VALUES = values.toArray(PrehistoricEntityInfo[]::new);
        LunarFossils.LOGGER.info("Added {} entries to PrehistoricEntityInfo", values.size() - vanillaCount);
    }
}

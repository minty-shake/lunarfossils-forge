package net.teamlf.lunarfossils.mixin;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricEntityInfo;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricMobType;
import com.github.teamfossilsarcheology.fossil.util.TimePeriod;
import dev.architectury.registry.registries.RegistrySupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PrehistoricEntityInfo.class)
public interface PrehistoricEntityInfoAccessor {

    /**
     * Makes it so we can add an PrehistoricEntityInfo "object"
     */
    @Invoker("<init>")
    static PrehistoricEntityInfo expanded$create(String name, int ordinal, RegistrySupplier<?> entitySupplier, PrehistoricMobType mobType, TimePeriod timePeriod, int backgroundEggColor, int highlightEggColor) {
        throw new AssertionError();
    }
}

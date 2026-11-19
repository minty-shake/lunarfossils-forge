package net.teamlf.lunarfossils.server.entity.obj;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.Prehistoric;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricEntityInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.LunarEntities;

public class ExampleEntity extends Prehistoric {

    public ExampleEntity(EntityType<? extends Prehistoric> entityType, Level level, ResourceLocation animationLocation) {
        super(entityType, level, animationLocation);
    }

    public ExampleEntity(EntityType<? extends Prehistoric> entityType, Level level) {
        super(entityType, level, LunarFossils.id("animations/entity/" + EntityType.getKey(entityType).getPath() + ".animation.json"));
    }

    @Override
    public PrehistoricEntityInfo info() {
        return LunarEntities.EXAMPLE_ENTITY.getInfo();
    }

    @Override
    public Item getOrderItem() {
        return Items.STICK;
    }
}

package net.teamlf.lunarfossils.server.entity.obj;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.Prehistoric;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricEntityInfo;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricMobType;
import com.github.teamfossilsarcheology.fossil.util.TimePeriod;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.teamlf.lunarfossils.LunarFossils;
import net.teamlf.lunarfossils.server.entity.LunarEntities;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;

@Getter
public final class LunarEntity<T extends Prehistoric> {

    private static final List<LunarEntity<?>> ALL = new ArrayList<>();

    private final String entityName;
    private final RegistrySupplier<EntityType<T>> type;

    @Nullable
    private final Supplier<AttributeSupplier.Builder> attributes;

    private final PrehistoricMobType mobType;
    private final TimePeriod timePeriod;
    private final int eggPrimary;
    private final int eggSecondary;

    private final String displayName;
    private final String uniqueBoneName;
    private final String eggItemTexture;

    private final float scaleBase;
    private final float scaleMax;

    @Nullable
    private final Float eggScale;
    private final Map<String, List<Double>> actionDelays;

    private PrehistoricEntityInfo info;

    private LunarEntity(Builder<T> b) {
        this.entityName = b.entityName;
        this.attributes = b.attributes;
        this.mobType = b.mobType;
        this.timePeriod = b.timePeriod;
        this.eggPrimary = b.eggPrimary;
        this.eggSecondary = b.eggSecondary;
        this.displayName = b.displayName != null ? b.displayName : Character.toUpperCase(b.entityName.charAt(0)) + b.entityName.substring(1);
        this.uniqueBoneName = b.uniqueBoneName;
        this.eggItemTexture = b.eggItemTexture != null ? b.eggItemTexture : b.entityName + "_egg";
        this.scaleBase = b.scaleBase;
        this.scaleMax = b.scaleMax;
        this.eggScale = b.eggScale;
        this.actionDelays = Collections.unmodifiableMap(new LinkedHashMap<>(b.actionDelays));

        this.type = LunarEntities.ENTITY_TYPE_REGISTER.register(b.entityName, () ->
                EntityType.Builder.of(b.factory, b.category).sized(b.width, b.height).build(LunarFossils.id(b.entityName).toString())
        );

        ALL.add(this);
    }

    public static List<LunarEntity<?>> all() {
        return Collections.unmodifiableList(ALL);
    }

    public void registerExtras() {
        if (attributes != null) {
            EntityAttributeRegistry.register(type, attributes);
        }
    }

    @Nullable
    public T create(Level level) {
        return type.get().create(level);
    }

    public String enumName() {
        return entityName.toUpperCase(Locale.ROOT);
    }

    public PrehistoricEntityInfo getInfo() {
        if (info == null) {
            throw new IllegalStateException("PrehistoricEntityInfo for " + entityName + " not created yet; is the mixin applied?");
        }
        return info;
    }

    public void setInfo(PrehistoricEntityInfo info) {
        if (this.info != null) {
            throw new IllegalStateException("PrehistoricEntityInfo for " + entityName + " already set");
        }
        this.info = info;
    }

    public static <T extends Prehistoric> Builder<T> builder(String entityName, EntityType.EntityFactory<T> factory) {
        return new Builder<>(entityName, factory);
    }

    @RequiredArgsConstructor
    public static final class Builder<T extends Prehistoric> {
        private final String entityName;
        private final EntityType.EntityFactory<T> factory;

        private MobCategory category = MobCategory.CREATURE;
        private float width = 1.0F;
        private float height = 1.0F;
        private Supplier<AttributeSupplier.Builder> attributes;
        private int eggPrimary = 0xFFFFFF;
        private int eggSecondary = 0xFFFFFF;

        private PrehistoricMobType mobType = PrehistoricMobType.DINOSAUR;
        private TimePeriod timePeriod = TimePeriod.MESOZOIC;

        private String displayName;
        private String uniqueBoneName = "%s Unique Bone";
        private String eggItemTexture;
        private float scaleBase = Float.NaN;
        private float scaleMax = Float.NaN;
        private Float eggScale;
        private final Map<String, List<Double>> actionDelays = new LinkedHashMap<>();

        public Builder<T> category(MobCategory category) {
            this.category = category;
            return this;
        }

        public Builder<T> size(float width, float height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder<T> attributes(Supplier<AttributeSupplier.Builder> attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder<T> spawnEgg(int primaryColor, int secondaryColor) {
            this.eggPrimary = primaryColor;
            this.eggSecondary = secondaryColor;
            return this;
        }

        public Builder<T> mobType(PrehistoricMobType mobType) {
            this.mobType = mobType;
            return this;
        }

        public Builder<T> timePeriod(TimePeriod timePeriod) {
            this.timePeriod = timePeriod;
            return this;
        }

        public Builder<T> displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder<T> uniqueBoneName(String uniqueBoneName) {
            this.uniqueBoneName = uniqueBoneName;
            return this;
        }

        public Builder<T> scale(float baby, float adult) {
            this.scaleBase = baby;
            this.scaleMax = adult;
            return this;
        }

        public Builder<T> eggScale(float eggScale) {
            this.eggScale = eggScale;
            return this;
        }

        public Builder<T> eggItemTexture(String eggItemTexture) {
            this.eggItemTexture = eggItemTexture;
            return this;
        }

        public Builder<T> actionDelay(String animation, double... seconds) {
            List<Double> list = new ArrayList<>();
            for (double second : seconds) {
                list.add(second);
            }
            this.actionDelays.put(animation, List.copyOf(list));
            return this;
        }

        public LunarEntity<T> build() {
            if (entityName == null || entityName.isBlank()) {
                throw new IllegalArgumentException("ExpandedEntity needs a name");
            }
            if (Float.isNaN(scaleBase) || Float.isNaN(scaleMax)) {
                throw new IllegalArgumentException("ExpandedEntity " + entityName + " needs .scale(base, max)");
            }
            return new LunarEntity<>(this);
        }
    }
}

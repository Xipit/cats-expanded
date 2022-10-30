package xipit.cats.expanded.goal;

import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import xipit.cats.expanded.util.ModAnimalEntityMixinInterface;

public class ModDruggedBehaviourGoal
        extends WanderAroundGoal {
    public static final float CHANCE = 0.001f;
    protected static final double speed = 1.75;
    protected final float probability;


    public ModDruggedBehaviourGoal(PathAwareEntity mob, float probability) {
        super(mob, speed);
        this.probability = probability;
    }

    @Override
    public boolean canStart() {
        Vec3d vec3d;
        if ((vec3d = this.getWanderTarget()) == null) {
            return false;
        }
        this.targetX = vec3d.x;
        this.targetY = vec3d.y;
        this.targetZ = vec3d.z;

        return ((ModAnimalEntityMixinInterface) mob).getCatsExpandedCatnipHighDuration() > 0;
    }

    @Override
    @Nullable
    protected Vec3d getWanderTarget() {
        if (this.mob.isInsideWaterOrBubbleColumn()) {
            Vec3d vec3d = FuzzyTargeting.find(this.mob, 15, 7);
            return vec3d == null ? super.getWanderTarget() : vec3d;
        }
        if (this.mob.getRandom().nextFloat() >= this.probability) {
            return FuzzyTargeting.find(this.mob, 6, 3);
        }
        return super.getWanderTarget();
    }

    @Override
    public boolean canStop() {
        return ((ModAnimalEntityMixinInterface) mob).getCatsExpandedCatnipHighDuration() == 0;
    }

    @Override
    public void tick() {
        ((ModAnimalEntityMixinInterface) mob).decreaseCatsExpandedCatnipHighDuration(2);

        if (Math.random() >= 0.5) {
            mob.playSound(SoundEvents.ENTITY_CAT_PURREOW, (float) (0.4f + 0.4f * (Math.random() - Math.random())), 1f);
        } else {
            mob.playSound(SoundEvents.ENTITY_CAT_PURR, (float) (0.4f + 0.4f * (Math.random() - Math.random())), 1.1f);
        }
    }
}

package xipit.cats.expanded.util;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.util.math.Vec3d;

public class ModDruggedBehaviourGoal 
extends WanderAroundGoal{
    public static final float CHANCE = 0.001f;
    protected static final double speed = 3;
    protected final float probability;
    

    public ModDruggedBehaviourGoal(PathAwareEntity mob, float probability){
        super(mob, speed);
        this.probability = probability;
    }

    @Override
    public boolean canStart(){
        return mob.hasStatusEffect(StatusEffects.NAUSEA) && super.canStart();
    }

    @Override
    @Nullable
    protected Vec3d getWanderTarget(){
        if (this.mob.isInsideWaterOrBubbleColumn()) {
            Vec3d vec3d = FuzzyTargeting.find(this.mob, 15, 7);
            return vec3d == null ? super.getWanderTarget() : vec3d;
        }
        if (this.mob.getRandom().nextFloat() >= this.probability) {
            return FuzzyTargeting.find(this.mob, 5, 3);
        }
        return super.getWanderTarget();
    }
}

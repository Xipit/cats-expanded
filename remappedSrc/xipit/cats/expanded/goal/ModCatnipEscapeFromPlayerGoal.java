package xipit.cats.expanded.goal;

import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import xipit.cats.expanded.util.ModCreeperEntityMixinInterface;

public class ModCatnipEscapeFromPlayerGoal
        extends FleeEntityGoal<PlayerEntity> {

    private double accessableFleeSpeed;


    public ModCatnipEscapeFromPlayerGoal(PathAwareEntity mob, float distance, double slowSpeed,
                                         double fastSpeed) {
        super(mob, PlayerEntity.class, distance, slowSpeed, fastSpeed);
        accessableFleeSpeed = fastSpeed;
    }


    @Override
    public boolean canStart() {
        return ((ModCreeperEntityMixinInterface) mob).getCatsExpandedIsCatnipEscaping()
                && super.canStart();
    }

    @Override
    public void start() {
        this.fleeingEntityNavigation.startMovingAlong(this.fleePath, this.accessableFleeSpeed);
    }

    @Override
    public void stop() {
        this.targetEntity = null;
        ((ModCreeperEntityMixinInterface) mob).setCatsExpandedIsCatnipEscaping(false);
    }
}
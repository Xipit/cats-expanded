package xipit.cats.expanded.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldView;
import xipit.cats.expanded.block.ModBlocks;

public class ModEatCatnipGoal
    extends MoveToTargetPosGoal {

    protected final Random random = new Random();
    private static final int EATING_TIME = 40;
    protected int timer;
    private static final int HIGH_DURATION = 300;



    public ModEatCatnipGoal(PathAwareEntity mob, double speed, int range, int maxYDifference) {
        super(mob, speed, range, maxYDifference);
    }



    @Override
    public double getDesiredSquaredDistanceToTarget() {
        return 2.0;
    }

    @Override
    public boolean shouldResetPath() {
        return this.tryingTime % 100 == 0;
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isOf(ModBlocks.CATNIP_BUSH) && blockState.get(ModBlocks.CATNIP_BUSH.AGE) >= 2;
    }

    //TODO: add custom sound
    @Override
    public void tick() {
        if (this.hasReached()) {
            if (this.timer >= 40) {
                this.eatCatnip();
            } else {
                ++this.timer;
            }
        } else if (!this.hasReached() && random.nextFloat() < 0.05f) {
            mob.playSound(SoundEvents.ENTITY_FOX_SNIFF, 1.0f, 1.0f);
        }
        super.tick();
    }

    protected void eatCatnip() {
        if (!mob.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            return;
        }
        BlockState blockState = mob.world.getBlockState(this.targetPos);
        if (blockState.isOf(ModBlocks.CATNIP_BUSH)) {
            this.devourCatnip(blockState);
        }
    }


    private void devourCatnip(BlockState state) {
        // Nausea is important for ModDruggedBehaviourGoal that simulates the effects of catnip
        mob.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, HIGH_DURATION));
        mob.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, HIGH_DURATION));
        mob.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, HIGH_DURATION));

        //TODO: add custom sound
        mob.playSound(SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, 1.0f, 1.0f);
        mob.world.setBlockState(this.targetPos, (BlockState)state.with(ModBlocks.CATNIP_BUSH.AGE, 1), Block.NOTIFY_LISTENERS);
    }

    @Override
    public boolean canStart() {
        return !mob.isSleeping() && super.canStart() && random.nextInt(10) < 7;
    }

    @Override
    public void start() {
        this.timer = 0;
        //mob.setSitting(false);
        super.start();
    }
}


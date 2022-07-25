package xipit.cats.expanded.goal;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldView;
import xipit.cats.expanded.block.CatnipBushBlock;
import xipit.cats.expanded.block.ModBlocks;
import xipit.cats.expanded.util.ModAnimalEntityMixinInterface;

public class ModEatCatnipGoal
        extends MoveToTargetPosGoal {

    protected final Random random = new Random();
    protected int timer;

    public ModEatCatnipGoal(AnimalEntity mob, double speed, int range, int maxYDifference) {
        super(mob, speed, range, maxYDifference);
    }


    @Override
    public double getDesiredDistanceToTarget() {
        return 2.0;
    }

    @Override
    public boolean shouldResetPath() {
        return this.tryingTime % 100 == 0;
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isOf(ModBlocks.CATNIP_BUSH) && blockState.get(CatnipBushBlock.AGE) >= 2;
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
        ((ModAnimalEntityMixinInterface) mob).increaseCatsExpandedCatnipHighDuration(150);

        //TODO: add custom sound
        mob.playSound(SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, 1.0f, 1.0f);
        //mob.playSound(SoundEvents.ENTITY_CAT_EAT, 1.0f, 1.0f);
        mob.world.setBlockState(this.targetPos, (BlockState) state.with(CatnipBushBlock.AGE, 1), Block.NOTIFY_LISTENERS);
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


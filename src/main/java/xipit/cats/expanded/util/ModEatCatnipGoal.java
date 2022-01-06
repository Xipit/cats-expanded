package xipit.cats.expanded.util;

import java.util.EnumSet;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import xipit.cats.expanded.block.ModBlocks;


// look at BeeEntity$PollinateGoal in findFlower to figure out targetPos
public class ModEatCatnipGoal
extends MoveToTargetPosGoal {
    private static final int MAX_TIMER = 40;
    private static final Predicate<BlockState> CATNIP_BUSH_PREDICATE = BlockStatePredicate.forBlock(ModBlocks.CATNIP_BUSH);
    private final PathAwareEntity mob;
    private final World world;
    private int timer;
    private boolean reached;

    public ModEatCatnipGoal(PathAwareEntity mob, double speed, int range) {
        super(mob, speed, range, 6);
        this.mob = mob;
        this.world = mob.world;
        this.lowestY = -2;
        this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 50 : 1000) != 0) {
            return false;
        }
        BlockPos blockPos = this.mob.getBlockPos();
        if (CATNIP_BUSH_PREDICATE.test(this.world.getBlockState(blockPos))) {
            return true;
        }
        return this.world.getBlockState(blockPos.down()).isOf(ModBlocks.CATNIP_BUSH);
    }

    @Override
    public void start() {
        this.timer = this.getTickCount(40);
        this.world.sendEntityStatus(this.mob, (byte)10);
        this.mob.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.timer = 0;
    }

    @Override
    public boolean shouldContinue() {
        return this.timer > 0;
    }

    public int getTimer() {
        return this.timer;
    }

    // TODO: set targetpos somehow
    @Override
    public void tick() {
        this.timer = Math.max(0, this.timer - 1);
        if (this.timer != this.getTickCount(4)) {
            return;
        }

        BlockPos blockPos = this.getTargetPos();
        if (!blockPos.isWithinDistance(this.mob.getPos(), this.getDesiredSquaredDistanceToTarget())) {
            this.reached = false;
            ++this.tryingTime;
            if (this.shouldResetPath()) {
                this.mob.getNavigation().startMovingTo((double)blockPos.getX() + 0.5, blockPos.getY(), (double)blockPos.getZ() + 0.5, this.speed);
            }
        } else {
            this.reached = true;
            --this.tryingTime;
        }


        
        if (CATNIP_BUSH_PREDICATE.test(this.world.getBlockState(blockPos))) {
            this.world.setBlockState(blockPos, ModBlocks.CATNIP_BUSH.getDefaultState(), Block.NOTIFY_LISTENERS);

            this.mob.onEatingGrass();
            this.mob.emitGameEvent(GameEvent.EAT, this.mob.getCameraBlockPos());
        } else {
            BlockPos blockPos2 = blockPos.down();
            if (this.world.getBlockState(blockPos2).isOf(Blocks.GRASS_BLOCK)) {
                if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    this.world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, blockPos2, Block.getRawIdFromState(Blocks.GRASS_BLOCK.getDefaultState()));
                    this.world.setBlockState(blockPos2, Blocks.DIRT.getDefaultState(), Block.NOTIFY_LISTENERS);
                }
                this.mob.onEatingGrass();
                this.mob.emitGameEvent(GameEvent.EAT, this.mob.getCameraBlockPos());
            }
        }
    }

    @Override
    protected boolean isTargetPos(WorldView var1, BlockPos var2) {
        // TODO Auto-generated method stub
        return false;
    }
}


package xipit.cats.expanded.util;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

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
import xipit.cats.expanded.item.ModItems;


// look at BeeEntity$PollinateGoal in findFlower to figure out targetPos
public class ModEatCatnipGoal
extends MoveToTargetPosGoal {
    private static final int MAX_TIMER = 40;
    private static final Predicate<BlockState> CATNIP_BUSH_PREDICATE = BlockStatePredicate.forBlock(ModBlocks.CATNIP_BUSH);
    private final PathAwareEntity mob;
    private final World world;
    private int timer;
    private boolean reached;
    @Nullable
    BlockPos catnipBushPos;

    public ModEatCatnipGoal(PathAwareEntity mob, double speed, int range) {
        super(mob, speed, range, 6);
        this.mob = mob;
        this.world = mob.world;
        this.lowestY = -2;
        this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        Optional<BlockPos> optional = this.getCatnipBush();
        if(optional.isPresent()){
            //catnipBushPos = optional.get();
            targetPos = optional.get();
            startMovingToTarget();
            return true;
        }
        return false;
    }

    private Optional<BlockPos> getCatnipBush() {
        return this.findCatnipBush(CATNIP_BUSH_PREDICATE, 15.0);
    }

    private Optional<BlockPos> findCatnipBush(Predicate<BlockState> predicate, double searchDistance) {
        BlockPos blockPos = mob.getBlockPos();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int i = 0;
        while ((double)i <= searchDistance) {
            int j = 0;
            while ((double)j < searchDistance) {
                int k = 0;
                while (k <= j) {
                    int l;
                    int n = l = k < j && k > -j ? j : 0;
                    while (l <= j) {
                        mutable.set(blockPos, k, i - 1, l);
                        if (blockPos.isWithinDistance(mutable, searchDistance) && predicate.test(world.getBlockState(mutable))) {
                            return Optional.of(mutable);
                        }
                        l = l > 0 ? -l : 1 - l;
                    }
                    k = k > 0 ? -k : 1 - k;
                }
                ++j;
            }
            i = i > 0 ? -i : 1 - i;
        }
        return Optional.empty();
    }


    @Override
    public void start() {
        this.timer = this.getTickCount(40);
        // dont know what this does: 
        //      this.world.sendEntityStatus(this.mob, (byte)10);
    }

    @Override
    public void stop() {
        this.timer = 0;
        this.mob.getNavigation().stop();
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

            if (CATNIP_BUSH_PREDICATE.test(this.world.getBlockState(blockPos))) {
                this.world.setBlockState(blockPos, ModBlocks.CATNIP_BUSH.getDefaultState(), Block.NOTIFY_LISTENERS);
    
                this.mob.onEatingGrass();
                this.mob.emitGameEvent(GameEvent.EAT, this.mob.getCameraBlockPos());
            }
        }


    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
            return blockState.isOf(ModBlocks.CATNIP_BUSH) && blockState.get(ModBlocks.CATNIP_BUSH.AGE) >= 2;
        
    }
}


package xipit.cats.expanded;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.block.ModBlocks;

import java.util.function.Predicate;

public class ModCreeperNavigation extends MobNavigation {
    private static final Predicate<BlockState> CATNIP_BUSH_PREDICATE = BlockStatePredicate.forBlock(ModBlocks.CATNIP_BUSH);

    public ModCreeperNavigation(MobEntity mobEntity, World world) {
        super(mobEntity, world);
    }

    // TODO: very likely wrong approach, ask in fabric discord
    @Override
    public boolean isValidPosition(BlockPos pos) {
        BlockPos blockPos = new BlockPos(this.getPos().add(new Vec3d(0, -1, 0)));
        BlockState blockstate = ((Entity)entity).world.getBlockState(blockPos);
        BlockPos blockPos0 = new BlockPos(this.getPos().add(new Vec3d(0, 0, 0)));
        BlockState blockstate0 = ((Entity)entity).world.getBlockState(blockPos);
        BlockPos blockPos2 = new BlockPos(this.getPos().add(new Vec3d(0, -2, 0)));
        BlockState blockstate2 = ((Entity)entity).world.getBlockState(blockPos);

        // returns false currently ISSUE TODO
        CatsExpandedMod.LOGGER.info("Y - 1:" + CATNIP_BUSH_PREDICATE.test(blockstate));
        CatsExpandedMod.LOGGER.info("Y    :" + CATNIP_BUSH_PREDICATE.test(blockstate0));
        CatsExpandedMod.LOGGER.info("Y + 1:" + CATNIP_BUSH_PREDICATE.test(blockstate2));
        CatsExpandedMod.LOGGER.info("isof Y - 1:" + blockstate.isOf(ModBlocks.CATNIP_BUSH));
        CatsExpandedMod.LOGGER.info("isof Y    :" + blockstate0.isOf(ModBlocks.CATNIP_BUSH));
        CatsExpandedMod.LOGGER.info("isof Y + 1:" + blockstate2.isOf(ModBlocks.CATNIP_BUSH));

        CatsExpandedMod.LOGGER.info(blockstate.getBlock().toString());
        CatsExpandedMod.LOGGER.info(blockstate0.getBlock().toString());
        CatsExpandedMod.LOGGER.info(blockstate2.getBlock().toString());

        CatsExpandedMod.LOGGER.info(((Entity)entity).world.getBlockState(pos).getBlock().toString());

        return CATNIP_BUSH_PREDICATE.test(blockstate) || super.isAtValidPosition();
    }
}

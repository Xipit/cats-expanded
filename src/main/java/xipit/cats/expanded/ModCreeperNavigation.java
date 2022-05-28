package xipit.cats.expanded;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.block.ModBlocks;

public class ModCreeperNavigation extends MobNavigation {
    public ModCreeperNavigation(MobEntity mobEntity, World world) {
        super(mobEntity, world);
    }

    @Override
    protected boolean isAtValidPosition() {
        BlockPos blockPos = new BlockPos(this.getPos().add(new Vec3d(0, -1, 0)));
        Block block = ((Entity)entity).world.getBlockState(blockPos).getBlock();

        // returns false currently ISSUE TODO
        CatsExpandedMod.LOGGER.info(block == ModBlocks.CATNIP_BUSH);

        return block != ModBlocks.CATNIP_BUSH || super.isAtValidPosition();
    }
}

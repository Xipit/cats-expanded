package xipit.cats.expanded.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.state.property.IntProperty;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xipit.cats.expanded.block.CatnipBushBlock;
import xipit.cats.expanded.block.ModBlocks;

// interaction with catnip -> bees now want to grow catnip, same behaviour as with sweet_berry_bush
@Mixin(targets = "net.minecraft.entity.passive.BeeEntity$GrowCropsGoal")
public abstract class GrowCropsGoalMixin {
    // The synthetic field of BeeEntity.this
    @Final
    @Shadow
    BeeEntity field_20373;

    @Inject(method = "tick", at = @At("TAIL"))
    protected void InjectTick(CallbackInfo ci) {
        for (int i = 1; i <= 2; ++i) {
            BlockPos blockPos = field_20373.getBlockPos().down(i);
            BlockState blockState = field_20373.world.getBlockState(blockPos);
            boolean bl = false;
            IntProperty intProperty = null;

            if (!blockState.isIn(BlockTags.BEE_GROWABLES)) continue;

            if (blockState.isOf(ModBlocks.CATNIP_BUSH)) {
                int cropBlock = blockState.get(CatnipBushBlock.AGE);
                if (cropBlock < 3) {
                    bl = true;
                    intProperty = CatnipBushBlock.AGE;
                }
            }

            if (!bl) continue;

            field_20373.world.syncWorldEvent(WorldEvents.PLANT_FERTILIZED, blockPos, 0);
            field_20373.world.setBlockState(blockPos, (BlockState) blockState.with(intProperty, blockState.get(intProperty) + 1));
            ((BeeEntityInvoker) field_20373).invokeAddCropCounter();
        }
    }
}

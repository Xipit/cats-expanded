package xipit.cats.expanded.mixin;

import org.apache.commons.lang3.NotImplementedException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;
import xipit.cats.expanded.item.ModItems;


// mostly copied from https://github.com/d4rkm0nkey/CompostableRottenFlesh/blob/1.17/Fabric/src/main/java/me/lumpy/monkey/compostable/rottenflesh/mixin/CompostMixin.java
@Mixin(ComposterBlock.class)
public abstract class ComposterBlockMixin
        extends Block {
    public ComposterBlockMixin(Settings settings) {
        super(settings);
    }


    @Invoker("registerCompostableItem")
    private static void invokeRegisterCompostableItem(float levelIncreaseChance, ItemConvertible item) {
        throw new NotImplementedException("The invoke failed.");
    }


    @Inject(at = @At("HEAD"), method = "registerDefaultCompostableItems()V")
    private static void injectRegisterDefaultCompostableItems(CallbackInfo info) {
        ComposterBlockMixin.invokeRegisterCompostableItem(0.3f, ModItems.CATNIP);
    }
}

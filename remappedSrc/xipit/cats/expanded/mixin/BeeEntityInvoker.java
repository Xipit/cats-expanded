package xipit.cats.expanded.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.passive.BeeEntity;

@Mixin(BeeEntity.class)
public interface BeeEntityInvoker {

    @Invoker("addCropCounter")
    void invokeAddCropCounter();
}

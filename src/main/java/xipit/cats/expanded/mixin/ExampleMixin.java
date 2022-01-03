package xipit.cats.expanded.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import xipit.cats.expanded.CatsExpanded;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		CatsExpanded.LOGGER.info("This line is printed by an example mod mixin!");
	}
}

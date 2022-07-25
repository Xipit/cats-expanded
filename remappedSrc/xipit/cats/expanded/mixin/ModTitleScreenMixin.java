package xipit.cats.expanded.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.TitleScreen;
import xipit.cats.expanded.CatsExpandedClient;
import xipit.cats.expanded.CatsExpandedMod;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public abstract class ModTitleScreenMixin {
    Random rand = new Random();

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {

        int index = rand.nextInt(CatsExpandedClient.CATFACTS.length);

        CatsExpandedMod.LOGGER.info("Random Catfact: " + CatsExpandedClient.CATFACTS[index]);
    }
}


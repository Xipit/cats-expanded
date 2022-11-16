package xipit.cats.expanded.mixin;

import net.minecraft.client.render.entity.CatEntityRenderer;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.entity.ModEntities;

@Mixin(CatEntityRenderer.class)
public class ModCatEntityRendererMixin {

    @Inject(method = "getTexture", at = @At("RETURN"), cancellable = true)
    private void InjectGetTexture(CatEntity catEntity, CallbackInfoReturnable<Identifier> cir) {
        CatVariant variant = catEntity.getVariant();

        if (variant == ModEntities.PEANUT_BUTTER) {
            CatsExpandedMod.LOGGER.info("PEANUT BUTTER IS BEEING RENDERED");

            cir.setReturnValue(ModEntities.PEANUT_BUTTER_TEXTURE_ID);
        }
    }
}

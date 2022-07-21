package xipit.cats.expanded.mixin;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

/*
 *  Source: @Noaaan from https://github.com/Noaaan/MythicMetals
 */
@Mixin(EntityModelLayers.class)
public interface AccessorEntityModelLayers {
    @Accessor("LAYERS")
    static Set<EntityModelLayer> getAllModels() {
        throw new IllegalStateException();
    }
}
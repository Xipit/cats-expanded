package xipit.cats.expanded.mixin;

import net.minecraft.entity.passive.AnimalEntity;
import org.spongepowered.asm.mixin.Mixin;
import xipit.cats.expanded.util.ModAnimalEntityMixinInterface;

@Mixin(value = AnimalEntity.class)
public abstract class ModAnimalEntityMixin
        implements ModAnimalEntityMixinInterface {
    private static final int MAX_CATNIP_HIGH_DURATION = 400;

    private int catsExpandedCatnipHighDuration = 0;

    public int getCatsExpandedCatnipHighDuration() {
        return catsExpandedCatnipHighDuration;
    }

    public void increaseCatsExpandedCatnipHighDuration(int time) {
        if (catsExpandedCatnipHighDuration + time > MAX_CATNIP_HIGH_DURATION) {
            catsExpandedCatnipHighDuration = MAX_CATNIP_HIGH_DURATION;
        } else {
            catsExpandedCatnipHighDuration += time;
        }
    }

    public void decreaseCatsExpandedCatnipHighDuration(int time) {
        if (catsExpandedCatnipHighDuration - time <= 0) {
            catsExpandedCatnipHighDuration = 0;
        } else {
            catsExpandedCatnipHighDuration -= time;
        }
    }

}

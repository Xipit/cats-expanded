package xipit.cats.expanded.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.passive.AnimalEntity;
import xipit.cats.expanded.util.ModAnimalEntityMixinInterface;

@Mixin(value = AnimalEntity.class)
public class ModAnimalEntityMixin
implements ModAnimalEntityMixinInterface{
    private static final int MAX_CATNIP_HIGH_DURATION = 400;
    
    private Boolean catsExpandedIsCatnipDrugged = false;

    private int catsExpandedCatnipHighDuration = 0;

    public int getCatsExpandedCatnipHighDuration(){
        return catsExpandedCatnipHighDuration;
    }

    public void increaseCatsExpandedCatnipHighDuration(int time){
        if(catsExpandedCatnipHighDuration + time > MAX_CATNIP_HIGH_DURATION){
            catsExpandedCatnipHighDuration = MAX_CATNIP_HIGH_DURATION;
        }else{
            catsExpandedCatnipHighDuration += time;
        }
    }

    public void decreaseCatsExpandedCatnipHighDuration(int time){
        if(catsExpandedCatnipHighDuration - time <= 0){
            catsExpandedCatnipHighDuration = 0;
        }else{
            catsExpandedCatnipHighDuration -= time;
        }
    }

    public Boolean getCatsExpandedIsCatnipDrugged(){
        return catsExpandedIsCatnipDrugged;
    }

    public void setCatsExpandedIsCatnipDrugged(Boolean isCatnipDrugged){
        catsExpandedIsCatnipDrugged = isCatnipDrugged;
    }
    
}

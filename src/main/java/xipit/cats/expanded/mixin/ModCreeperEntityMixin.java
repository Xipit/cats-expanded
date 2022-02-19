package xipit.cats.expanded.mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.goal.ModCatnipEscapeGoal;
import xipit.cats.expanded.item.ModItems;
import xipit.cats.expanded.util.ModCreeperEntityMixinInterface;

// helpful link:    https://github.com/SpongePowered/Mixin/wiki/Introduction-to-Mixins---Understanding-Mixin-Architecture
// also:            https://fabricmc.net/wiki/tutorial:mixin_accessors
@Mixin(value = CreeperEntity.class)
public class ModCreeperEntityMixin
extends HostileEntity 
implements ModCreeperEntityMixinInterface{

    protected ModCreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    // set true here, but false in stop() in ModCatnipEscapeGoal.java
    private boolean catsExpandedIsCatnipEscaping = false;

    public boolean getCatsExpandedIsCatnipEscaping(){
        return catsExpandedIsCatnipEscaping;
    }

    public void setCatsExpandedIsCatnipEscaping(boolean value){
        catsExpandedIsCatnipEscaping = value;
    }

    // Return -> before every return statement, ordinal = 0 -> only at the first return statement
    @Inject(method = "initGoals", at = @At(value= "RETURN", ordinal = 0))
    protected void InjectInitGoals(CallbackInfo ci){
        
        // flee from players, after ingesting catnip
        this.goalSelector.add(1, new ModCatnipEscapeGoal(this, 6.0f, 1.0, 1.2));
        
    }


    @Inject(method = "interactMob", at = @At(value = "TAIL"), cancellable = true)
    protected void InjectInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        ItemStack itemStack = player.getStackInHand(hand);

        if(itemStack.getItem() == ModItems.CATNIP){
            ((ModCreeperEntityMixinInterface)this).setCatsExpandedIsCatnipEscaping(true);;

            CatsExpandedMod.LOGGER.info("YOOOOOO CREEPER ATE CATNIP");

            //player.incrementStat(ModStats.AMOUNT_OF_CATNIP_FED_TO_CATS);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
        
        
    }
}

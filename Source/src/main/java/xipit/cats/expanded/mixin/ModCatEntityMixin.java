package xipit.cats.expanded.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xipit.cats.expanded.goal.ModDruggedBehaviourGoal;
import xipit.cats.expanded.goal.ModEatCatnipGoal;
import xipit.cats.expanded.item.ModItems;
import xipit.cats.expanded.stats.ModStats;
import xipit.cats.expanded.util.ModAnimalEntityMixinInterface;

// helpful link:    https://github.com/SpongePowered/Mixin/wiki/Introduction-to-Mixins---Understanding-Mixin-Architecture
// also:            https://fabricmc.net/wiki/tutorial:mixin_accessors
@Mixin(value = CatEntity.class)
public abstract class ModCatEntityMixin 
    extends TameableEntity{

    protected ModCatEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super((EntityType<? extends TameableEntity>)entityType, world);
    }

    // Return -> before every return statement, ordinal = 0 -> only at the first return statement
    @Inject(method = "initGoals", at = @At(value= "RETURN", ordinal = 0))
    protected void InjectInitGoals(CallbackInfo ci){

        // catnip can now be used to tempt cats and is a higher priority than fish
        TemptGoal catnipTemptGoal = new TemptGoal(this,0.8, Ingredient.ofItems(ModItems.CATNIP), true);
        this.goalSelector.add(3, catnipTemptGoal);

        // the desire to consume catnip
        this.goalSelector.add(7, new ModEatCatnipGoal(this, (double)1.2f, 12, 1));

        // consuming catnip now makes cats/ocelots go bonkers/zoom
        this.goalSelector.add(2, new ModDruggedBehaviourGoal(this, 2));
    }

    // When holding catnip and right clicking on a tamed cat which you own, feed them catnip
    @Inject(method = "interactMob", at = @At(value = "HEAD"), cancellable = true)
    protected void InjectHEADInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        ItemStack itemStack = player.getStackInHand(hand);

        if(this.isTamed()){
            if(this.isOwner(player)){
                if(itemStack.getItem() == ModItems.CATNIP){
                    this.eat(player, hand, itemStack);
                    ((ModAnimalEntityMixinInterface)this).increaseCatsExpandedCatnipHighDuration(150);
                    this.playSound(SoundEvents.ENTITY_CAT_EAT, 1.0f, 1.0f);

                    player.incrementStat(ModStats.AMOUNT_OF_CATNIP_FED_TO_CATS);
                    cir.setReturnValue(ActionResult.SUCCESS);
                }
            }
        }        
    }


    @Inject(method = "interactMob", at = @At(value = "TAIL"), cancellable = true)
    protected void InjectTAILInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        ItemStack itemStack = player.getStackInHand(hand);

        if(itemStack.getItem() == ModItems.CATNIP){
            this.eat(player, hand, itemStack);
            ((ModAnimalEntityMixinInterface)this).increaseCatsExpandedCatnipHighDuration(150);
            this.playSound(SoundEvents.ENTITY_CAT_EAT, 1.0f, 1.0f);

            player.incrementStat(ModStats.AMOUNT_OF_CATNIP_FED_TO_CATS);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
        
        
    }
    

}

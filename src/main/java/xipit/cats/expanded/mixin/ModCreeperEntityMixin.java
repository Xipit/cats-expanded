package xipit.cats.expanded.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.predicate.block.BlockStatePredicate;
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
import xipit.cats.expanded.ModCreeperNavigation;
import xipit.cats.expanded.block.ModBlocks;
import xipit.cats.expanded.goal.ModCatnipEscapeFromPlayerGoal;
import xipit.cats.expanded.item.ModItems;
import xipit.cats.expanded.stats.ModStats;
import xipit.cats.expanded.util.ModCreeperEntityMixinInterface;

import java.util.function.Predicate;

// helpful link:    https://github.com/SpongePowered/Mixin/wiki/Introduction-to-Mixins---Understanding-Mixin-Architecture
// also:            https://fabricmc.net/wiki/tutorial:mixin_accessors
@Mixin(value = CreeperEntity.class)
public abstract class ModCreeperEntityMixin
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

    private static final Predicate<BlockState> CATNIP_BUSH_PREDICATE = BlockStatePredicate.forBlock(ModBlocks.CATNIP_BUSH);

    // Return -> before every return statement, ordinal = 0 -> only at the first return statement
    @Inject(method = "initGoals", at = @At(value= "RETURN", ordinal = 0))
    protected void InjectInitGoals(CallbackInfo ci){
        
        // flee from players after ingesting catnip
        this.goalSelector.add(1, new ModCatnipEscapeFromPlayerGoal(this, 6.0f, 1.0, 1.2));
        
    }


    @Inject(method = "interactMob", at = @At(value = "TAIL"), cancellable = true)
    protected void InjectInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        ItemStack itemStack = player.getStackInHand(hand);

        if(itemStack.getItem() == ModItems.CATNIP){
            if(((ModCreeperEntityMixinInterface)this).getCatsExpandedIsCatnipEscaping()){
                cir.setReturnValue(ActionResult.PASS);
            }

            ((ModCreeperEntityMixinInterface)this).setCatsExpandedIsCatnipEscaping(true);;

            // actually use catnip, normally this.eat() is used
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            player.incrementStat(ModStats.AMOUNT_OF_CATNIP_SCARED_CREEPERS);
            cir.setReturnValue(ActionResult.success(this.world.isClient));
        }
        
        
    }

    // pathfind around catnipBushBlock
    @Override
    protected EntityNavigation createNavigation(World world) {
        return new ModCreeperNavigation(this, world);
    }
}

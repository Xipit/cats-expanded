package xipit.cats.expanded.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import xipit.cats.expanded.item.ModItems;

// helpful link: https://github.com/SpongePowered/Mixin/wiki/Introduction-to-Mixins---Understanding-Mixin-Architecture
@Mixin(CatEntity.class)
public abstract class ModCatEntityMixin 
    extends TameableEntity{

    @Shadow
    private net.minecraft.entity.ai.goal.TemptGoal temptGoal;


    protected ModCatEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super((EntityType<? extends TameableEntity>)entityType, world);
        
    }

    // Return -> before every return statement, ordinal = 0 -> only at the first return statement
    @Inject(method = "initGoals", at = @At(value= "RETURN", ordinal = 0))
    protected void InjectInitGoals(CallbackInfo cbi){

        // catnip can now be used to tempt cats
        TemptGoal oldTemptGoal = new TemptGoal(this,0.6, Ingredient.ofItems(Items.COD, Items.SALMON), true);
        TemptGoal newTemptGoal = new TemptGoal(this,0.8, Ingredient.ofItems(ModItems.CATNIP), true);
        this.goalSelector.remove(oldTemptGoal);
        this.goalSelector.add(3, newTemptGoal);
        this.goalSelector.add(4, oldTemptGoal);
    }
}

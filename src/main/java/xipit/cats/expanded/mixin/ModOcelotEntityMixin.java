package xipit.cats.expanded.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import xipit.cats.expanded.goal.ModDruggedBehaviourGoal;
import xipit.cats.expanded.goal.ModEatCatnipGoal;
import xipit.cats.expanded.item.ModItems;

// helpful link: https://github.com/SpongePowered/Mixin/wiki/Introduction-to-Mixins---Understanding-Mixin-Architecture
@Mixin(value = OcelotEntity.class)
public abstract class ModOcelotEntityMixin 
    extends AnimalEntity{


    protected ModOcelotEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super((EntityType<? extends AnimalEntity>)entityType, world);
        
    }

    // Return -> before every return statement, ordinal = 0 -> only at the first return statement
    @Inject(method = "initGoals", at = @At(value= "RETURN", ordinal = 0))
    protected void InjectInitGoals(CallbackInfo ci){

        // catnip can now be used to tempt cats and is a higher priority than fish
        TemptGoal newTemptGoal = new TemptGoal(this,0.8, Ingredient.ofItems(ModItems.CATNIP), true);
        this.goalSelector.add(3, newTemptGoal);

        // the desire to consume catnip
        this.goalSelector.add(7, new ModEatCatnipGoal(this, (double)1.2f, 12, 1));

        // consuming catnip now makes cats/ocelots go bonkers/zoom
        this.goalSelector.add(1, new ModDruggedBehaviourGoal(this, 2));
    }



}
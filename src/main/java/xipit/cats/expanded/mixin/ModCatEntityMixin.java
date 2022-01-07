package xipit.cats.expanded.mixin;

import java.util.Random;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import xipit.cats.expanded.block.ModBlocks;
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
    protected void InjectInitGoals(CallbackInfo ci){

        // catnip can now be used to tempt cats
        TemptGoal oldTemptGoal = new TemptGoal(this,0.6, Ingredient.ofItems(Items.COD, Items.SALMON), true);
        TemptGoal newTemptGoal = new TemptGoal(this,0.8, Ingredient.ofItems(ModItems.CATNIP), true);
        this.goalSelector.remove(oldTemptGoal);
        this.goalSelector.add(3, newTemptGoal);
        this.goalSelector.add(4, oldTemptGoal);

        this.goalSelector.add(7, new ModEatCatnipGoal(this, (double)1.2f, 12, 1));
    }



    static class ModEatCatnipGoal
    extends MoveToTargetPosGoal {

    protected final Random random = new Random();
    private static final int EATING_TIME = 40;
    protected int timer;



    public ModEatCatnipGoal(PathAwareEntity mob, double speed, int range, int maxYDifference) {
        super(mob, speed, range, maxYDifference);
    }



    @Override
    public double getDesiredSquaredDistanceToTarget() {
        return 2.0;
    }

    @Override
    public boolean shouldResetPath() {
        return this.tryingTime % 100 == 0;
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isOf(ModBlocks.CATNIP_BUSH) && blockState.get(ModBlocks.CATNIP_BUSH.AGE) >= 2;
    }

    @Override
    public void tick() {
        if (this.hasReached()) {
            if (this.timer >= 40) {
                this.eatCatnip();
            } else {
                ++this.timer;
            }
        } else if (!this.hasReached() && random.nextFloat() < 0.05f) {
            mob.playSound(SoundEvents.ENTITY_FOX_SNIFF, 1.0f, 1.0f);
        }
        super.tick();
    }

    protected void eatCatnip() {
        if (!mob.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            return;
        }
        BlockState blockState = mob.world.getBlockState(this.targetPos);
        if (blockState.isOf(ModBlocks.CATNIP_BUSH)) {
            this.devourCatnip(blockState);
        }
    }


    private void devourCatnip(BlockState state) {
        //TODO: Add new StatusEffect for a catnip high + maybe new animation
        mob.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200));

        mob.playSound(SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, 1.0f, 1.0f);
        mob.world.setBlockState(this.targetPos, (BlockState)state.with(ModBlocks.CATNIP_BUSH.AGE, 1), Block.NOTIFY_LISTENERS);
    }

    @Override
    public boolean canStart() {
        return !mob.isSleeping() && super.canStart();
    }

    @Override
    public void start() {
        this.timer = 0;
        //mob.setSitting(false);
        super.start();
    }
}
}

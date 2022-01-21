package xipit.cats.expanded.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.gen.CatSpawner;
import net.minecraft.world.gen.Spawner;
import xipit.cats.expanded.config.ModConfig;
import xipit.cats.expanded.config.ReadModConfig;

@Mixin(CatSpawner.class)
public abstract class ModCatSpawnerMixin 
implements Spawner{
    protected final int MAX_CATS_IN_AREA = 5;

    // needs to be monitored to make sure it stays the same
    @Shadow
    private int spawn(BlockPos pos, ServerWorld world) {
        CatEntity catEntity = EntityType.CAT.create(world);
        if (catEntity == null) {
            return 0;
        }
        catEntity.initialize(world, world.getLocalDifficulty(pos), SpawnReason.NATURAL, null, null);
        catEntity.refreshPositionAndAngles(pos, 0.0f, 0.0f);
        world.spawnEntityAndPassengers(catEntity);
        return 1;
    }
    

    // Tail -> last return statement
    @Inject(method = "spawn", at = @At("TAIL"), cancellable = true)
    protected void InjectSpawn(ServerWorld world, boolean arg1, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir){
        // check configs
        if(ReadModConfig.spawnConfigs.get(ModConfig.extraCatSpawnID).getAsBoolean()){
            cir.setReturnValue(0);
        }

        // copied, because blockpos is needed. locals could be used, but couldnt get it to work
        ServerPlayerEntity playerEntity = world.getRandomAlivePlayer();
        Random random = world.random;
        int i = (8 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
        int j = (8 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
        BlockPos blockPos = playerEntity.getBlockPos().add(i, 0, j);


        if (SpawnHelper.canSpawn(SpawnRestriction.Location.ON_GROUND, world, blockPos, EntityType.CAT)) {
            cir.setReturnValue(this.spawnInWild(world, blockPos));
        }
    }


    private int spawnInWild(ServerWorld world, BlockPos pos){
        int i = 48;
        if ((world.getNonSpectatingEntities(CatEntity.class, new Box(pos).expand(i, 8.0, i))).size() < MAX_CATS_IN_AREA) {
            return this.spawn(pos, world);
        }
        return 0;
    }
}

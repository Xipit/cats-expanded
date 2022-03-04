package xipit.cats.expanded.world.feature.catnip;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.block.CatnipBushBlock;
import xipit.cats.expanded.block.ModBlocks;

// configure what gets placed
public class ModConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> PATCH_CATNIP_BUSH = register("patch_catnip", 
                    

   /* public static final ConfiguredFeature<?, ?> PATCH_CATNIP_BUSH = register("patch_catnip", 
    Feature.RANDOM_PATCH.configure(createRandomPatchFeatureConfig(
        Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(
            BlockStateProvider.of((BlockState)ModBlocks.CATNIP_BUSH.getDefaultState().
                with(CatnipBushBlock.AGE, 3)))), 
                    List.of(Blocks.GRASS_BLOCK))));*/

/*
    public static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature){
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(CatsExpandedMod.MOD_ID, name), configuredFeature);
    }*/

    public static <FC extends FeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> register(String name, F feature, FC config) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(CatsExpandedMod.MOD_ID, name), new ConfiguredFeature<FC, F>(feature, config));
    }

    // copied from ConfiguredFeatures.class only for the catnipbush patch
    public static <FC extends FeatureConfig, F extends Feature<FC>> RandomPatchFeatureConfig createRandomPatchFeatureConfig(F feature, FC config, List<Block> predicateBlocks, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(feature, config, createBlockPredicate(predicateBlocks)));
    }

    private static BlockPredicate createBlockPredicate(List<Block> validGround) {
        BlockPredicate blockPredicate = !validGround.isEmpty() ? BlockPredicate.bothOf(BlockPredicate.IS_AIR, BlockPredicate.matchingBlocks(validGround, new BlockPos(0, -1, 0))) : BlockPredicate.IS_AIR;
        return blockPredicate;
    }

    public static void registerConfiguredFeatures(){
        CatsExpandedMod.LOGGER.info("Registering ModConfiguredFeatures for " + CatsExpandedMod.MOD_ID);
    }
}

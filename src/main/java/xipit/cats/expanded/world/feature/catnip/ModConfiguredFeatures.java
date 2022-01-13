package xipit.cats.expanded.world.feature.catnip;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.block.CatnipBushBlock;
import xipit.cats.expanded.block.ModBlocks;

// configure what gets placed
public class ModConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> PATCH_CATNIP_BUSH = register("patch_catnip", 
    Feature.RANDOM_PATCH.configure(createRandomPatchFeatureConfig(
        Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(
            BlockStateProvider.of((BlockState)ModBlocks.CATNIP_BUSH.getDefaultState().
                with(CatnipBushBlock.AGE, 3)))), 
                    List.of(Blocks.GRASS_BLOCK))));


    public static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature){
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(CatsExpandedMod.MOD_ID, name), configuredFeature);
    }

    // copied from ConfiguredFeatures.class only for the catnipbush patch
    public static RandomPatchFeatureConfig createRandomPatchFeatureConfig(ConfiguredFeature<?, ?> feature, List<Block> validGround) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(feature, validGround, 32);
    }

    public static void registerConfiguredFeatures(){
        System.out.println("Registering ModConfiguredFeatures for " + CatsExpandedMod.MOD_ID);
    }
}

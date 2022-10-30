package xipit.cats.expanded.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import xipit.cats.expanded.block.CatnipBushBlock;
import xipit.cats.expanded.block.ModBlocks;
import xipit.cats.expanded.util.RegistryHelper;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> PATCH_CATNIP_BUSH = new ConfiguredFeature<>(
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                    Feature.SIMPLE_BLOCK,
                    new SimpleBlockFeatureConfig(BlockStateProvider.of((BlockState) ModBlocks.CATNIP_BUSH.getDefaultState().with(CatnipBushBlock.AGE, 3))),
                    List.of(Blocks.GRASS_BLOCK)
            )
    );

    public static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> registerConfiguredFeature(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, RegistryHelper.id(name), configuredFeature);
    }

    public static void register() {
        registerConfiguredFeature("patch_catnip_bush", PATCH_CATNIP_BUSH);
    }
}

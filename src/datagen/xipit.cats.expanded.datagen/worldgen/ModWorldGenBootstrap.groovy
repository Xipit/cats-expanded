package xipit.cats.expanded.datagen.worldgen

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryEntryLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.ConfiguredFeatures
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import xipit.cats.expanded.block.CatnipBushBlock
import xipit.cats.expanded.block.ModBlocks
import xipit.cats.expanded.world.gen.ModWorldGen
import xipit.cats.expanded.world.modifiers.ModSmallSquarePlacementModifier

class ModWorldGenBootstrap{
    private ModWorldGenBootstrap() {

    }

    static void configuredFeatures(Registerable<ConfiguredFeature> registry){
        def placedFeatureLookup = registry.getRegistryLookup(RegistryKeys.PLACED_FEATURE)

        registry.register(ModWorldGen.CATNIP_BUSH_PATCH_CF, createCatnipBushPatchConfiguredFeature())
    }

    private static ConfiguredFeature createCatnipBushPatchConfiguredFeature(){
        return new ConfiguredFeature<>(
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(
                        BlockStateProvider.of((BlockState) ModBlocks.CATNIP_BUSH.getDefaultState().with(CatnipBushBlock.AGE, 3))
                ),
                List.of(Blocks.GRASS_BLOCK)
            )
        )
    }


    static void placedFeatures(Registerable<PlacedFeature> registry){
        def configuredFeatureLookup = registry.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)

        registry.register(ModWorldGen.CATNIP_BUSH_PATCH_PF, createCatnipBushPatchPlacedFeature(configuredFeatureLookup))
    }

    private static createCatnipBushPatchPlacedFeature(RegistryEntryLookup<ConfiguredFeature> configuredFeatureLookup){
        return new PlacedFeature(
            configuredFeatureLookup.getOrThrow(ModWorldGen.CATNIP_BUSH_PATCH_CF),
            List.of(RarityFilterPlacementModifier.of(32),
                ModSmallSquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of()
            )
        )
    }
}
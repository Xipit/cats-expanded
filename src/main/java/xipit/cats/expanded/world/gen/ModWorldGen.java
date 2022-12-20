package xipit.cats.expanded.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.util.RegistryHelper;

public class ModWorldGen {

    private static final Identifier CATNIP_BUSH_PATCH_ID = RegistryHelper.id("patch_catnip_bush");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CATNIP_BUSH_PATCH_CF = getConfiguredFeature(CATNIP_BUSH_PATCH_ID);
    public static final RegistryKey<PlacedFeature> CATNIP_BUSH_PATCH_PF = getPlacedFeature(CATNIP_BUSH_PATCH_ID);

    public static void register() {
        CatsExpandedMod.LOGGER.info("Registering all WorldGen related stuff for " + CatsExpandedMod.MOD_ID);

        addPlacedFeatures();
    }

    public static void addPlacedFeatures() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                CATNIP_BUSH_PATCH_PF
        );
    }

    private static RegistryKey<PlacedFeature> getPlacedFeature(Identifier placedFeatureIdentifier) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, placedFeatureIdentifier);
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> getConfiguredFeature(Identifier configuredFeatureIdentifier) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, configuredFeatureIdentifier);
    }

}

package xipit.cats.expanded.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.GenerationStep;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.world.feature.ModConfiguredFeatures;
import xipit.cats.expanded.world.feature.ModPlacedFeatures;

public class ModWorldGen {
    public static void register(){
        CatsExpandedMod.LOGGER.info("Registering all WorldGen related stuff for " + CatsExpandedMod.MOD_ID);

		ModConfiguredFeatures.register();
		ModPlacedFeatures.register();

		addPlacedFeatures();
    }

    public static void addPlacedFeatures(){
        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST),
        	GenerationStep.Feature.VEGETAL_DECORATION, 
        	getPlacedFeature(getPlacedFeatureIdentifier(ModPlacedFeatures.PATCH_CATNIP_BUSH)));
    }

    private static RegistryKey<PlacedFeature> getPlacedFeature(Identifier placedFeatureIdentifier){
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, placedFeatureIdentifier);
    }

    private static Identifier getPlacedFeatureIdentifier(PlacedFeature placedFeature){
        return BuiltinRegistries.PLACED_FEATURE.getId(placedFeature);
    }
}

package xipit.cats.expanded.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import xipit.cats.expanded.world.feature.ModPlacedFeatures;

// configure where it is placed
public class ModCatnipGeneration {

    public static void generateCatnip(){
        BiomeModifications.addFeature(BiomeSelectors.categories((Biome.Category.PLAINS),(Biome.Category.FOREST)), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_CATNIP_PLACED_KEY);
    }
}

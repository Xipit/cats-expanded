<<<<<<< HEAD:remappedSrc/xipit/cats/expanded/world/feature/catnip/CatnipBushConfiguredFeature.java
package xipit.cats.expanded.world.feature.catnip;
=======
package xipit.cats.expanded.world.feature;
>>>>>>> 82065380c13d3c311ec36a253ddb6520f80bc184:src/main/java/xipit/cats/expanded/world/feature/CatnipBushConfiguredFeature.java


import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.block.CatnipBushBlock;
import xipit.cats.expanded.block.ModBlocks;

// configure what gets generated
public class CatnipBushConfiguredFeature {

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_CATNIP_BUSH = ConfiguredFeatures.register(
        "patch_catnip_bush", 
            Feature.RANDOM_PATCH, 
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, 
                    new SimpleBlockFeatureConfig(BlockStateProvider.of((BlockState)ModBlocks.CATNIP_BUSH.getDefaultState().with(CatnipBushBlock.AGE, 3))), 
                        List.of(Blocks.GRASS_BLOCK)));



    public static void registerConfiguredFeatures(){
        CatsExpandedMod.LOGGER.info("Registering ModConfiguredFeatures for " + CatsExpandedMod.MOD_ID);
    }
}

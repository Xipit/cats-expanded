package xipit.cats.expanded.world.feature.newUpdate;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import xipit.cats.expanded.util.RegistryHelper;
import xipit.cats.expanded.world.modifiers.ModSmallSquarePlacementModifier;

public class CatnipBushPlacedFeature {

    public static final RegistryKey<PlacedFeature> PATCH_CATNIP_PLACED_KEY = RegistryHelper.registerPlacedFeatureKey("patch_catnip_placed");

    public static final RegistryEntry<PlacedFeature> PATCH_BERRY_RARE = PlacedFeatures.register(
        "patch_berry_rare", 
            CatnipBushConfiguredFeature.PATCH_CATNIP_BUSH, 
                RarityFilterPlacementModifier.of(32), 
                    ModSmallSquarePlacementModifier.of(), 
                        PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, 
                            BiomePlacementModifier.of());


}

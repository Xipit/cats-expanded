<<<<<<<HEAD:remappedSrc/xipit/cats/expanded/world/feature/catnip/CatnipBushPlacedFeature.java
        package xipit.cats.expanded.world.feature.catnip;
        =======
        package xipit.cats.expanded.world.feature;
        >>>>>>>82065380c13d3c311ec36a253ddb6520f80bc184:src/main/java/xipit/cats/expanded/world/feature/CatnipBushPlacedFeature.java

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import xipit.cats.expanded.util.RegistryHelper;
import xipit.cats.expanded.world.modifiers.ModSmallSquarePlacementModifier;

// configure where it gets placed annd how
public class CatnipBushPlacedFeature {

    public static final RegistryKey<PlacedFeature> PATCH_CATNIP_PLACED_KEY = RegistryHelper.registerPlacedFeatureKey("patch_catnip_placed");

    public static final RegistryEntry<PlacedFeature> PATCH_CATNIP_PLACED = PlacedFeatures.register(
            "patch_catnip_placed",
            CatnipBushConfiguredFeature.PATCH_CATNIP_BUSH,
            RarityFilterPlacementModifier.of(32),
            ModSmallSquarePlacementModifier.of(),
            PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
            BiomePlacementModifier.of());


}

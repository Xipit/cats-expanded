package xipit.cats.expanded.world.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.world.feature.catnip.ModConfiguredFeatures;
import xipit.cats.expanded.world.modifiers.ModSmallSquarePlacementModifier;

// configure how it gets placed
public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> PATCH_CATNIP_PLACED_KEY = registerKey("patch_catnip_placed");


    public static final PlacedFeature PATCH_CATNIP_PLACED = registerPlacedFeature("patch_catnip_placed", 
        ModConfiguredFeatures.PATCH_CATNIP_BUSH.withPlacement(RarityFilterPlacementModifier.of(32), 
            ModSmallSquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));



    private static PlacedFeature registerPlacedFeature(String name, PlacedFeature placedFeature){
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(CatsExpandedMod.MOD_ID, name), placedFeature);
    }

    private static RegistryKey<PlacedFeature> registerKey(String name){
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(CatsExpandedMod.MOD_ID, name));
    }
}

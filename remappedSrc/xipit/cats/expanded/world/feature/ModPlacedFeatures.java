package xipit.cats.expanded.world.feature;

import java.util.List;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import xipit.cats.expanded.util.RegistryHelper;
import xipit.cats.expanded.world.modifiers.ModSmallSquarePlacementModifier;


public class ModPlacedFeatures {
    public static final PlacedFeature PATCH_CATNIP_BUSH = new PlacedFeature(RegistryEntry.of(ModConfiguredFeatures.PATCH_CATNIP_BUSH), List.of(RarityFilterPlacementModifier.of(32), 
    ModSmallSquarePlacementModifier.of(), 
        PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, 
            BiomePlacementModifier.of()));

    public static void register(){
        registerPlacedFeature("patch_catnip_bush", PATCH_CATNIP_BUSH);
    }

    public static PlacedFeature registerPlacedFeature(String name, PlacedFeature placedFeature){
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, RegistryHelper.id(name), placedFeature);
    }
}

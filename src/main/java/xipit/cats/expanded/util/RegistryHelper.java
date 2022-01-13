package xipit.cats.expanded.util;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.PlacedFeature;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.mixin.AccessorEntityModelLayers;

/*
 *  helper class containing methods for registering
 *  inspired by (and partly sourced): @Noaaan from https://github.com/Noaaan/MythicMetals
 */
public class RegistryHelper {
    public static Identifier id(String name){
        return new Identifier(CatsExpandedMod.MOD_ID, name);
    }


    public static Item registerItem(String name, Item item){
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registry.ITEM, id(name), item);
    }


    public static PlacedFeature registerPlacedFeature(String name, PlacedFeature placedFeature){
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, id(name), placedFeature);
    }

    public static RegistryKey<PlacedFeature> registerPlacedFeatureKey(String name){
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, id(name));
    }

    // register custom mod block 
    public static Block registerBlock(String name, Block block){
        return Registry.register(Registry.BLOCK, id(name), block);
    }
    // register custom mod block with item
    public static Block registerBlockWithItem(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, id(name), block);
    }
    // register accompanied item for one block
    public static Item registerBlockItem(String name, Block block){
        return Registry.register(Registry.ITEM, id(name), 
            new BlockItem(block, new FabricItemSettings().group(CatsExpandedMod.CATEAR_GROUP)));
    }

    public static EntityModelLayer model(String name) {
        return model(name, "main");
    }

    public static EntityModelLayer model(String name, String layer) {
        var result = new EntityModelLayer(id(name), layer);
        AccessorEntityModelLayers.getAllModels().add(result);
        return result;
    }
}

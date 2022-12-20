package xipit.cats.expanded.util;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.item.ModItems;
import xipit.cats.expanded.mixin.AccessorEntityModelLayers;

/*
 *  helper class containing methods for registering
 *  inspired by (and partly sourced): @Noaaan from https://github.com/Noaaan/MythicMetals
 */
public class RegistryHelper {
    public static Identifier id(String name) {
        return new Identifier(CatsExpandedMod.MOD_ID, name);
    }

    public static Item registerItem(String name, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
        ItemGroupEvents.modifyEntriesEvent(ModItems.CATEAR_GROUP).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, id(name), item);
    }

    // register custom mod block
    public static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, id(name), block);
    }


    // register custom mod block with item
    public static Block registerBlockWithItem(String name, Block block) {
        registerBlockItem(name, block);
        return registerBlock(name, block);
    }

    // register accompanied item for one block
    public static Item registerBlockItem(String name, Block block) {
        return registerItem(name, new BlockItem(block, new Item.Settings()));
    }

    public static EntityModelLayer model(String name) {
        return model(name, "main");
    }

    public static EntityModelLayer model(String name, String layer) {
        var result = new EntityModelLayer(id(name), layer);
        AccessorEntityModelLayers.getAllModels().add(result);
        return result;
    }

    public static Stat<Identifier> registerStatistic(Identifier id) {
        Registry.register(Registries.CUSTOM_STAT, id.getPath(), id);
        return Stats.CUSTOM.getOrCreateStat(id, StatFormatter.DEFAULT);
    }

    public static Stat<Identifier> registerStatistic(Identifier id, StatFormatter formatter) {
        Registry.register(Registries.CUSTOM_STAT, id.getPath(), id);
        return Stats.CUSTOM.getOrCreateStat(id, formatter);
    }

    // copied from Blocks.class, since it is private there, but used for pumpkins
    public static Boolean always(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return true;
    }
}

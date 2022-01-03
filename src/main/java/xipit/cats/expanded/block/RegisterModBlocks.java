package xipit.cats.expanded.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xipit.cats.expanded.CatsExpanded;

public class RegisterModBlocks {


public static final CatnipBushBlock CATNIP_BUSH = new CatnipBushBlock(AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH));



    // register custom mod block 
    private static Block registerBlock(String name, Block block){
        return Registry.register(Registry.BLOCK, new Identifier(CatsExpanded.MOD_ID, name), block);
    }

    // register custom mod block with item
    private static Block registerBlockWithItem(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(CatsExpanded.MOD_ID, name), block);
    }
    
    // register accompanied item for one block
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registry.ITEM, new Identifier(CatsExpanded.MOD_ID, name), 
            new BlockItem(block, new FabricItemSettings().group(CatsExpanded.CATEAR_GROUP)));
    }

    // function called on mod initalization
    public static void register(){
        System.out.println("Registering ModBlocks for " + CatsExpanded.MOD_ID);

        registerBlock("catnip_bush", CATNIP_BUSH);
    }
}

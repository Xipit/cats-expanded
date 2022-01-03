package xipit.cats.expanded.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xipit.cats.expanded.CatsExpanded;
import xipit.cats.expanded.block.RegisterModBlocks;

public class RegisterModItems {
    public static final ArmorMaterial CATEAR_ARMOR_MATERIAL = new CatearArmorMaterial();

    public static final CatearItem CATEARS = new CatearItem(CATEAR_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(CatsExpanded.CATEAR_GROUP));
    public static final AliasedBlockItem CATNIP = new AliasedBlockItem(RegisterModBlocks.CATNIP_BUSH, new Item.Settings().group(CatsExpanded.CATEAR_GROUP));

    private static Item registerItem(String name, Item item){
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registry.ITEM, new Identifier(CatsExpanded.MOD_ID, name), item);
    }
    public static void register(){
        System.out.println("Registering ModItems for " + CatsExpanded.MOD_ID);

        registerItem("catears", CATEARS);
        registerItem("catnip", CATNIP);
    }
}

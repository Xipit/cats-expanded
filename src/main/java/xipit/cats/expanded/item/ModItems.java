package xipit.cats.expanded.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.block.ModBlocks;
import xipit.cats.expanded.item.armor.CatearArmor;
import xipit.cats.expanded.item.armor.CatearArmorMaterial;
import xipit.cats.expanded.util.RegistryHelper;

public class ModItems {
    public static final ArmorMaterial CATEAR_ARMOR_MATERIAL = new CatearArmorMaterial();


    public static final CatearArmor CATEARS = new CatearArmor(CATEAR_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(CatsExpandedMod.CATEAR_GROUP));
    public static final AliasedBlockItem CATNIP = new CatnipItem(ModBlocks.CATNIP_BUSH, new Item.Settings().group(CatsExpandedMod.CATEAR_GROUP));

   
    public static void register(){
        CatsExpandedMod.LOGGER.info("Registering ModItems");

        RegistryHelper.registerItem("catears", CATEARS);
        RegistryHelper.registerItem("catnip", CATNIP);
    }
}

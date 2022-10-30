package xipit.cats.expanded.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.block.ModBlocks;
import xipit.cats.expanded.item.armor.CatearArmor;
import xipit.cats.expanded.util.RegistryHelper;

public class ModItems {
    public static final Settings CATEAR_GROUP = new Item.Settings().group(CatsExpandedMod.CATEAR_GROUP);

    // Translationkey for CatearArmor is hardcoded --> wont change with different ids
    public static final CatearArmor CATEARS = new CatearArmor(EquipmentSlot.HEAD, CATEAR_GROUP);
    public static final CatearArmor CATEARS_CYAN = new CatearArmor("cyan", EquipmentSlot.HEAD, CATEAR_GROUP);
    public static final CatearArmor CATEARS_MAGENTA = new CatearArmor("magenta", EquipmentSlot.HEAD, CATEAR_GROUP);
    public static final CatearArmor CATEARS_PURPLE = new CatearArmor("purple", EquipmentSlot.HEAD, CATEAR_GROUP);
    public static final CatearArmor CATEARS_RED = new CatearArmor("red", EquipmentSlot.HEAD, CATEAR_GROUP);
    public static final CatearArmor CATEARS_RAINBOW = new CatearArmor("rainbow", EquipmentSlot.HEAD, CATEAR_GROUP);

    public static final AliasedBlockItem CATNIP = new CatnipItem(ModBlocks.CATNIP_BUSH, new Item.Settings().group(CatsExpandedMod.CATEAR_GROUP));


    public static void register() {
        CatsExpandedMod.LOGGER.info("Registering ModItems");

        RegistryHelper.registerItem("catears", CATEARS);
        RegistryHelper.registerItem("catears_cyan", CATEARS_CYAN);
        RegistryHelper.registerItem("catears_magenta", CATEARS_MAGENTA);
        RegistryHelper.registerItem("catears_purple", CATEARS_PURPLE);
        RegistryHelper.registerItem("catears_red", CATEARS_RED);
        RegistryHelper.registerItem("catears_rainbow", CATEARS_RAINBOW);

        RegistryHelper.registerItem("catnip", CATNIP);
    }
}

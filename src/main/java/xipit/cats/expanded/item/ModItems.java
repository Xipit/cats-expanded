package xipit.cats.expanded.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.block.ModBlocks;
import xipit.cats.expanded.item.armor.CatearArmor;
import xipit.cats.expanded.util.RegistryHelper;

public class ModItems {
    public static final ItemGroup CATEAR_GROUP = FabricItemGroup.builder(new Identifier(CatsExpandedMod.MOD_ID, "general"))
            .icon(() -> new ItemStack(Items.CYAN_TERRACOTTA))
            .build();

    // Translationkey for CatearArmor is hardcoded --> wont change with different ids
    public static final CatearArmor CATEARS = new CatearArmor(EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_WHITE = new CatearArmor("white", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_BLACK = new CatearArmor("black", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_GRAY = new CatearArmor("gray", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_LIGHT_GRAY = new CatearArmor("light_gray", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_RED = new CatearArmor("red", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_ORANGE = new CatearArmor("orange", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_MAGENTA = new CatearArmor("magenta", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_PURPLE = new CatearArmor("purple", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_PINK = new CatearArmor("pink", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_YELLOW = new CatearArmor("yellow", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_BLUE = new CatearArmor("blue", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_LIGHT_BLUE = new CatearArmor("light_blue", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_CYAN = new CatearArmor("cyan", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_GREEN = new CatearArmor("green", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_LIME = new CatearArmor("lime", EquipmentSlot.HEAD);
    public static final CatearArmor CATEARS_RAINBOW = new CatearArmor("rainbow", EquipmentSlot.HEAD);


    public static final AliasedBlockItem CATNIP = new CatnipItem(ModBlocks.CATNIP_BUSH, new Item.Settings());

    public static final AliasedBlockItem CARVED_CAT_PUMPKIN = new AliasedBlockItem(ModBlocks.CARVED_CAT_PUMPKIN, new Item.Settings());
    public static final AliasedBlockItem CAT_O_LANTERN = new AliasedBlockItem(ModBlocks.CAT_O_LANTERN, new Item.Settings());

    public static void register() {
        CatsExpandedMod.LOGGER.info("Registering ModItems");

        RegistryHelper.registerItem("catears", CATEARS);
        RegistryHelper.registerItem("catears_white", CATEARS_WHITE);
        RegistryHelper.registerItem("catears_black", CATEARS_BLACK);
        RegistryHelper.registerItem("catears_gray", CATEARS_GRAY);
        RegistryHelper.registerItem("catears_light_gray", CATEARS_LIGHT_GRAY);
        RegistryHelper.registerItem("catears_red", CATEARS_RED);
        RegistryHelper.registerItem("catears_orange", CATEARS_ORANGE);
        RegistryHelper.registerItem("catears_magenta", CATEARS_MAGENTA);
        RegistryHelper.registerItem("catears_purple", CATEARS_PURPLE);
        RegistryHelper.registerItem("catears_pink", CATEARS_PINK);
        RegistryHelper.registerItem("catears_yellow", CATEARS_YELLOW);
        RegistryHelper.registerItem("catears_blue", CATEARS_BLUE);
        RegistryHelper.registerItem("catears_light_blue", CATEARS_LIGHT_BLUE);
        RegistryHelper.registerItem("catears_cyan", CATEARS_CYAN);
        RegistryHelper.registerItem("catears_green", CATEARS_GREEN);
        RegistryHelper.registerItem("catears_lime", CATEARS_LIME);
        RegistryHelper.registerItem("catears_rainbow", CATEARS_RAINBOW);

        RegistryHelper.registerItem("catnip", CATNIP);

        RegistryHelper.registerItem("carved_cat_pumpkin", CARVED_CAT_PUMPKIN);
        RegistryHelper.registerItem("cat_o_lantern", CAT_O_LANTERN);

    }
}

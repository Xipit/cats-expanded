package xipit.cats.expanded.block;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.util.RegistryHelper;

public class ModBlocks {

    public static final CatnipBushBlock CATNIP_BUSH
            = new CatnipBushBlock(AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).nonOpaque());

    public static final Block CARVED_CAT_PUMPKIN
            = new CarvedPumpkinBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.ORANGE).strength(1.0f).sounds(BlockSoundGroup.WOOD).allowsSpawning(RegistryHelper::always));
    public static final Block CAT_O_LANTERN
            = new CarvedPumpkinBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.ORANGE).strength(1.0f).sounds(BlockSoundGroup.WOOD).luminance(state -> 15).allowsSpawning(RegistryHelper::always));

    // function called on mod initalization
    public static void register() {
        CatsExpandedMod.LOGGER.info("Registering ModBlocks");

        RegistryHelper.registerBlock("catnip_bush", CATNIP_BUSH);

        RegistryHelper.registerBlock("carved_cat_pumpkin", CARVED_CAT_PUMPKIN);
        RegistryHelper.registerBlock("cat_o_lantern", CAT_O_LANTERN);
    }
}

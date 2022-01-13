package xipit.cats.expanded.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.util.RegistryHelper;

public class ModBlocks {

    public static final CatnipBushBlock CATNIP_BUSH
    = new CatnipBushBlock(AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).nonOpaque());





    // function called on mod initalization
    public static void register(){
        System.out.println("Registering ModBlocks for " + CatsExpandedMod.MOD_ID);

        RegistryHelper.registerBlock("catnip_bush", CATNIP_BUSH);
    }
}

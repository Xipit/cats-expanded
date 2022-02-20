package xipit.cats.expanded.stats;

import net.minecraft.util.Identifier;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.util.RegistryHelper;

public class ModStats {
    public static final Identifier AMOUNT_OF_CATNIP_FED_TO_CATS = RegistryHelper.id("amount_of_catnip_fed_to_cats");
    public static final Identifier AMOUNT_OF_CATNIP_SCARED_CREEPERS = RegistryHelper.id("amount_of_catnip_scared_creepers");


    public static void register(){
        CatsExpandedMod.LOGGER.info("Registering ModStats");

		RegistryHelper.registerStatistic(AMOUNT_OF_CATNIP_FED_TO_CATS);
        RegistryHelper.registerStatistic(AMOUNT_OF_CATNIP_SCARED_CREEPERS);
    }
}

package xipit.cats.expanded;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xipit.cats.expanded.block.ModBlocks;
import xipit.cats.expanded.config.CatsExpandedConfig;
import xipit.cats.expanded.item.ModItems;
import xipit.cats.expanded.stats.ModStats;
import xipit.cats.expanded.world.gen.ModWorldGen;

public class CatsExpandedMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MOD_ID = "catsexpanded";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Initializing ...");

        MidnightConfig.init(MOD_ID, CatsExpandedConfig.class);

        ModItems.register();
        ModBlocks.register();
        ModStats.register();

        ModWorldGen.register();
    }
}

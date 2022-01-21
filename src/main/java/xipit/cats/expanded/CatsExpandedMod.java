package xipit.cats.expanded;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import xipit.cats.expanded.block.ModBlocks;
import xipit.cats.expanded.config.ModConfig;
import xipit.cats.expanded.config.ReadModConfig;
import xipit.cats.expanded.item.ModItems;
import xipit.cats.expanded.stats.ModStats;
import xipit.cats.expanded.util.RegistryHelper;
import xipit.cats.expanded.world.gen.ModWorldGen;

import com.oroarmor.config.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CatsExpandedMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "catsexpanded";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static final Config CONFIG = new ModConfig();

	public static final ItemGroup CATEAR_GROUP = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "general"))
	.icon(() -> new ItemStack(Items.CYAN_TERRACOTTA))
	.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Initializing ...");

		//TODO: check permission levels for players and give it to registerConfig
		RegistryHelper.registerConfig(CONFIG);
		ReadModConfig.readConfig(CONFIG);

		ModItems.register();
		ModBlocks.register();
		ModStats.register();

		ModWorldGen.generateModWorldGen();

		LOGGER.info("... Finished!");
	}
}

package xipit.simple.catears;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import xipit.simple.catears.block.RegisterModBlocks;
import xipit.simple.catears.item.RegisterModItems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleCatears implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("simplecatears");
	public static final String MOD_ID = "simplecatears";


	public static final ItemGroup CATEAR_GROUP = FabricItemGroupBuilder.create(new Identifier("simplecatears", "general"))
	.icon(() -> new ItemStack(Items.CYAN_TERRACOTTA))
	.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		System.out.println("Initializing " + MOD_ID);

		RegisterModItems.register();
		RegisterModBlocks.register();
	}
}

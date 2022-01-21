package xipit.cats.expanded.config;

import java.io.File;
import java.util.List;

import com.oroarmor.config.Config;
import com.oroarmor.config.ConfigItem;
import com.oroarmor.config.ConfigItemGroup;

import net.fabricmc.loader.api.FabricLoader;
import xipit.cats.expanded.CatsExpandedMod;
import static com.google.common.collect.ImmutableList.of;

public class ModConfig extends Config{
    // IDs for easy reference
    public static final String extraCatSpawnID = "extra_cat_spawn";

    public static final ConfigItemGroup spawningGroup = new ConfigGroupSpawning();
    public static final List<ConfigItemGroup> configs = of(spawningGroup);

    public ModConfig() {
        super(configs, new File(FabricLoader.getInstance().getConfigDir().toFile(), CatsExpandedMod.MOD_ID + ".json"), CatsExpandedMod.MOD_ID);
    }
    
    public static class ConfigGroupSpawning extends ConfigItemGroup{
        public static final ConfigItem<Boolean> extraCatSpawn = new ConfigItem<Boolean>(extraCatSpawnID, false, "Spawns extra cats");

        public ConfigGroupSpawning() {
            super(of(extraCatSpawn), "spawning");
        }
        
    }
}

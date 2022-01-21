package xipit.cats.expanded.config;

import com.google.gson.JsonObject;
import com.oroarmor.config.Config;
import com.oroarmor.config.ConfigItemGroup;

import xipit.cats.expanded.CatsExpandedMod;

public class ReadModConfig {
    
    public static JsonObject spawnConfigs;

    public static void readConfig(Config config){
        CatsExpandedMod.LOGGER.info("Reading config file");

        try{
            // .get(0) for the first ConfigItemGroup in ModConfig, which is the spawning group
            spawnConfigs = ((ConfigItemGroup)config.getConfigs().get(0).getConfigs()).toJson();
        } catch(Exception e){
            CatsExpandedMod.LOGGER.error("Error while reading config file ._.", e);
        }
    }
}

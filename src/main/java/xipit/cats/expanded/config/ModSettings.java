package xipit.cats.expanded.config;

import me.lortseam.completeconfig.api.ConfigContainer;
import me.lortseam.completeconfig.data.Config;
import xipit.cats.expanded.CatsExpandedMod;

public class ModSettings extends Config implements ConfigContainer{
    
    public ModSettings(){
        super(CatsExpandedMod.MOD_ID);
    }

    // return configs from ModConfigGroup
    @Override
    public ConfigContainer[] getTransitives(){
        return new ConfigContainer[]{new ModServerConfigGroup()};
    }
}

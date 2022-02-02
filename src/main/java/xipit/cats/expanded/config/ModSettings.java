package xipit.cats.expanded.config;

import me.lortseam.completeconfig.api.ConfigContainer;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.data.Config;
import xipit.cats.expanded.CatsExpandedMod;

public class ModSettings extends Config implements ConfigContainer{
    
    public ModSettings(){
        super(CatsExpandedMod.MOD_ID);
    }
   
    // @Getter doesnt seem to work :/
    @ConfigEntry(comment = "Enable extra non-conditional cat-spawns") // Comment in the .conf file
    private boolean extraCatSpawning = false;
    public boolean isExtraCatSpawning() {return extraCatSpawning;}

}

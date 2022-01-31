package xipit.cats.expanded.config;

import lombok.Getter;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.api.ConfigGroup;

public class ModServerConfigGroup implements ConfigGroup{
    // Settings that should run on the logical server and not be editable when playing on a dedicated one
    // not implemented right now, needs a lot of work to sync properly

    @Transitive
    public static class SpawningGroup implements ConfigGroup{

        // Comment in the .conf file
        @Getter
        @ConfigEntry(comment = "Enable extra non-conditional cat-spawns")
        public boolean extraCatSpawning = false;
    }
}

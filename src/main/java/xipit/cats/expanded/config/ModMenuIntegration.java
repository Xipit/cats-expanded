package xipit.cats.expanded.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.lortseam.completeconfig.gui.ConfigScreenBuilder;
import me.lortseam.completeconfig.gui.cloth.ClothConfigScreenBuilder;
import xipit.cats.expanded.CatsExpandedMod;

public class ModMenuIntegration implements ModMenuApi{
    ConfigScreenBuilder screenBuilder = new ClothConfigScreenBuilder();

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory(){
        return parent -> screenBuilder.build(parent, CatsExpandedMod.CONFIG);
    }
}

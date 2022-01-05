package xipit.cats.expanded;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import xipit.cats.expanded.block.ModBlocks;

public class CatsExpandedClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CATNIP_BUSH, RenderLayer.getCutout());
        
    }
    
    
}

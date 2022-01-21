package xipit.cats.expanded;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import xipit.cats.expanded.block.ModBlocks;
import xipit.cats.expanded.item.armor.CatearArmor;
import xipit.cats.expanded.util.ModelHandler;

@Environment(EnvType.CLIENT)
public class CatsExpandedClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        CatsExpandedMod.LOGGER.info("Registering client side custom rendering");

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CATNIP_BUSH, RenderLayer.getCutout());
        ModelHandler.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));
        registerArmorRenderer();
    }
    
    private void registerArmorRenderer() {
        Item[] armors = Registry.ITEM.stream()
                .filter(i -> i instanceof CatearArmor
                        && Registry.ITEM.getKey(i).get().getValue().getNamespace().equals(CatsExpandedMod.MOD_ID))
                .toArray(Item[]::new);

        ArmorRenderer renderer = (matrices, vertexConsumer, stack, entity, slot, light, original) -> {
            CatearArmor armor = (CatearArmor) stack.getItem();
            var model = armor.getArmorModel();
            var texture = armor.getArmorTexture(stack, slot);
            original.setAttributes(model);
            ArmorRenderer.renderPart(matrices, vertexConsumer, light, stack, model, texture);
        };
        ArmorRenderer.register(renderer, armors);
    }
}

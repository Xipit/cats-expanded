package xipit.cats.expanded.util;

import java.util.function.BiConsumer;

import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import xipit.cats.expanded.item.armor.CatearArmorModelData;

/*
 *  basic implementation is sourced from @Noaaan from https://github.com/Noaaan/MythicMetals
 */

public class ModelHandler {
    public static final EntityModelLayer CATEAR = RegistryHelper.model(("catear_armor"));

    public static void init(BiConsumer<EntityModelLayer, TexturedModelData> consumer){
        consumer.accept(CATEAR, TexturedModelData.of(CatearArmorModelData.getModelData(), 16, 16));
    }
}

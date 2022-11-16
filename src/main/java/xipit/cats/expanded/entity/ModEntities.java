package xipit.cats.expanded.entity;

import net.minecraft.entity.passive.CatVariant;
import net.minecraft.util.Identifier;
import xipit.cats.expanded.CatsExpandedMod;
import xipit.cats.expanded.util.RegistryHelper;

public class ModEntities {

    public static final Identifier PEANUT_BUTTER_TEXTURE_ID = RegistryHelper.id("textures/entity/cat/peanut_butter.png");
    public static final CatVariant PEANUT_BUTTER = new CatVariant(PEANUT_BUTTER_TEXTURE_ID);

    public static void register() {
        CatsExpandedMod.LOGGER.info("Registering ModEntities (including variants)");

        RegistryHelper.registerCatVariant("peanut_butter", PEANUT_BUTTER);
    }
}

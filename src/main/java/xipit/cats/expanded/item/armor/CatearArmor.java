package xipit.cats.expanded.item.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import xipit.cats.expanded.util.ModelHandler;
import xipit.cats.expanded.util.RegistryHelper;

import java.util.List;

/*
 *    base of code is sourced from @Noaaan from https://github.com/Noaaan/MythicMetals
 */

public class CatearArmor extends ArmorItem {

    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> model;
    public final EquipmentSlot slot;
    private final String dye;

    public CatearArmor(String dye, EquipmentSlot slot) {
        this(dye, slot, new Item.Settings());
    }

    public CatearArmor(EquipmentSlot slot) {
        this("default", slot, new Item.Settings());
    }

    public CatearArmor(String dye, EquipmentSlot slot, Settings settings) {
        super(new CatearArmorMaterial(), slot, settings);
        this.slot = slot;
        this.dye = dye;
    }

    @Environment(EnvType.CLIENT)
    public BipedEntityModel<LivingEntity> getArmorModel() {
        if (model == null) {
            model = provideArmorModelForSlot(slot);
        }
        return model;
    }

    @Environment(EnvType.CLIENT)
    protected BipedEntityModel<LivingEntity> provideArmorModelForSlot(EquipmentSlot slot) {
        var models = MinecraftClient.getInstance().getEntityModelLoader();
        var root = models.getModelPart(ModelHandler.CATEAR);

        return new CatearArmorBipedEntityModel(root, slot);
    }

    @NotNull
    public Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        if (slot != EquipmentSlot.HEAD) {
            return RegistryHelper.id("");
        }
        return RegistryHelper.id(String.format("textures/models/catear_model_%s.png", dye));
    }

    // REASON: Same translationkey for all dyed variants, since their name should not change
    @Override
    public String getTranslationKey() {
        return "item.catsexpanded.catears";
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (dye.equals("default")) {
            return;
        }
        tooltip.add(Text.translatable("item.catsexpanded.dyed.tooltip"));
    }

}
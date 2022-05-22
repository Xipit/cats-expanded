
package xipit.cats.expanded.item.armor;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import xipit.cats.expanded.util.ModelHandler;
import xipit.cats.expanded.util.RegistryHelper;

/*
 *    base of code is sourced from @Noaaan from https://github.com/Noaaan/MythicMetals
 */

public class CatearArmor extends ArmorItem{

    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> model;
    public final EquipmentSlot slot;
    private final String dye;

    public CatearArmor(EquipmentSlot slot, Settings settings) {
        this(new CatearArmorMaterial(), slot, settings);
    }

    public CatearArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        this("default", new CatearArmorMaterial(), slot, settings);
    }

    public CatearArmor(String dye, ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
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
        return RegistryHelper.id(String.format("textures/models/catear_model_%s.png", dye));
    }

    // REASON: Same translationkey for all dyed variants, since their name should not change
    @Override
    public String getTranslationKey() {
        return "item.catsexpanded.catears";
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        if(!dye.equals("default")){
            return;
        }
        tooltip.add(Text.translatable("item.catsexpanded.catears.tooltip"));
    }
    
}
package xipit.cats.expanded.item.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;


public class CatearArmorMaterial implements ArmorMaterial {
    static int durability_helmet = 15;
    static int protection_helmet = 1;

    static int enchantibility = 2;


    // helmet, chestplate, leggings, boots
    public static final int[] BASE_DURABILITY = new int[]{durability_helmet, 1, 1, 1};
    public static final int[] PROTECTION_VALUES = new int[]{protection_helmet, 1, 1, 1};


    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()];
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return enchantibility;
    }

    // TODO: implement custom sound
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    // TODO: add custom repair ingredients
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.LEATHER);
    }

    @Override
    public String getName() {
        // Must be all lowercase
        return "cateararmormaterial";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
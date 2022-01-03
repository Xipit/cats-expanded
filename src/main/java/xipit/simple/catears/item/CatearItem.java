
package xipit.simple.catears.item;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
// import net.minecraft.util.Formatting;
import net.minecraft.world.World;


public class CatearItem extends ArmorItem{
    public CatearItem(ArmorMaterial material, EquipmentSlot slot, Settings settings){
        super(material, slot, settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("item.simplecatears.catears.tooltip"));

        //tooltip.add(new TranslatableText("item.simplecatears.catears.tooltip").formatted((Formatting.RED)));
    }
    
}
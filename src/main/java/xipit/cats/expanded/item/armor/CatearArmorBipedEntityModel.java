package xipit.cats.expanded.item.armor;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

/*
 *  sourced from @Noaaan from https://github.com/Noaaan/MythicMetals
 */

public class CatearArmorBipedEntityModel extends BipedEntityModel<LivingEntity> {
    final EquipmentSlot slot;

    public CatearArmorBipedEntityModel(ModelPart root, EquipmentSlot slot) {
        super(root);
        this.slot = slot;
    }

    @Override
    public void render(MatrixStack ms, VertexConsumer buffer, int light, int overlay, float r, float g, float b, float a) {
        renderArmorPart(slot);
        super.render(ms, buffer, light, overlay, r, g, b, a);
    }

    private void renderArmorPart(EquipmentSlot slot) {
        setVisible(false);
        if (slot == EquipmentSlot.HEAD) {
            head.visible = true;
        }
    }


}
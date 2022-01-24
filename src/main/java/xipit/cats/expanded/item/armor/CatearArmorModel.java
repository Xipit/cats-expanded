// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.17+
// Paste this class into your mod and generate all required imports

package xipit.cats.expanded.item.armor;


import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;

public class CatearArmorModel{
	
	public static ModelData getModelData(){
		ModelData data = new ModelData();
		ModelPartData root = data.getRoot();

		root.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
		root.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
		root.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);
		root.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
		root.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
		root.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);

		var head = root.addChild(
			"head",
			ModelPartBuilder.create(),
			ModelTransform.NONE);

		head.addChild(
			"ear_left", 
			ModelPartBuilder.create()
				.uv(4,6)
				.cuboid(-3.0F, -9.0F, 0.0F, 1.0F, 2.0F, 1.0F)
				.uv(4,4)
				.cuboid(-5.0F, -7.0F, 0.0F, 2.0F, 1.0F, 1.0F)
				.uv(0,0)
				.cuboid(-6.0F, -12.0F, 0.0F, 1.0F, 5.0F, 1.0F)
				.uv(8,2)
				.cuboid(-4.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F)
				.uv(8,0)
				.cuboid(-5.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F)
				.uv(4,0)
				.cuboid(-5.0F, -10.0F, 0.25F, 1.0F, 3.0F, 1.0F)
				.uv(0,6)
				.cuboid(-4.0F, -9.0F, 0.25F, 1.0F, 2.0F, 1.0F)
			, ModelTransform.NONE
		);
		head.addChild(
			"ear_right", 
			ModelPartBuilder.create()
				.uv(4,6)
				.mirrored(true)
				.cuboid(2.0F, -9.0F, 0.0F, 1.0F, 2.0F, 1.0F)
				.uv(4,4)
				.mirrored(true)
				.cuboid(3.0F, -7.0F, 0.0F, 2.0F, 1.0F, 1.0F)
				.uv(0,0)
				.mirrored(true)
				.cuboid(5.0F, -12.0F, 0.0F, 1.0F, 5.0F, 1.0F)
				.uv(8,2)
				.mirrored(true)
				.cuboid(3.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F)
				.uv(8,0)
				.mirrored(true)
				.cuboid(4.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F)
				.uv(4,0)
				.mirrored(true)
				.cuboid(4.0F, -10.0F, 0.25F, 1.0F, 3.0F, 1.0F)
				.uv(0,6)
				.mirrored(true)
				.cuboid(3.0F, -9.0F, 0.25F, 1.0F, 2.0F, 1.0F)
		, ModelTransform.NONE
		);

		return data;
	}
}

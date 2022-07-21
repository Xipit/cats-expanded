// Made with Blockbench 4.1.1
	// Exported for Minecraft version 1.17
	// Paste this class into your mod and generate all required imports
	
	package xipit.cats.expanded;
	
	public class custom_model extends SinglePartEntityModel<Entity> {
		private final ModelPart root;
	
		private final ModelPart ear_left;
	private final ModelPart ear_right;
	
		public custom_model(ModelPart root) {
this.root = root;
	
// TODO define your model parts here - 'this.body = root.getChild("body");' etc
		}
	
		public static TexturedModelData getTexturedModelData() {
// TODO replace 'undefined' with 'root'
	
ModelData data = new ModelData();
ModelPartData root = data.getRoot();
	
ModelPartData ear_left = undefined.addChild(
				"ear_left",
				ModelPartBuilder.create()
		.uv(4, 6)
		.mirrored(false)
		.cuboid(-3.0F, -9.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 4)
		.mirrored(false)
		.cuboid(-5.0F, -7.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0)
		.mirrored(false)
		.cuboid(-6.0F, -12.0F, 0.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 2)
		.mirrored(false)
		.cuboid(-4.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 0)
		.mirrored(false)
		.cuboid(-5.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 0)
		.mirrored(false)
		.cuboid(-5.0F, -10.0F, 0.25F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 6)
		.mirrored(false)
		.cuboid(-4.0F, -9.0F, 0.25F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)),
				ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
			);

		ModelPartData ear_right = undefined.addChild(
				"ear_right",
				ModelPartBuilder.create()
		.uv(0, 6)
		.mirrored(true)
		.cuboid(3.0F, -9.0F, 0.25F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 0)
		.mirrored(true)
		.cuboid(4.0F, -10.0F, 0.25F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 0)
		.mirrored(true)
		.cuboid(4.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 2)
		.mirrored(true)
		.cuboid(3.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0)
		.mirrored(true)
		.cuboid(5.0F, -12.0F, 0.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 4)
		.mirrored(true)
		.cuboid(3.0F, -7.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 6)
		.mirrored(true)
		.cuboid(2.0F, -9.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)),
				ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
			);
	
return TexturedModelData.of(data, 16, 16);
		}
	
		@Override
		public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
//previously the render function, render code was moved to a method below
		}
	
		@Override
		public ModelPart getPart() {
return this.root;
		}
	
		
	}
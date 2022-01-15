// Made with Blockbench 4.1.1
	// Exported for Minecraft version 1.17 Mojmap
	// Paste this class into your mod and generate all required imports
	
	package xipit.cats.expanded;
	
	public class custom_model extends HierarchicalModel<Entity> {
		private final ModelPart root;
	
		private final ModelPart ear_left;
	private final ModelPart ear_right;
	
		public custom_model(ModelPart root) {
this.root = root;
	
// TODO define your model parts here - 'this.body = root.getChild("body");' etc
		}
	
		public static LayerDefinition getTexturedModelData() {
// TODO replace 'undefined' with 'root'
	
MeshDefinition data = new MeshDefinition();
PartDefinition root = data.getRoot();
	
PartDefinition ear_left = undefined.addOrReplaceChild(
				"ear_left",
				CubeListBuilder.create()
		.texOffs(4, 6)
		.mirror(false)
		.addBox(-3.0F, -9.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 4)
		.mirror(false)
		.addBox(-5.0F, -7.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0)
		.mirror(false)
		.addBox(-6.0F, -12.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 2)
		.mirror(false)
		.addBox(-4.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 0)
		.mirror(false)
		.addBox(-5.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 0)
		.mirror(false)
		.addBox(-5.0F, -10.0F, 0.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6)
		.mirror(false)
		.addBox(-4.0F, -9.0F, 0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
		PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
			);

		PartDefinition ear_right = undefined.addOrReplaceChild(
				"ear_right",
				CubeListBuilder.create()
		.texOffs(0, 6)
		.mirror(true)
		.addBox(3.0F, -9.0F, 0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 0)
		.mirror(true)
		.addBox(4.0F, -10.0F, 0.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 0)
		.mirror(true)
		.addBox(4.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 2)
		.mirror(true)
		.addBox(3.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0)
		.mirror(true)
		.addBox(5.0F, -12.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 4)
		.mirror(true)
		.addBox(3.0F, -7.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 6)
		.mirror(true)
		.addBox(2.0F, -9.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
		PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
			);
	
return LayerDefinition.create(data, 16, 16);
		}
	
		@Override
		public void setupAnim(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
//previously the render function, render code was moved to a method below
		}
	
		@Override
		public ModelPart root() {
return this.root;
		}
	
		
	}
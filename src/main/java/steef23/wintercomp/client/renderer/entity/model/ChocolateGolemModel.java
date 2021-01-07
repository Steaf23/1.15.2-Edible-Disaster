package steef23.wintercomp.client.renderer.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import steef23.wintercomp.entity.ChocolateGolemEntity;

public class ChocolateGolemModel<T extends ChocolateGolemEntity> extends EntityModel<T> 
{
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer legRight;
	private final ModelRenderer legLeft;
	private final ModelRenderer armLeft;
	private final ModelRenderer armRight;

	public ChocolateGolemModel() 
	{
		this.textureWidth = 256;
		this.textureHeight = 256;

		this.body = new ModelRenderer(this);
		this.body.setRotationPoint(24.0F, -4.0F, -20.0F);
		this.body.setTextureOffset(0, 0).addBox(-48.0F, -36.0F, 0.0F, 48.0F, 36.0F, 36.0F, 0.0F, false);
		this.body.setTextureOffset(0, 72).addBox(-43.0F, 0.0F, 12.0F, 38.0F, 12.0F, 20.0F, 0.0F, false);

		this.head = new ModelRenderer(this);
		this.head.setRotationPoint(-24.0F, -36.0F, 18.0F);
		this.body.addChild(head);
		this.head.setTextureOffset(0, 104).addBox(-9.0F, -14.0F, -6.0F, 18.0F, 14.0F, 16.0F, 0.0F, false);
		this.head.setTextureOffset(0, 0).addBox(-3.0F, -10.0F, -10.0F, 6.0F, 10.0F, 4.0F, 0.0F, false);
		this.head.setTextureOffset(132, 0).addBox(-11.0F, -6.0F, -8.0F, 22.0F, 6.0F, 14.0F, 0.0F, false);

		this.legRight = new ModelRenderer(this);
		this.legRight.setRotationPoint(-35.0F, 8.0F, 20.0F);
		this.body.addChild(legRight);
		this.legRight.setTextureOffset(128, 72).addBox(-8.0F, 4.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		this.legLeft = new ModelRenderer(this);
		this.legLeft.setRotationPoint(-13.0F, 8.0F, 20.0F);
		this.body.addChild(legLeft);
		this.legLeft.setTextureOffset(128, 72).addBox(-8.0F, 4.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		this.armLeft = new ModelRenderer(this);
		this.armLeft.setRotationPoint(0.0F, -26.0F, 20.0F);
		this.body.addChild(armLeft);
		this.armLeft.setTextureOffset(100, 100).addBox(0.0F, -10.0F, -8.0F, 6.0F, 52.0F, 16.0F, 0.0F, false);

		this.armRight = new ModelRenderer(this);
		this.armRight.setRotationPoint(0.0F, -26.0F, 20.0F);
		this.body.addChild(armRight);
		this.armRight.setTextureOffset(100, 100).addBox(-54.0F, -10.0F, -8.0F, 6.0F, 52.0F, 16.0F, 0.0F, false);
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) 
	{
	      this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
	      this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
	      this.legLeft.rotateAngleX = -1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
	      this.legRight.rotateAngleX = 1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
	      this.legLeft.rotateAngleY = 0.0F;
	      this.legRight.rotateAngleY = 0.0F;
	}
	
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		this.body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
    private float triangleWave(float p_78172_1_, float p_78172_2_) 
    {
    	return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
    }
}
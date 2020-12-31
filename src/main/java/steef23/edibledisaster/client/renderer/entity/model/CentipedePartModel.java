package steef23.edibledisaster.client.renderer.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.Vector3d;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import steef23.edibledisaster.entity.CentipedePartEntity;

public class CentipedePartModel<T extends CentipedePartEntity> extends EntityModel<CentipedePartEntity> 
{
	private final ModelRenderer centipedePartBase;
	
	@SuppressWarnings("unused")
	private CentipedePartEntity partInstance;

	public CentipedePartModel(String partType)
	{
		textureWidth = 64;
		textureHeight = 64;
		
		if (partType == "head")
		{
			this.centipedePartBase = new ModelRenderer(this, 0, 0);
			this.centipedePartBase.setRotationPoint(0.0F, 24.0F, 0.0F);
			this.centipedePartBase.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F, false);
		}
		else if (partType == "body")
		{
			this.centipedePartBase = new ModelRenderer(this, 0, 32);
			this.centipedePartBase.setRotationPoint(0.0F, 24.0F, 0.0F);
			this.centipedePartBase.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F, false);
		}
		else
		{
			this.centipedePartBase = null;
		}

		
	}
	
	@Override
	public void setLivingAnimations(CentipedePartEntity entityIn, float limbSwing, float limbSwingAmount,
			float partialTick) 
	{
		this.partInstance = entityIn;
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) 
	{	
		matrixStackIn.push();
		this.centipedePartBase.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		matrixStackIn.pop();
	}

	@Override
	public void setRotationAngles(CentipedePartEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) 
	{

	}
	
	public static void translateStack(MatrixStack matrixStack, Vector3d vec)
	{
		matrixStack.translate(vec.x, vec.y, vec.z);
	}
}
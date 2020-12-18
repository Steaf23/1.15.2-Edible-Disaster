package steef23.edibledisaster.client.renderer.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import steef23.edibledisaster.entity.CentipedeEntity;

public class CentipedeEntityModel<T extends CentipedeEntity> extends EntityModel<T> 
{
	private final ModelRenderer centipedeHead;

	public CentipedeEntityModel() 
	{
		textureWidth = 64;
		textureHeight = 64;

		this.centipedeHead = new ModelRenderer(this, 0, 0);
		this.centipedeHead.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.centipedeHead.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) 
	{
		
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) 
	{	
		this.centipedeHead.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}
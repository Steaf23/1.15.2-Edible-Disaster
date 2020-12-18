package steef23.edibledisaster.client.renderer.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import steef23.edibledisaster.entity.CentipedeEntity;

public class CentipedeBodyModel<T extends CentipedeEntity> extends EntityModel<T> 
{
	private final ModelRenderer centipedeBodyPart;

	public CentipedeBodyModel()
	{
		textureWidth = 64;
		textureHeight = 64;
		
		this.centipedeBodyPart = new ModelRenderer(this, 0, 32);
		this.centipedeBodyPart.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.centipedeBodyPart.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F, false);
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
		this.centipedeBodyPart.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}
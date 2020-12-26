package steef23.edibledisaster.client.renderer.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import steef23.edibledisaster.entity.CentipedeEntity;

public class CentipedeModel<T extends CentipedeEntity> extends EntityModel<T> 
{
	private final ModelRenderer centipedePart;

	public CentipedeModel(String partType)
	{
		textureWidth = 64;
		textureHeight = 64;
		
		if (partType == "head")
		{
			this.centipedePart = new ModelRenderer(this, 0, 0);
			this.centipedePart.setRotationPoint(0.0F, 24.0F, 0.0F);
			this.centipedePart.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F, false);
		}
		else if (partType == "body")
		{
			this.centipedePart = new ModelRenderer(this, 0, 32);
			this.centipedePart.setRotationPoint(0.0F, 24.0F, 0.0F);
			this.centipedePart.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F, false);
		}
		else
		{
			this.centipedePart = null;
		}

		
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
		this.centipedePart.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}
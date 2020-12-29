package steef23.edibledisaster.client.renderer.entity;

import java.util.ArrayList;
import java.util.HashMap;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.client.renderer.entity.model.CentipedePartModel;
import steef23.edibledisaster.entity.CentipedeEntity;
import steef23.edibledisaster.entity.CentipedePartEntity;

public class CentipedeRenderer extends EntityRenderer<CentipedeEntity>
{
	protected static final ResourceLocation TEXTURE = new ResourceLocation(EdibleDisaster.MOD_ID, "textures/entity/centipede_entity.png");
	private HashMap<CentipedePartEntity, CentipedePartModel<CentipedePartEntity>> partModels = new HashMap<CentipedePartEntity, CentipedePartModel<CentipedePartEntity>>();
	
	public CentipedeRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
	}
	
	@Override
	public void render(CentipedeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) 
	{
		ArrayList<CentipedePartEntity> bodyParts = entityIn.getBodyParts();
		if (this.partModels.size() != bodyParts.size())
		{
			HashMap<CentipedePartEntity, CentipedePartModel<CentipedePartEntity>> tmpModels = new HashMap<CentipedePartEntity, CentipedePartModel<CentipedePartEntity>>();
			bodyParts.forEach((part) -> 
			{
				tmpModels.put(part, new CentipedePartModel<CentipedePartEntity>(part.partType));
			});
			this.partModels = tmpModels;
		}
		
		matrixStackIn.push();
		partModels.forEach((part, model) -> 
		{
			renderPartModel(model, part, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		});
		matrixStackIn.pop();
	}
	
	@Override
	public ResourceLocation getEntityTexture(CentipedeEntity entity) 
	{
		return TEXTURE;
	}
	
	public void renderPartModel(CentipedePartModel<CentipedePartEntity> partModel, CentipedePartEntity entityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		matrixStackIn.push();
	    matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
	    matrixStackIn.translate(0.0D, (double)-1.501F, 0.0D);
	    IVertexBuilder ivertexbuilder3 = bufferIn.getBuffer(RenderType.getEntitySolid(this.getEntityTexture(entityIn.centipede)));
	    partModel.setLivingAnimations(entityIn, 0.0f, 0.0f, partialTicks);
	    partModel.render(matrixStackIn, ivertexbuilder3, packedLightIn, 1, 1.0F, 1.0F, 1.0F, 1.0F);
	    matrixStackIn.pop();
	}
}

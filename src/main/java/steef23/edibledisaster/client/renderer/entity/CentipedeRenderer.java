package steef23.edibledisaster.client.renderer.entity;

import java.util.ArrayList;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.client.renderer.entity.model.CentipedeModel;
import steef23.edibledisaster.entity.CentipedeEntity;
import steef23.edibledisaster.entity.CentipedePartEntity;

public class CentipedeRenderer extends MobRenderer<CentipedeEntity, EntityModel<CentipedeEntity>>
{
	protected static final ResourceLocation TEXTURE = new ResourceLocation(EdibleDisaster.MOD_ID, "textures/entity/centipede_entity.png");
	private ArrayList<CentipedeModel<CentipedeEntity>> models = new ArrayList<CentipedeModel<CentipedeEntity>>();
	
	public CentipedeRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new CentipedeModel<CentipedeEntity>(null), 0.5f);
	}
	
	@Override
	public void render(CentipedeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) 
	{
		ArrayList<CentipedePartEntity> bodyParts = entityIn.getBodyParts();
		if (this.models.size() != bodyParts.size())
		{
			ArrayList<CentipedeModel<CentipedeEntity>> tmpModels = new ArrayList<CentipedeModel<CentipedeEntity>>();
			for (CentipedePartEntity bodyPart : bodyParts)
			{
				tmpModels.add(new CentipedeModel<CentipedeEntity>(bodyPart.partType));
			}
			
			this.models = tmpModels;
		}
		
		for (CentipedeModel<CentipedeEntity> model : this.models)
		{
			renderPartModel(model, entityIn, 0.0f, 0.0f, matrixStackIn, bufferIn, packedLightIn);
		}
	}
	
	
	
	@Override
	public ResourceLocation getEntityTexture(CentipedeEntity entity) 
	{
		return TEXTURE;
	}
	
	public void renderPartModel(CentipedeModel<CentipedeEntity> model, CentipedeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		
		matrixStackIn.push();

	    this.applyRotations(entityIn, matrixStackIn, 0.0f, 0.0f, partialTicks);
	    matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
	    this.preRenderCallback(entityIn, matrixStackIn, partialTicks);
	    matrixStackIn.translate(0.0D, (double)-1.501F, 0.0D);

	    model.setLivingAnimations(entityIn, 0.0f, 0.0f, partialTicks);
	    model.setRotationAngles(entityIn, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
	    boolean flag = this.isVisible(entityIn);
	    boolean flag1 = !flag && !entityIn.isInvisibleToPlayer(Minecraft.getInstance().player);
	    RenderType rendertype = this.func_230042_a_(entityIn, flag, flag1);
	    if (rendertype != null) {
	    	IVertexBuilder ivertexbuilder = bufferIn.getBuffer(rendertype);
	        int i = getPackedOverlay(entityIn, this.getOverlayProgress(entityIn, partialTicks));
	        model.render(matrixStackIn, ivertexbuilder, packedLightIn, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
	    }

	    matrixStackIn.pop();
//	    super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
}

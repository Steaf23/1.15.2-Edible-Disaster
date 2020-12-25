package steef23.edibledisaster.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.client.renderer.entity.model.CentipedeBodyModel;
import steef23.edibledisaster.client.renderer.entity.model.CentipedeHeadModel;
import steef23.edibledisaster.entity.CentipedeHeadEntity;

public class CentipedeRenderer extends MobRenderer<CentipedeHeadEntity, EntityModel<CentipedeHeadEntity>>
{
	protected static final ResourceLocation TEXTURE = new ResourceLocation(EdibleDisaster.MOD_ID, "textures/entity/centipede_entity.png");
	private boolean isHead;
	private final CentipedeHeadModel<CentipedeHeadEntity> headModel = new CentipedeHeadModel<>();
	private final CentipedeBodyModel<CentipedeHeadEntity> bodyModel = new CentipedeBodyModel<>();
	
	public CentipedeRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new CentipedeHeadModel<CentipedeHeadEntity>(), 0.5f);
		this.isHead = true;
	}
	
	@Override
	public void render(CentipedeHeadEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) 
	{
		boolean isHead = entityIn.isHead();
		if (isHead != this.isHead)
		{
			if (isHead)
			{
				this.entityModel = this.headModel;
			}
			else
			{
				this.entityModel = this.bodyModel;
			}
		}
		this.isHead = isHead;
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	@Override
	public ResourceLocation getEntityTexture(CentipedeHeadEntity entity) 
	{
		return TEXTURE;
	}
}

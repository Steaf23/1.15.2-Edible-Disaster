package steef23.edibledisaster.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.client.renderer.entity.model.CentipedeEntityModel;
import steef23.edibledisaster.entity.CentipedeEntity;

public class CentipedeEntityRenderer extends MobRenderer<CentipedeEntity, CentipedeEntityModel<CentipedeEntity>>
{
	protected static final ResourceLocation TEXTURE = new ResourceLocation(EdibleDisaster.MOD_ID, "textures/entity/centipede_entity.png");
	public CentipedeEntityRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new CentipedeEntityModel<CentipedeEntity>(), 0.5f);
	}
	
	@Override
	public ResourceLocation getEntityTexture(CentipedeEntity entity) {
		return TEXTURE;
	}
}

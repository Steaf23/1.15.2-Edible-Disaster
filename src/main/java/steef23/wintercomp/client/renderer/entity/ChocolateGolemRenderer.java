package steef23.wintercomp.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import steef23.wintercomp.WinterComp;
import steef23.wintercomp.client.renderer.entity.model.ChocolateGolemModel;
import steef23.wintercomp.entity.ChocolateGolemEntity;

public class ChocolateGolemRenderer extends MobRenderer<ChocolateGolemEntity, ChocolateGolemModel<ChocolateGolemEntity>>
{
	protected static final ResourceLocation TEXTURE = new ResourceLocation(WinterComp.MOD_ID, "textures/entity/chocolate_golem.png");
	
	public ChocolateGolemRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ChocolateGolemModel<ChocolateGolemEntity>(), 0.5f);
	}
	
	@Override
	public ResourceLocation getEntityTexture(ChocolateGolemEntity entity) 
	{
		return TEXTURE;
	}
}

package steef23.wintercomp.util;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import steef23.wintercomp.WinterComp;
import steef23.wintercomp.client.renderer.entity.CentipedeRenderer;
import steef23.wintercomp.client.renderer.entity.ChocolateGolemRenderer;
import steef23.wintercomp.init.EDEntityTypes;
import steef23.wintercomp.init.EDFluids;

@Mod.EventBusSubscriber(modid = WinterComp.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber 
{
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(EDEntityTypes.CENTIPEDE_ENTITY.get(), CentipedeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EDEntityTypes.CHOCOLATE_GOLEM_ENTITY.get(), ChocolateGolemRenderer::new);
		RenderTypeLookup.setRenderLayer(EDFluids.CHOCOLATE_FLUID.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(EDFluids.CHOCOLATE_FLOWING.get(), RenderType.getTranslucent());
	}
}

package steef23.edibledisaster.util;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.client.renderer.entity.CentipedeRenderer;
import steef23.edibledisaster.init.EDEntityTypes;
import steef23.edibledisaster.init.EDFluids;

@Mod.EventBusSubscriber(modid = EdibleDisaster.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber 
{
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(EDEntityTypes.CENTIPEDE_ENTITY.get(), CentipedeRenderer::new);
		RenderTypeLookup.setRenderLayer(EDFluids.B_CHOCOLATE_FLUID.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(EDFluids.B_CHOCOLATE_FLOWING.get(), RenderType.getTranslucent());
	}
}

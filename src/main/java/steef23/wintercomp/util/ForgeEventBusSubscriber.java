package steef23.wintercomp.util;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import steef23.wintercomp.WinterComp;
import steef23.wintercomp.init.EDDimensions;

@Mod.EventBusSubscriber(modid = WinterComp.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber 
{
	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event)
	{
		if (DimensionType.byName(WinterComp.WINTER_DIM_TYPE) == null)
		{
			DimensionManager.registerDimension(WinterComp.WINTER_DIM_TYPE, EDDimensions.WINTER_DIM.get(), null, true);
		}
		WinterComp.LOGGER.info("Dimensions Registered!");
	}
}

package steef23.edibledisaster.util;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.init.EDDimensions;

@Mod.EventBusSubscriber(modid = EdibleDisaster.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber 
{
	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event)
	{
		if (DimensionType.byName(EdibleDisaster.WINTER_DIM_TYPE) == null)
		{
			DimensionManager.registerDimension(EdibleDisaster.WINTER_DIM_TYPE, EDDimensions.WINTER_DIM.get(), null, true);
		}
		EdibleDisaster.LOGGER.info("Dimensions Registered!");
	}
}

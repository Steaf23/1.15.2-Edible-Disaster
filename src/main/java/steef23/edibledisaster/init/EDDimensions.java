package steef23.edibledisaster.init;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.world.dimension.WinterModDimension;

public class EDDimensions 
{
	public static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, EdibleDisaster.MOD_ID);
	
	public static final RegistryObject<ModDimension> WINTER_DIM = DIMENSIONS.register("winter_dim", 
			() -> new WinterModDimension());

}

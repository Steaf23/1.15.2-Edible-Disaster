package steef23.wintercomp.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.wintercomp.WinterComp;
import steef23.wintercomp.tileentity.FluidPumpTileEntity;

public class EDTileEntityTypes 
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES,WinterComp.MOD_ID);
	
	public static final RegistryObject<TileEntityType<FluidPumpTileEntity>> FLUID_PUMP = TILE_ENTITY_TYPES
			.register("fluid_pump", () -> TileEntityType.Builder
					.create(FluidPumpTileEntity::new, EDBlocks.FLUID_PUMP.get()).build(null));
}

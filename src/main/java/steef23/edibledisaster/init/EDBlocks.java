package steef23.edibledisaster.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.block.BloodChocolateBlock;
import steef23.edibledisaster.block.FluidPumpBlock;
import steef23.edibledisaster.block.WinterPortalBlock;

public class EDBlocks 
{
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, EdibleDisaster.MOD_ID);
	
	public static final RegistryObject<Block> FLUID_PUMP = BLOCKS.register("fluid_pump", 
			() -> new FluidPumpBlock(() -> EDTileEntityTypes.FLUID_PUMP.get(), Block.Properties.from(Blocks.IRON_BLOCK)));

	public static final RegistryObject<Block> B_CHOCOLATE_BLOCK = BLOCKS.register("b_chocolate_block", 
			() -> new BloodChocolateBlock(Block.Properties.from(Blocks.STONE).tickRandomly()));
	
	public static final RegistryObject<FlowingFluidBlock> B_CHOCOLATE = BLOCKS.register("b_chocolate", 
			() -> new FlowingFluidBlock(() -> EDFluids.B_CHOCOLATE_FLUID.get(), Block.Properties.create(Material.WATER)
					.doesNotBlockMovement().hardnessAndResistance(100.0f).noDrops()));
	
	public static final RegistryObject<Block> WINTER_PORTAL = BLOCKS.register("winter_portal", 
			() -> new WinterPortalBlock(Block.Properties.from(Blocks.END_PORTAL)));
}

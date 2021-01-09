package steef23.wintercomp.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.wintercomp.WinterComp;
import steef23.wintercomp.block.ChocolateBlock;
import steef23.wintercomp.block.ChocolateFluidBlock;
import steef23.wintercomp.block.FluidPumpBlock;
import steef23.wintercomp.block.WinterPortalBlock;

public class EDBlocks 
{
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, WinterComp.MOD_ID);
	
	public static final RegistryObject<Block> FLUID_PUMP = BLOCKS.register("fluid_pump", 
			() -> new FluidPumpBlock(() -> EDTileEntityTypes.FLUID_PUMP.get(), Block.Properties.from(Blocks.IRON_BLOCK)));

	public static final RegistryObject<Block> CHOCOLATE_BLOCK = BLOCKS.register("chocolate_block", 
			() -> new ChocolateBlock(Block.Properties.from(Blocks.STONE).tickRandomly()));
	
	public static final RegistryObject<FlowingFluidBlock> CHOCOLATE = BLOCKS.register("chocolate", 
			() -> new ChocolateFluidBlock(() -> EDFluids.CHOCOLATE_FLUID.get(), Block.Properties.create(Material.WATER)
					.doesNotBlockMovement().hardnessAndResistance(100.0f).noDrops().tickRandomly()));
	
	public static final RegistryObject<Block> WINTER_PORTAL = BLOCKS.register("winter_portal", 
			() -> new WinterPortalBlock(Block.Properties.from(Blocks.END_PORTAL)));
}

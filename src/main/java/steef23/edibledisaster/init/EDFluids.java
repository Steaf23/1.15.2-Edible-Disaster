package steef23.edibledisaster.init;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.edibledisaster.EdibleDisaster;

public class EDFluids 
{
	public static final ResourceLocation B_CHOCOLATE_STILL_RL = new ResourceLocation(EdibleDisaster.MOD_ID, "block/b_chocolate_still");
	public static final ResourceLocation B_CHOCOLATE_FLOWING_RL = new ResourceLocation(EdibleDisaster.MOD_ID, "block/b_chocolate_flowing");

	public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<Fluid>(ForgeRegistries.FLUIDS, EdibleDisaster.MOD_ID);
	
	public static final RegistryObject<FlowingFluid> B_CHOCOLATE_FLUID = FLUIDS.register("b_chocolate_fluid", 
			() -> new ForgeFlowingFluid.Source(EDFluids.B_CHOCOLATE_PROPERTIES));
	
	public static final RegistryObject<FlowingFluid> B_CHOCOLATE_FLOWING = FLUIDS.register("b_chocolate_flowing", 
			() -> new ForgeFlowingFluid.Flowing(EDFluids.B_CHOCOLATE_PROPERTIES));
	
	public static final ForgeFlowingFluid.Properties B_CHOCOLATE_PROPERTIES = new ForgeFlowingFluid.Properties(
			() -> B_CHOCOLATE_FLUID.get(), 
			() -> B_CHOCOLATE_FLOWING.get(), 
			FluidAttributes.builder(B_CHOCOLATE_STILL_RL, B_CHOCOLATE_FLOWING_RL).density(5).luminosity(10).rarity(Rarity.RARE))
			.bucket(() -> EDItems.B_CHOCOLATE_BUCKET.get()).block(() -> EDBlocks.B_CHOCOLATE.get());
}

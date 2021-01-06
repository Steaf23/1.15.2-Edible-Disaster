package steef23.wintercomp.init;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.wintercomp.WinterComp;

public class EDFluids 
{
	public static final ResourceLocation CHOCOLATE_STILL_RL = new ResourceLocation(WinterComp.MOD_ID, "block/chocolate_still");
	public static final ResourceLocation CHOCOLATE_FLOWING_RL = new ResourceLocation(WinterComp.MOD_ID, "block/chocolate_flowing");
	public static final ResourceLocation CHOCOLATE_OVERLAY_RL = new ResourceLocation(WinterComp.MOD_ID, "block/chocolate_overlay"); 

	public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<Fluid>(ForgeRegistries.FLUIDS, WinterComp.MOD_ID);
	
	public static final RegistryObject<FlowingFluid> CHOCOLATE_FLUID = FLUIDS.register("chocolate_fluid", 
			() -> new ForgeFlowingFluid.Source(EDFluids.CHOCOLATE_PROPERTIES));
	
	public static final RegistryObject<FlowingFluid> CHOCOLATE_FLOWING = FLUIDS.register("chocolate_flowing", 
			() -> new ForgeFlowingFluid.Flowing(EDFluids.CHOCOLATE_PROPERTIES));
	
	public static final ForgeFlowingFluid.Properties CHOCOLATE_PROPERTIES = new ForgeFlowingFluid.Properties(
			() -> CHOCOLATE_FLUID.get(), 
			() -> CHOCOLATE_FLOWING.get(), 
			FluidAttributes.builder(CHOCOLATE_STILL_RL, CHOCOLATE_FLOWING_RL).density(5).luminosity(10).overlay(CHOCOLATE_OVERLAY_RL).rarity(Rarity.RARE))
			.bucket(() -> EDItems.CHOCOLATE_BUCKET.get()).block(() -> EDBlocks.CHOCOLATE.get());
}

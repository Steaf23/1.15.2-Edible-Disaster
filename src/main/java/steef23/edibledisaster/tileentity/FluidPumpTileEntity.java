package steef23.edibledisaster.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import steef23.edibledisaster.init.EDTileEntityTypes;

public class FluidPumpTileEntity extends TileFluidHandler implements ITickableTileEntity
{
	
	public FluidPumpTileEntity(TileEntityType<?> tileEntityTypeIn) 
	{
		super(tileEntityTypeIn);
	}
	
	public FluidPumpTileEntity()
	{
		this(EDTileEntityTypes.FLUID_PUMP.get());
		this.getTank().setCapacity(16000);
	}

	@Override
	public void tick() 
	{
		if (!this.world.isRemote)
		{
			Block inputBlock = this.world.getBlockState(this.pos.down()).getBlock();
			if (inputBlock instanceof FlowingFluidBlock && this.world.getFluidState(this.pos.down()).isSource())
			{
				FlowingFluidBlock inputFluidBlock = (FlowingFluidBlock)inputBlock;
				Fluid inputFluid = inputFluidBlock.getFluid().getFluid();
				
				FluidStack fluidStack = this.getTank().getFluid();
				if (fluidStack.isFluidEqual(FluidStack.EMPTY))
				{
					fluidStack = new FluidStack(inputFluid, 0);
				}
				
				if (fluidStack.getRawFluid() == inputFluid)
				{
					if (fluidStack.getAmount() < this.getTank().getCapacity())
					{
						fluidStack.grow(1);
						this.getTank().setFluid(fluidStack);
					}
				}
			}
		}
	}
	
	public FluidTank getTank()
	{
		return this.tank;
	}
}

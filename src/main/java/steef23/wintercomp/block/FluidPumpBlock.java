package steef23.wintercomp.block;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import steef23.wintercomp.init.EDTileEntityTypes;
import steef23.wintercomp.tileentity.FluidPumpTileEntity;

public class FluidPumpBlock extends Block
{
	@SuppressWarnings("unused")
	private final Supplier<TileEntityType<? extends FluidPumpTileEntity>> tileEntityTypeSupplier;
	
	private static final VoxelShape SHAPE_LOWER = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 4.0D, 12.0D);
	private static final VoxelShape SHAPE_UPPER = Block.makeCuboidShape(0.0D, 4.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape SHAPE_COMBINED = VoxelShapes.combineAndSimplify(SHAPE_LOWER, SHAPE_UPPER, IBooleanFunction.OR);

	public FluidPumpBlock(Supplier<TileEntityType<? extends FluidPumpTileEntity>> tileEntitySupplierIn, Properties properties) 
	{
		super(properties);
		this.tileEntityTypeSupplier = tileEntitySupplierIn;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE_COMBINED;
	}
	
	//return a bucket of fluid when the player uses a bucket on it
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) 
	{
		ItemStack itemStack = player.getHeldItem(handIn);
		if (itemStack.isEmpty())
		{
			return ActionResultType.PASS;
		}
		if (!worldIn.isRemote)
		{
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof FluidPumpTileEntity)
			{
				FluidPumpTileEntity fluidPump = (FluidPumpTileEntity)te;
				LazyOptional<IFluidHandler> fluidHandler = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
				if (fluidHandler.isPresent() && itemStack.getItem() == Items.BUCKET)
				{
					System.out.println(fluidPump.getTank().getFluidAmount());
					if (fluidPump.getTank().getFluidAmount() >= 1000)
					{
						if (!player.abilities.isCreativeMode)
						{
							itemStack.shrink(1);
							
							if (itemStack.isEmpty())
							{
								player.setHeldItem(handIn, getFilledBucket(fluidPump));
							}
							else if (!player.inventory.addItemStackToInventory(getFilledBucket(fluidPump)))
							{
								player.dropItem(getFilledBucket(fluidPump), false);
							}
						}
						fluidPump.getTank().getFluid().shrink(1000);
						worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
					}
				}
			}
		}
		
		return ActionResultType.SUCCESS;
	}
	
	private static ItemStack getFilledBucket(FluidPumpTileEntity fluidPump)
	{
		return FluidUtil.getFilledBucket(new FluidStack(fluidPump.getTank().getFluid().getRawFluid(), 1000));
	}
	
	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) 
	{
		return false;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) 
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return EDTileEntityTypes.FLUID_PUMP.get().create();
	}
}

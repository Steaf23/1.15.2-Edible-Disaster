package steef23.edibledisaster.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import steef23.edibledisaster.init.EDBlocks;

public class BloodChocolateBlock extends Block
{
	public static final BooleanProperty POLISHED = BooleanProperty.create("polished");
	
	public BloodChocolateBlock(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(POLISHED, Boolean.valueOf(false)));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		builder.add(POLISHED);
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) 
	{
//		System.out.println("BLOOD_CHOCOLATE_BLOCK_TICK");
		if (!worldIn.isRemote)
		{
			if (!state.get(POLISHED))
			{
				if (canMelt(state, worldIn, pos))
				{
					worldIn.setBlockState(pos, EDBlocks.B_CHOCOLATE.get().getDefaultState());
				}
			}
		}
	}
	
	private static boolean canMelt(BlockState state, ServerWorld worldIn, BlockPos pos)
	{
		Block up = worldIn.getBlockState(pos.up()).getBlock();
		Block down = worldIn.getBlockState(pos.down()).getBlock();
		Block north = worldIn.getBlockState(pos.north()).getBlock();
		Block east = worldIn.getBlockState(pos.east()).getBlock();
		Block south = worldIn.getBlockState(pos.south()).getBlock();
		Block west = worldIn.getBlockState(pos.west()).getBlock();
		
		Block fluid = EDBlocks.B_CHOCOLATE.get();
		Block solid = EDBlocks.B_CHOCOLATE_BLOCK.get();
		
		boolean isSurrounded = (north == fluid || north == solid) &&
							   (east == fluid || east == solid) &&
							   (south == fluid || south == solid) &&
							   (west == fluid || west == solid) &&
							   (down == solid);
		
		return (worldIn.dimension.doesWaterVaporize() || 
				worldIn.getBiome(pos).doesWaterFreeze(worldIn, pos) ||
				worldIn.getLight(pos) > 13 - state.getOpacity(worldIn, pos)) &&
				(up == fluid) &&
				isSurrounded;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) 
	{
		ItemStack itemStack = player.getHeldItem(handIn);
		if (!worldIn.isRemote && !state.get(POLISHED) && true)
		{
			worldIn.setBlockState(pos, state.with(POLISHED, Boolean.valueOf(true)));
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
}

package steef23.edibledisaster.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import steef23.edibledisaster.init.EDBlocks;

public class BloodChocolateBlock extends Block
{
	public BloodChocolateBlock(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) 
	{
//		System.out.println("BLOOD_CHOCOLATE_BLOCK_TICK");
		if (!worldIn.isRemote)
		{
			if (canMelt(state, worldIn, pos))
			{
				worldIn.setBlockState(pos, EDBlocks.B_CHOCOLATE.get().getDefaultState());
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
}

package steef23.edibledisaster.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

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
	}
}

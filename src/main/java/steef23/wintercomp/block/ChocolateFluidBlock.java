package steef23.wintercomp.block;

import java.util.HashMap;
import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import steef23.wintercomp.entity.ChocolateGolemEntity;
import steef23.wintercomp.init.EDBlocks;
import steef23.wintercomp.init.EDEntityTypes;

public class ChocolateFluidBlock extends FlowingFluidBlock
{
	
	
	private static final String[][] golemEast = 
		{{"~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~"},
		 {"~,~,~,~,~",
		  "~,C,C,C,~",
   		  "~,C,~,C,~",
   		  "~,I,I,I,~",
		  "~,~,~,~,~"},
		 {"~,~,P,~,~",
		  "C,C,C,C,C",
		  "C,C,F,C,C",
		  "C,I,I,I,C",
		  "~,C,~,C,~"},
		 {"~,~,~,~,~",
		  "~,C,C,C,~",
		  "~,C,C,C,~",
		  "~,I,I,I,~",
		  "~,~,~,~,~"},
		 {"~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~"}};
	private static final String[][] golemWest =
		{{"~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~"},
		 {"~,~,~,~,~",
		  "~,C,C,C,~",
   		  "~,C,C,C,~",
   		  "~,I,I,I,~",
		  "~,~,~,~,~"},
		 {"~,~,P,~,~",
		  "C,C,C,C,C",
		  "C,C,F,C,C",
		  "C,I,I,I,C",
		  "~,C,~,C,~"},
		 {"~,~,~,~,~",
		  "~,C,C,C,~",
		  "~,C,~,C,~",
		  "~,I,I,I,~",
		  "~,~,~,~,~"},
		 {"~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~",
		  "~,~,~,~,~"}};
	private static final String[][] golemNorth = 
		{{"~,~,~,~,~",
		  "~,~,C,~,~",
		  "~,~,C,~,~",
		  "~,~,C,~,~",
		  "~,~,~,~,~"},
		 {"~,~,~,~,~",
		  "~,C,C,C,~",
   		  "~,C,C,C,~",
   		  "~,I,I,I,~",
		  "~,~,C,~,~"},
		 {"~,~,P,~,~",
		  "~,C,C,C,~",
		  "~,C,F,~,~",
		  "~,I,I,I,~",
		  "~,~,~,~,~"},
		 {"~,~,~,~,~",
		  "~,C,C,C,~",
		  "~,C,C,C,~",
		  "~,I,I,I,~",
		  "~,~,C,~,~"},
		 {"~,~,~,~,~",
		  "~,~,C,~,~",
		  "~,~,C,~,~",
		  "~,~,C,~,~",
		  "~,~,~,~,~"}};
	private static final String[][] golemSouth = 
		{{"~,~,~,~,~",
		  "~,~,C,~,~",
		  "~,~,C,~,~",
		  "~,~,C,~,~",
		  "~,~,~,~,~"},
		 {"~,~,~,~,~",
		  "~,C,C,C,~",
   		  "~,C,C,C,~",
   		  "~,I,I,I,~",
		  "~,~,C,~,~"},
		 {"~,~,P,~,~",
		  "~,C,C,C,~",
		  "~,~,F,C,~",
		  "~,I,I,I,~",
		  "~,~,~,~,~"},
		 {"~,~,~,~,~",
		  "~,C,C,C,~",
		  "~,C,C,C,~",
		  "~,I,I,I,~",
		  "~,~,C,~,~"},
		 {"~,~,~,~,~",
		  "~,~,C,~,~",
		  "~,~,C,~,~",
		  "~,~,C,~,~",
		  "~,~,~,~,~"}};
	
	private static final HashMap<String, Block> blockLUT =  new HashMap<>();

	public ChocolateFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) 
	{
		super(supplier, properties);
	}
	
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) 
	{	
		
		if (oldState.getBlock() != state.getBlock()) 
		{
			if (state.get(LEVEL) == 0 && worldIn.getBlockState(pos.down()).getBlock() == Blocks.IRON_BLOCK)
			{
				trySpawnGolem(worldIn, pos);
			}
	    }
		super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) 
	{
		if (!worldIn.isRemote && state.get(LEVEL) == 0)
		{
			if (this.tryFreeze(worldIn, pos))
			{
				worldIn.setBlockState(pos, EDBlocks.CHOCOLATE_BLOCK.get().getDefaultState());
			}
		}
		super.randomTick(state, worldIn, pos, random);
	}
	
	private boolean tryFreeze(World worldIn, BlockPos pos)
	{		
		int iceCounter = 0; 
		for (Direction direction : Direction.values())
		{
			if (worldIn.getBlockState(pos.offset(direction)).getBlock() == Blocks.BLUE_ICE)
			{
				iceCounter++;
			}
		}
		
		if (iceCounter >= 3)
		{
			return true;
		}
		return false;
	}
	
	private static boolean checkBox(World worldIn, BlockPos pos, String[][] arrayIn)
	{
		BlockPos ftl = pos.add(new Vec3i(2, 2, 2));
		
		for (int i = 0; i < arrayIn.length; i++)
		{
			for (int j = 0; j < arrayIn[0].length; j++)
			{
				for (int k = 0; k < arrayIn[0][0].split(",").length; k++)
				{
					
					Block gridBlock = blockLUT.get(arrayIn[i][j].split(",")[k]);
					Block worldBlock = worldIn.getBlockState(ftl.add(-i, -j, -k)).getBlock();
					if (worldBlock != gridBlock)
					{
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private void trySpawnGolem(World worldIn, BlockPos pos)
	{
		blockLUT.put("~", Blocks.AIR);
		blockLUT.put("C", EDBlocks.CHOCOLATE_BLOCK.get());
		blockLUT.put("I", Blocks.IRON_BLOCK);
		blockLUT.put("P", Blocks.CARVED_PUMPKIN);
		blockLUT.put("F", EDBlocks.CHOCOLATE.get());
		
		boolean east = checkBox(worldIn, pos, golemEast);
		boolean west = checkBox(worldIn, pos, golemWest);
		boolean north = checkBox(worldIn, pos, golemNorth);
		boolean south = checkBox(worldIn, pos, golemSouth);
		if (east || west || north || south)
		{
			for (int x = -2; x < 3; x++)
			{
				for (int y = -2; y < 3; y++)
				{
					for (int z = -2; z < 3; z++)
					{
						worldIn.setBlockState(pos.add(x, y, z), Blocks.AIR.getDefaultState(), 2);
						worldIn.playEvent(2001, pos, Block.getStateId(worldIn.getBlockState(pos)));
					}
				}
			}
			
			ChocolateGolemEntity golemEntity = EDEntityTypes.CHOCOLATE_GOLEM_ENTITY.get().create(worldIn);
			float yaw = 0;
			if (east) yaw = 0;
			else if (west) yaw = 180;
			else if (north) yaw = 90;
			else if (south) yaw = 270;
			
			golemEntity.setLocationAndAngles(pos.getX(), pos.getY() - 1, pos.getZ(), yaw, 0);
			worldIn.addEntity(golemEntity);
			
			for(ServerPlayerEntity serverplayerentity : worldIn.getEntitiesWithinAABB(ServerPlayerEntity.class, golemEntity.getBoundingBox().grow(5.0D))) 
			{
				CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, golemEntity);
         	}
		}
	}
}

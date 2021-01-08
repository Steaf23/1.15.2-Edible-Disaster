package steef23.wintercomp.block;

import java.util.function.Supplier;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockMaterialMatcher;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPattern.PatternHelper;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import steef23.wintercomp.entity.ChocolateGolemEntity;
import steef23.wintercomp.init.EDBlocks;
import steef23.wintercomp.init.EDEntityTypes;

public class ChocolateFluidBlock extends FlowingFluidBlock
{
	private static String[] frontLayer = {"~~~~~",
			   							  "~CCC~",
			   							  "~C~C~",
			   							  "~III~",
			   							  "~~~~~"};
	private static String[] middleLayer = {"~~P~~",
										   "CCCCC",
										   "CCFCC",
										   "CIIIC",
										   "~C~C~"};
	private static String[] backLayer = {"~~~~~",
			  							 "~CCC~",
			  							 "~CCC~",
			  							 "~III~",
			  							 "~~~~~"};

	private BlockPattern chocolateGolemPattern;

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
				this.trySpawnGolem(worldIn, pos);
				System.out.println("BLOCK ADDED");
			}
	    }
		super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
	}
	
	private void trySpawnGolem(World worldIn, BlockPos pos)
	{
		float yaw =  0.0f;
		PatternHelper blockPatternHelper = this.getChocolateGolemPattern().match(worldIn, pos);
		
		System.out.format("%s width: %d, height: %d, depth: %d", blockPatternHelper.toString(),
				blockPatternHelper.getWidth(), blockPatternHelper.getHeight(), blockPatternHelper);
		
		if (blockPatternHelper != null)
		{
			for (int i = 0; i < this.getChocolateGolemPattern().getThumbLength(); ++i)
			{
				CachedBlockInfo cachedBlockInfo = blockPatternHelper.translateOffset(0,  i, 0);
				worldIn.setBlockState(cachedBlockInfo.getPos(), Blocks.AIR.getDefaultState(), 2);
				worldIn.playEvent(2001, cachedBlockInfo.getPos(), Block.getStateId(cachedBlockInfo.getBlockState()));
			}
			
			ChocolateGolemEntity stoneGolemEntity = EDEntityTypes.CHOCOLATE_GOLEM_ENTITY.get().create(worldIn);
			BlockPos blockpos = blockPatternHelper.translateOffset(0, 1, 0).getPos();
			stoneGolemEntity.setLocationAndAngles((double)blockpos.getX() + 0.5D, 
												  (double)blockpos.getY() + 0.05D, 
												  (double)blockpos.getZ() + 0.5D, 
												  yaw, 
												  0.0f);
			worldIn.addEntity(stoneGolemEntity);
			
			for(ServerPlayerEntity serverplayerentity : worldIn.getEntitiesWithinAABB(ServerPlayerEntity.class, stoneGolemEntity.getBoundingBox().grow(5.0D))) {
	            CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, stoneGolemEntity);
	         	}

	        for(int l = 0; l < this.getChocolateGolemPattern().getThumbLength(); ++l) {
	            CachedBlockInfo cachedBlockInfo1 = blockPatternHelper.translateOffset(0, l, 0);
	            worldIn.notifyNeighbors(cachedBlockInfo1.getPos(), Blocks.AIR);
	        }
		}
	}

	private BlockPattern getChocolateGolemPattern() 
	{

		if (this.chocolateGolemPattern == null) 
	    {
			BlockPatternBuilder patternBuilder = BlockPatternBuilder.start();
			
			patternBuilder.aisle(frontLayer)
				.where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR)))
	        	.where('P', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.CARVED_PUMPKIN)))
	        	.where('C', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(EDBlocks.CHOCOLATE_BLOCK.get())))
	        	.where('F', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(EDBlocks.CHOCOLATE.get())))
	        	.where('I', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.IRON_BLOCK)));
			
			patternBuilder.aisle(middleLayer)
				.where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR)))
	        	.where('P', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.CARVED_PUMPKIN)))
	        	.where('C', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(EDBlocks.CHOCOLATE_BLOCK.get())))
	        	.where('F', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(EDBlocks.CHOCOLATE.get())))
	        	.where('I', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.IRON_BLOCK)));
			
			patternBuilder.aisle(backLayer)
				.where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR)))
				.where('P', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.CARVED_PUMPKIN)))
				.where('C', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(EDBlocks.CHOCOLATE_BLOCK.get())))
				.where('F', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(EDBlocks.CHOCOLATE.get())))
				.where('I', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.IRON_BLOCK)));
			
			this.chocolateGolemPattern = patternBuilder.build();
	   	}
		return this.chocolateGolemPattern;
	}
}

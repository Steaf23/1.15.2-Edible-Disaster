package steef23.edibledisaster.block;

import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.init.EDDimensions;

public class WinterPortalBlock extends Block 
{
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
	
	public WinterPortalBlock(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE;
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) 
	{
		if (!worldIn.isRemote && !entityIn.isPassenger() && !entityIn.isBeingRidden() && entityIn.isNonBoss() && 
				VoxelShapes.compare(VoxelShapes.create(entityIn.getBoundingBox().offset(
						(double)(-pos.getX()), (double)(-pos.getY()), (double)(-pos.getZ()))), state.getShape(worldIn, pos), IBooleanFunction.AND)) 
		{
			entityIn.changeDimension(entityIn.dimension.getModType() == EDDimensions.WINTER_DIM.get() ? DimensionType.OVERWORLD : DimensionType.byName(EdibleDisaster.WINTER_DIM_TYPE), 
					new ITeleporter() 
			{
                @Override
                public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw,
                        Function<Boolean, Entity> repositionEntity) 
                {
                    PlayerEntity playerIn = (PlayerEntity) entity;
                    playerIn = (PlayerEntity) repositionEntity.apply(false);
                    playerIn.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());

                    return playerIn;
                }
            });
	    }
	}
	
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) 
	{
		return ItemStack.EMPTY;
	}
	
	public boolean isReplaceable(BlockState p_225541_1_, Fluid p_225541_2_) 
	{
		return false;
	}
}

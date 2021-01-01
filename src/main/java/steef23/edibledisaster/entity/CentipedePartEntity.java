package steef23.edibledisaster.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;

//represents a single body part
public class CentipedePartEntity extends Entity
{
	public final CentipedeEntity centipede;
	public final String partType;
	private final CentipedePartEntity following;
	
	private static final DataParameter<Boolean> SHOULD_MOVE = EntityDataManager.createKey(CentipedeEntity.class, DataSerializers.BOOLEAN);
		
	public CentipedePartEntity(CentipedeEntity centipede, String partType, @Nullable CentipedePartEntity followingPart) 
	{
		super(centipede.getType(), centipede.world);
		this.centipede = centipede;
		this.partType = partType;
		this.following = followingPart;
	}
	
	public double distanceToFollowing()
	{
		if (this.partType == "head")
		{
			return this.getPositionVec().squareDistanceTo(this.centipede.getPositionVec());
		}
		else
		{
			return this.getPositionVec().squareDistanceTo(this.following.getPositionVec());
		}
	}
	
	public CentipedePartEntity getFollowing()
	{
		return this.following;
	}

	@Override
	protected void readAdditional(CompoundNBT compound) 
	{
		
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) 
	{
		
	}
	
	@Override
	public boolean canBeCollidedWith() 
	{
		return true;
	}

	@Override
	public IPacket<?> createSpawnPacket() 
	{
		throw new UnsupportedOperationException();
	}

	@Override
	protected void registerData() 
	{
		this.dataManager.register(SHOULD_MOVE, false);
	}
	
	public DataParameter<Boolean> shouldMove()
	{
		return SHOULD_MOVE;
	}
}

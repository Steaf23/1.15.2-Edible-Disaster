package steef23.edibledisaster.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3d;

//represents a single body part
public class CentipedePartEntity extends Entity
{
	public final CentipedeEntity centipede;
	public final String partType;
	private final CentipedePartEntity following;
	
	private static final DataParameter<Float> POS_X = EntityDataManager.createKey(CentipedeEntity.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> POS_Y = EntityDataManager.createKey(CentipedeEntity.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> POS_Z = EntityDataManager.createKey(CentipedeEntity.class, DataSerializers.FLOAT);
	
	private static final DataParameter<Boolean> MOVING = EntityDataManager.createKey(CentipedeEntity.class, DataSerializers.BOOLEAN);
	
	public CentipedePartEntity(CentipedeEntity centipede, String partType, @Nullable CentipedePartEntity followingPart) 
	{
		super(centipede.getType(), centipede.world);
		this.centipede = centipede;
		this.partType = partType;
		this.following = followingPart;
	}

	@Override
	public void tick() 
	{
		super.tick();
		if (!this.world.isRemote)
		{
			double distance = distanceToFollowing();
			System.out.println(distance);
			if (distance > 0.5D)
			{
				Vec3d position = this.getPositionVec();
				Vec3d goal = this.partType == "head" ? this.centipede.getPositionVec() : this.following.getPositionVec();
				Vec3d direction = goal.subtract(position).normalize();
				Vec3d movementVec = direction.scale(this.centipede.getCurrentMovementSpeed()).scale(0.01D).add(this.getPositionVec());
				
				this.dataManager.set(POS_X, (float)movementVec.x);
				this.dataManager.set(POS_Y, (float)movementVec.y);
				this.dataManager.set(POS_Z, (float)movementVec.z);
				
				this.dataManager.set(MOVING, true);
			}
			else
			{
				this.dataManager.set(MOVING, false);
			}
		}
		else
		{
			if (this.dataManager.get(MOVING))
			{
				this.setPosition(this.dataManager.get(POS_X), this.dataManager.get(POS_Y), this.dataManager.get(POS_Z));
			}
		}
	}
	
//	//gets the translation vector between currentPos and prevPos used for rendering
//	public Vector3d getPartTranslation()
//	{
//		if (this.currentPos == null || this.prevPos == null)
//		{
//			return new Vector3d(0.0D, 0.0D, 0.0D);
//		}
//		return new Vector3d(this.currentPos.getX() - this.prevPos.getX(), 
//				this.currentPos.getY() - this.prevPos.getY(), 
//				this.currentPos.getZ() - this.prevPos.getZ());
//	}
	
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
	
	@Override
	protected void registerData() 
	{
		this.dataManager.register(POS_X, (float)this.getPosX());
		this.dataManager.register(POS_Y, (float)this.getPosY());
		this.dataManager.register(POS_Z, (float)this.getPosZ());
		
		this.dataManager.register(MOVING, false);
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
}

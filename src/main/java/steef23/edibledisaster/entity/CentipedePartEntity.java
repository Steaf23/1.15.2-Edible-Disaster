package steef23.edibledisaster.entity;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.Vector3d;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;

//represents a single body part
public class CentipedePartEntity extends Entity
{
	public final CentipedeEntity centipede;
	public final String partType;
	private BlockPos currentPos;
	private BlockPos prevPos;
	private final CentipedePartEntity following;
	
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
//		System.out.println("server Side?:" + !this.world.isRemote + "EntityID: " + this.getEntityId() + "@ " + this.getPosX() + ", " +  this.getPosY() + ", " + this.getPosZ());
		if (this.partType == "head")
		{
			BlockPos entityPos = new BlockPos(this.centipede.getPosX(), this.centipede.getPosY(), this.centipede.getPosZ());
			System.out.println(entityPos);
			if (!isSamePos(this.currentPos, entityPos))
			{
				this.prevPos = this.currentPos;
				this.currentPos = entityPos;
			}
		}
		else //part is not Head
		{
			if (!isSamePos(this.currentPos, this.following.prevPos))
			{
				this.prevPos = this.currentPos;
				this.currentPos = this.following.prevPos;
			}
		}
	}
	
	//gets the translation vector between currentPos and prevPos
	public Vector3d getPartTranslation()
	{
		if (this.currentPos == null || this.prevPos == null)
		{
			return new Vector3d(0.0D, 0.0D, 0.0D);
		}
		return new Vector3d(this.currentPos.getX() - this.prevPos.getX(), 
				this.currentPos.getY() - this.prevPos.getY(), 
				this.currentPos.getZ() - this.prevPos.getZ());
	}
	
	public boolean setCurrentPos(BlockPos pos)
	{
		this.currentPos = pos;
		return pos != null;
	}
	
	public BlockPos getCurrentPos()
	{
		return this.currentPos;
	}
	
	public BlockPos getPrevPos()
	{
		return this.prevPos != null ? this.prevPos : this.currentPos;
	}
	
	@Override
	protected void registerData() 
	{

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
	
	public boolean isSamePos(BlockPos p1, BlockPos p2)
	{
		if (p1 == null || p2 ==  null)
		{
			return false;
		}
		
		return p1.getX() == p2.getX() && p1.getY() == p2.getY() && p1.getZ() == p1.getZ();
	}
}

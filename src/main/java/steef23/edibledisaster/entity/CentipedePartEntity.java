package steef23.edibledisaster.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;

//represents a single body part
public class CentipedePartEntity extends Entity
{
	public final CentipedeEntity centipede;
	public final String partType;
	
	public CentipedePartEntity(CentipedeEntity centipede, String partType) 
	{
		super(centipede.getType(), centipede.world);
		this.centipede = centipede;
		this.partType = partType;
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
	
}

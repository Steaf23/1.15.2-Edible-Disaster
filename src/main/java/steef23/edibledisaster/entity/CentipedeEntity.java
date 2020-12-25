package steef23.edibledisaster.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;

//represents the single headpart / master part
public class CentipedeEntity extends MobEntity
{
	private static final double defaultMoveSpeed = .2D;
	
	//stores body entity ids for easy conversion
	private final CentipedePartEntity[] bodyParts;
	
	public CentipedeEntity(EntityType<? extends CentipedeEntity> type, World worldIn) 
	{
		super(type, worldIn);
	}
	
	@Override
	public void tick() 
	{
		super.tick();
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(defaultMoveSpeed);
	    this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.3D);
	}
	
//	@Override
//	public void writeAdditional(CompoundNBT compound) 
//	{
//		super.writeAdditional(compound);
//		ListNBT listnbt = new ListNBT();
//		
//		for (int i = 0; i < this.body.size(); i++)
//		{
//			CompoundNBT nbtId = new CompoundNBT();
//			nbtId.putInt("Id", this.body.get(i));
//			listnbt.add(nbtId);
//		}
//		compound.put("Parts", listnbt);
//	}
//	
//	@Override
//	public void readAdditional(CompoundNBT compound) 
//	{
//		super.readAdditional(compound);
//		ListNBT listnbt = compound.getList("Parts", 3);
//		
//		LinkedList<Integer> parts = new LinkedList<>();
//		
//		for (int i = 0; i < listnbt.size(); ++i)
//		{
//			parts.addLast(listnbt.getCompound(i).getInt("Id"));
//		}
//		
//		this.body = parts;
//	}
}

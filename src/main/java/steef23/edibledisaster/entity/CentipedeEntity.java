package steef23.edibledisaster.entity;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;

//represents the single headpart / master part
public class CentipedeEntity extends MobEntity implements IMob
{
	private static final double defaultMoveSpeed = .2D;
	
	//stores body entity ids for easy conversion
	private final ArrayList<CentipedePartEntity> bodyParts;
	
	public CentipedeEntity(EntityType<? extends CentipedeEntity> type, World worldIn) 
	{
		super(type, worldIn);
		this.bodyParts = getNewParts();
	}
	
	private ArrayList<CentipedePartEntity> getNewParts()
	{
		CentipedePartEntity head = new CentipedePartEntity(this, "head");
		int bodyPartAmount = Math.max(4, Math.abs(new Random().nextInt()) % 12);
		
		ArrayList<CentipedePartEntity> parts = new ArrayList<>();
		parts.add(head);
		for(int i = 0; i < bodyPartAmount; i++)
		{
			CentipedePartEntity part = new CentipedePartEntity(this, "body");
			part.setPosition(this.getPosX() + 1.0D, this.getPosY(), this.getPosZ());
			parts.add(part);
		}
		
		return parts;
	}
	
//	@Override
//	public void livingTick() 
//	{
//		super.tick();
//	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(defaultMoveSpeed);
	    this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
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
	
	public ArrayList<CentipedePartEntity> getBodyParts()
	{
		return this.bodyParts;
	}
}

package steef23.edibledisaster.entity;

import java.util.LinkedList;
import java.util.Random;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import steef23.edibledisaster.init.EDEntityTypes;

public class CentipedeEntity extends MobEntity
{
	private static final double defaultMoveSpeed = .2D;
	private static final DataParameter<Boolean> IS_HEAD = EntityDataManager.createKey(CentipedeEntity.class, DataSerializers.BOOLEAN);
	
	//stores body entity ids for easy conversion
	private LinkedList<Integer> body = new LinkedList<>();
	
	private boolean firstTick = false;
	
	public CentipedeEntity(EntityType<? extends MobEntity> type, World worldIn) 
	{
		super(type, worldIn);

	}
	
	@Override
	public void tick() 
	{
		super.tick();
		
		if (!this.world.isRemote())
		{
			if (!firstTick)
			{
				firstTick = true;
				if (this.dataManager.get(IS_HEAD) && this.body.size() <= 0)
				{
					spawnAdditionalBodyParts();
				}
			}
		}
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(defaultMoveSpeed);
	    this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.3D);
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();
		this.dataManager.register(IS_HEAD, true);
	}
	
	private void spawnAdditionalBodyParts()
	{
		int amount = Math.max(4, Math.abs(new Random().nextInt()) % 12);
		System.out.println(amount);
		for (int i = 0; i < amount; i++)
		{
			CentipedeEntity body = new CentipedeEntity(EDEntityTypes.CENTIPEDE_ENTITY.get(), this.world);
			body.dataManager.set(IS_HEAD, false);
			BlockPos pos = this.getPosition();
			body.setPosition(pos.getX() + 1 + i, pos.getY(), pos.getZ());
			this.world.addEntity(body);	
			this.body.addLast(body.getEntityId());
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) 
	{
		super.writeAdditional(compound);
		ListNBT listnbt = new ListNBT();
		
		for (int i = 0; i < this.body.size(); i++)
		{
			CompoundNBT nbtId = new CompoundNBT();
			nbtId.putInt("Id", this.body.get(i));
			listnbt.add(nbtId);
		}
		compound.put("Parts", listnbt);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) 
	{
		super.readAdditional(compound);
		ListNBT listnbt = compound.getList("Parts", 3);
		
		LinkedList<Integer> parts = new LinkedList<>();
		
		for (int i = 0; i < listnbt.size(); ++i)
		{
			parts.addLast(listnbt.getCompound(i).getInt("Id"));
		}
		
		this.body = parts;
	}
	
//	private CompoundNBT writePart(CompoundNBT compound)
//	{
//		return compound;
//	}
//	
//	private void readPart(CompoundNBT compound)
//	{
//		
//	}
	
	public boolean isHead() 
	{
		return this.dataManager.get(IS_HEAD);
	}
	
	
}

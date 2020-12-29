package steef23.edibledisaster.entity;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//represents the single headpart / master part
public class CentipedeEntity extends CreatureEntity
{
	private static final double defaultMoveSpeed = 1.0D;
	private static final DataParameter<Integer> PART_AMOUNT = EntityDataManager.createKey(CentipedeEntity.class, DataSerializers.VARINT);
	
	private boolean firstTick = true;
	
	//stores body entity ids for easy conversion
	private ArrayList<CentipedePartEntity> bodyParts;
	
	public CentipedeEntity(EntityType<? extends CentipedeEntity> type, World worldIn) 
	{
		super(type, worldIn);
		this.bodyParts = getNewParts(worldIn);
	}
	
	private ArrayList<CentipedePartEntity> getNewParts(World worldIn)
	{
		if (!this.world.isRemote)
		{
			this.dataManager.set(PART_AMOUNT, Math.max(4, Math.abs(new Random().nextInt()) % 12));
		}
		return getNewPartList();
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(1, new MoveTowardsTargetGoal(this, defaultMoveSpeed, 32.0f));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, true));
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(defaultMoveSpeed);
	    this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();
		this.dataManager.register(PART_AMOUNT, 0);
	}
	
	@Override
	protected void updateAITasks() 
	{
		super.updateAITasks();
	}
	
	
	@Override
	public void livingTick() 
	{
		if (firstTick)
		{
			firstTick = false;
			this.bodyParts.forEach((part) ->
			{
				part.setCurrentPos(new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()));
			});
		}
		
		this.bodyParts.forEach((part) ->
		{
			part.tick();
		});
		
		if (world.isRemote)
		{
			this.syncPartsToClient();
		}
		
		super.livingTick();
	}
	
	//get parts from the datamanager and put them into the client so they get rendered correctly
	@OnlyIn(Dist.CLIENT)
	public void syncPartsToClient()
	{
		//get parts from server
		//if client_parts != manager_parts + head
		if (this.bodyParts.size() != this.dataManager.get(PART_AMOUNT) + 1)
		{
			this.bodyParts = this.getNewPartList();
		}
	}
	
	public ArrayList<CentipedePartEntity> getNewPartList()
	{
		ArrayList<CentipedePartEntity> parts = new ArrayList<>();
		
		CentipedePartEntity head = new CentipedePartEntity(this, "head", null);
		parts.add(head);
		for(int i = 0; i < this.dataManager.get(PART_AMOUNT); i++)
		{
			CentipedePartEntity part = new CentipedePartEntity(this, "body", parts.get(i));
			parts.add(part);
		}
		System.out.println("Server side?: "  + !this.world.isRemote);
		parts.forEach((part) ->
		{
			System.out.println("part: " + part.partType);
		});
		
		return parts;
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

package steef23.edibledisaster.entity;

import java.util.ArrayList;
import java.util.Random;

import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

//represents the single headpart / master part
public class CentipedeEntity extends CreatureEntity
{
	private static final double defaultMoveSpeed = 1.0D;
	
	private boolean firstTick = true;
	
	//stores body entity ids for easy conversion
	private ArrayList<Pair<CentipedePartEntity, Vec3d>> bodyParts;
	
	public CentipedeEntity(EntityType<? extends CentipedeEntity> type, World worldIn) 
	{
		super(type, worldIn);
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
	protected void updateAITasks() 
	{
		super.updateAITasks();
	}
	
	
	@Override
	public void livingTick() 
	{	
		super.livingTick();
		
		if (this.firstTick)
		{
			this.firstTick = false;
			this.bodyParts = getNewPartList();
		}
		
//		Vec3d[] partPosVectors = new Vec3d[this.bodyParts.size()];
//		for (int i = 0; i < partPosVectors.length; i++)
//		{
//			partPosVectors[i] = new Vec3d(this.bodyParts.get(i).getFirst().getPosX(),this.bodyParts.get(i).getFirst().getPosY(), this.bodyParts.get(i).getFirst().getPosZ());
//		}

		for (int i = 0; i < this.bodyParts.size(); i++)
		{
			CentipedePartEntity part = this.bodyParts.get(i).getFirst();
			Vec3d currentPos = this.bodyParts.get(i).getSecond();
			Vec3d followingPos = i <= 0 ? this.getPositionVec() : this.bodyParts.get(i - 1).getSecond();
			double distance = followingPos.distanceTo(currentPos);
			if (i == 0) System.out.format("Side: %s, Distance: %.2f \n", this.world.isRemote ? "Client" : "Server", distance);
			if (distance >= 1.0D)
			{
				//direction from the following position to the currentPosition
				Vec3d direction = followingPos.subtract(currentPos).normalize();
				Vec3d targetPos = direction.scale(distance - 1.0D).add(currentPos);
				part.position = targetPos;
				
				//target pos should always be the entityPos - .5 when it gets over
				this.bodyParts.set(i, Pair.of(part, targetPos));
			}
//			System.out.format("Side: %s Part: %s Position: (%.2f, %.2f, %.2f) Distance: %.2f \n", 
//					this.world.isRemote ? "Client" : "Server",
//					part.partType, 
//					part.getPosX(), part.getPosY(), part.getPosZ(),
//					distance);
		}
//        for(int i = 0; i < this.bodyParts.size(); i++) {
//            this.bodyParts.get(i).getFirst().prevPosX = partPosVectors[i].x;
//            this.bodyParts.get(i).getFirst().prevPosY = partPosVectors[i].y;
//            this.bodyParts.get(i).getFirst().prevPosZ = partPosVectors[i].z;
//            this.bodyParts.get(i).getFirst().lastTickPosX = partPosVectors[i].x;
//            this.bodyParts.get(i).getFirst().lastTickPosY = partPosVectors[i].y;
//            this.bodyParts.get(i).getFirst().lastTickPosZ = partPosVectors[i].z;
//         }
	}
	
	public ArrayList<Pair<CentipedePartEntity, Vec3d>> getNewPartList()
	{
		ArrayList<Pair<CentipedePartEntity, Vec3d>> parts = new ArrayList<>();
		EntitySize entitySize = EntitySize.flexible(1.0f, 1.0f);
		
		CentipedePartEntity head = new CentipedePartEntity(this, "head", entitySize, null);
		parts.add(Pair.of(head, new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ())));
		head.position = new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ());
		
		int count = Math.max(4, Math.abs(new Random().nextInt()) % 12);
		
		for(int i = 0; i < count; i++)
		{
			CentipedePartEntity part = new CentipedePartEntity(this, "body", entitySize, parts.get(i).getFirst());
			part.position = new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ());
			parts.add(Pair.of(part, new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ())));
		}
		
		System.out.println("Server side?: "  + !this.world.isRemote);
		parts.forEach((part) ->
		{
			System.out.println("part: " + part.getFirst().partType);
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
	
	public ArrayList<Pair<CentipedePartEntity, Vec3d>> getBodyParts()
	{
		return this.bodyParts;
	}
	
	public double getCurrentMovementSpeed()
	{
		return defaultMoveSpeed;
	}
}

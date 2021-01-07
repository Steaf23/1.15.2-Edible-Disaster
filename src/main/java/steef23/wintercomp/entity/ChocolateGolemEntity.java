package steef23.wintercomp.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ChocolateGolemEntity extends GolemEntity 
{

	private static final double defaultMoveSpeed = 0.25D;
	
	public ChocolateGolemEntity(EntityType<? extends GolemEntity> type, World worldIn) 
	{
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(64.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(defaultMoveSpeed);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 6.0f));
		this.goalSelector.addGoal(2,  new WaterAvoidingRandomWalkingGoal(this, 0.6D));
	}
}

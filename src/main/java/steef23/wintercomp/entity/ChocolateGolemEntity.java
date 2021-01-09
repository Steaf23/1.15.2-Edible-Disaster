package steef23.wintercomp.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChocolateGolemEntity extends CreatureEntity
{

	private static final double defaultMoveSpeed = 0.25D;
	
	private int attackTimer;
	
	public ChocolateGolemEntity(EntityType<? extends ChocolateGolemEntity> type, World worldIn) 
	{
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(defaultMoveSpeed);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 2.0D, false));
	    this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0f));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
	}
	
	@Override
	public void livingTick() 
	{
		super.livingTick();
		if (this.attackTimer > 0) 
		{
			--this.attackTimer;
		}
	}
	
	private float getAttackDamage() 
	{
		return (float)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
	}
	   
	@Override
	public boolean attackEntityAsMob(Entity entityIn) 
	{
		this.attackTimer = 10;
		this.world.setEntityState(this, (byte)4);
		float damageAmount = this.getAttackDamage();
		float trueDamage = damageAmount > 0.0F ? damageAmount / 2.0F + (float)this.rand.nextInt((int)damageAmount) : 0.0F;
		boolean succesfull = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), trueDamage);
		if (succesfull) 
		{
			entityIn.setMotion(entityIn.getMotion().add(0.0D, (double)0.4F, 0.0D));
			this.applyEnchantments(this, entityIn);
		}

		this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		return succesfull;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) 
	{
		if (id == 4) 
		{
			this.attackTimer = 10;
			this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		} else {
			super.handleStatusUpdate(id);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackTimer() 
	{
		return this.attackTimer;
	}
   
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return SoundEvents.ENTITY_IRON_GOLEM_HURT;
	}

	protected SoundEvent getDeathSound() 
	{
		return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
	}
   
	protected void playStepSound(BlockPos pos, BlockState blockIn) 
	{
		this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 1.0F, 1.0F);
	}
}

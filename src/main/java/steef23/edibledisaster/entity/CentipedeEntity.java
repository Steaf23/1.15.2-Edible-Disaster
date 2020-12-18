package steef23.edibledisaster.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class CentipedeEntity extends MobEntity
{
	private static final double defaultMoveSpeed = .2D;

	private boolean isHead;
	
	public CentipedeEntity(EntityType<? extends MobEntity> type, World worldIn) 
	{
		this(type, worldIn, true);

	}

	public CentipedeEntity(EntityType<? extends MobEntity> type, World worldIn, boolean isHead)
	{
		super(type, worldIn);
		this.isHead = isHead;
		
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if (this.getHealth() < this.getMaxHealth())
		{
			this.isHead = false;
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
	
	public boolean isHead() {
		return isHead;
	}
}

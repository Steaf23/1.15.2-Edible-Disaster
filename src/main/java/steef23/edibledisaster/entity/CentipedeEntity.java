package steef23.edibledisaster.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import steef23.edibledisaster.init.EDEntityTypes;

public class CentipedeEntity extends MobEntity
{
	private static final double defaultMoveSpeed = .2D;

	private boolean isHead = true;
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
				if (isHead)
				{
					spawnBodyPart();
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
	
	private CentipedeEntity spawnBodyPart()
	{
		CentipedeEntity body = new CentipedeEntity(EDEntityTypes.CENTIPEDE_ENTITY.get(), this.world);
		BlockPos pos = this.getPosition();
		body.setPosition(pos.getX() + 1, pos.getY(), pos.getZ());
		this.world.addEntity(body);	
		return body;
	}
	
	public boolean isHead() 
	{
		return isHead;
	}
	
}

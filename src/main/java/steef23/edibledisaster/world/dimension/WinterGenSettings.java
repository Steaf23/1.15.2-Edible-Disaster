package steef23.edibledisaster.world.dimension;

import net.minecraft.world.gen.GenerationSettings;

public class WinterGenSettings extends GenerationSettings
{
	public int getBiomeSize()
	{
		return 4;
	}
	
	public int getRiverSize()
	{
		return 4;
	}
	
	public int getBiomeId()
	{
		return -1;
	}
	
	@Override
	public int getBedrockFloorHeight() 
	{
		return 0;
	}
}

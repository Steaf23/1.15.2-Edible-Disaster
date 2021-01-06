package steef23.wintercomp.world.biome;

import net.minecraft.world.biome.Biome;

public class WinterHorrorBiome extends Biome
{

	public WinterHorrorBiome(Builder biomeBuilder) 
	{
		super(biomeBuilder);
	}
	
	@Override
	public int getGrassColor(double posX, double posZ) 
	{
		return 0x000000;
	}
	
	@Override
	public int getFoliageColor() 
	{
		return 0x000000;
	}
}

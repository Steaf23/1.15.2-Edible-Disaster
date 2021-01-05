package steef23.edibledisaster.world.dimension;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class WinterBiomeProviderSettings implements IBiomeProviderSettings
{
	private final long seed;
	private final WorldType worldType;
	private WinterGenSettings generatorSettings = new WinterGenSettings();
	
	public WinterBiomeProviderSettings(WorldInfo info) 
	{
		this.seed = info.getSeed();
		this.worldType = info.getGenerator();
	}
	
	public WinterBiomeProviderSettings setGeneratorSettings(WinterGenSettings settings) 
	{
		this.generatorSettings = settings;
		return this;
	}
	
	public long getSeed()
	{
		return this.seed;
	}
	
	public WorldType getWorldType()
	{
		return this.worldType;
	}
	
	public WinterGenSettings getGeneratorSettings()
	{
		return this.generatorSettings;
	}
}

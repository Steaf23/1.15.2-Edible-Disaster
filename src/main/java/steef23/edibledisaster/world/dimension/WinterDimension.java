package steef23.edibledisaster.world.dimension;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import steef23.edibledisaster.client.renderer.HailRenderer;
import steef23.edibledisaster.init.EDBlocks;

public class WinterDimension extends Dimension
{
	public static final HailRenderer weatherHandler = new HailRenderer();
	
	public WinterDimension(World worldIn, DimensionType typeIn) 
	{
		super(worldIn, typeIn, 0.0f);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() 
	{
		WinterGenSettings winterGenSettings = new WinterGenSettings();
		winterGenSettings.setDefaultBlock(Blocks.BLACK_TERRACOTTA.getDefaultState());
		winterGenSettings.setDefaultFluid(EDBlocks.B_CHOCOLATE.get().getDefaultState());
		return new WinterChunkGenerator(this.world, 
				new WinterBiomeProvider(new WinterBiomeProviderSettings(this.world.getWorldInfo())),
				winterGenSettings);
	}

	@Override
	public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) 
	{
		return null;
	}

	@Override
	public BlockPos findSpawn(int posX, int posZ, boolean checkValid) 
	{
		return null;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) 
	{
		return 0;
	}

	@Override
	public boolean isSurfaceWorld() 
	{
		return false;
	}

	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTicks) 
	{
		return Vec3d.ZERO;
	}

	@Override
	public boolean canRespawnHere() 
	{
		return false;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) 
	{
		return false;
	}
	
	@Override
	public SleepResult canSleepAt(PlayerEntity player, BlockPos pos) 
	{
		return SleepResult.DENY;
	}
	
	@Override
	public boolean hasSkyLight() 
	{
		return true;
	}
	
	@Override
	public int getActualHeight() 
	{
		return 256;
	}
	
	@Override
	public IRenderHandler getWeatherRenderer() 
	{
		return weatherHandler;
	}
}

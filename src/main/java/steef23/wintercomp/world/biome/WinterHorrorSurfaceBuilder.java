package steef23.wintercomp.world.biome;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import steef23.wintercomp.init.EDBlocks;

public class WinterHorrorSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

	public WinterHorrorSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> function) {
		super(function);
	}

	@Override
	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise,
			BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) 
	{
		SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed,
				new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.getDefaultState(),
						EDBlocks.CHOCOLATE_BLOCK.get().getDefaultState(), EDBlocks.CHOCOLATE_BLOCK.get().getDefaultState()));
	}

}

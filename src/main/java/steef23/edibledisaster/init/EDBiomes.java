package steef23.edibledisaster.init;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.world.biome.WinterHorrorBiome;
import steef23.edibledisaster.world.biome.WinterHorrorSurfaceBuilder;

public class EDBiomes 
{
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, EdibleDisaster.MOD_ID);
	
	public static final RegistryObject<Biome> WINTER_BIOME = BIOMES.register("winter_biome",
			() -> new WinterHorrorBiome(new Biome.Builder()
					.surfaceBuilder(new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(
							register("winter_surface",
									new WinterHorrorSurfaceBuilder(
											SurfaceBuilderConfig::deserialize)),
							new SurfaceBuilderConfig(Blocks.COARSE_DIRT.getDefaultState(),
									Blocks.DIRT.getDefaultState(),
									Blocks.DIRT.getDefaultState())))
					.precipitation(RainType.RAIN).category(Category.NONE).depth(0.1f).scale(0.4f).temperature(1.0f).downfall(1.0f).waterColor(0x000000).waterFogColor(0x000000).parent(null)));
	
	public static void registerBiomes()
	{
		registerBiome(WINTER_BIOME.get(), Type.COLD, Type.DRY, Type.SPOOKY);
	}
	
	private static void registerBiome(Biome biome, Type... types)
	{
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
	
	@SuppressWarnings("deprecation")
	private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builderIn) {
		return (F) (Registry.<SurfaceBuilder<?>>register(Registry.SURFACE_BUILDER, key, builderIn));
	}

}

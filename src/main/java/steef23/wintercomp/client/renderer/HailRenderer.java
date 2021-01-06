package steef23.wintercomp.client.renderer;

import java.util.Random;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ILightReader;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.client.IRenderHandler;
import steef23.wintercomp.WinterComp;

public class HailRenderer implements IRenderHandler 
{
	public static final ResourceLocation HAIL_TEXTURES = new ResourceLocation(WinterComp.MOD_ID, "textures/environment/hail.png");
	
	private final float[] hailSizeX = new float[1024];
	private final float[] hailSizeZ = new float[1024];
	
	public HailRenderer() 
	{
		for(int x = 0; x < 32; ++x) 
		{
			for(int z = 0; x < 32; ++x) 
			{
				float f = (float)(z - 16);
	            float f1 = (float)(x - 16);
	            float f2 = MathHelper.sqrt(f * f + f1 * f1);
	            this.hailSizeX[x << 5 | z] = -f1 / f2;
	            this.hailSizeZ[x << 5 | z] = f / f2;
	        }
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc) 
	{
		float xIn = 0.0f;
		float yIn = 100.0f;
		float zIn = 0.0f;
		
		float rainStrength = world.getRainStrength(partialTicks);
		if (rainStrength > 0.0F) 
		{
//			lightmapIn.enableLightmap();
			int posX = MathHelper.floor(xIn);
			int posY = MathHelper.floor(yIn);
			int posZ = MathHelper.floor(zIn);
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferbuilder = tessellator.getBuffer();
			RenderSystem.disableCull();
			RenderSystem.normal3f(0.0F, 1.0F, 0.0F);
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.defaultAlphaFunc();
			int renderRange = 5;
			if (mc.gameSettings.fancyGraphics) 
			{
				renderRange = 10;
			}
			
			// tessellator only has to be started once depending on weather (rain or snow) 
			int startTessellator = -1;
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
			
			//loop through a square grid with origin at (posZ,posX)
			for (int gridZ = posZ - renderRange; gridZ <= posZ + renderRange; ++gridZ) 
			{
				for (int gridX = posX - renderRange; gridX <= posX + renderRange; ++gridX) 
				{
					int l1 = (gridZ - posZ + 16) * 32 + gridX - posX + 16;
					double d0 = (double) this.hailSizeX[l1] * 0.5D;
					double d1 = (double) this.hailSizeZ[l1] * 0.5D;
					blockpos$mutable.setPos(gridX, 0, gridZ);
					Biome biome = world.getBiome(blockpos$mutable);
					if (biome.getPrecipitation() != Biome.RainType.NONE) 
					{
						int maxRenderHeight = world.getHeight(Heightmap.Type.MOTION_BLOCKING, blockpos$mutable).getY();
						int lowerRenderBound = posY - renderRange;
						int upperRenderBound = posY + renderRange;
						if (lowerRenderBound < maxRenderHeight) 
						{
							lowerRenderBound = maxRenderHeight;
						}

						if (upperRenderBound < maxRenderHeight) 
						{
							upperRenderBound = maxRenderHeight;
						}
						
						int newMaxRenderHeight = maxRenderHeight;
						if (maxRenderHeight < posY) 
						{
							newMaxRenderHeight = posY;
						}

						if (lowerRenderBound != upperRenderBound) 
						{
							Random random = new Random(
									(long) (gridX * gridX * 3121 + gridX * 45238971 ^ gridZ * gridZ * 418711 + gridZ * 13761));
							blockpos$mutable.setPos(gridX, lowerRenderBound, gridZ);
							if (startTessellator != 0) 
							{
								if (startTessellator >= 0) 
								{
									tessellator.draw();
								}

								startTessellator = 0;
								mc.getTextureManager().bindTexture(HAIL_TEXTURES);
								bufferbuilder.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
							}

							int i3 = ticks + gridX * gridX * 3121 + gridX * 45238971 + gridZ * gridZ * 418711 + gridZ * 13761
									& 31;
							float f3 = -((float) i3 + partialTicks) / 32.0F * (3.0F + random.nextFloat());
							double d2 = (double) ((float) gridX + 0.5F) - xIn;
							double d4 = (double) ((float) gridZ + 0.5F) - zIn;
							float f4 = MathHelper.sqrt(d2 * d2 + d4 * d4) / (float) renderRange;
							float f5 = ((1.0F - f4 * f4) * 0.5F + 0.5F) * rainStrength;
							blockpos$mutable.setPos(gridX, newMaxRenderHeight, gridZ);
							int combinedLight = getCombinedLight(world, blockpos$mutable);
							bufferbuilder.pos((double) gridX - xIn - d0 + 0.5D, (double) upperRenderBound - yIn, (double) gridZ - zIn - d1 + 0.5D)
									.tex(0.0F, (float) lowerRenderBound * 0.25F + f3).color(1.0F, 1.0F, 1.0F, f5).lightmap(combinedLight)
									.endVertex();
							bufferbuilder.pos((double) gridX - xIn + d0 + 0.5D, (double) upperRenderBound - yIn, (double) gridZ - zIn + d1 + 0.5D)
									.tex(1.0F, (float) lowerRenderBound * 0.25F + f3).color(1.0F, 1.0F, 1.0F, f5).lightmap(combinedLight)
									.endVertex();
							bufferbuilder.pos((double) gridX - xIn + d0 + 0.5D, (double) lowerRenderBound - yIn, (double) gridZ - zIn + d1 + 0.5D)
									.tex(1.0F, (float) upperRenderBound * 0.25F + f3).color(1.0F, 1.0F, 1.0F, f5).lightmap(combinedLight)
									.endVertex();
							bufferbuilder.pos((double) gridX - xIn - d0 + 0.5D, (double) lowerRenderBound - yIn, (double) gridZ - zIn - d1 + 0.5D)
									.tex(0.0F, (float) upperRenderBound * 0.25F + f3).color(1.0F, 1.0F, 1.0F, f5).lightmap(combinedLight)
									.endVertex();
						}
					}
				}
			}
			if (startTessellator >= 0) 
			{
				tessellator.draw();
	        }

	        RenderSystem.enableCull();
	        RenderSystem.disableBlend();
	        RenderSystem.defaultAlphaFunc();
//	        lightmapIn.disableLightmap();
		}
	}
	
	public static int getCombinedLight(ILightReader lightReaderIn, BlockPos blockPosIn) 
	{
		return getPackedLightmapCoords(lightReaderIn, lightReaderIn.getBlockState(blockPosIn), blockPosIn);
	}

	public static int getPackedLightmapCoords(ILightReader lightReaderIn, BlockState blockStateIn, BlockPos blockPosIn) {
		if (blockStateIn.isEmissiveRendering()) 
		{
			return 15728880;
	    } 
		else 
		{
			int i = lightReaderIn.getLightFor(LightType.SKY, blockPosIn);
	        int j = lightReaderIn.getLightFor(LightType.BLOCK, blockPosIn);
	        int k = blockStateIn.getLightValue(lightReaderIn, blockPosIn);
	        if (j < k) 
	        {
	        	j = k;
	        }
	        return i << 20 | j << 4;
		}
	}
}

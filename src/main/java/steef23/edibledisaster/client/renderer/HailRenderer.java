package steef23.edibledisaster.client.renderer;

import java.util.Random;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.client.IRenderHandler;
import steef23.edibledisaster.EdibleDisaster;

public class HailRenderer implements IRenderHandler 
{
	public static final ResourceLocation HAIL_TEXTURES = new ResourceLocation(EdibleDisaster.MOD_ID,
			"textures/environment/hail.png");

	@Override
	public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		if (!(f <= 0.0F)) {
			lightmapIn.enableLightmap();
			World world = this.mc.world;
			int i = MathHelper.floor(xIn);
			int j = MathHelper.floor(yIn);
			int k = MathHelper.floor(zIn);
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferbuilder = tessellator.getBuffer();
			RenderSystem.disableCull();
			RenderSystem.normal3f(0.0F, 1.0F, 0.0F);
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.defaultAlphaFunc();
			int l = 5;
			if (this.mc.gameSettings.fancyGraphics) {
				l = 10;
			}

			int i1 = -1;
			float f1 = (float) this.ticks + partialTicks;
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

			for (int j1 = k - l; j1 <= k + l; ++j1) {
				for (int k1 = i - l; k1 <= i + l; ++k1) {
					int l1 = (j1 - k + 16) * 32 + k1 - i + 16;
					double d0 = (double) this.rainSizeX[l1] * 0.5D;
					double d1 = (double) this.rainSizeZ[l1] * 0.5D;
					blockpos$mutable.setPos(k1, 0, j1);
					Biome biome = world.getBiome(blockpos$mutable);
					if (biome.getPrecipitation() != Biome.RainType.NONE) {
						int i2 = world.getHeight(Heightmap.Type.MOTION_BLOCKING, blockpos$mutable).getY();
						int j2 = j - l;
						int k2 = j + l;
						if (j2 < i2) {
							j2 = i2;
						}

						if (k2 < i2) {
							k2 = i2;
						}

						int l2 = i2;
						if (i2 < j) {
							l2 = j;
						}

						if (j2 != k2) {
							Random random = new Random(
									(long) (k1 * k1 * 3121 + k1 * 45238971 ^ j1 * j1 * 418711 + j1 * 13761));
							blockpos$mutable.setPos(k1, j2, j1);
							float f2 = biome.getTemperature(blockpos$mutable);
							if (f2 >= 0.15F) {
								if (i1 != 0) {
									if (i1 >= 0) {
										tessellator.draw();
									}

									i1 = 0;
									this.mc.getTextureManager().bindTexture(RAIN_TEXTURES);
									bufferbuilder.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
								}

								int i3 = this.ticks + k1 * k1 * 3121 + k1 * 45238971 + j1 * j1 * 418711 + j1 * 13761
										& 31;
								float f3 = -((float) i3 + partialTicks) / 32.0F * (3.0F + random.nextFloat());
								double d2 = (double) ((float) k1 + 0.5F) - xIn;
								double d4 = (double) ((float) j1 + 0.5F) - zIn;
								float f4 = MathHelper.sqrt(d2 * d2 + d4 * d4) / (float) l;
								float f5 = ((1.0F - f4 * f4) * 0.5F + 0.5F) * f;
								blockpos$mutable.setPos(k1, l2, j1);
								int j3 = getCombinedLight(world, blockpos$mutable);
								bufferbuilder
										.pos((double) k1 - xIn - d0 + 0.5D, (double) k2 - yIn,
												(double) j1 - zIn - d1 + 0.5D)
										.tex(0.0F, (float) j2 * 0.25F + f3).color(1.0F, 1.0F, 1.0F, f5).lightmap(j3)
										.endVertex();
								bufferbuilder
										.pos((double) k1 - xIn + d0 + 0.5D, (double) k2 - yIn,
												(double) j1 - zIn + d1 + 0.5D)
										.tex(1.0F, (float) j2 * 0.25F + f3).color(1.0F, 1.0F, 1.0F, f5).lightmap(j3)
										.endVertex();
								bufferbuilder
										.pos((double) k1 - xIn + d0 + 0.5D, (double) j2 - yIn,
												(double) j1 - zIn + d1 + 0.5D)
										.tex(1.0F, (float) k2 * 0.25F + f3).color(1.0F, 1.0F, 1.0F, f5).lightmap(j3)
										.endVertex();
								bufferbuilder
										.pos((double) k1 - xIn - d0 + 0.5D, (double) j2 - yIn,
												(double) j1 - zIn - d1 + 0.5D)
										.tex(0.0F, (float) k2 * 0.25F + f3).color(1.0F, 1.0F, 1.0F, f5).lightmap(j3)
										.endVertex();
							} else {
								if (i1 != 1) {
									if (i1 >= 0) {
										tessellator.draw();
									}

									i1 = 1;
									this.mc.getTextureManager().bindTexture(SNOW_TEXTURES);
									bufferbuilder.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
								}

								float f6 = -((float) (this.ticks & 511) + partialTicks) / 512.0F;
								float f7 = (float) (random.nextDouble()
										+ (double) f1 * 0.01D * (double) ((float) random.nextGaussian()));
								float f8 = (float) (random.nextDouble()
										+ (double) (f1 * (float) random.nextGaussian()) * 0.001D);
								double d3 = (double) ((float) k1 + 0.5F) - xIn;
								double d5 = (double) ((float) j1 + 0.5F) - zIn;
								float f9 = MathHelper.sqrt(d3 * d3 + d5 * d5) / (float) l;
								float f10 = ((1.0F - f9 * f9) * 0.3F + 0.5F) * f;
								blockpos$mutable.setPos(k1, l2, j1);
								int k3 = getCombinedLight(world, blockpos$mutable);
								int l3 = k3 >> 16 & '\uffff';
								int i4 = (k3 & '\uffff') * 3;
								int j4 = (l3 * 3 + 240) / 4;
								int k4 = (i4 * 3 + 240) / 4;
								bufferbuilder
										.pos((double) k1 - xIn - d0 + 0.5D, (double) k2 - yIn,
												(double) j1 - zIn - d1 + 0.5D)
										.tex(0.0F + f7, (float) j2 * 0.25F + f6 + f8).color(1.0F, 1.0F, 1.0F, f10)
										.lightmap(k4, j4).endVertex();
								bufferbuilder
										.pos((double) k1 - xIn + d0 + 0.5D, (double) k2 - yIn,
												(double) j1 - zIn + d1 + 0.5D)
										.tex(1.0F + f7, (float) j2 * 0.25F + f6 + f8).color(1.0F, 1.0F, 1.0F, f10)
										.lightmap(k4, j4).endVertex();
								bufferbuilder
										.pos((double) k1 - xIn + d0 + 0.5D, (double) j2 - yIn,
												(double) j1 - zIn + d1 + 0.5D)
										.tex(1.0F + f7, (float) k2 * 0.25F + f6 + f8).color(1.0F, 1.0F, 1.0F, f10)
										.lightmap(k4, j4).endVertex();
								bufferbuilder
										.pos((double) k1 - xIn - d0 + 0.5D, (double) j2 - yIn,
												(double) j1 - zIn - d1 + 0.5D)
										.tex(0.0F + f7, (float) k2 * 0.25F + f6 + f8).color(1.0F, 1.0F, 1.0F, f10)
										.lightmap(k4, j4).endVertex();
							}
						}
					}
				}
			}
		}
		mc.getTextureManager().bindTexture(HAIL_TEXTURES);
	}
}

package steef23.edibledisaster.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.entity.CentipedeEntity;

public class EDEntityTypes 
{	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, EdibleDisaster.MOD_ID);
	
	
	public static final RegistryObject<EntityType<CentipedeEntity>> CENTIPEDE_ENTITY = ENTITY_TYPES
			.register("centipede_entity", 
					() -> EntityType.Builder.<CentipedeEntity>create(CentipedeEntity::new, EntityClassification.CREATURE)
					.size(1.0f, 1.0f)
					.build(new ResourceLocation(EdibleDisaster.MOD_ID, "centipede_entity").toString()));
	
	public static void registerPlacementTypes()
	{
		registerPlacementType(CENTIPEDE_ENTITY.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void registerPlacementType(EntityType type, EntitySpawnPlacementRegistry.PlacementType spawnType)
	{
		EntitySpawnPlacementRegistry.register(type, spawnType, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
	}
}
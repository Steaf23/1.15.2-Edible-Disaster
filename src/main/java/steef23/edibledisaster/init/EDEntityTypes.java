package steef23.edibledisaster.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.entity.CentipedeEntity;

public class EDEntityTypes 
{	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, EdibleDisaster.MOD_ID);
	
	
	public static final RegistryObject<EntityType<CentipedeEntity>> STONE_GOLEM_ENTITY = ENTITY_TYPES
			.register("centipede_entity", 
					() -> EntityType.Builder.<CentipedeEntity>create(CentipedeEntity::new, EntityClassification.CREATURE)
					.size(1.0f, 1.6f)
					.build(new ResourceLocation(EdibleDisaster.MOD_ID, "centipede_entity").toString()));
}
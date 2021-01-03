package steef23.edibledisaster.init;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.edibledisaster.EdibleDisaster;
import steef23.edibledisaster.EdibleDisaster.EDItemGroup;
import steef23.edibledisaster.item.CentipedeSpawnEggItem;

public class EDItems 
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, EdibleDisaster.MOD_ID);
	
	public static final RegistryObject<CentipedeSpawnEggItem> CENTIPEDE_SPAWN_EGG = ITEMS.register("centipede_spawn_egg", 
			() -> new CentipedeSpawnEggItem(EDEntityTypes.CENTIPEDE_ENTITY, 0x000000, 0xFFFFFF,
					new Item.Properties().group(EDItemGroup.instance))); 
	
	public static final RegistryObject<BucketItem> B_CHOCOLATE_BUCKET = ITEMS.register("b_chocolate_bucket", 
			() -> new BucketItem(() -> EDFluids.B_CHOCOLATE_FLUID.get(), new Item.Properties().group(EDItemGroup.instance).maxStackSize(1)));
}

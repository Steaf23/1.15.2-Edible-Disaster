package steef23.wintercomp.init;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.wintercomp.WinterComp;
import steef23.wintercomp.WinterComp.EDItemGroup;
import steef23.wintercomp.item.CentipedeSpawnEggItem;

public class EDItems 
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, WinterComp.MOD_ID);
	
	public static final RegistryObject<CentipedeSpawnEggItem> CENTIPEDE_SPAWN_EGG = ITEMS.register("centipede_spawn_egg", 
			() -> new CentipedeSpawnEggItem(EDEntityTypes.CENTIPEDE_ENTITY, 0x000000, 0xFFFFFF,
					new Item.Properties().group(EDItemGroup.instance))); 
	
	public static final RegistryObject<BucketItem> CHOCOLATE_BUCKET = ITEMS.register("chocolate_bucket", 
			() -> new BucketItem(() -> EDFluids.CHOCOLATE_FLUID.get(), new Item.Properties().group(EDItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> CHOCOLATE_CORE = ITEMS.register("chocolate_core",
			() -> new Item(new Item.Properties().group(EDItemGroup.instance)));
}

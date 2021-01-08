package steef23.wintercomp.init;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.wintercomp.WinterComp;
import steef23.wintercomp.WinterComp.EDItemGroup;
import steef23.wintercomp.item.ChocolateGolemSpawnEggItem;

public class EDItems 
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, WinterComp.MOD_ID);
	
	public static final RegistryObject<ChocolateGolemSpawnEggItem> CHOCOLATE_GOLEM_SPAWN_EGG = ITEMS.register("chocolate_golem_spawn_egg", 
			() -> new ChocolateGolemSpawnEggItem(EDEntityTypes.CHOCOLATE_GOLEM_ENTITY, 0x50291A, 0x2E69AF,
					new Item.Properties().group(EDItemGroup.instance))); 
	
	public static final RegistryObject<BucketItem> CHOCOLATE_BUCKET = ITEMS.register("chocolate_bucket", 
			() -> new BucketItem(() -> EDFluids.CHOCOLATE_FLUID.get(), new Item.Properties().group(EDItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> CHOCOLATE_CORE = ITEMS.register("chocolate_core",
			() -> new Item(new Item.Properties().group(EDItemGroup.instance)));
}

package steef23.edibledisaster;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import steef23.edibledisaster.init.EDEntityTypes;
import steef23.edibledisaster.init.EDItems;
import steef23.edibledisaster.item.CentipedeSpawnEggItem;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("edibledisaster")
@Mod.EventBusSubscriber(modid = EdibleDisaster.MOD_ID, bus=Bus.MOD)
public class EdibleDisaster
{
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "edibledisaster";
	public static EdibleDisaster instance;
	
	public EdibleDisaster()
	{
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        
        //register stuff
        EDItems.ITEMS.register(modEventBus);
        EDEntityTypes.ENTITY_TYPES.register(modEventBus);
        
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void doClientStuff(final FMLClientSetupEvent event) 
    {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) 
    {

    }
    
    @SubscribeEvent
    public static void onSpawnEntities(final RegistryEvent.Register<EntityType<?>> event)
    {
    	CentipedeSpawnEggItem.initSpawnEgg();
    }
    
    public static class EDItemGroup extends ItemGroup
    {
    	public static final EDItemGroup instance = new EDItemGroup(ItemGroup.GROUPS.length, "edibledisastergroup");

		private EDItemGroup(int index, String label) 
		{
			super(index, label);
		}
		
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(Blocks.BEDROCK);
		}
    }
}

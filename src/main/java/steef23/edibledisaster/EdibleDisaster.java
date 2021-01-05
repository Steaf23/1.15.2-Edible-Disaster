package steef23.edibledisaster;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import steef23.edibledisaster.init.EDBiomes;
import steef23.edibledisaster.init.EDBlocks;
import steef23.edibledisaster.init.EDDimensions;
import steef23.edibledisaster.init.EDEntityTypes;
import steef23.edibledisaster.init.EDFluids;
import steef23.edibledisaster.init.EDItems;
import steef23.edibledisaster.init.EDPotionTypes;
import steef23.edibledisaster.init.EDTileEntityTypes;
import steef23.edibledisaster.item.CentipedeSpawnEggItem;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("edibledisaster")
@Mod.EventBusSubscriber(modid = EdibleDisaster.MOD_ID, bus=Bus.MOD)
public class EdibleDisaster
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "edibledisaster";
	public static EdibleDisaster instance;
	
	public static final ResourceLocation WINTER_DIM_TYPE = new ResourceLocation(MOD_ID, "winter");
	
	public EdibleDisaster()
	{
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        
        //register stuff
        EDItems.ITEMS.register(modEventBus);
        EDFluids.FLUIDS.register(modEventBus);
        EDBlocks.BLOCKS.register(modEventBus);
        EDTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        EDEntityTypes.ENTITY_TYPES.register(modEventBus);
        EDPotionTypes.POTION_TYPES.register(modEventBus);
        EDBiomes.BIOMES.register(modEventBus);
        EDDimensions.DIMENSIONS.register(modEventBus);
        
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	@SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) 
    {
    	final IForgeRegistry<Item> registry = event.getRegistry();
    	
    	EDBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).filter(block -> 
    		!(block instanceof FlowingFluidBlock)
    		).forEach(block -> {
    			final Item.Properties properties = new Item.Properties().group(EDItemGroup.instance);
    			final BlockItem blockItem = new BlockItem(block, properties);
    			blockItem.setRegistryName(block.getRegistryName());
    			registry.register(blockItem);
    		});
    	
    	LOGGER.debug("Registered BlockItems!");
    }


    private void setup(final FMLCommonSetupEvent event)
    {
    	EDEntityTypes.registerPlacementTypes();
    	registerEntityWorldSpawn(EDEntityTypes.CENTIPEDE_ENTITY.get(), 15, 1, 2, EDBiomes.WINTER_BIOME.get());
    }
    
    public void registerEntityWorldSpawn(EntityType<?> entity, int weight, int minGroupIn, int maxGroupIn, Biome... biomes)
    {
    	for (Biome biome : biomes)
    	{
    		if (biome != null)
    		{
    			biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, minGroupIn, maxGroupIn));
    		}
    	}
    }
    
    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event)
    {
    	EDBiomes.registerBiomes();
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

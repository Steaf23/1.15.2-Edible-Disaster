package steef23.wintercomp.init;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import steef23.wintercomp.WinterComp;

public class EDPotionTypes 
{
	public static final DeferredRegister<Potion> POTION_TYPES = new DeferredRegister<>(ForgeRegistries.POTION_TYPES, WinterComp.MOD_ID);
	
	public static final RegistryObject<Potion> HUNGER= POTION_TYPES.register("hunger", 
			() -> new Potion(new EffectInstance(Effects.HUNGER, 1800)));
	
	public static final RegistryObject<Potion> LONG_HUNGER= POTION_TYPES.register("long_hunger", 
			() -> new Potion("hunger", new EffectInstance(Effects.HUNGER, 4800)));
	
	public static final RegistryObject<Potion> STRONG_HUNGER= POTION_TYPES.register("strong_hunger", 
			() -> new Potion("hunger", new EffectInstance(Effects.HUNGER, 400, 3)));
}

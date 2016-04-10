package alter.BESM;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.ModAPIManager;

@Mod (modid = Main.MODID, name = Main.MODNAME, version = Main.MODVERSION)
public class Main {
	public static final String MODID = "besm";
	public static final String MODVERSION = "quick 'n' dirty";
	public static final String MODNAME = "Botania Enchanted Soil Mover";
	public static boolean botaniaLoaded = false;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		botaniaLoaded = Loader.isModLoaded("Botania");
		ModItems.init();
	}
}

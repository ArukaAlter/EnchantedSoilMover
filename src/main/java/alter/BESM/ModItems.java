package alter.BESM;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModItems {
	
	
	public static Item terrasteelTrowel;
	public static void init(){
		terrasteelTrowel = new TerrasteelTrowel();
		GameRegistry.registerItem(terrasteelTrowel, "terrasteelTrowel");
		
		Item resource = (Item) Item.itemRegistry.getObject("Botania:manaResource");
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.terrasteelTrowel), "ti", 't', new ItemStack(resource, 1, 3), 'i', new ItemStack(resource, 1, 4));
	}
}

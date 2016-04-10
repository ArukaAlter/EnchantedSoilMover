package alter.BESM;

import cpw.mods.fml.common.Optional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.ManaItemHandler;

@Optional.Interface(modid = "Botania", iface = "vazkii.botania.api.mana.ManaItemHandler", striprefs = true)
public class ToolsCommon {
	public final static class ToolCommons {

		public static Material[] materialsShovel = new Material[]{ Material.grass, Material.ground, Material.sand, Material.snow, Material.craftedSnow, Material.clay };

		public static void damageItem(ItemStack stack, int dmg, EntityLivingBase entity, int manaPerDamage) {
			int manaToRequest = dmg * manaPerDamage;
			boolean manaRequested = entity instanceof EntityPlayer ? ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) entity, manaToRequest, true) : false;

			if(!manaRequested)
				stack.damageItem(dmg, entity);
		}
	}
}

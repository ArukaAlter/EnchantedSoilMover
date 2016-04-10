package alter.BESM;

import java.util.List;

import alter.BESM.ToolsCommon.ToolCommons;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import cpw.mods.fml.common.Optional;

@Optional.Interface(modid = "Botania", iface = "vazkii.botania.api.mana.IManaUsingItem", striprefs = true)
public class TerrasteelTrowel extends ItemSpade implements IManaUsingItem{
	public TerrasteelTrowel(){
		super(ToolMaterial.IRON);
		this.setUnlocalizedName("besm.trowelTerrasteel");
		this.setTextureName("besm:trowelTerrasteel");
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
		for(int i=1; i<4; i++)
		list.add(StatCollector.translateToLocal("besm.underlist." + i));
	}
	private static final int MANA_PER_DAMAGE = 100;
	private static final int REMOVE_COST = 450000;
	static Block soil = (Block) Block.blockRegistry.getObject("Botania:enchantedSoil");
	@Override
	public boolean usesMana(ItemStack stack) {
		return true;}
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase user, EntityLivingBase target) {
		ToolCommons.damageItem(stack, 7, target, MANA_PER_DAMAGE);
		return true;}
	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5) {
		if(!world.isRemote && player instanceof EntityPlayer && stack.getItemDamage() > 0 && ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) player, MANA_PER_DAMAGE * 2, true))
			stack.setItemDamage(stack.getItemDamage() - 1);}
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
		if (block.getBlockHardness(world, x, y, z) != 0F)
			ToolCommons.damageItem(stack, 1, entity, MANA_PER_DAMAGE);
		return true;}
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz){
		return removeSoil(stack, player, world, x, y, z, side, hitx, hity, hitz, soil, REMOVE_COST);}
	public static boolean removeSoil(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz, Block block, int cost){
		if(ManaItemHandler.requestManaExact(stack, player, REMOVE_COST, false)){
			ItemStack soilItem = new ItemStack(Item.getItemFromBlock(soil), 1);
			if(!world.isRemote && world.getBlock(x, y, z) == soil){
				ManaItemHandler.requestManaExact(stack, player, REMOVE_COST, true);
				world.setBlockToAir(x, y, z);
				world.spawnEntityInWorld(new EntityItem(world, x+0.5F, y+1.5F, z+0.5F, soilItem));
				world.playSoundEffect(x, y+0.5, z, "minecraft:step.grass", 1f, 1f);
				return true;
			}
			else
				return false;
		}
		return true;
	}
}

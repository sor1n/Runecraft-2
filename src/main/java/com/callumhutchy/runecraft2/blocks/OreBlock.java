package com.callumhutchy.runecraft2.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import reference.Reference;

import com.callumhutchy.runecraft2.Runecraft2;
import com.callumhutchy.runecraft2.blocks.models.OreModel;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityAdamantiteOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityCoalOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityCopperOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityGoldOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityIronOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityMithrilOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityRuniteOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntitySilverOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityTinOre;
import com.callumhutchy.runecraft2.items.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OreBlock extends BlockContainer{

	

	private int blockID;
	public Item itemToBeDropped;
	private final OreModel model;
	public String tileEntityClass;

	public OreBlock(Material p_i45394_1_, String resourceLocation, String unlocalisedName, Item item, String tileEntity) {
		super(p_i45394_1_);
		this.setCreativeTab(Runecraft2.tabRunecraft2Metal);
        this.setBlockBounds(0.0625F, 0F, 0.0625F, 0.9375F, 0.8125F, 0.9375F);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
        this.setBlockTextureName(resourceLocation);
        this.setBlockName(unlocalisedName);
        itemToBeDropped = item;
        this.model = new OreModel();
        tileEntityClass = tileEntity;
	}

	 

	//You don't want the normal render type, or it wont render properly.
	@Override
	public int getRenderType() {
	        return -1;
	}
	
	
	
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
	   return false;
	}

	//And this tell it that you can see through this block, and neighbor blocks should be rendered.
	public boolean isOpaqueCube()
	{
	   return false;
	}


	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		switch(tileEntityClass){
		case "adamantite": return Items.adamantiteOre;
		case "coal": return Items.coalOre;
		case "copper": return Items.copperOre;
		case "gold": return Items.goldOre;
		case "iron": return Items.ironOre;
		case "mithril": return Items.mithrilOre;
		case "runite": return Items.runiteOre;
		case "silver": return Items.silverOre;
		case "tin": return Items.tinOre;
		default: return null;
		}
    }
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {

		switch(tileEntityClass){
		case "adamantite": return new TileEntityAdamantiteOre();
		case "coal": return new TileEntityCoalOre();
		case "copper": return new TileEntityCopperOre();
		case "gold": return new TileEntityGoldOre();
		case "iron": return new TileEntityIronOre();
		case "mithril": return new TileEntityMithrilOre();
		case "runite": return new TileEntityRuniteOre();
		case "silver": return new TileEntitySilverOre();
		case "tin": return new TileEntityTinOre();
		default: return null;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	}



}
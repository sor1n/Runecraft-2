package com.callumhutchy.runecraft2.blocks.ore;

import java.util.Random;

import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityCopperOre;
import com.callumhutchy.runecraft2.items.Items;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CopperOreBlock extends OreBlock{

	public CopperOreBlock(Material p_i45394_1_, String resourceLocation) {
		super(p_i45394_1_, resourceLocation);
	
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		 return new TileEntityCopperOre();
	}
	
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_, Item itemDropped)
    {
        return Items.copperOre;
    }
	
}
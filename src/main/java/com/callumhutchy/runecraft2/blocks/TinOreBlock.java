package com.callumhutchy.runecraft2.blocks;

import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityCopperOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityTinOre;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TinOreBlock extends OreBlock {

	public TinOreBlock(Material p_i45394_1_, String resourceLocation) {
		super(p_i45394_1_, resourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		 return new TileEntityTinOre();
	}
	
}
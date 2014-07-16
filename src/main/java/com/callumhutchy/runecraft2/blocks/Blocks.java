package com.callumhutchy.runecraft2.blocks;

import java.util.Random;

import reference.BlockNames;

import com.callumhutchy.runecraft2.Runecraft2;
import com.callumhutchy.runecraft2.items.Items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;



public class Blocks{

	public static Random random;
	
	//Altar
	public static Block Altar;
	
	//Ore
	public static Block CopperOre;
	public static Block TinOre;

	public static void init(){
		//Altar
		Altar = new Altar(Material.rock).setCreativeTab(Runecraft2.tabRunecraft2Magic);
		
		//Ore
		CopperOre = new CopperOreBlock(Material.rock, "runecraft2:textures/blocks/OreModelCopperTexture.png").setBlockTextureName("runecraft2:OreModelCopperTexture").setBlockName("copperore");
		TinOre = new TinOreBlock(Material.rock, "runecraft2:textures/blocks/OreModelTinTexture.png").setBlockTextureName("runecraft2:OreModelTinTexture");
				
		
	}
	
	
	
	public static void gameRegistry(){
		GameRegistry.registerBlock(Altar, "Altar");
		GameRegistry.registerBlock(CopperOre, "copperore");
		GameRegistry.registerBlock(TinOre, "TinOre");
	}
	
}

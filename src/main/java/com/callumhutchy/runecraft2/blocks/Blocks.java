package com.callumhutchy.runecraft2.blocks;

import java.util.Random;

import reference.BlockNames;

import com.callumhutchy.runecraft2.Runecraft2;
import com.callumhutchy.runecraft2.blocks.ore.AdamantiteOreBlock;
import com.callumhutchy.runecraft2.blocks.ore.CoalOreBlock;
import com.callumhutchy.runecraft2.blocks.ore.CopperOreBlock;
import com.callumhutchy.runecraft2.blocks.ore.GoldOreBlock;
import com.callumhutchy.runecraft2.blocks.ore.IronOreBlock;
import com.callumhutchy.runecraft2.blocks.ore.MithrilOreBlock;
import com.callumhutchy.runecraft2.blocks.ore.RuniteOreBlock;
import com.callumhutchy.runecraft2.blocks.ore.SilverOreBlock;
import com.callumhutchy.runecraft2.blocks.ore.TinOreBlock;
import com.callumhutchy.runecraft2.items.Items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;



public class Blocks{

	public static Random random;
	
	//Altar
	public static Block Altar;
	
	//Ores
	public static Block CopperOre;
	public static Block TinOre;
	public static Block AdamantiteOre;
	public static Block CoalOre;
	public static Block GoldOre;
	public static Block IronOre;
	public static Block MithrilOre;
	public static Block RuniteOre;
	public static Block SilverOre;
	

	public static void init(){
		//Altar
		Altar = new Altar(Material.rock).setCreativeTab(Runecraft2.tabRunecraft2Magic).setBlockName("altar");
		
		//Ores
		CopperOre = new CopperOreBlock(Material.rock, "runecraft2:textures/blocks/OreModelCopperTexture.png").setBlockTextureName("runecraft2:OreModelCopperTexture").setBlockName(BlockNames.CopperOre_Unlo);
		TinOre = new TinOreBlock(Material.rock, "runecraft2:textures/blocks/OreModelTinTexture.png").setBlockTextureName("runecraft2:OreModelTinTexture").setBlockName(BlockNames.TinOre_Unlo);
		AdamantiteOre = new AdamantiteOreBlock(Material.rock, "runecraft2:textures/blocks/OreModelAdamantiteTexture.png").setBlockTextureName("runecraft2:OreModelAdamantiteTexture").setBlockName(BlockNames.AdamantiteOre_Unlo);		
		CoalOre = new CoalOreBlock(Material.rock, "runecraft2:textures/blocks/OreModelCoalTexture.png").setBlockTextureName("runecraft2:OreModelCoalTexture").setBlockName(BlockNames.CoalOre_Unlo);
		GoldOre = new GoldOreBlock(Material.rock, "runecraft2:textures/blocks/OreModelGoldTexture.png").setBlockTextureName("runecraft2:OreModelGoldTexture").setBlockName(BlockNames.GoldOre_Unlo);
		IronOre = new IronOreBlock(Material.rock, "runecraft2:textures/blocks/OreModelIronTexture.png").setBlockTextureName("runecraft2:OreModelIronTexture").setBlockName(BlockNames.IronOre_Unlo);
		MithrilOre = new MithrilOreBlock(Material.rock, "runecraft2:textures/blocks/OreModelMithrilTexture.png").setBlockTextureName("runecraft2:OreModelMithrilTexture").setBlockName(BlockNames.MithrilOre_Unlo);
		RuniteOre = new RuniteOreBlock(Material.rock, "runecraft2:textures/blocks/OreModelRuniteTexture.png").setBlockTextureName("runecraft2:OreModelRuniteTexture").setBlockName(BlockNames.RuniteOre_Unlo);
		SilverOre = new SilverOreBlock(Material.rock, "runecraft2:textures/blocks/OreModelSilverTexture.png").setBlockTextureName("runecraft2:OreModelSilverTexture").setBlockName(BlockNames.SilverOre_Unlo);
	}
	
	
	
	public static void gameRegistry(){
		//Altars
		GameRegistry.registerBlock(Altar, BlockNames.Altar_Unlo);
		
		//Ores
		GameRegistry.registerBlock(CopperOre, BlockNames.CopperOre_Unlo);
		GameRegistry.registerBlock(TinOre, BlockNames.TinOre_Unlo);
		GameRegistry.registerBlock(AdamantiteOre, BlockNames.AdamantiteOre_Unlo);
		GameRegistry.registerBlock(CoalOre, BlockNames.CoalOre_Unlo);
		GameRegistry.registerBlock(GoldOre, BlockNames.GoldOre_Unlo);
		GameRegistry.registerBlock(IronOre, BlockNames.IronOre_Unlo);
		GameRegistry.registerBlock(MithrilOre, BlockNames.MithrilOre_Unlo);
		GameRegistry.registerBlock(RuniteOre, BlockNames.RuniteOre_Unlo);
		GameRegistry.registerBlock(SilverOre, BlockNames.SilverOre_Unlo);
	}
	
}

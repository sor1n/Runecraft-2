package com.callumhutchy.runecraft2.items;

import com.callumhutchy.runecraft2.Runecraft2;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import reference.ItemNames;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Items {

	
	//Runes
	public static Item airRune;
	
	//Talismans
	public static Item airTalisman;
	
	//Ores
	public static Item CopperOre;
	
	
	public static void init(){
		//Runes
		airRune = new Rune().setUnlocalizedName(ItemNames.AirRune_Unlo);
		
		//Talismans
		airTalisman = new BaseItem().setUnlocalizedName(ItemNames.AirTalisman_Unlo).setCreativeTab(Runecraft2.tabRunecraft2Magic);
		
	}
	
	
	@SuppressWarnings("deprecation")
	public static void addNames(){
		//Runes
		LanguageRegistry.addName(airRune, ItemNames.AirRune_Lo);
		
		//Talismans
		LanguageRegistry.addName(airTalisman, ItemNames.AirTalisman_Lo);
		
		
	}
	
	
	public static void gameRegistery(){
		//Runes
		GameRegistry.registerItem(airRune, airRune.getUnlocalizedName());
		
		//Talismans
		GameRegistry.registerItem(airTalisman, airTalisman.getUnlocalizedName());
		
	}
	
	
	public static void craftingRecipes(){
		GameRegistry.addShapelessRecipe(new ItemStack(airTalisman), airRune, Item.getItemById(280));
		
		
	}
	
}

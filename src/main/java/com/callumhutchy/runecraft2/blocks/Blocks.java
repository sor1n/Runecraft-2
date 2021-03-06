package com.callumhutchy.runecraft2.blocks;

import java.util.Random;

import reference.BlockNames;

import com.callumhutchy.runecraft2.Runecraft2;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntityCopperOre;
import com.callumhutchy.runecraft2.items.Items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class Blocks {

	public static Random	random;

	// Altar

	// No soul altar not in RS
	public static Block		AirAltar, WaterAltar, FireAltar, EarthAltar,
			MindAltar, BodyAltar, CosmicAltar, ChaosAltar, NatureAltar,
			LawAltar, DeathAltar, BloodAltar, AstralAltar;

	public static Block		AirRuneAltar, WaterRuneAltar, FireRuneAltar,
			EarthRuneAltar, MindRuneAltar, BodyRuneAltar, CosmicRuneAltar,
			ChaosRuneAltar, NatureRuneAltar, LawRuneAltar, DeathRuneAltar,
			BloodRuneAltar, AstralRuneAltar;

	// Ores
	public static Block		CopperOre, TinOre, AdamantiteOre, CoalOre, GoldOre,
			IronOre, MithrilOre, RuniteOre, SilverOre;

	public static Block		RuneEssenceOre, PureEssenceOre;
	
	public static Block RCFurnace, Anvil;


	public static void init() {
		// Altar

		AirAltar = new Altar(Material.rock, "runecraft2:textures/blocks/AirAltarTexture.png", BlockNames.AirAltar_Unlo, "airaltar");
		WaterAltar = new Altar(Material.rock, "runecraft2:textures/blocks/WaterAltarTexture.png", BlockNames.WaterAltar_Unlo, "wateraltar");
		FireAltar = new Altar(Material.rock, "runecraft2:textures/blocks/FireAltarTexture.png", BlockNames.FireAltar_Unlo, "firealtar");
		EarthAltar = new Altar(Material.rock, "runecraft2:textures/blocks/EarthAltarTexture.png", BlockNames.EarthAltar_Unlo, "earthaltar");
		MindAltar = new Altar(Material.rock, "runecraft2:textures/blocks/MindAltarTexture.png", BlockNames.MindAltar_Unlo, "mindaltar");
		BodyAltar = new Altar(Material.rock, "runecraft2:textures/blocks/BodyAltarTexture.png", BlockNames.BodyAltar_Unlo, "bodyaltar");
		CosmicAltar = new Altar(Material.rock, "runecraft2:textures/blocks/CosmicAltarTexture.png", BlockNames.CosmicAltar_Unlo, "cosmicaltar");
		ChaosAltar = new Altar(Material.rock, "runecraft2:textures/blocks/ChaosAltarTexture.png", BlockNames.ChaosAltar_Unlo, "chaosaltar");
		NatureAltar = new Altar(Material.rock, "runecraft2:textures/blocks/NatureAltarTexture.png", BlockNames.NatureAltar_Unlo, "naturealtar");
		LawAltar = new Altar(Material.rock, "runecraft2:textures/blocks/LawAltarTexture.png", BlockNames.LawAltar_Unlo, "lawaltar");
		DeathAltar = new Altar(Material.rock, "runecraft2:textures/blocks/DeathAltarTexture.png", BlockNames.DeathAltar_Unlo, "deathaltar");
		BloodAltar = new Altar(Material.rock, "runecraft2:textures/blocks/BloodAltarTexture.png", BlockNames.BloodAltar_Unlo, "bloodaltar");
		AstralAltar = new Altar(Material.rock, "runecraft2:textures/blocks/AstralAltarTexture.png", BlockNames.AstralAltar_Unlo, "astralaltar");

		AirRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/AirRuneAltarTexture.png", BlockNames.AirRuneAltar_Unlo, "airrunealtar");
		WaterRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/WaterRuneAltarTexture.png", BlockNames.WaterRuneAltar_Unlo, "waterrunealtar");
		FireRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/FireRuneAltarTexture.png", BlockNames.FireRuneAltar_Unlo, "firerunealtar");
		EarthRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/EarthRuneAltarTexture.png", BlockNames.EarthRuneAltar_Unlo, "earthrunealtar");
		MindRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/MindRuneAltarTexture.png", BlockNames.MindRuneAltar_Unlo, "mindrunealtar");
		BodyRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/BodyRuneAltarTexture.png", BlockNames.BodyRuneAltar_Unlo, "bodyrunealtar");
		CosmicRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/CosmicRuneAltarTexture.png", BlockNames.CosmicRuneAltar_Unlo, "cosmicrunealtar");
		ChaosRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/ChaosRuneAltarTexture.png", BlockNames.ChaosRuneAltar_Unlo, "chaosrunealtar");
		NatureRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/NatureRuneAltarTexture.png", BlockNames.NatureRuneAltar_Unlo, "naturerunealtar");
		LawRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/LawRuneAltarTexture.png", BlockNames.LawRuneAltar_Unlo, "lawrunealtar");
		DeathRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/DeathRuneAltarTexture.png", BlockNames.DeathRuneAltar_Unlo, "deathrunealtar");
		BloodRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/BloodRuneAltarTexture.png", BlockNames.BloodRuneAltar_Unlo, "bloodrunealtar");
		AstralRuneAltar = new Altar(Material.rock, "runecraft2:textures/blocks/AstralRuneAltarTexture.png", BlockNames.AstralRuneAltar_Unlo, "astralrunealtar");

		// Ores
		CopperOre = new OreBlock(Material.rock, "runecraft2:OreModelCopperTexture", BlockNames.CopperOre_Unlo, Items.copperOre, "copper");
		TinOre = new OreBlock(Material.rock, "runecraft2:OreModelTinTexture", BlockNames.TinOre_Unlo, Items.tinOre, "tin");
		AdamantiteOre = new OreBlock(Material.rock, "runecraft2:reModelAdamantiteTexture", BlockNames.AdamantiteOre_Unlo, Items.adamantiteOre, "adamantite");
		CoalOre = new OreBlock(Material.rock, "runecraft2:OreModelCoalTexture", BlockNames.CoalOre_Unlo, Items.coalOre, "coal");
		GoldOre = new OreBlock(Material.rock, "runecraft2:OreModelGoldTexture", BlockNames.GoldOre_Unlo, Items.goldOre, "gold");
		IronOre = new OreBlock(Material.rock, "runecraft2:OreModelIronTexture", BlockNames.IronOre_Unlo, Items.ironOre, "iron");
		MithrilOre = new OreBlock(Material.rock, "runecraft2:OreModelMithrilTexture", BlockNames.MithrilOre_Unlo, Items.mithrilOre, "mithril");
		RuniteOre = new OreBlock(Material.rock, "runecraft2:OreModelRuniteTexture", BlockNames.RuniteOre_Unlo, Items.runiteOre, "runite");
		SilverOre = new OreBlock(Material.rock, "runecraft2:OreModelSilverTexture", BlockNames.SilverOre_Unlo, Items.silverOre, "silver");

		RuneEssenceOre = new OreBlock(Material.rock, "runecraft2:OreModelRuneEssenceTexture", BlockNames.RuneEssenceOre_Unlo, Items.runeEssence, "runeessence");
		PureEssenceOre = new OreBlock(Material.rock, "runecraft2:OreModelPureEssenceTexture", BlockNames.PureEssenceOre_Unlo, Items.pureEssence, "pureessence");

		RCFurnace = new RCFurnace(Material.iron);
		Anvil= new Anvil(Material.iron);
		
	
	}

	public static void gameRegistry() {
		// Altars

		GameRegistry.registerBlock(AirAltar, BlockNames.AirAltar_Unlo);
		GameRegistry.registerBlock(WaterAltar, BlockNames.WaterAltar_Unlo);
		GameRegistry.registerBlock(FireAltar, BlockNames.FireAltar_Unlo);
		GameRegistry.registerBlock(EarthAltar, BlockNames.EarthAltar_Unlo);
		GameRegistry.registerBlock(MindAltar, BlockNames.MindAltar_Unlo);
		GameRegistry.registerBlock(BodyAltar, BlockNames.BodyAltar_Unlo);
		GameRegistry.registerBlock(CosmicAltar, BlockNames.CosmicAltar_Unlo);
		GameRegistry.registerBlock(ChaosAltar, BlockNames.ChaosAltar_Unlo);
		GameRegistry.registerBlock(NatureAltar, BlockNames.NatureAltar_Unlo);
		GameRegistry.registerBlock(LawAltar, BlockNames.LawAltar_Unlo);
		GameRegistry.registerBlock(DeathAltar, BlockNames.DeathAltar_Unlo);
		GameRegistry.registerBlock(BloodAltar, BlockNames.BloodAltar_Unlo);
		GameRegistry.registerBlock(AstralAltar, BlockNames.AstralAltar_Unlo);

		GameRegistry.registerBlock(AirRuneAltar, BlockNames.AirRuneAltar_Unlo);
		GameRegistry.registerBlock(WaterRuneAltar, BlockNames.WaterRuneAltar_Unlo);
		GameRegistry.registerBlock(FireRuneAltar, BlockNames.FireRuneAltar_Unlo);
		GameRegistry.registerBlock(EarthRuneAltar, BlockNames.EarthRuneAltar_Unlo);
		GameRegistry.registerBlock(MindRuneAltar, BlockNames.MindRuneAltar_Unlo);
		GameRegistry.registerBlock(BodyRuneAltar, BlockNames.BodyRuneAltar_Unlo);
		GameRegistry.registerBlock(CosmicRuneAltar, BlockNames.CosmicRuneAltar_Unlo);
		GameRegistry.registerBlock(ChaosRuneAltar, BlockNames.ChaosRuneAltar_Unlo);
		GameRegistry.registerBlock(NatureRuneAltar, BlockNames.NatureRuneAltar_Unlo);
		GameRegistry.registerBlock(LawRuneAltar, BlockNames.LawRuneAltar_Unlo);
		GameRegistry.registerBlock(DeathRuneAltar, BlockNames.DeathRuneAltar_Unlo);
		GameRegistry.registerBlock(BloodRuneAltar, BlockNames.BloodRuneAltar_Unlo);
		GameRegistry.registerBlock(AstralRuneAltar, BlockNames.AstralRuneAltar_Unlo);

		// Ores
		GameRegistry.registerBlock(CopperOre, BlockNames.CopperOre_Unlo);
		GameRegistry.registerBlock(TinOre, BlockNames.TinOre_Unlo);
		GameRegistry.registerBlock(AdamantiteOre, BlockNames.AdamantiteOre_Unlo);
		GameRegistry.registerBlock(CoalOre, BlockNames.CoalOre_Unlo);
		GameRegistry.registerBlock(GoldOre, BlockNames.GoldOre_Unlo);
		GameRegistry.registerBlock(IronOre, BlockNames.IronOre_Unlo);
		GameRegistry.registerBlock(MithrilOre, BlockNames.MithrilOre_Unlo);
		GameRegistry.registerBlock(RuniteOre, BlockNames.RuniteOre_Unlo);
		GameRegistry.registerBlock(SilverOre, BlockNames.SilverOre_Unlo);
		
		GameRegistry.registerBlock(RuneEssenceOre, BlockNames.RuneEssenceOre_Unlo);
		GameRegistry.registerBlock(PureEssenceOre, BlockNames.PureEssenceOre_Unlo);
		
		GameRegistry.registerBlock(RCFurnace, BlockNames.RCFurnace_Unlo);
		GameRegistry.registerBlock(Anvil, BlockNames.Anvil_Unlo);
		
	
	}

}

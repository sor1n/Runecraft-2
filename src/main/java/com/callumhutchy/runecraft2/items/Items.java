package com.callumhutchy.runecraft2.items;

import com.callumhutchy.runecraft2.Runecraft2;
import reference.Materials;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import reference.ItemNames;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

public class Items {

	// Runes
	public static Item	airRune, armadylRune, astralRune, bloodRune, bodyRune,
			chaosRune, cosmicRune, deathRune, dustRune, earthRune, fireRune,
			lavaRune, lawRune, mindRune, mistRune, mudRune, natureRune,
			smokeRune, soulRune, steamRune, waterRune;

	public static Item	runeEssence, pureEssence;

	// Talismans
	public static Item	airTalisman, waterTalisman, fireTalisman,
			earthTalisman, mindTalisman, bodyTalisman, cosmicTalisman,
			chaosTalisman, natureTalisman, lawTalisman, deathTalisman,
			bloodTalisman, astralTalisman, soulTalisman;

	public static Item	airTalismanStaff, waterTalismanStaff,
			fireTalismanStaff, earthTalismanStaff, mindTalismanStaff,
			bodyTalismanStaff, cosmicTalismanStaff, chaosTalismanStaff,
			natureTalismanStaff, lawTalismanStaff, deathTalismanStaff,
			bloodTalismanStaff;

	// Ores
	public static Item	adamantiteOre, coalOre, copperOre, goldOre, ironOre,
			mithrilOre, runiteOre, silverOre, tinOre;

	// Bars
	public static Item	bronzeBar, ironBar, silverBar, steelBar, goldBar,
			mithrilBar, adamantBar, runeBar;

	public static Item	spellbook;

	public static Item	staff, staffofair, staffofwater, staffofearth,
			staffoffire;

	// Hatchets
	public static Item	bronzehatchet, ironhatchet, steelhatchet, blackhatchet,
			mithrilhatchet, adamanthatchet, runehatchet;

	// Pickaxes
	public static Item	bronzepickaxe, ironpickaxe, steelpickaxe,
			mithrilpickaxe, adamantpickaxe, runepickaxe;

	public static void init() {
		// Runes
		airRune = new Rune(ItemNames.AirRune_Unlo);
		armadylRune = new Rune(ItemNames.ArmadylRune_Unlo);
		astralRune = new Rune(ItemNames.AstralRune_Unlo);
		bloodRune = new Rune(ItemNames.BloodRune_Unlo);
		bodyRune = new Rune(ItemNames.BodyRune_Unlo);
		chaosRune = new Rune(ItemNames.ChaosRune_Unlo);
		cosmicRune = new Rune(ItemNames.CosmicRune_Unlo);
		deathRune = new Rune(ItemNames.DeathRune_Unlo);
		dustRune = new Rune(ItemNames.DustRune_Unlo);
		earthRune = new Rune(ItemNames.EarthRune_Unlo);
		fireRune = new Rune(ItemNames.FireRune_Unlo);
		lavaRune = new Rune(ItemNames.LavaRune_Unlo);
		lawRune = new Rune(ItemNames.LawRune_Unlo);
		mindRune = new Rune(ItemNames.MindRune_Unlo);
		mistRune = new Rune(ItemNames.MistRune_Unlo);
		mudRune = new Rune(ItemNames.MudRune_Unlo);
		natureRune = new Rune(ItemNames.NatureRune_Unlo);
		smokeRune = new Rune(ItemNames.SmokeRune_Unlo);
		soulRune = new Rune(ItemNames.SoulRune_Unlo);
		steamRune = new Rune(ItemNames.SteamRune_Unlo);
		waterRune = new Rune(ItemNames.WaterRune_Unlo);

		runeEssence = new Rune(ItemNames.RuneEssence_Unlo);
		pureEssence = new Rune(ItemNames.PureEssence_Unlo);
		// Talismans
		airTalisman = new BaseMagicItem(ItemNames.AirTalisman_Unlo, 1);
		waterTalisman = new BaseMagicItem(ItemNames.WaterTalisman_Unlo, 1);
		fireTalisman = new BaseMagicItem(ItemNames.FireTalisman_Unlo, 1);
		earthTalisman = new BaseMagicItem(ItemNames.EarthTalisman_Unlo, 1);
		mindTalisman = new BaseMagicItem(ItemNames.MindTalisman_Unlo, 1);
		bodyTalisman = new BaseMagicItem(ItemNames.BodyTalisman_Unlo, 1);
		cosmicTalisman = new BaseMagicItem(ItemNames.CosmicTalisman_Unlo, 1);
		chaosTalisman = new BaseMagicItem(ItemNames.ChaosTalisman_Unlo, 1);
		natureTalisman = new BaseMagicItem(ItemNames.NatureTalisman_Unlo, 1);
		lawTalisman = new BaseMagicItem(ItemNames.LawTalisman_Unlo, 1);
		deathTalisman = new BaseMagicItem(ItemNames.DeathTalisman_Unlo, 1);
		bloodTalisman = new BaseMagicItem(ItemNames.BloodTalisman_Unlo, 1);
		astralTalisman = new BaseMagicItem(ItemNames.AstralTalisman_Unlo, 1);

		airTalismanStaff = new Staff(ItemNames.AirTalismanStaff_Unlo, 1, null, 0, true);
		waterTalismanStaff = new Staff(ItemNames.WaterTalismanStaff_Unlo, 1, null, 0, true);
		fireTalismanStaff = new Staff(ItemNames.FireTalismanStaff_Unlo, 1, null, 0, true);
		earthTalismanStaff = new Staff(ItemNames.EarthTalismanStaff_Unlo, 1, null, 0, true);
		mindTalismanStaff = new Staff(ItemNames.MindTalismanStaff_Unlo, 1, null, 0, true);
		bodyTalismanStaff = new Staff(ItemNames.BodyTalismanStaff_Unlo, 1, null, 0, true);
		cosmicTalismanStaff = new Staff(ItemNames.CosmicTalismanStaff_Unlo, 1, null, 0, true);
		chaosTalismanStaff = new Staff(ItemNames.ChaosTalismanStaff_Unlo, 1, null, 0, true);
		natureTalismanStaff = new Staff(ItemNames.NatureTalismanStaff_Unlo, 1, null, 0, true);
		lawTalismanStaff = new Staff(ItemNames.LawTalismanStaff_Unlo, 1, null, 0, true);
		deathTalismanStaff = new Staff(ItemNames.DeathTalismanStaff_Unlo, 1, null, 0, true);
		bloodTalismanStaff = new Staff(ItemNames.BloodTalismanStaff_Unlo, 1, null, 0, true);

		// Ores
		adamantiteOre = new Ore().setUnlocalizedName(ItemNames.AdamantiteOre_Unlo);
		coalOre = new Ore().setUnlocalizedName(ItemNames.CoalOre_Unlo);
		copperOre = new Ore().setUnlocalizedName(ItemNames.CopperOre_Unlo);
		goldOre = new Ore().setUnlocalizedName(ItemNames.GoldOre_Unlo);
		ironOre = new Ore().setUnlocalizedName(ItemNames.IronOre_Unlo);
		mithrilOre = new Ore().setUnlocalizedName(ItemNames.MithrilOre_Unlo);
		runiteOre = new Ore().setUnlocalizedName(ItemNames.RuniteOre_Unlo);
		silverOre = new Ore().setUnlocalizedName(ItemNames.SilverOre_Unlo);
		tinOre = new Ore().setUnlocalizedName(ItemNames.TinOre_Unlo);

		// Bars
		bronzeBar = new Bar().setUnlocalizedName(ItemNames.BronzeBar_Unlo);
		ironBar = new Bar().setUnlocalizedName(ItemNames.IronBar_Unlo);
		silverBar = new Bar().setUnlocalizedName(ItemNames.SilverBar_Unlo);
		steelBar = new Bar().setUnlocalizedName(ItemNames.SteelBar_Unlo);
		goldBar = new Bar().setUnlocalizedName(ItemNames.GoldBar_Unlo);
		mithrilBar = new Bar().setUnlocalizedName(ItemNames.MithrilBar_Unlo);
		adamantBar = new Bar().setUnlocalizedName(ItemNames.AdamantBar_Unlo);
		runeBar = new Bar().setUnlocalizedName(ItemNames.RuneBar_Unlo);

		spellbook = new SpellBook(ItemNames.SpellBook_Unlo, 1);
		// Staves
		staff = new Staff(ItemNames.Staff_Unlo, 1, null, 0, false);
		staffofair = new Staff(ItemNames.StaffOfAir_Unlo, 1, "air", 1, false);
		staffofwater = new Staff(ItemNames.StaffOfWater_Unlo, 1, "water", 1, false);
		staffofearth = new Staff(ItemNames.StaffOfEarth_Unlo, 1, "earth", 1, false);
		staffoffire = new Staff(ItemNames.StaffOfFire_Unlo, 1, "fire", 1, false);

		// Hatchets
		bronzehatchet = new Hatchet(Materials.bronze, ItemNames.BronzeHatchet_Unlo);
		ironhatchet = new Hatchet(Materials.iron, ItemNames.IronHatchet_Unlo);
		steelhatchet = new Hatchet(Materials.steel, ItemNames.SteelHatchet_Unlo);
		blackhatchet = new Hatchet(Materials.black, ItemNames.BlackHatchet_Unlo);
		mithrilhatchet = new Hatchet(Materials.mithril, ItemNames.MithrilHatchet_Unlo);
		adamanthatchet = new Hatchet(Materials.adamant, ItemNames.AdamantHatchet_Unlo);
		runehatchet = new Hatchet(Materials.rune, ItemNames.RuneHatchet_Unlo);
		
		//Pickaxes
		bronzepickaxe = new Pickaxe(Materials.bronze, ItemNames.BronzePickaxe_Unlo);
		ironpickaxe = new Pickaxe(Materials.iron, ItemNames.IronPickaxe_Unlo);
		steelpickaxe = new Pickaxe(Materials.steel, ItemNames.SteelPickaxe_Unlo);
		mithrilpickaxe = new Pickaxe(Materials.mithril, ItemNames.MithrilPickaxe_Unlo);
		adamantpickaxe = new Pickaxe(Materials.adamant, ItemNames.AdamantPickaxe_Unlo);
		runepickaxe = new Pickaxe(Materials.rune, ItemNames.RunePickaxe_Unlo);

	}

	public static void gameRegistery() {
		// Runes
		GameRegistry.registerItem(airRune, airRune.getUnlocalizedName());
		GameRegistry.registerItem(armadylRune, armadylRune.getUnlocalizedName());
		GameRegistry.registerItem(astralRune, astralRune.getUnlocalizedName());
		GameRegistry.registerItem(bloodRune, bloodRune.getUnlocalizedName());
		GameRegistry.registerItem(bodyRune, bodyRune.getUnlocalizedName());
		GameRegistry.registerItem(chaosRune, chaosRune.getUnlocalizedName());
		GameRegistry.registerItem(cosmicRune, cosmicRune.getUnlocalizedName());
		GameRegistry.registerItem(deathRune, deathRune.getUnlocalizedName());
		GameRegistry.registerItem(dustRune, dustRune.getUnlocalizedName());
		GameRegistry.registerItem(earthRune, earthRune.getUnlocalizedName());
		GameRegistry.registerItem(fireRune, fireRune.getUnlocalizedName());
		GameRegistry.registerItem(lavaRune, lavaRune.getUnlocalizedName());
		GameRegistry.registerItem(lawRune, lawRune.getUnlocalizedName());
		GameRegistry.registerItem(mindRune, mindRune.getUnlocalizedName());
		GameRegistry.registerItem(mistRune, mistRune.getUnlocalizedName());
		GameRegistry.registerItem(mudRune, mudRune.getUnlocalizedName());
		GameRegistry.registerItem(natureRune, natureRune.getUnlocalizedName());
		GameRegistry.registerItem(smokeRune, smokeRune.getUnlocalizedName());
		GameRegistry.registerItem(soulRune, soulRune.getUnlocalizedName());
		GameRegistry.registerItem(steamRune, steamRune.getUnlocalizedName());
		GameRegistry.registerItem(waterRune, waterRune.getUnlocalizedName());

		GameRegistry.registerItem(runeEssence, runeEssence.getUnlocalizedName());
		GameRegistry.registerItem(pureEssence, pureEssence.getUnlocalizedName());

		// Talismans
		GameRegistry.registerItem(airTalisman, airTalisman.getUnlocalizedName());
		GameRegistry.registerItem(waterTalisman, waterTalisman.getUnlocalizedName());
		GameRegistry.registerItem(fireTalisman, fireTalisman.getUnlocalizedName());
		GameRegistry.registerItem(earthTalisman, earthTalisman.getUnlocalizedName());
		GameRegistry.registerItem(mindTalisman, mindTalisman.getUnlocalizedName());
		GameRegistry.registerItem(bodyTalisman, bodyTalisman.getUnlocalizedName());
		GameRegistry.registerItem(cosmicTalisman, cosmicTalisman.getUnlocalizedName());
		GameRegistry.registerItem(chaosTalisman, chaosTalisman.getUnlocalizedName());
		GameRegistry.registerItem(natureTalisman, natureTalisman.getUnlocalizedName());
		GameRegistry.registerItem(lawTalisman, lawTalisman.getUnlocalizedName());
		GameRegistry.registerItem(deathTalisman, deathTalisman.getUnlocalizedName());
		GameRegistry.registerItem(bloodTalisman, bloodTalisman.getUnlocalizedName());
		GameRegistry.registerItem(astralTalisman, astralTalisman.getUnlocalizedName());

		GameRegistry.registerItem(airTalismanStaff, airTalismanStaff.getUnlocalizedName());
		GameRegistry.registerItem(waterTalismanStaff, waterTalismanStaff.getUnlocalizedName());
		GameRegistry.registerItem(fireTalismanStaff, fireTalismanStaff.getUnlocalizedName());
		GameRegistry.registerItem(earthTalismanStaff, earthTalismanStaff.getUnlocalizedName());
		GameRegistry.registerItem(mindTalismanStaff, mindTalismanStaff.getUnlocalizedName());
		GameRegistry.registerItem(bodyTalismanStaff, bodyTalismanStaff.getUnlocalizedName());
		GameRegistry.registerItem(cosmicTalismanStaff, cosmicTalismanStaff.getUnlocalizedName());
		GameRegistry.registerItem(chaosTalismanStaff, chaosTalismanStaff.getUnlocalizedName());
		GameRegistry.registerItem(natureTalismanStaff, natureTalismanStaff.getUnlocalizedName());
		GameRegistry.registerItem(lawTalismanStaff, lawTalismanStaff.getUnlocalizedName());
		GameRegistry.registerItem(deathTalismanStaff, deathTalismanStaff.getUnlocalizedName());
		GameRegistry.registerItem(bloodTalismanStaff, bloodTalismanStaff.getUnlocalizedName());

		// Ores
		GameRegistry.registerItem(adamantiteOre, adamantiteOre.getUnlocalizedName());
		GameRegistry.registerItem(coalOre, coalOre.getUnlocalizedName());
		GameRegistry.registerItem(copperOre, copperOre.getUnlocalizedName());
		GameRegistry.registerItem(goldOre, goldOre.getUnlocalizedName());
		GameRegistry.registerItem(ironOre, ironOre.getUnlocalizedName());
		GameRegistry.registerItem(mithrilOre, mithrilOre.getUnlocalizedName());
		GameRegistry.registerItem(runiteOre, runiteOre.getUnlocalizedName());
		GameRegistry.registerItem(silverOre, silverOre.getUnlocalizedName());
		GameRegistry.registerItem(tinOre, tinOre.getUnlocalizedName());

		// Bars
		GameRegistry.registerItem(bronzeBar, bronzeBar.getUnlocalizedName());
		GameRegistry.registerItem(ironBar, ironBar.getUnlocalizedName());
		GameRegistry.registerItem(silverBar, silverBar.getUnlocalizedName());
		GameRegistry.registerItem(steelBar, steelBar.getUnlocalizedName());
		GameRegistry.registerItem(goldBar, goldBar.getUnlocalizedName());
		GameRegistry.registerItem(mithrilBar, mithrilBar.getUnlocalizedName());
		GameRegistry.registerItem(adamantBar, adamantBar.getUnlocalizedName());
		GameRegistry.registerItem(runeBar, runeBar.getUnlocalizedName());

		GameRegistry.registerItem(spellbook, spellbook.getUnlocalizedName());

		// Staves
		GameRegistry.registerItem(staff, staff.getUnlocalizedName());
		GameRegistry.registerItem(staffofair, staffofair.getUnlocalizedName());
		GameRegistry.registerItem(staffofwater, staffofwater.getUnlocalizedName());
		GameRegistry.registerItem(staffofearth, staffofearth.getUnlocalizedName());
		GameRegistry.registerItem(staffoffire, staffoffire.getUnlocalizedName());

		// Hatchet
		GameRegistry.registerItem(bronzehatchet, bronzehatchet.getUnlocalizedName());
		GameRegistry.registerItem(ironhatchet, ironhatchet.getUnlocalizedName());
		GameRegistry.registerItem(steelhatchet, steelhatchet.getUnlocalizedName());
		GameRegistry.registerItem(blackhatchet, blackhatchet.getUnlocalizedName());
		GameRegistry.registerItem(mithrilhatchet, mithrilhatchet.getUnlocalizedName());
		GameRegistry.registerItem(adamanthatchet, adamanthatchet.getUnlocalizedName());
		GameRegistry.registerItem(runehatchet, runehatchet.getUnlocalizedName());

		//Pickaxes
		GameRegistry.registerItem(bronzepickaxe, bronzepickaxe.getUnlocalizedName());
		GameRegistry.registerItem(ironpickaxe, ironpickaxe.getUnlocalizedName());
		GameRegistry.registerItem(steelpickaxe, steelpickaxe.getUnlocalizedName());
		GameRegistry.registerItem(mithrilpickaxe, mithrilpickaxe.getUnlocalizedName());
		GameRegistry.registerItem(adamantpickaxe, adamantpickaxe.getUnlocalizedName());
		GameRegistry.registerItem(runepickaxe, runepickaxe.getUnlocalizedName());
		
	}

	public static void craftingRecipes() {
		// Talismans
		GameRegistry.addShapelessRecipe(new ItemStack(airTalisman), airRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(waterTalisman), waterRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(fireTalisman), fireRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(earthTalisman), earthRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(mindTalisman), mindRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(bodyTalisman), bodyRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(cosmicTalisman), cosmicRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(chaosTalisman), chaosRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(natureTalisman), natureRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(lawTalisman), lawRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(deathTalisman), deathRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(bloodTalisman), bloodRune, Item.getItemById(280));
		GameRegistry.addShapelessRecipe(new ItemStack(astralTalisman), astralRune, Item.getItemById(280));

		GameRegistry.addShapelessRecipe(new ItemStack(spellbook), Item.getItemById(340), runeEssence);

		// Staffs
		GameRegistry.addShapedRecipe(new ItemStack(staff), new Object[] { "OOX", "OXO", "XOO", 'X', Item.getItemById(280) });
		GameRegistry.addShapedRecipe(new ItemStack(staffofair), new Object[] { "OZ", "XO", 'X', staff, 'Z', airRune });
		GameRegistry.addShapedRecipe(new ItemStack(staffofwater), new Object[] { "OZ", "XO", 'X', staff, 'Z', waterRune });
		GameRegistry.addShapedRecipe(new ItemStack(staffofearth), new Object[] { "OZ", "XO", 'X', staff, 'Z', earthRune });
		GameRegistry.addShapedRecipe(new ItemStack(staffoffire), new Object[] { "OZ", "XO", 'X', staff, 'Z', fireRune });

		// Talisman Staff
		GameRegistry.addShapelessRecipe(new ItemStack(airTalismanStaff), staff, airTalisman);
		GameRegistry.addShapelessRecipe(new ItemStack(waterTalismanStaff), staff, waterTalisman);
		GameRegistry.addShapelessRecipe(new ItemStack(fireTalismanStaff), staff, fireTalisman);
		GameRegistry.addShapelessRecipe(new ItemStack(earthTalismanStaff), staff, earthTalisman);
		GameRegistry.addShapelessRecipe(new ItemStack(mindTalismanStaff), staff, mindTalisman);
		GameRegistry.addShapelessRecipe(new ItemStack(bodyTalismanStaff), staff, bodyTalisman);
		GameRegistry.addShapelessRecipe(new ItemStack(cosmicTalismanStaff), staff, cosmicTalisman);
		GameRegistry.addShapelessRecipe(new ItemStack(chaosTalismanStaff), staff, chaosTalisman);
		GameRegistry.addShapelessRecipe(new ItemStack(natureTalismanStaff), staff, natureTalisman);
		GameRegistry.addShapelessRecipe(new ItemStack(lawTalismanStaff), staff, lawTalisman);
		GameRegistry.addShapelessRecipe(new ItemStack(deathTalismanStaff), staff, deathTalisman);
		GameRegistry.addShapelessRecipe(new ItemStack(bloodTalismanStaff), staff, bloodTalisman);

	}

}

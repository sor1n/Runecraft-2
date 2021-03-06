package reference;

import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityAnvil;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityRCFurnace;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityAirAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityAirRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityBloodAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityBloodRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityBodyAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityBodyRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityChaosAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityChaosRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityCosmicAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityCosmicRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityDeathAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityDeathRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityEarthAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityEarthRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityFireAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityFireRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityLawAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityLawRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityMindAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityMindRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityNatureAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityNatureRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityWaterAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityWaterRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntityAdamantiteOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntityCoalOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntityCopperOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntityGoldOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntityIronOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntityMithrilOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntityPureEssenceOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntityRuneEssenceOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntityRuniteOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntitySilverOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.ores.TileEntityTinOre;
import com.callumhutchy.runecraft2.client.gui.tileentities.TileEntityRCFurnaceGui;
import com.callumhutchy.runecraft2.client.gui.tileentities.TileEntitySkills;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntities {

	public static void init(){
		//Altars (base)
		GameRegistry.registerTileEntity(TileEntityAirAltar.class, "teairaltar");
		GameRegistry.registerTileEntity(TileEntityBloodAltar.class, "tebloodaltar");
		GameRegistry.registerTileEntity(TileEntityBodyAltar.class, "tebodyaltar");
		GameRegistry.registerTileEntity(TileEntityChaosAltar.class, "techaosaltar");
		GameRegistry.registerTileEntity(TileEntityCosmicAltar.class, "tecosmicaltar");
		GameRegistry.registerTileEntity(TileEntityDeathAltar.class, "tedeathaltar");
		GameRegistry.registerTileEntity(TileEntityEarthAltar.class, "teearthaltar");
		GameRegistry.registerTileEntity(TileEntityFireAltar.class, "tefirealtar");
		GameRegistry.registerTileEntity(TileEntityLawAltar.class, "telawaltar");
		GameRegistry.registerTileEntity(TileEntityMindAltar.class, "temindaltar");
		GameRegistry.registerTileEntity(TileEntityNatureAltar.class, "tenaturealtar");
		GameRegistry.registerTileEntity(TileEntityWaterAltar.class, "tewateraltar");
		
		//Altars (rune)
		GameRegistry.registerTileEntity(TileEntityAirRuneAltar.class, "teairrunealtar");
		GameRegistry.registerTileEntity(TileEntityBloodRuneAltar.class, "tebloodrunealtar");
		GameRegistry.registerTileEntity(TileEntityBodyRuneAltar.class, "tebodyrunealtar");
		GameRegistry.registerTileEntity(TileEntityChaosRuneAltar.class, "techaosrunealtar");
		GameRegistry.registerTileEntity(TileEntityCosmicRuneAltar.class, "tecosmicrunealtar");
		GameRegistry.registerTileEntity(TileEntityDeathRuneAltar.class, "tedeathrunealtar");
		GameRegistry.registerTileEntity(TileEntityEarthRuneAltar.class, "teearthrunealtar");
		GameRegistry.registerTileEntity(TileEntityFireRuneAltar.class, "tefirerunealtar");
		GameRegistry.registerTileEntity(TileEntityLawRuneAltar.class, "telawrunealtar");
		GameRegistry.registerTileEntity(TileEntityMindRuneAltar.class, "temindrunealtar");
		GameRegistry.registerTileEntity(TileEntityNatureRuneAltar.class, "tenaturerunealtar");
		GameRegistry.registerTileEntity(TileEntityWaterRuneAltar.class, "tewaterrunealtar");
		
		//Guis
		GameRegistry.registerTileEntity(TileEntitySkills.class, "teskillsgui");
		//GameRegistry.registerTileEntity(TileEntityRCFurnace.class, "tercfurnacegui");
		
		//Spells
		
		//Ores
		GameRegistry.registerTileEntity(TileEntityAdamantiteOre.class, "teadamantiteore");
		GameRegistry.registerTileEntity(TileEntityCoalOre.class, "tecoalore");
		GameRegistry.registerTileEntity(TileEntityCopperOre.class, "tecopperore");
		GameRegistry.registerTileEntity(TileEntityGoldOre.class, "tegoldore");
		GameRegistry.registerTileEntity(TileEntityIronOre.class, "teironore");
		GameRegistry.registerTileEntity(TileEntityMithrilOre.class, "temithrilore");
		GameRegistry.registerTileEntity(TileEntityPureEssenceOre.class, "tepureessenceore");
		GameRegistry.registerTileEntity(TileEntityRuneEssenceOre.class, "teruneessenceore");
		GameRegistry.registerTileEntity(TileEntityRuniteOre.class, "teruniteore");
		GameRegistry.registerTileEntity(TileEntitySilverOre.class, "tesilverore");
		GameRegistry.registerTileEntity(TileEntityTinOre.class, "tetinore");
		
		GameRegistry.registerTileEntity(TileEntityRCFurnace.class, "tercfurnace");
		GameRegistry.registerTileEntity(TileEntityAnvil.class, "teanvil");
		
	}
}

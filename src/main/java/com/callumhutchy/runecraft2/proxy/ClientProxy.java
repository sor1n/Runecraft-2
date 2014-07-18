package com.callumhutchy.runecraft2.proxy;

import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityAdamantiteOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityCoalOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityCopperOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityGoldOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityIronOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityMithrilOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityRuniteOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntitySilverOre;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityTinOre;
import com.callumhutchy.runecraft2.blocks.renderer.AltarRenderer;
import com.callumhutchy.runecraft2.blocks.renderer.ore.AdamantiteOreRenderer;
import com.callumhutchy.runecraft2.blocks.renderer.ore.CoalOreRenderer;
import com.callumhutchy.runecraft2.blocks.renderer.ore.CopperOreRenderer;
import com.callumhutchy.runecraft2.blocks.renderer.ore.GoldOreRenderer;
import com.callumhutchy.runecraft2.blocks.renderer.ore.IronOreRenderer;
import com.callumhutchy.runecraft2.blocks.renderer.ore.MithrilOreRenderer;
import com.callumhutchy.runecraft2.blocks.renderer.ore.OreRenderer;
import com.callumhutchy.runecraft2.blocks.renderer.ore.RuniteOreRenderer;
import com.callumhutchy.runecraft2.blocks.renderer.ore.SilverOreRenderer;
import com.callumhutchy.runecraft2.blocks.renderer.ore.TinOreRenderer;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	public void registerRenderThings() {
        
		//Altars
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltar.class, new AltarRenderer());
        
		
        //Ores
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTinOre.class, new TinOreRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCopperOre.class, new CopperOreRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdamantiteOre.class, new AdamantiteOreRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoalOre.class, new CoalOreRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGoldOre.class, new GoldOreRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIronOre.class, new IronOreRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMithrilOre.class, new MithrilOreRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRuniteOre.class, new RuniteOreRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySilverOre.class, new SilverOreRenderer());
	
	}
	
}

package com.callumhutchy.runecraft2.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.callumhutchy.runecraft2.blocks.Blocks;
import com.jcraft.jorbis.Block;

public class WorldGenOre extends WorldGenerator {
	@Override
	public boolean generate(World world, Random random, int varX, int varY, int varZ) {

		while (world.isAirBlock(varX, varY, varZ) && varY > 2) {
			--varY;
		}

		varY++;
		int Xreset = varX;
		int Yreset = varY;
		int Zreset = varZ;
		if(world.getBlock(varX, varY - 1, varZ) != net.minecraft.init.Blocks.water){
			if(world.getBlock(varX, varY - 1, varZ) == net.minecraft.init.Blocks.tallgrass){
				varY--;
			}
			if(world.getBlock(varX, varY - 1, varZ) == net.minecraft.init.Blocks.snow_layer){
				varY--;
			}
			world.setBlock(varX, varY, varZ, Blocks.RuneEssenceOre);
		}
		
		

		
		return true;
	}
}

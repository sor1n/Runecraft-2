package com.callumhutchy.runecraft2.worldgen;

import handler.ConfigurationHandler;

import java.util.Random;

import com.callumhutchy.runecraft2.blocks.Blocks;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class AltarWorldGeneratorRunecraft2 implements IWorldGenerator {
	Random	rand	= new Random();

	public AltarWorldGeneratorRunecraft2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// TODO Auto-generated method stub
		int multiplier = rand.nextInt(ConfigurationHandler.WorldGenMultiplier);
		switch (world.provider.dimensionId) {
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		case 0:
			generateSurface(world, random, chunkX * 16 * multiplier, chunkZ * 16 * multiplier);
		}

	}

	private void generateSurface(World world, Random random, int blockX, int blockZ) {
		boolean air, earth, fire, water;
		air = earth = fire = water = false;
		int Xcoord = blockX + random.nextInt(16);
		int Ycoord = random.nextInt(90);
		int Zcoord = blockZ + random.nextInt(16);

		int rand = randInt(1, 6);
		if (ConfigurationHandler.altarWorldGen) {
			if (rand == 1) {
				int whichAltar = randInt(1, 4);
				if (whichAltar == 1 && earth == false && fire == false && water == false) {
					(new WorldGenAirAltar()).generate(world, random, Xcoord, Ycoord, Zcoord);
//					world.setBlock(blockX + 5 + random.nextInt(4), Ycoord	,blockZ + 5 + random.nextInt(4), Blocks.RuneEssenceOre);
//					world.setBlock(blockX + 5 + random.nextInt(4), Ycoord	,blockZ + 5 + random.nextInt(4), Blocks.RuneEssenceOre);
					(new WorldGenOre()).generate(world, random, blockX + 5 + random.nextInt(4), Ycoord, blockZ + 5 + random.nextInt(4));
					(new WorldGenOre()).generate(world, random, blockX + 5 + random.nextInt(4), Ycoord, blockZ + 5 + random.nextInt(4));
					air = true;
					
				}
				if (whichAltar == 2 && air == false && fire == false && water == false) {
					(new WorldGenEarthAltar()).generate(world, random, Xcoord, Ycoord, Zcoord);
					(new WorldGenOre()).generate(world, random, blockX + 5 + random.nextInt(4), Ycoord, blockZ + 5 + random.nextInt(4));
					(new WorldGenOre()).generate(world, random, blockX + 5 + random.nextInt(4), Ycoord, blockZ + 5 + random.nextInt(4));
					earth = true;
				}
				if (whichAltar == 3 && air == false && earth == false && water == false) {
					(new WorldGenFireAltar()).generate(world, random, Xcoord, Ycoord, Zcoord);
					(new WorldGenOre()).generate(world, random, blockX + 5 + random.nextInt(4), Ycoord, blockZ + 5 + random.nextInt(4));
					(new WorldGenOre()).generate(world, random, blockX + 5 + random.nextInt(4), Ycoord, blockZ + 5 + random.nextInt(4));
					fire = true;
				}
				if (whichAltar == 4 && air == false && earth == false && fire == false) {
					(new WorldGenWaterAltar()).generate(world, random, Xcoord, Ycoord, Zcoord);
					(new WorldGenOre()).generate(world, random, blockX + 5 + random.nextInt(4), Ycoord, blockZ + 5 + random.nextInt(4));
					(new WorldGenOre()).generate(world, random, blockX + 5 + random.nextInt(4), Ycoord, blockZ + 5 + random.nextInt(4));
					water = true;
				}

			}
		}

	}

	private void generateNether(World world, Random random, int blockX, int blockZ) {
		int Xcoord = blockX + random.nextInt(16);
		int Ycoord = random.nextInt(60);
		int Zcoord = blockZ + random.nextInt(16);

	}

	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}

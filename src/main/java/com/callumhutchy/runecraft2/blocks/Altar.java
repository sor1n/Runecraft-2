package com.callumhutchy.runecraft2.blocks;

import handler.ExtendedPlayer;

import java.util.Random;



import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import reference.ExpChart;
import reference.Reference;

import com.callumhutchy.runecraft2.Runecraft2;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityAirAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityAirRuneAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityAstralAltar;
import com.callumhutchy.runecraft2.blocks.models.tileentities.altars.TileEntityAstralRuneAltar;
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
import com.callumhutchy.runecraft2.items.Items;
import com.callumhutchy.runecraft2.items.Staff;
import com.callumhutchy.runecraft2.keys.KeyInputHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Altar extends BlockContainer {

	public Item			item;
	public String		tileEntityClass;
	public static int	numberOfRuneEssences;
	public String		altarName;
	public static int	blocktick	= 0;

	protected Altar(Material p_i45386_1_, String textureName, String unlocalisedName, String tileEntity) {
		super(p_i45386_1_);

		if (tileEntity.contains("runealtar")) {

		} else {
			this.setCreativeTab(Runecraft2.tabRunecraft2Magic);
		}
		this.setHardness(3F);
		this.setHarvestLevel("pickaxe", 1);
		this.setBlockName(unlocalisedName);
		this.setBlockTextureName(textureName);

		tileEntityClass = tileEntity;
		altarName = unlocalisedName;
		this.needsRandomTick = true;

	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		switch (altarName) {

		case "airrunealtar":
			return Item.getItemFromBlock(Blocks.AirAltar);

		case "waterrunealtar":
			return Item.getItemFromBlock(Blocks.WaterAltar);

		case "firerunealtar":
			return Item.getItemFromBlock(Blocks.FireAltar);

		case "earthrunealtar":
			return Item.getItemFromBlock(Blocks.EarthAltar);

		case "mindrunealtar":
			return Item.getItemFromBlock(Blocks.MindAltar);

		case "bodyrunealtar":
			return Item.getItemFromBlock(Blocks.BodyAltar);

		case "cosmicrunealtar":
			return Item.getItemFromBlock(Blocks.CosmicAltar);

		case "chaosrunealtar":
			return Item.getItemFromBlock(Blocks.ChaosAltar);

		case "naturerunealtar":
			return Item.getItemFromBlock(Blocks.NatureAltar);

		case "lawrunealtar":
			return Item.getItemFromBlock(Blocks.LawAltar);

		case "deathrunealtar":
			return Item.getItemFromBlock(Blocks.DeathAltar);

		case "bloodrunealtar":
			return Item.getItemFromBlock(Blocks.BloodAltar);

		case "astralrunealtar":
			return Item.getItemFromBlock(Blocks.AstralAltar);

		default:
			return Item.getItemFromBlock(Blocks.AirAltar);
		}

	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {

		switch (altarName) {
		case "airaltar":
			return new TileEntityAirAltar();
		case "airrunealtar":
			return new TileEntityAirRuneAltar();
		case "wateraltar":
			return new TileEntityWaterAltar();
		case "waterrunealtar":
			return new TileEntityWaterRuneAltar();
		case "firealtar":
			return new TileEntityFireAltar();
		case "firerunealtar":
			return new TileEntityFireRuneAltar();
		case "earthaltar":
			return new TileEntityEarthAltar();
		case "earthrunealtar":
			return new TileEntityEarthRuneAltar();
		case "mindaltar":
			return new TileEntityMindAltar();
		case "mindrunealtar":
			return new TileEntityMindRuneAltar();
		case "bodyaltar":
			return new TileEntityBodyAltar();
		case "bodyrunealtar":
			return new TileEntityBodyRuneAltar();
		case "cosmicaltar":
			return new TileEntityCosmicAltar();
		case "cosmicrunealtar":
			return new TileEntityCosmicRuneAltar();
		case "chaosaltar":
			return new TileEntityChaosAltar();
		case "chaosrunealtar":
			return new TileEntityChaosRuneAltar();
		case "naturealtar":
			return new TileEntityNatureAltar();
		case "naturerunealtar":
			return new TileEntityNatureRuneAltar();
		case "lawaltar":
			return new TileEntityLawAltar();
		case "lawrunealtar":
			return new TileEntityLawRuneAltar();
		case "deathaltar":
			return new TileEntityDeathAltar();
		case "deathrunealtar":
			return new TileEntityDeathRuneAltar();
		case "bloodaltar":
			return new TileEntityBloodAltar();
		case "bloodrunealtar":
			return new TileEntityBloodRuneAltar();
		case "astralaltar":
			return new TileEntityAstralAltar();
		case "astralrunealtar":
			return new TileEntityAstralRuneAltar();

		default:
			return new TileEntityAirAltar();
		}

	}

	@Override
	public int getRenderType() {
		return -1;
	}

	// It's not an opaque cube, so you need this.
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	// It's not a normal block, so you need this too.
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	}

	public boolean onBlockActivated(World world, int varx, int vary, int varz, EntityPlayer entity, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (!world.isRemote) {
			System.out.println("TESTTESTTEST");
			String altarType = altarName;
			if (blocktick == 0) {
				if (entity.inventory.getCurrentItem() != null) {
					item = entity.inventory.getCurrentItem().getItem();
				}
				ExtendedPlayer props = ExtendedPlayer.get(entity);

				switch (altarType) {
				case "airaltar":
					try {
						if (item == Items.runeEssence) {
							numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
							System.out.println(numberOfRuneEssences);
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.AirRuneAltar);
							entity.getHeldItem().stackSize = 0;
							TileEntityAirRuneAltar tileEntity = (TileEntityAirRuneAltar) world.getTileEntity(varx, vary, varz);
							tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
							item = null;
							tileEntity.time = 5;
						} else {
						}
					} finally {
					}
					break;
				case "wateraltar":
					if (item == Items.runeEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.WaterRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityWaterRuneAltar tileEntity = (TileEntityWaterRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					} else {
					}
					break;
				case "firealtar":
					if (item == Items.runeEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.FireRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityFireRuneAltar tileEntity = (TileEntityFireRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					} else {
					}
					break;
				case "earthaltar":
					if (item == Items.runeEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.EarthRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityEarthRuneAltar tileEntity = (TileEntityEarthRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					} else {
					}
					break;
				case "mindaltar":
					if (item == Items.runeEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.MindRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityMindRuneAltar tileEntity = (TileEntityMindRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					}
					break;
				case "bodyaltar":
					if (item == Items.runeEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.BodyRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityBodyRuneAltar tileEntity = (TileEntityBodyRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					}
					break;
				case "cosmicaltar":
					if (item == Items.pureEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.CosmicRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityCosmicRuneAltar tileEntity = (TileEntityCosmicRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					}
					break;
				case "chaosaltar":
					if (item == Items.pureEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.ChaosRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityChaosRuneAltar tileEntity = (TileEntityChaosRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					}
					break;
				case "naturealtar":
					if (item == Items.pureEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.NatureRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityNatureRuneAltar tileEntity = (TileEntityNatureRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					}
					break;
				case "lawaltar":
					if (item == Items.pureEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.LawRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityLawRuneAltar tileEntity = (TileEntityLawRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					}
					break;
				case "deathaltar":
					if (item == Items.pureEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.DeathRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityDeathRuneAltar tileEntity = (TileEntityDeathRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					}
					break;
				case "bloodaltar":
					if (item == Items.pureEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.BloodRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityBloodRuneAltar tileEntity = (TileEntityBloodRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					}
					break;
				case "astralaltar":
					if (item == Items.pureEssence) {
						numberOfRuneEssences = entity.inventory.getCurrentItem().stackSize;
						System.out.println(numberOfRuneEssences);
						world.setBlockToAir(varx, vary, varz);
						world.setBlock(varx, vary, varz, Blocks.AstralRuneAltar);
						entity.getHeldItem().stackSize = 0;
						TileEntityAstralRuneAltar tileEntity = (TileEntityAstralRuneAltar) world.getTileEntity(varx, vary, varz);
						tileEntity.quantityOfRuneEssence = numberOfRuneEssences;
						item = null;
					}
					break;
				// ///////////////////////////////////////////////////////////////////////////////////////////////////
				case "airrunealtar":
					try {

						if (props.isSkillHighEnough("runecrafting", 1)) {
							boolean test = false;

							TileEntityAirRuneAltar tileEntity = (TileEntityAirRuneAltar) world.getTileEntity(varx, vary, varz);
							boolean talismanstaffsneaking = false;
							if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
								System.out.println("Sneaky");
								talismanstaffsneaking = true;
							}else{
								
							}
							System.out.println(talismanstaffsneaking);
						
							if ((item instanceof Staff && !item.getUnlocalizedName().contains("talismanstaff")) && world.getBlock(varx, vary, varz) == Blocks.AirRuneAltar ) {

								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.runeEssence, tileEntity.quantityOfRuneEssence)));
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.AirAltar);

							}
							if ((item == Items.airTalisman || (item.getUnlocalizedName().contains("airtalismanstaff") && !talismanstaffsneaking)) && world.getBlock(varx, vary, varz) == Blocks.AirRuneAltar) {

								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.airRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.AIR_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.AIR_RUNE_EXP + " Runecrafting Experience."));
								}
								if (numberOfRuneEssences >= 5) {
									int randomint = randInt(1, 5);
									if (randomint == 1) {
										int randomRune = randInt(0, 2);
										switch (randomRune) {
										case 0:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.waterRune, 1)));
											break;
										case 1:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.fireRune, 1)));
											break;
										case 2:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.earthRune, 1)));
											break;
										}
									}
									randomint = 10;
									item = null;

								}
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.AirAltar);

							}

						}

					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;
				// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				case "waterrunealtar":
					try {

						TileEntityWaterRuneAltar tileEntity = (TileEntityWaterRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.WaterRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.runeEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.WaterAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 5)) {
							if ((item == Items.waterTalisman || item == Items.waterTalismanStaff) && world.getBlock(varx, vary, varz) == Blocks.WaterRuneAltar) {
								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.waterRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.WATER_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.sendChatMessage("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.WATER_RUNE_EXP + " Runecrafting Experience.");
								}
								if (numberOfRuneEssences >= 5) {
									int randomint = randInt(1, 10);
									if (randomint == 1) {
										int randomRune = randInt(0, 1);
										switch (randomRune) {
										case 0:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.mindRune, 1)));
											break;
										case 1:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.bodyRune, 1)));
											break;

										}
									}
									randomint = 10;
									item = null;
								}
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.WaterAltar);

							}
						} else if (!props.isSkillHighEnough("runecrafting", 5) && item == Items.waterTalisman && world.getBlock(varx, vary, varz) == Blocks.WaterRuneAltar) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 5, but you are only " + props.currentRunecraftingLevel);
						}

					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;

				case "firerunealtar":
					try {
						TileEntityFireRuneAltar tileEntity = (TileEntityFireRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.FireRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.runeEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.FireAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 14)) {
							if ((item == Items.fireTalisman || item == Items.fireTalismanStaff) && world.getBlock(varx, vary, varz) == Blocks.FireRuneAltar) {

								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.fireRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.FIRE_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.sendChatMessage("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.FIRE_RUNE_EXP + " Runecrafting Experience.");
								}
								if (numberOfRuneEssences >= 5) {
									int randomint = randInt(1, 10);
									if (randomint == 1) {
										int randomRune = randInt(0, 1);
										switch (randomRune) {
										case 0:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.mindRune, 1)));
											break;
										case 1:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.bodyRune, 1)));
											break;

										}
									}
									randomint = 10;
									item = null;
								}
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.FireAltar);
							}
						} else if (!props.isSkillHighEnough("runecrafting", 14) && item == Items.fireTalisman && world.getBlock(varx, vary, varz) == Blocks.FireRuneAltar) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 14, but you are only " + props.currentRunecraftingLevel);
						}
					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;
				case "earthrunealtar":
					try {
						TileEntityEarthRuneAltar tileEntity = (TileEntityEarthRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.EarthRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.runeEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.EarthAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 9)) {
							if ((item == Items.earthTalisman || item == Items.earthTalismanStaff) && world.getBlock(varx, vary, varz) == Blocks.EarthRuneAltar) {
								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.earthRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.EARTH_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.sendChatMessage("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.EARTH_RUNE_EXP + " Runecrafting Experience.");
								}
								if (numberOfRuneEssences >= 5) {
									int randomint = randInt(1, 10);
									if (randomint == 1) {
										int randomRune = randInt(0, 1);
										switch (randomRune) {
										case 0:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.mindRune, 1)));
											break;
										case 1:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.bodyRune, 1)));
											break;

										}
									}
									randomint = 10;
									item = null;
								}
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.EarthAltar);

							}
						} else if (!props.isSkillHighEnough("runecrafting", 9) && item == Items.earthTalisman && world.getBlock(varx, vary, varz) == Blocks.EarthRuneAltar) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 9, but you are only " + props.currentRunecraftingLevel);
						}
					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;
				case "mindrunealtar":

					try {
						TileEntityMindRuneAltar tileEntity = (TileEntityMindRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.MindRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.runeEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.MindAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 23)) {
							if ((item == Items.mindTalisman || item == Items.mindTalismanStaff) && world.getBlock(varx, vary, varz) == Blocks.MindRuneAltar) {
								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.mindRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.MIND_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.MIND_RUNE_EXP + " Runecrafting Experience."));
								}
								if (numberOfRuneEssences >= 5) {
									int randomint = randInt(1, 10);
									if (randomint == 1) {
										int randomRune = randInt(0, 2);
										switch (randomRune) {
										case 0:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.natureRune, 1)));
											break;
										case 1:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.lawRune, 1)));
											break;
										case 2:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.cosmicRune, 1)));
											break;
										}
									}
									randomint = 10;
									item = null;
								}
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.MindAltar);

							}
						} else if (!props.isSkillHighEnough("runecrafting", 23) && item == Items.mindTalisman && world.getBlock(varx, vary, varz) == Blocks.MindRuneAltar) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 23, but you are only " + props.currentRunecraftingLevel);
						}
					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;
				case "bodyrunealtar":
					try {
						TileEntityBodyRuneAltar tileEntity = (TileEntityBodyRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.BodyRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.runeEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.BodyAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 18)) {
							if ((item == Items.bodyTalisman || item == Items.bodyTalismanStaff) && world.getBlock(varx, vary, varz) == Blocks.BodyRuneAltar) {
								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.bodyRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.BODY_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.BODY_RUNE_EXP + " Runecrafting Experience."));
								}
								if (numberOfRuneEssences >= 5) {
									int randomint = randInt(1, 10);
									if (randomint == 1) {
										int randomRune = randInt(0, 2);
										switch (randomRune) {
										case 0:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.natureRune, 1)));
											break;
										case 1:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.lawRune, 1)));
											break;
										case 2:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.cosmicRune, 1)));
											break;
										}
									}
									randomint = 10;
									item = null;
								}
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.BodyAltar);

							}
						} else if (!props.isSkillHighEnough("runecrafting", 18) && item == Items.bodyTalisman && world.getBlock(varx, vary, varz) == Blocks.BodyRuneAltar) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 18, but you are only " + props.currentRunecraftingLevel);
						}
					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;
				case "cosmicrunealtar":
					try {
						TileEntityCosmicRuneAltar tileEntity = (TileEntityCosmicRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.CosmicRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.pureEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.CosmicAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 27)) {
							if ((item == Items.cosmicTalisman || item == Items.cosmicTalismanStaff) && world.getBlock(varx, vary, varz) == Blocks.CosmicRuneAltar) {
								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.cosmicRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.COSMIC_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.COSMIC_RUNE_EXP + " Runecrafting Experience."));
								}
								if (numberOfRuneEssences >= 5) {
									int randomint = randInt(1, 20);
									if (randomint == 1) {
										int randomRune = randInt(0, 1);
										switch (randomRune) {
										case 0:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.chaosRune, 1)));
											break;
										case 1:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.deathRune, 1)));
											break;

										}
									}
									randomint = 10;
									item = null;
								}
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.CosmicAltar);

							}
						} else if (!props.isSkillHighEnough("runecrafting", 27) && item == Items.cosmicTalisman && world.getBlock(varx, vary, varz) == Blocks.CosmicRuneAltar) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 27, but you are only " + props.currentRunecraftingLevel);
						}
					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;
				case "chaosrunealtar":
					try {
						TileEntityChaosRuneAltar tileEntity = (TileEntityChaosRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.ChaosRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.pureEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.ChaosAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 35)) {
							if ((item == Items.chaosTalisman || item == Items.chaosTalismanStaff) && world.getBlock(varx, vary, varz) == Blocks.ChaosRuneAltar) {
								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.chaosRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.CHAOS_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.CHAOS_RUNE_EXP + " Runecrafting Experience."));
								}
								item = null;
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.ChaosAltar);
							}
						} else if (!props.isSkillHighEnough("runecrafting", 35) && item == Items.chaosTalisman && world.getBlock(varx, vary, varz) == Blocks.ChaosRuneAltar) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 35, but you are only " + props.currentRunecraftingLevel);
						}
					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;

				case "naturerunealtar":
					try {
						TileEntityNatureRuneAltar tileEntity = (TileEntityNatureRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.NatureRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.pureEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.NatureAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 44)) {
							if ((item == Items.natureTalisman || item == Items.natureTalismanStaff) && world.getBlock(varx, vary, varz) == Blocks.NatureRuneAltar) {
								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.natureRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.NATURE_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.NATURE_RUNE_EXP + " Runecrafting Experience."));
								}
								if (numberOfRuneEssences >= 5) {
									int randomint = randInt(1, 20);
									if (randomint == 1) {
										int randomRune = randInt(0, 1);
										switch (randomRune) {
										case 0:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.chaosRune, 1)));
											break;
										case 1:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.deathRune, 1)));
											break;

										}
									}
									randomint = 10;
									item = null;
									world.setBlockToAir(varx, vary, varz);
									world.setBlock(varx, vary, varz, Blocks.NatureAltar);
								}
							} else if (!props.isSkillHighEnough("runecrafting", 44) && item == Items.natureTalisman && world.getBlock(varx, vary, varz) == Blocks.NatureRuneAltar) {
								Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 44, but you are only " + props.currentRunecraftingLevel);
							}

						}
					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;
				case "lawrunealtar":
					try {
						TileEntityLawRuneAltar tileEntity = (TileEntityLawRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.LawRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.pureEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.LawAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 54)) {
							if ((item == Items.lawTalisman || item == Items.lawTalismanStaff) && world.getBlock(varx, vary, varz) == Blocks.LawRuneAltar) {
								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.lawRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.LAW_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.LAW_RUNE_EXP + " Runecrafting Experience."));
								}
								if (numberOfRuneEssences >= 5) {
									int randomint = randInt(1, 20);
									if (randomint == 1) {
										int randomRune = randInt(0, 1);
										switch (randomRune) {
										case 0:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.chaosRune, 1)));
											break;
										case 1:
											world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.deathRune, 1)));
											break;

										}
									}
									randomint = 10;
									item = null;
									world.setBlockToAir(varx, vary, varz);
									world.setBlock(varx, vary, varz, Blocks.LawAltar);
								}

							} else if (!props.isSkillHighEnough("runecrafting", 54) && item == Items.lawTalisman && world.getBlock(varx, vary, varz) == Blocks.LawRuneAltar) {
								Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 54, but you are only " + props.currentRunecraftingLevel);
							}
						}
					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;
				case "deathrunealtar":
					try {
						TileEntityDeathRuneAltar tileEntity = (TileEntityDeathRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.DeathRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.pureEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.DeathAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 65)) {
							if ((item == Items.deathTalisman || item == Items.deathTalismanStaff) && world.getBlock(varx, vary, varz) == Blocks.DeathRuneAltar) {
								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.deathRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.DEATH_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.DEATH_RUNE_EXP + " Runecrafting Experience."));
								}
								item = null;
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.DeathAltar);
							}
						} else if (!props.isSkillHighEnough("runecrafting", 65) && item == Items.deathTalisman && world.getBlock(varx, vary, varz) == Blocks.DeathRuneAltar) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 65, but you are only " + props.currentRunecraftingLevel);
						}
					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;

				case "bloodrunealtar":
					try {
						TileEntityBloodRuneAltar tileEntity = (TileEntityBloodRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.BloodRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.pureEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.BloodAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 77)) {
							if ((item == Items.bloodTalisman || item == Items.bloodTalismanStaff) && world.getBlock(varx, vary, varz) == Blocks.BloodRuneAltar) {
								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.bloodRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.BLOOD_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.BLOOD_RUNE_EXP + " Runecrafting Experience."));
								}
								item = null;
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.BloodAltar);
							}

						} else if (!props.isSkillHighEnough("runecrafting", 65) && item == Items.bloodTalisman && world.getBlock(varx, vary, varz) == Blocks.BloodRuneAltar) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 77, but you are only " + props.currentRunecraftingLevel);
						}
					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;
				case "astralrunealtar":
					try {
						TileEntityAstralRuneAltar tileEntity = (TileEntityAstralRuneAltar) world.getTileEntity(varx, vary, varz);
						if (item instanceof Staff && world.getBlock(varx, vary, varz) == Blocks.AstralRuneAltar && !item.getUnlocalizedName().contains("talisman")) {

							world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.pureEssence, tileEntity.quantityOfRuneEssence)));
							world.setBlockToAir(varx, vary, varz);
							world.setBlock(varx, vary, varz, Blocks.AstralAltar);

						}
						if (props.isSkillHighEnough("runecrafting", 85)) {
							if (item == Items.astralTalisman && world.getBlock(varx, vary, varz) == Blocks.AstralRuneAltar) {
								world.spawnEntityInWorld(new EntityItem(world, varx, vary + 1, varz, new ItemStack(Items.astralRune, tileEntity.quantityOfRuneEssence)));
								props.currentRunecraftingExp = props.currentRunecraftingExp + tileEntity.quantityOfRuneEssence * ExpChart.ASTRAL_RUNE_EXP;
								if (!props.suppressExpMessages) {
									Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You gained " + tileEntity.quantityOfRuneEssence * ExpChart.ASTRAL_RUNE_EXP + " Runecrafting Experience."));
								}
								item = null;
								world.setBlockToAir(varx, vary, varz);
								world.setBlock(varx, vary, varz, Blocks.AstralAltar);
							}

						} else if (!props.isSkillHighEnough("runecrafting", 85) && item == Items.astralTalisman && world.getBlock(varx, vary, varz) == Blocks.AstralRuneAltar) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("Your Runecrafting is not high enough. You require a skill level of 85, but you are only " + props.currentRunecraftingLevel);
						}
					} catch (Exception e) {
						System.out.println("Altar threw error.");
					} finally {
					}
					break;

				default:
					;
				}

				item = null;
				blocktick = 20;
			}
		}
		return true;
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

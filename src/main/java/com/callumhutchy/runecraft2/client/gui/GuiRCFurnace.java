package com.callumhutchy.runecraft2.client.gui;

import handler.ConfigurationHandler;
import handler.ExtendedPlayer;
import handler.Runecraft2EventHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import reference.ExpChart;
import reference.OreTimes;

import com.callumhutchy.runecraft2.blocks.containers.ContainerRCFurnace;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityRCFurnace;
import com.callumhutchy.runecraft2.client.gui.GuiSpellBook.FontCodes;
import com.callumhutchy.runecraft2.items.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class GuiRCFurnace extends GuiContainer {
	private static final ResourceLocation	resourceLocation			= new ResourceLocation("runecraft2:textures/gui/furnacegui.png");
	protected static final ResourceLocation	widgetTextures				= new ResourceLocation("runecraft2:textures/gui/furnaceicons.png");

	public static final int					GUI_ID						= 21;

	private static final int				SPELL_ICON_SIZE				= 16;
	private static final int				SPELL_ICON_SPACING			= SPELL_ICON_SIZE + 4;
	private static final int				SPELL_ICON_BASE_U_OFFSET	= 0;
	private static final int				SPELL_ICON_BASE_V_OFFSET	= 198;
	private static final int				SPELL_ICONS_PER_ROW			= 11;

	public String							tooltipNewlineDelimeter		= "_p";

	public long								tooltipDelay				= 900;

	/**
	 * The maximum width in pixels a tooltip can occupy before word wrapping
	 * occurs
	 */
	public int								tooltipMaxWidth				= 150;

	protected int							tooltipXOffset				= 0;
	protected int							tooltipYOffset				= 10;

	private final static int				LINE_HEIGHT					= 11;

	private long							mouseoverTime				= 0;
	private long							prevSystemTime				= -1;

	public boolean							bronzebarselected, ironbarselected,
			silverbarselected, steelbarselected, goldbarselected,
			mithrilbarselected, adamantbarselected, runebarselected = false;

	int										xSize						= 248;
	int										ySize						= 134;
	int										xStart						= (width / 2) - (xSize / 2);
	int										yStart						= (height / 2) - (ySize / 2);
	int										iconyStart					= yStart + 22;
	int										iconxStart					= xStart + 17;

	int										bronzeminx, bronzemaxx, bronzeminy,
			bronzemaxy, ironminx, ironmaxx, ironminy, ironmaxy, silverminx,
			silvermaxx, silverminy, silvermaxy, steelminx, steelmaxx,
			steelminy, steelmaxy, goldminx, goldmaxx, goldminy, goldmaxy,
			mithrilminx, mithrilmaxx, mithrilminy, mithrilmaxy, adamantminx,
			adamantmaxx, adamantminy, adamantmaxy, runeminx, runemaxx,
			runeminy, runemaxy;

	int										frame						= 1;
	private GuiButton						doneBtn, craftallBtn,
			customcraftBtn, onecraftBtn, fivecraftBtn, tencraftBtn, craftBtn,
			rightBtn, leftBtn;
	private GuiTextField					craftAmount;
	private GuiTextField					craftTime;
	int										amountToCraft;
	public TileEntityRCFurnace				tileEntityFurnace;
	public World							world;
	public EntityPlayer						player;

	public GuiRCFurnace(EntityPlayer inventoryPlayer) {
		super(new ContainerRCFurnace(inventoryPlayer));
		System.out.println(inventoryPlayer);
		player = inventoryPlayer;
		ExtendedPlayer props = ExtendedPlayer.get(player);
		player.addChatMessage(new ChatComponentText("Hey Player"));
		player.addChatMessage(new ChatComponentText(props.currentRCFurnace.toString()));
		tileEntityFurnace = props.currentRCFurnace;
		world = player.worldObj;
		if (tileEntityFurnace.itemsToReturn == true) {
			System.out.println("Its reached method 2");
			System.out.println(tileEntityFurnace.amountOfProduct);
			System.out.println(tileEntityFurnace.furnaceProduct.toString());
			player.inventory.addItemStackToInventory(new ItemStack(tileEntityFurnace.furnaceProduct, tileEntityFurnace.amountOfProduct));
			tileEntityFurnace.itemsToReturn = false;
			tileEntityFurnace.amountOfProduct = 0;
		}

	}

	public void initGui() {
		int xSize = 256;
		int ySize = 256;
		int xStart = (width / 2) - (xSize / 2);
		int yStart = (height / 2) - (ySize / 2);
		int iconyStart = yStart + 22;
		int iconxStart = xStart + 17;
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		buttonList.add(doneBtn = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96 + 12, "Done"));

		buttonList.add(craftallBtn = new GuiButton(2, xStart + 6, yStart + 89, "All"));

		buttonList.add(onecraftBtn = new GuiButton(4, xStart + 38, yStart + 89, "1"));
		buttonList.add(fivecraftBtn = new GuiButton(5, xStart + 70, yStart + 89, "5"));
		buttonList.add(tencraftBtn = new GuiButton(6, xStart + 102, yStart + 89, "10"));
		// buttonList.add(customcraftBtn = new GuiButton(3, xStart + 134, yStart
		// + 89, "Custom"));
		buttonList.add(craftBtn = new GuiButton(7, xStart + 134, yStart + 89, "Craft"));

		buttonList.add(rightBtn = new GuiButton(8, xStart + 223, yStart + 87, ">"));

		buttonList.add(leftBtn = new GuiButton(9, xStart + 223, yStart + 109, "<"));
		this.craftAmount = new GuiTextField(this.fontRendererObj, xStart + 6, yStart + 111, 30, 20);
		this.craftAmount.setFocused(true);
		this.craftAmount.setMaxStringLength(3);
		this.craftAmount.setText("");

		this.craftTime = new GuiTextField(this.fontRendererObj, xStart + 60, yStart + 111, 60, 20);

	}

	public void drawScreen(int par1, int par2, float par3) {

		int xSize = 256;
		int ySize = 256;
		int xStart = (width / 2) - (xSize / 2);
		int yStart = (height / 2) - (ySize / 2);
		int iconyStart = yStart + 22;
		int iconxStart = xStart + 17;

		this.drawBackground(0);
		this.doesGuiPauseGame();

		this.drawBars();
		// this.DrawTooltipScreen();

		this.drawCenteredString(this.fontRendererObj, "Furnace", xStart + 40, yStart + 5, 16777215);

		this.craftAmount.drawTextBox();
		this.craftTime.drawTextBox();

		// rightBtn.width = leftBtn.width = 20;
		// rightBtn.height = leftBtn.height = 20;
		// onecraftBtn.width = fivecraftBtn.width = tencraftBtn.width = 30;
		// onecraftBtn.height = fivecraftBtn.height = tencraftBtn.height = 20;
		// craftallBtn.width = 30;
		// craftallBtn.height = craftBtn.height = 20;
		//
		// craftBtn.width = 35;

		super.drawScreen(par1, par2, par3);
		this.IsButtonMouseovered(par1, par2, null);
	}

	public void updateScreen() {
		ExtendedPlayer props = ExtendedPlayer.get(player);
		this.craftAmount.updateCursorCounter();
		tileEntityFurnace = props.currentRCFurnace;
		writeTime(tileEntityFurnace.time);
		world = player.worldObj;
		if (tileEntityFurnace.itemsToReturn == true) {
			System.out.println("Its reached method 2");
			System.out.println(tileEntityFurnace.amountOfProduct);
			System.out.println(tileEntityFurnace.furnaceProduct.toString());
			player.inventory.addItemStackToInventory(new ItemStack(tileEntityFurnace.furnaceProduct, tileEntityFurnace.amountOfProduct));
			tileEntityFurnace.itemsToReturn = false;
			tileEntityFurnace.amountOfProduct = 0;
		}

	}

	protected void actionPerformed(GuiButton button) {

		// if (button == doneBtn) {
		// System.out.println("Testing");
		// this.mc.thePlayer.closeScreen();
		//
		// } else if (button == rightBtn && button != leftBtn) {
		// if (frame < 5 && button == rightBtn) {
		// frame++;
		//
		// }
		//
		// } else if (button == leftBtn && button != rightBtn) {
		// if (frame > 1 && button == leftBtn) {
		// System.out.println(frame);
		// frame = frame - 2;
		// System.out.println(frame);
		// }
		// }
		switch (button.id) {
		case 1:
			this.mc.thePlayer.closeScreen();
		case 2:
			if (bronzebarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.copperOre, 1) && hasNumItems(this.mc.thePlayer, Items.tinOre, 1)) {
					int numberOfCopperOre, numberOfTinOre;

					numberOfCopperOre = getNumItems(this.mc.thePlayer, Items.copperOre);
					numberOfTinOre = getNumItems(this.mc.thePlayer, Items.tinOre);
					amountToCraft = Math.min(numberOfCopperOre, numberOfTinOre);

					craftAmount.setText(String.valueOf(amountToCraft));
				}

				break;
			} else if (ironbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.ironOre, 1)) {
					int numberOfIronOre;

					numberOfIronOre = getNumItems(this.mc.thePlayer, Items.ironOre);

					amountToCraft = numberOfIronOre;

					craftAmount.setText(String.valueOf(amountToCraft));
				}

				break;

			} else if (silverbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.silverOre, 1)) {
					int numberOfSilverOre;

					numberOfSilverOre = getNumItems(this.mc.thePlayer, Items.silverOre);

					amountToCraft = numberOfSilverOre;

					craftAmount.setText(String.valueOf(amountToCraft));
				}

				break;

			} else if (steelbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.ironOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 2)) {
					int numberOfIronOre, numberOfCoalOre;

					numberOfIronOre = getNumItems(this.mc.thePlayer, Items.ironOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 2;
					amountToCraft = Math.min(numberOfIronOre, numberOfCoalOre);

					craftAmount.setText(String.valueOf(amountToCraft));
				}

				break;

			} else if (goldbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.goldOre, 1)) {
					int numberOfGoldOre;

					numberOfGoldOre = getNumItems(this.mc.thePlayer, Items.goldOre);

					amountToCraft = numberOfGoldOre;

					craftAmount.setText(String.valueOf(amountToCraft));
				}

				break;

			} else if (mithrilbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.mithrilOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 4)) {
					int numberOfMithrilOre, numberOfCoalOre;

					numberOfMithrilOre = getNumItems(this.mc.thePlayer, Items.mithrilOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 4;
					amountToCraft = Math.min(numberOfMithrilOre, numberOfCoalOre);

					craftAmount.setText(String.valueOf(amountToCraft));
				}

				break;

			} else if (adamantbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.adamantiteOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 6)) {
					int numberOfAdamantiteOre, numberOfCoalOre;

					numberOfAdamantiteOre = getNumItems(this.mc.thePlayer, Items.adamantiteOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 6;
					amountToCraft = Math.min(numberOfAdamantiteOre, numberOfCoalOre);

					craftAmount.setText(String.valueOf(amountToCraft));
				}

				break;

			} else if (runebarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.runiteOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 8)) {
					int numberOfRuniteOre, numberOfCoalOre;

					numberOfRuniteOre = getNumItems(this.mc.thePlayer, Items.runiteOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 8;
					amountToCraft = Math.min(numberOfRuniteOre, numberOfCoalOre);

					craftAmount.setText(String.valueOf(amountToCraft));
				}

				break;

			}
		case 3:
			this.craftAmount.setFocused(true);
			break;
		case 4:
			if (bronzebarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.copperOre, 1) && hasNumItems(this.mc.thePlayer, Items.tinOre, 1)) {

					craftAmount.setText(String.valueOf(1));
				}

				break;
			} else if (ironbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.ironOre, 1)) {

					craftAmount.setText(String.valueOf(1));
				}

				break;

			} else if (silverbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.silverOre, 1)) {

					craftAmount.setText(String.valueOf(1));
				}

				break;

			} else if (steelbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.ironOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 2)) {

					craftAmount.setText(String.valueOf(1));
				}

				break;

			} else if (goldbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.goldOre, 1)) {

					craftAmount.setText(String.valueOf(1));
				}

				break;

			} else if (mithrilbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.mithrilOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 4)) {

					craftAmount.setText(String.valueOf(1));
				}

				break;

			} else if (adamantbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.adamantiteOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 6)) {

					craftAmount.setText(String.valueOf(1));
				}

				break;

			} else if (runebarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.runiteOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 8)) {

					craftAmount.setText(String.valueOf(1));
				}

				break;

			}
		case 5:
			if (bronzebarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.copperOre, 1) && hasNumItems(this.mc.thePlayer, Items.tinOre, 1)) {
					int numberOfCopperOre, numberOfTinOre;

					numberOfCopperOre = getNumItems(this.mc.thePlayer, Items.copperOre);
					numberOfTinOre = getNumItems(this.mc.thePlayer, Items.tinOre);
					amountToCraft = Math.min(numberOfCopperOre, numberOfTinOre);

					if (amountToCraft >= 5) {
						amountToCraft = 5;
						craftAmount.setText(String.valueOf(5));
					} else if (amountToCraft < 5) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}

				}

				break;
			} else if (ironbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.ironOre, 1)) {
					int numberOfIronOre;

					numberOfIronOre = getNumItems(this.mc.thePlayer, Items.ironOre);

					amountToCraft = numberOfIronOre;

					if (amountToCraft >= 5) {
						amountToCraft = 5;
						craftAmount.setText(String.valueOf(5));
					} else if (amountToCraft < 5) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (silverbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.silverOre, 1)) {
					int numberOfSilverOre;

					numberOfSilverOre = getNumItems(this.mc.thePlayer, Items.silverOre);

					amountToCraft = numberOfSilverOre;

					if (amountToCraft >= 5) {
						amountToCraft = 5;
						craftAmount.setText(String.valueOf(5));
					} else if (amountToCraft < 5) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (steelbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.ironOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 2)) {
					int numberOfIronOre, numberOfCoalOre;

					numberOfIronOre = getNumItems(this.mc.thePlayer, Items.ironOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 2;
					amountToCraft = Math.min(numberOfIronOre, numberOfCoalOre);

					if (amountToCraft >= 5) {
						amountToCraft = 5;
						craftAmount.setText(String.valueOf(5));
					} else if (amountToCraft < 5) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (goldbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.goldOre, 1)) {
					int numberOfGoldOre;

					numberOfGoldOre = getNumItems(this.mc.thePlayer, Items.goldOre);

					amountToCraft = numberOfGoldOre;

					if (amountToCraft >= 5) {
						amountToCraft = 5;
						craftAmount.setText(String.valueOf(5));
					} else if (amountToCraft < 5) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (mithrilbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.mithrilOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 4)) {
					int numberOfMithrilOre, numberOfCoalOre;

					numberOfMithrilOre = getNumItems(this.mc.thePlayer, Items.mithrilOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 4;
					amountToCraft = Math.min(numberOfMithrilOre, numberOfCoalOre);

					if (amountToCraft >= 5) {
						amountToCraft = 5;
						craftAmount.setText(String.valueOf(5));
					} else if (amountToCraft < 5) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (adamantbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.adamantiteOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 6)) {
					int numberOfAdamantiteOre, numberOfCoalOre;

					numberOfAdamantiteOre = getNumItems(this.mc.thePlayer, Items.adamantiteOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 6;
					amountToCraft = Math.min(numberOfAdamantiteOre, numberOfCoalOre);

					if (amountToCraft >= 5) {
						amountToCraft = 5;
						craftAmount.setText(String.valueOf(5));
					} else if (amountToCraft < 5) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (runebarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.runiteOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 8)) {
					int numberOfRuniteOre, numberOfCoalOre;

					numberOfRuniteOre = getNumItems(this.mc.thePlayer, Items.runiteOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 8;
					amountToCraft = Math.min(numberOfRuniteOre, numberOfCoalOre);

					if (amountToCraft >= 5) {
						amountToCraft = 5;
						craftAmount.setText(String.valueOf(5));
					} else if (amountToCraft < 5) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			}
		case 6:
			if (bronzebarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.copperOre, 1) && hasNumItems(this.mc.thePlayer, Items.tinOre, 1)) {
					int numberOfCopperOre, numberOfTinOre;

					numberOfCopperOre = getNumItems(this.mc.thePlayer, Items.copperOre);
					numberOfTinOre = getNumItems(this.mc.thePlayer, Items.tinOre);
					amountToCraft = Math.min(numberOfCopperOre, numberOfTinOre);

					if (amountToCraft >= 10) {
						amountToCraft = 10;
						craftAmount.setText(String.valueOf(10));
					} else if (amountToCraft < 10) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}

				}

				break;
			} else if (ironbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.ironOre, 1)) {
					int numberOfIronOre;

					numberOfIronOre = getNumItems(this.mc.thePlayer, Items.ironOre);

					amountToCraft = numberOfIronOre;

					if (amountToCraft >= 10) {
						amountToCraft = 10;
						craftAmount.setText(String.valueOf(10));
					} else if (amountToCraft < 10) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (silverbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.silverOre, 1)) {
					int numberOfSilverOre;

					numberOfSilverOre = getNumItems(this.mc.thePlayer, Items.silverOre);

					amountToCraft = numberOfSilverOre;

					if (amountToCraft >= 10) {
						amountToCraft = 10;
						craftAmount.setText(String.valueOf(10));
					} else if (amountToCraft < 10) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (steelbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.ironOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 2)) {
					int numberOfIronOre, numberOfCoalOre;

					numberOfIronOre = getNumItems(this.mc.thePlayer, Items.ironOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 2;
					amountToCraft = Math.min(numberOfIronOre, numberOfCoalOre);

					if (amountToCraft >= 10) {
						amountToCraft = 10;
						craftAmount.setText(String.valueOf(10));
					} else if (amountToCraft < 10) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (goldbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.goldOre, 1)) {
					int numberOfGoldOre;

					numberOfGoldOre = getNumItems(this.mc.thePlayer, Items.goldOre);

					amountToCraft = numberOfGoldOre;

					if (amountToCraft >= 10) {
						amountToCraft = 10;
						craftAmount.setText(String.valueOf(10));
					} else if (amountToCraft < 10) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (mithrilbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.mithrilOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 4)) {
					int numberOfMithrilOre, numberOfCoalOre;

					numberOfMithrilOre = getNumItems(this.mc.thePlayer, Items.mithrilOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 4;
					amountToCraft = Math.min(numberOfMithrilOre, numberOfCoalOre);

					if (amountToCraft >= 10) {
						amountToCraft = 10;
						craftAmount.setText(String.valueOf(10));
					} else if (amountToCraft < 10) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (adamantbarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.adamantiteOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 6)) {
					int numberOfAdamantiteOre, numberOfCoalOre;

					numberOfAdamantiteOre = getNumItems(this.mc.thePlayer, Items.adamantiteOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 6;
					amountToCraft = Math.min(numberOfAdamantiteOre, numberOfCoalOre);

					if (amountToCraft >= 10) {
						amountToCraft = 10;
						craftAmount.setText(String.valueOf(10));
					} else if (amountToCraft < 10) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			} else if (runebarselected) {
				if (hasNumItems(this.mc.thePlayer, Items.runiteOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 8)) {
					int numberOfRuniteOre, numberOfCoalOre;

					numberOfRuniteOre = getNumItems(this.mc.thePlayer, Items.runiteOre);
					numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
					numberOfCoalOre = (int) numberOfCoalOre / 8;
					amountToCraft = Math.min(numberOfRuniteOre, numberOfCoalOre);

					if (amountToCraft >= 10) {
						amountToCraft = 10;
						craftAmount.setText(String.valueOf(10));
					} else if (amountToCraft < 10) {
						craftAmount.setText(String.valueOf(amountToCraft));
					}
				}

				break;

			}
		case 7:
			if (bronzebarselected) {
				System.out.println(amountToCraft);
				for (int i = 0; i < amountToCraft; i++) {
					consumeMultipleItems(this.mc.thePlayer, Items.copperOre, Items.tinOre);
				}

				tileEntityFurnace.doneCooking = false;
				tileEntityFurnace.furnaceProduct = Items.bronzeBar;
				tileEntityFurnace.time = OreTimes.bronzeTime * amountToCraft;
				tileEntityFurnace.amountOfProduct = amountToCraft;

				break;
			} else if (ironbarselected) {
				for (int i = 0; i < amountToCraft; i++) {
					this.mc.thePlayer.inventory.consumeInventoryItem(Items.ironOre);
				}
				tileEntityFurnace.doneCooking = false;
				tileEntityFurnace.furnaceProduct = Items.ironBar;
				tileEntityFurnace.time = OreTimes.ironTime * amountToCraft;
				tileEntityFurnace.amountOfProduct = amountToCraft;
				// % that it won't craft
				break;

			} else if (silverbarselected) {

				for (int i = 0; i < amountToCraft; i++) {
					this.mc.thePlayer.inventory.consumeInventoryItem(Items.silverOre);
				}
				tileEntityFurnace.doneCooking = false;
				tileEntityFurnace.furnaceProduct = Items.silverBar;
				tileEntityFurnace.time = OreTimes.silverTime * amountToCraft;
				tileEntityFurnace.amountOfProduct = amountToCraft;
				break;

			} else if (steelbarselected) {
				
				
				
				for (int i = 0; i < amountToCraft; i++) {
					this.mc.thePlayer.inventory.consumeInventoryItem(Items.ironOre);
					consumeMultipleOfOneItem(this.mc.thePlayer, Items.coalOre, 2);
				}

				tileEntityFurnace.doneCooking = false;
				tileEntityFurnace.furnaceProduct = Items.steelBar;
				tileEntityFurnace.time = OreTimes.steelTime * amountToCraft;
				tileEntityFurnace.amountOfProduct = amountToCraft;
				break;

			} else if (goldbarselected) {
				
				for (int i = 0; i < amountToCraft; i++) {
					this.mc.thePlayer.inventory.consumeInventoryItem(Items.goldOre);
				}
				tileEntityFurnace.doneCooking = false;
				tileEntityFurnace.furnaceProduct = Items.goldBar;
				tileEntityFurnace.time = OreTimes.goldTime * amountToCraft;
				tileEntityFurnace.amountOfProduct = amountToCraft;
				break;

			} else if (mithrilbarselected) {
				
				
				for (int i = 0; i < amountToCraft; i++) {
					this.mc.thePlayer.inventory.consumeInventoryItem(Items.mithrilOre);
					consumeMultipleOfOneItem(this.mc.thePlayer, Items.coalOre, 4);
					
				}
				tileEntityFurnace.doneCooking = false;
				tileEntityFurnace.furnaceProduct = Items.mithrilBar;
				tileEntityFurnace.time = OreTimes.mithrilTime * amountToCraft;
				tileEntityFurnace.amountOfProduct = amountToCraft;
				break;

			} else if (adamantbarselected) {
				
				
				for (int i = 0; i < amountToCraft; i++) {
					this.mc.thePlayer.inventory.consumeInventoryItem(Items.adamantiteOre);
					consumeMultipleOfOneItem(this.mc.thePlayer, Items.coalOre, 6);
					
				}
				tileEntityFurnace.doneCooking = false;
				tileEntityFurnace.furnaceProduct = Items.adamantBar;
				tileEntityFurnace.time = OreTimes.adamantTime * amountToCraft;
				tileEntityFurnace.amountOfProduct = amountToCraft;
				break;

			} else if (runebarselected) {
				
				
				
				for (int i = 0; i < amountToCraft; i++) {
					this.mc.thePlayer.inventory.consumeInventoryItem(Items.runiteOre);
					consumeMultipleOfOneItem(this.mc.thePlayer, Items.coalOre, 8);
					
				}
				tileEntityFurnace.doneCooking = false;
				tileEntityFurnace.furnaceProduct = Items.runeBar;
				tileEntityFurnace.time = OreTimes.runeTime * amountToCraft;
				tileEntityFurnace.amountOfProduct = amountToCraft;
				
				break;

			}

		case 8:
			if (frame < 5 && button == rightBtn) {
				frame++;

			}
		case 9:
			if (frame > 1 && button == leftBtn) {
				System.out.println(frame);
				frame = frame - 2;
				System.out.println(frame);
			}
		}

	}

	public void drawBackground(int p_146278_1_) {
		int xSize = 256;
		int ySize = 256;
		int xStart = (width / 2) - (xSize / 2);
		int yStart = (height / 2) - (ySize / 2);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(resourceLocation);
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}

	public boolean doesGuiPauseGame(boolean var1) {
		return false;

	}

	public void drawBars() {
		int xSize = 256;
		int ySize = 256;
		int xStart = (width / 2) - (xSize / 2);
		int yStart = (height / 2) - (ySize / 2);
		int width = 53;
		int height = 55;
		int iconyStart = yStart + 25;
		int iconxStart = xStart + 13;
		int iconxStart1 = iconxStart;
		int iconxStart2 = iconxStart1 + width + 4;
		int iconxStart3 = iconxStart2 + width + 4;
		int iconxStart4 = iconxStart3 + width + 4;
		int iconxFinish1 = iconxStart + width;
		int iconxFinish2 = iconxFinish1 + width + 4;
		int iconxFinish3 = iconxFinish2 + width + 4;
		int iconxFinish4 = iconxFinish3 + width + 4;

		ExtendedPlayer props = ExtendedPlayer.get(this.mc.thePlayer);
		if (ConfigurationHandler.furnaceRequiresSmithing) {
			switch (frame) {
			case 0:
				if (!bronzebarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

					bronzeminx = iconxStart1;
					bronzemaxx = iconxFinish1;
					bronzeminy = iconyStart;
					bronzemaxy = iconyStart + height;
				}
				if (bronzebarselected && props.currentSmithingLevel >= 1) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

					bronzeminx = iconxStart1;
					bronzemaxx = iconxFinish1;
					bronzeminy = iconyStart;
					bronzemaxy = iconyStart + height;
				}
				if (!ironbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 57, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart2;
					ironmaxx = iconxFinish2;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}
				if (ironbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !(props.currentSmithingLevel >= 15)) {
					ironbarselected = false;
				}
				if (ironbarselected && props.currentSmithingLevel >= 15) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 170, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart2;
					ironmaxx = iconxFinish2;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}
				if (!silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 108, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart3;
					silvermaxx = iconxFinish3;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (silverbarselected && !this.mc.thePlayer.inventory.hasItem(Items.silverOre) || !(props.currentSmithingLevel >= 20)) {
					silverbarselected = false;
				}
				if (silverbarselected && props.currentSmithingLevel >= 20) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 108, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart3;
					silvermaxx = iconxFinish3;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (!steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 162, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart4;
					steelmaxx = iconxFinish4;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (steelbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre) || !(props.currentSmithingLevel >= 15)) {
					steelbarselected = false;
				}
				if (steelbarselected && props.currentSmithingLevel >= 30) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 162, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart4;
					steelmaxx = iconxFinish4;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				break;
			case 1:
				if (!bronzebarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

					bronzeminx = iconxStart1;
					bronzemaxx = iconxFinish1;
					bronzeminy = iconyStart;
					bronzemaxy = iconyStart + height;
				}
				if (bronzebarselected && props.currentSmithingLevel >= 1) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

					bronzeminx = iconxStart1;
					bronzemaxx = iconxFinish1;
					bronzeminy = iconyStart;
					bronzemaxy = iconyStart + height;
				}
				if (!ironbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 57, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart2;
					ironmaxx = iconxFinish2;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}
				if (ironbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !(props.currentSmithingLevel >= 15)) {
					ironbarselected = false;
				}
				if (ironbarselected && this.mc.thePlayer.inventory.hasItem(Items.ironOre) && props.currentSmithingLevel >= 15) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 170, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart2;
					ironmaxx = iconxFinish2;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}

				if (!silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 108, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart3;
					silvermaxx = iconxFinish3;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (silverbarselected && !this.mc.thePlayer.inventory.hasItem(Items.silverOre) || !(props.currentSmithingLevel >= 20)) {
					silverbarselected = false;
				}
				if (silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 108, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart3;
					silvermaxx = iconxFinish3;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (!steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 162, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart4;
					steelmaxx = iconxFinish4;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (steelbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre) || !(props.currentSmithingLevel >= 15)) {
					steelbarselected = false;
				}
				if (steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 162, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart4;
					steelmaxx = iconxFinish4;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				break;
			case 2:
				if (!ironbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 162, 57, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart1;
					ironmaxx = iconxFinish1;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}
				if (ironbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !(props.currentSmithingLevel >= 15)) {
					ironbarselected = false;
				}
				if (ironbarselected && this.mc.thePlayer.inventory.hasItem(Items.ironOre) && props.currentSmithingLevel >= 15) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 162, 170, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart1;
					ironmaxx = iconxFinish1;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}

				if (!silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 108, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart2;
					silvermaxx = iconxFinish2;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (silverbarselected && !this.mc.thePlayer.inventory.hasItem(Items.silverOre) || !(props.currentSmithingLevel >= 20)) {
					silverbarselected = false;
				}
				if (silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 108, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart2;
					silvermaxx = iconxFinish2;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (!steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 162, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart3;
					steelmaxx = iconxFinish3;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (steelbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre) || !(props.currentSmithingLevel >= 15)) {
					steelbarselected = false;
				}
				if (steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 162, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart3;
					steelmaxx = iconxFinish3;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (!goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 0, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart4;
					goldmaxx = iconxFinish4;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}
				if (goldbarselected && !this.mc.thePlayer.inventory.hasItem(Items.goldOre) || !(props.currentSmithingLevel >= 40)) {
					goldbarselected = false;
				}
				if (goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 0, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart4;
					goldmaxx = iconxFinish4;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}

				break;
			case 3:
				if (!silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart1;
					silvermaxx = iconxFinish1;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (silverbarselected && !this.mc.thePlayer.inventory.hasItem(Items.silverOre) || !(props.currentSmithingLevel >= 20)) {
					silverbarselected = false;
				}
				if (silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart1;
					silvermaxx = iconxFinish1;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (!steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart2;
					steelmaxx = iconxFinish2;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (steelbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre) || !(props.currentSmithingLevel >= 15)) {
					steelbarselected = false;
				}
				if (steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart2;
					steelmaxx = iconxFinish2;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (!goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 0, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart3;
					goldmaxx = iconxFinish3;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}
				if (goldbarselected && !this.mc.thePlayer.inventory.hasItem(Items.goldOre) || !(props.currentSmithingLevel >= 40)) {
					goldbarselected = false;
				}
				if (goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 0, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart3;
					goldmaxx = iconxFinish3;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}

				if (!mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 0, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart4;
					mithrilmaxx = iconxFinish4;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				if (mithrilbarselected && !this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre) || !(props.currentSmithingLevel >= 50)) {
					mithrilbarselected = false;
				}
				if (mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 0, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart4;
					mithrilmaxx = iconxFinish4;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				break;
			case 4:
				if (!steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 162, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart1;
					steelmaxx = iconxFinish1;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (steelbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre) || !(props.currentSmithingLevel >= 15)) {
					steelbarselected = false;
				}
				if (steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 162, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart1;
					steelmaxx = iconxFinish1;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (!goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 0, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart2;
					goldmaxx = iconxFinish2;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}
				if (goldbarselected && !this.mc.thePlayer.inventory.hasItem(Items.goldOre) || !(props.currentSmithingLevel >= 40)) {
					goldbarselected = false;
				}
				if (goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 0, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart2;
					goldmaxx = iconxFinish2;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}

				if (!mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 0, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart3;
					mithrilmaxx = iconxFinish3;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				if (mithrilbarselected && !this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre) || !(props.currentSmithingLevel >= 50)) {
					mithrilbarselected = false;
				}
				if (mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 0, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart3;
					mithrilmaxx = iconxFinish3;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				if (!adamantbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 54, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					adamantminx = iconxStart4;
					adamantmaxx = iconxFinish4;
					adamantminy = iconyStart;
					adamantmaxy = iconyStart + height;
				}
				if (adamantbarselected && !this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre) || !(props.currentSmithingLevel >= 70)) {
					adamantbarselected = false;
				}
				if (adamantbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 54, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					adamantminx = iconxStart4;
					adamantmaxx = iconxFinish4;
					adamantminy = iconyStart;
					adamantmaxy = iconyStart + height;
				}
				break;
			case 5:
				if (!goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 0, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart1;
					goldmaxx = iconxFinish1;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}
				if (goldbarselected && !this.mc.thePlayer.inventory.hasItem(Items.goldOre) || !(props.currentSmithingLevel >= 40)) {
					goldbarselected = false;
				}
				if (goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 0, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart1;
					goldmaxx = iconxFinish1;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}

				if (!mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 0, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart2;
					mithrilmaxx = iconxFinish2;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				if (mithrilbarselected && !this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre) || !(props.currentSmithingLevel >= 50)) {
					mithrilbarselected = false;
				}
				if (mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 0, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart2;
					mithrilmaxx = iconxFinish2;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				if (!adamantbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 54, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					adamantminx = iconxStart3;
					adamantmaxx = iconxFinish3;
					adamantminy = iconyStart;
					adamantmaxy = iconyStart + height;
				}
				if (adamantbarselected && !this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre) || !(props.currentSmithingLevel >= 70)) {
					adamantbarselected = false;
				}
				if (adamantbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 54, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					adamantminx = iconxStart3;
					adamantmaxx = iconxFinish3;
					adamantminy = iconyStart;
					adamantmaxy = iconyStart + height;
				}
				if (!runebarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 54, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					runeminx = iconxStart4;
					runemaxx = iconxFinish4;
					runeminy = iconyStart;
					runemaxy = iconyStart + height;
				}
				if (runebarselected && !this.mc.thePlayer.inventory.hasItem(Items.runiteOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre) || !(props.currentSmithingLevel >= 85)) {
					runebarselected = false;
				}
				if (runebarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 54, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					runeminx = iconxStart4;
					runemaxx = iconxFinish4;
					runeminy = iconyStart;
					runemaxy = iconyStart + height;
				}
				break;
			}
		}
		if (!ConfigurationHandler.furnaceRequiresSmithing) {
			switch (frame) {
			case 0:
				if (!bronzebarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

					bronzeminx = iconxStart1;
					bronzemaxx = iconxFinish1;
					bronzeminy = iconyStart;
					bronzemaxy = iconyStart + height;
				}
				if (bronzebarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

					bronzeminx = iconxStart1;
					bronzemaxx = iconxFinish1;
					bronzeminy = iconyStart;
					bronzemaxy = iconyStart + height;
				}
				if (!ironbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 57, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart2;
					ironmaxx = iconxFinish2;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}
				if (ironbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre)) {
					ironbarselected = false;
				}
				if (ironbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 170, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart2;
					ironmaxx = iconxFinish2;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}
				if (!silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 108, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart3;
					silvermaxx = iconxFinish3;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (silverbarselected && !this.mc.thePlayer.inventory.hasItem(Items.silverOre)) {
					silverbarselected = false;
				}
				if (silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 108, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart3;
					silvermaxx = iconxFinish3;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (!steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 162, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart4;
					steelmaxx = iconxFinish4;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (steelbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre)) {
					steelbarselected = false;
				}
				if (steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 162, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart4;
					steelmaxx = iconxFinish4;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				break;
			case 1:
				if (!bronzebarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

					bronzeminx = iconxStart1;
					bronzemaxx = iconxFinish1;
					bronzeminy = iconyStart;
					bronzemaxy = iconyStart + height;
				}
				if (bronzebarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

					bronzeminx = iconxStart1;
					bronzemaxx = iconxFinish1;
					bronzeminy = iconyStart;
					bronzemaxy = iconyStart + height;
				}
				if (!ironbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 57, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart2;
					ironmaxx = iconxFinish2;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}
				if (ironbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre)) {
					ironbarselected = false;
				}
				if (ironbarselected && this.mc.thePlayer.inventory.hasItem(Items.ironOre)) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 170, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart2;
					ironmaxx = iconxFinish2;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}

				if (!silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 108, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart3;
					silvermaxx = iconxFinish3;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (silverbarselected && !this.mc.thePlayer.inventory.hasItem(Items.silverOre)) {
					silverbarselected = false;
				}
				if (silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 108, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart3;
					silvermaxx = iconxFinish3;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (!steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 162, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart4;
					steelmaxx = iconxFinish4;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (steelbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre)) {
					steelbarselected = false;
				}
				if (steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 162, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart4;
					steelmaxx = iconxFinish4;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				break;
			case 2:
				if (!ironbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 162, 57, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart1;
					ironmaxx = iconxFinish1;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}
				if (ironbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre)) {
					ironbarselected = false;
				}
				if (ironbarselected && this.mc.thePlayer.inventory.hasItem(Items.ironOre)) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 162, 170, width, height);

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					ironminx = iconxStart1;
					ironmaxx = iconxFinish1;
					ironminy = iconyStart;
					ironmaxy = iconyStart + height;
				}

				if (!silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 108, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart2;
					silvermaxx = iconxFinish2;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (silverbarselected && !this.mc.thePlayer.inventory.hasItem(Items.silverOre)) {
					silverbarselected = false;
				}
				if (silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 108, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart2;
					silvermaxx = iconxFinish2;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (!steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 162, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart3;
					steelmaxx = iconxFinish3;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (steelbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre)) {
					steelbarselected = false;
				}
				if (steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 162, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart3;
					steelmaxx = iconxFinish3;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (!goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 0, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart4;
					goldmaxx = iconxFinish4;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}
				if (goldbarselected && !this.mc.thePlayer.inventory.hasItem(Items.goldOre)) {
					goldbarselected = false;
				}
				if (goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 0, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart4;
					goldmaxx = iconxFinish4;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}

				break;
			case 3:
				if (!silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart1;
					silvermaxx = iconxFinish1;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (silverbarselected && !this.mc.thePlayer.inventory.hasItem(Items.silverOre)) {
					silverbarselected = false;
				}
				if (silverbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 108, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					silverminx = iconxStart1;
					silvermaxx = iconxFinish1;
					silverminy = iconyStart;
					silvermaxy = iconyStart + height;
				}
				if (!steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart2;
					steelmaxx = iconxFinish2;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (steelbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre)) {
					steelbarselected = false;
				}
				if (steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 162, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart2;
					steelmaxx = iconxFinish2;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (!goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 0, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart3;
					goldmaxx = iconxFinish3;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}
				if (goldbarselected && !this.mc.thePlayer.inventory.hasItem(Items.goldOre)) {
					goldbarselected = false;
				}
				if (goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 0, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart3;
					goldmaxx = iconxFinish3;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}

				if (!mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 0, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart4;
					mithrilmaxx = iconxFinish4;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				if (mithrilbarselected && !this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre)) {
					mithrilbarselected = false;
				}
				if (mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 0, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart4;
					mithrilmaxx = iconxFinish4;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				break;
			case 4:
				if (!steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 162, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart1;
					steelmaxx = iconxFinish1;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (steelbarselected && !this.mc.thePlayer.inventory.hasItem(Items.ironOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre)) {
					steelbarselected = false;
				}
				if (steelbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 162, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					steelminx = iconxStart1;
					steelmaxx = iconxFinish1;
					steelminy = iconyStart;
					steelmaxy = iconyStart + height;
				}
				if (!goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 0, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart2;
					goldmaxx = iconxFinish2;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}
				if (goldbarselected && !this.mc.thePlayer.inventory.hasItem(Items.goldOre)) {
					goldbarselected = false;
				}
				if (goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 0, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart2;
					goldmaxx = iconxFinish2;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}

				if (!mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 0, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart3;
					mithrilmaxx = iconxFinish3;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				if (mithrilbarselected && !this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre)) {
					mithrilbarselected = false;
				}
				if (mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 0, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart3;
					mithrilmaxx = iconxFinish3;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				if (!adamantbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 54, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					adamantminx = iconxStart4;
					adamantmaxx = iconxFinish4;
					adamantminy = iconyStart;
					adamantmaxy = iconyStart + height;
				}
				if (adamantbarselected && !this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre)) {
					adamantbarselected = false;
				}
				if (adamantbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 54, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					adamantminx = iconxStart4;
					adamantmaxx = iconxFinish4;
					adamantminy = iconyStart;
					adamantmaxy = iconyStart + height;
				}
				break;
			case 5:
				if (!goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 0, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart1;
					goldmaxx = iconxFinish1;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}
				if (goldbarselected && !this.mc.thePlayer.inventory.hasItem(Items.goldOre)) {
					goldbarselected = false;
				}
				if (goldbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart1, iconyStart, 0, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					goldminx = iconxStart1;
					goldmaxx = iconxFinish1;
					goldminy = iconyStart;
					goldmaxy = iconyStart + height;
				}

				if (!mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 0, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart2;
					mithrilmaxx = iconxFinish2;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				if (mithrilbarselected && !this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre)) {
					mithrilbarselected = false;
				}
				if (mithrilbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart2, iconyStart, 0, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mithrilminx = iconxStart2;
					mithrilmaxx = iconxFinish2;
					mithrilminy = iconyStart;
					mithrilmaxy = iconyStart + height;
				}
				if (!adamantbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 54, 57, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					adamantminx = iconxStart3;
					adamantmaxx = iconxFinish3;
					adamantminy = iconyStart;
					adamantmaxy = iconyStart + height;
				}
				if (adamantbarselected && !this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre)) {
					adamantbarselected = false;
				}
				if (adamantbarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart3, iconyStart, 54, 170, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					adamantminx = iconxStart3;
					adamantmaxx = iconxFinish3;
					adamantminy = iconyStart;
					adamantmaxy = iconyStart + height;
				}
				if (!runebarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 54, 0, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					runeminx = iconxStart4;
					runemaxx = iconxFinish4;
					runeminy = iconyStart;
					runemaxy = iconyStart + height;
				}
				if (runebarselected && !this.mc.thePlayer.inventory.hasItem(Items.runiteOre) || !this.mc.thePlayer.inventory.hasItem(Items.coalOre)) {
					runebarselected = false;
				}
				if (runebarselected) {
					mc.getTextureManager().bindTexture(this.widgetTextures);
					this.drawTexturedModalRect(iconxStart4, iconyStart, 54, 113, width, height);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					runeminx = iconxStart4;
					runemaxx = iconxFinish4;
					runeminy = iconyStart;
					runemaxy = iconyStart + height;
				}
				break;
			}
		}
	}

	protected void mouseClicked(int varx, int vary, int p_73864_3_) {
		if (varx > doneBtn.xPosition && varx < doneBtn.xPosition + doneBtn.width && vary > doneBtn.yPosition && vary < doneBtn.yPosition + doneBtn.height) {
			actionPerformed(doneBtn);
		}
		if (varx > craftallBtn.xPosition && varx < craftallBtn.xPosition + craftallBtn.width && vary > craftallBtn.yPosition && vary < doneBtn.yPosition + doneBtn.height) {
			actionPerformed(craftallBtn);
		}

		if (varx > onecraftBtn.xPosition && varx < onecraftBtn.xPosition + onecraftBtn.width && vary > onecraftBtn.yPosition && vary < doneBtn.yPosition + doneBtn.height) {
			actionPerformed(onecraftBtn);
		}
		if (varx > fivecraftBtn.xPosition && varx < fivecraftBtn.xPosition + fivecraftBtn.width && vary > fivecraftBtn.yPosition && vary < doneBtn.yPosition + doneBtn.height) {
			actionPerformed(fivecraftBtn);
		}
		if (varx > tencraftBtn.xPosition && varx < tencraftBtn.xPosition + tencraftBtn.width && vary > tencraftBtn.yPosition && vary < doneBtn.yPosition + doneBtn.height) {
			actionPerformed(tencraftBtn);
		}
		if (varx > craftBtn.xPosition && varx < craftBtn.xPosition + craftBtn.width && vary > craftBtn.yPosition && vary < doneBtn.yPosition + doneBtn.height) {
			actionPerformed(craftBtn);
		}
		if (varx > rightBtn.xPosition && varx < rightBtn.xPosition + rightBtn.width && vary > rightBtn.yPosition && vary < doneBtn.yPosition + doneBtn.height) {
			actionPerformed(rightBtn);
		}
		if (varx > leftBtn.xPosition && varx < leftBtn.xPosition + leftBtn.width && vary > leftBtn.yPosition && vary < doneBtn.yPosition + doneBtn.height) {
			actionPerformed(leftBtn);
		}
		switch (frame) {
		case 0:
			if (varx > bronzeminx && varx < bronzemaxx && vary > bronzeminy && vary < bronzemaxy) {
				resetBools("bronze");
				bronzebarselected = !bronzebarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
			if (varx > ironminx && varx < ironmaxx && vary > ironminy && vary < ironmaxy) {
				resetBools("iron");
				ironbarselected = !ironbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
			if (varx > silverminx && varx < silvermaxx && vary > silverminy && vary < silvermaxy) {
				resetBools("silver");
				silverbarselected = !silverbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
			if (varx > steelminx && varx < steelmaxx && vary > steelminy && vary < steelmaxy) {
				resetBools("steel");
				steelbarselected = !steelbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
		case 1:
			if (varx > bronzeminx && varx < bronzemaxx && vary > bronzeminy && vary < bronzemaxy) {
				resetBools("bronze");
				bronzebarselected = !bronzebarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
			if (varx > ironminx && varx < ironmaxx && vary > ironminy && vary < ironmaxy) {
				resetBools("iron");
				ironbarselected = !ironbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}

			if (varx > silverminx && varx < silvermaxx && vary > silverminy && vary < silvermaxy) {
				resetBools("silver");
				silverbarselected = !silverbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}

			if (varx > steelminx && varx < steelmaxx && vary > steelminy && vary < steelmaxy) {
				resetBools("steel");
				steelbarselected = !steelbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}

		case 2:
			if (varx > ironminx && varx < ironmaxx && vary > ironminy && vary < ironmaxy) {
				resetBools("iron");
				ironbarselected = !ironbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
			if (varx > silverminx && varx < silvermaxx && vary > silverminy && vary < silvermaxy) {

				resetBools("silver");
				silverbarselected = !silverbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
			if (varx > steelminx && varx < steelmaxx && vary > steelminy && vary < steelmaxy) {
				resetBools("steel");
				steelbarselected = !steelbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
			if (varx > goldminx && varx < goldmaxx && vary > goldminy && vary < goldmaxy) {
				resetBools("gold");
				goldbarselected = !goldbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
		case 3:
			if (varx > silverminx && varx < silvermaxx && vary > silverminy && vary < silvermaxy) {
				resetBools("silver");
				silverbarselected = !silverbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
			if (varx > steelminx && varx < steelmaxx && vary > steelminy && vary < steelmaxy) {
				resetBools("steel");
				steelbarselected = !steelbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
			if (varx > goldminx && varx < goldmaxx && vary > goldminy && vary < goldmaxy) {
				resetBools("gold");
				goldbarselected = !goldbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
			if (varx > mithrilminx && varx < mithrilmaxx && vary > mithrilminy && vary < mithrilmaxy) {
				resetBools("mithril");
				mithrilbarselected = !mithrilbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;

				break;
			}
		case 4:
			if (varx > steelminx && varx < steelmaxx && vary > steelminy && vary < steelmaxy) {
				resetBools("steel");
				steelbarselected = !steelbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;
				break;
			}
			if (varx > goldminx && varx < goldmaxx && vary > goldminy && vary < goldmaxy) {
				resetBools("gold");
				goldbarselected = !goldbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;
				break;
			}
			if (varx > mithrilminx && varx < mithrilmaxx && vary > mithrilminy && vary < mithrilmaxy) {
				resetBools("mithril");
				mithrilbarselected = !mithrilbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;
				break;
			}
			if (varx > adamantminx && varx < adamantmaxx && vary > adamantminy && vary < adamantmaxy) {
				resetBools("adamant");
				adamantbarselected = !adamantbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;
				break;
			}
		case 5:
			if (varx > goldminx && varx < goldmaxx && vary > goldminy && vary < goldmaxy) {
				resetBools("gold");
				goldbarselected = !goldbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;
				break;
			}
			if (varx > mithrilminx && varx < mithrilmaxx && vary > mithrilminy && vary < mithrilmaxy) {
				resetBools("mithril");
				mithrilbarselected = !mithrilbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;
				break;
			}
			if (varx > adamantminx && varx < adamantmaxx && vary > adamantminy && vary < adamantmaxy) {
				resetBools("adamant");
				adamantbarselected = !adamantbarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;
				break;
			}
			if (varx > runeminx && varx < runemaxx && vary > runeminy && vary < runemaxy) {
				resetBools("rune");
				runebarselected = !runebarselected;
				this.craftAmount.setText("");
				this.amountToCraft = 0;
				break;
			}
		}

	}

	protected boolean IsButtonMouseovered(int varx, int vary, GuiButton button) {
		ExtendedPlayer props = ExtendedPlayer.get(this.mc.thePlayer);
		switch (frame) {
		case 0:
			if (bronzeminx < varx && varx < bronzemaxx && bronzeminy < vary && vary < bronzemaxy) {
				barToolTips("bronze", varx, vary);

			}
			if (varx > ironminx && varx < ironmaxx && vary > ironminy && vary < ironmaxy) {

				barToolTips("iron", varx, vary);

			}

			if (varx > silverminx && varx < silvermaxx && vary > silverminy && vary < silvermaxy) {

				barToolTips("silver", varx, vary);

			}

			if (varx > steelminx && varx < steelmaxx && vary > steelminy && vary < steelmaxy) {
				barToolTips("steel", varx, vary);
			}
			break;
		case 1:
			if (bronzeminx < varx && varx < bronzemaxx && bronzeminy < vary && vary < bronzemaxy) {
				barToolTips("bronze", varx, vary);
			}
			if (varx > ironminx && varx < ironmaxx && vary > ironminy && vary < ironmaxy) {
				barToolTips("iron", varx, vary);
			}

			if (varx > silverminx && varx < silvermaxx && vary > silverminy && vary < silvermaxy) {
				barToolTips("silver", varx, vary);
			}

			if (varx > steelminx && varx < steelmaxx && vary > steelminy && vary < steelmaxy) {
				barToolTips("steel", varx, vary);
			}
			break;
		case 2:
			if (varx > ironminx && varx < ironmaxx && vary > ironminy && vary < ironmaxy) {
				barToolTips("iron", varx, vary);
			}

			if (varx > silverminx && varx < silvermaxx && vary > silverminy && vary < silvermaxy) {
				barToolTips("silver", varx, vary);
			}

			if (varx > steelminx && varx < steelmaxx && vary > steelminy && vary < steelmaxy) {
				barToolTips("steel", varx, vary);
			}
			if (varx > goldminx && varx < goldmaxx && vary > goldminy && vary < goldmaxy) {
				barToolTips("gold", varx, vary);
			}
			break;
		case 3:
			if (varx > silverminx && varx < silvermaxx && vary > silverminy && vary < silvermaxy) {
				barToolTips("silver", varx, vary);
			}

			if (varx > steelminx && varx < steelmaxx && vary > steelminy && vary < steelmaxy) {
				barToolTips("steel", varx, vary);
			}
			if (varx > goldminx && varx < goldmaxx && vary > goldminy && vary < goldmaxy) {
				barToolTips("gold", varx, vary);
			}
			if (varx > mithrilminx && varx < mithrilmaxx && vary > mithrilminy && vary < mithrilmaxy) {
				barToolTips("mithril", varx, vary);
			}
			break;
		case 4:
			if (varx > steelminx && varx < steelmaxx && vary > steelminy && vary < steelmaxy) {
				barToolTips("steel", varx, vary);
			}
			if (varx > goldminx && varx < goldmaxx && vary > goldminy && vary < goldmaxy) {
				barToolTips("gold", varx, vary);
			}
			if (varx > mithrilminx && varx < mithrilmaxx && vary > mithrilminy && vary < mithrilmaxy) {
				barToolTips("mithril", varx, vary);
			}
			if (varx > adamantminx && varx < adamantmaxx && vary > adamantminy && vary < adamantmaxy) {
				barToolTips("adamant", varx, vary);
			}
			break;

		case 5:
			if (varx > goldminx && varx < goldmaxx && vary > goldminy && vary < goldmaxy) {
				barToolTips("gold", varx, vary);
			}
			if (varx > mithrilminx && varx < mithrilmaxx && vary > mithrilminy && vary < mithrilmaxy) {
				barToolTips("mithril", varx, vary);
			}
			if (varx > adamantminx && varx < adamantmaxx && vary > adamantminy && vary < adamantmaxy) {
				barToolTips("adamant", varx, vary);
			}
			if (varx > runeminx && varx < runemaxx && vary > runeminy && vary < runemaxy) {
				barToolTips("rune", varx, vary);
			}
			break;
		}
		return true;
	}

	private void DrawTooltipScreen(int varx, int vary, String string) {
		this.renderToolTip(new ItemStack(Items.bronzeBar), varx, vary);

	}

	protected void RenderTooltip(int x, int y, String tooltip) {
		String[] tooltipArray = ParseTooltipArrayFromString(tooltip);

		int tooltipWidth = GetTooltipWidth(tooltipArray);
		int tooltipHeight = GetTooltipHeight(tooltipArray);

		int tooltipX = x + tooltipXOffset;
		int tooltipY = y + tooltipYOffset;

		if (tooltipX > width - tooltipWidth - 7)
			tooltipX = width - tooltipWidth - 7;
		if (tooltipY > height - tooltipHeight - 8)
			tooltipY = height - tooltipHeight - 8;

		// render the background inside box
		int innerAlpha = -0xFEFFFF0; // very very dark purple
		drawGradientRect(tooltipX, tooltipY - 1, tooltipX + tooltipWidth + 6, tooltipY, innerAlpha, innerAlpha);
		drawGradientRect(tooltipX, tooltipY + tooltipHeight + 6, tooltipX + tooltipWidth + 6, tooltipY + tooltipHeight + 7, innerAlpha, innerAlpha);
		drawGradientRect(tooltipX, tooltipY, tooltipX + tooltipWidth + 6, tooltipY + tooltipHeight + 6, innerAlpha, innerAlpha);
		drawGradientRect(tooltipX - 1, tooltipY, tooltipX, tooltipY + tooltipHeight + 6, innerAlpha, innerAlpha);
		drawGradientRect(tooltipX + tooltipWidth + 6, tooltipY, tooltipX + tooltipWidth + 7, tooltipY + tooltipHeight + 6, innerAlpha, innerAlpha);

		// render the background outside box
		int outerAlpha1 = 0x505000FF;
		int outerAlpha2 = (outerAlpha1 & 0xFEFEFE) >> 1 | outerAlpha1 & -0x1000000;
		drawGradientRect(tooltipX, tooltipY + 1, tooltipX + 1, tooltipY + tooltipHeight + 6 - 1, outerAlpha1, outerAlpha2);
		drawGradientRect(tooltipX + tooltipWidth + 5, tooltipY + 1, tooltipX + tooltipWidth + 7, tooltipY + tooltipHeight + 6 - 1, outerAlpha1, outerAlpha2);
		drawGradientRect(tooltipX, tooltipY, tooltipX + tooltipWidth + 3, tooltipY + 1, outerAlpha1, outerAlpha1);
		drawGradientRect(tooltipX, tooltipY + tooltipHeight + 5, tooltipX + tooltipWidth + 7, tooltipY + tooltipHeight + 6, outerAlpha2, outerAlpha2);

		// render the foreground text
		int lineCount = 0;
		for (String s : tooltipArray) {
			mc.fontRenderer.drawStringWithShadow(s, tooltipX + 2, tooltipY + 2 + lineCount * LINE_HEIGHT, 0xFFFFFF);
			lineCount++;
		}
	}

	/**
	 * Converts a String representation of a tooltip into a String[], and also
	 * decodes any font codes used.
	 * 
	 * @param s
	 *            Ex: "Hello,_nI am your _ltooltip_r and you love me."
	 * @return An array of Strings such that each String width does not exceed
	 *         tooltipMaxWidth
	 */
	protected String[] ParseTooltipArrayFromString(String s) {
		s = DecodeStringCodes(s);
		String[] tooltipSections = s.split(tooltipNewlineDelimeter);
		ArrayList<String> tooltipArrayList = new ArrayList<String>();

		for (String section : tooltipSections) {
			String tooltip = "";
			String[] tooltipWords = section.split(" ");

			for (int i = 0; i < tooltipWords.length; i++) {
				int lineWidthWithNextWord = mc.fontRenderer.getStringWidth(tooltip + tooltipWords[i]);
				if (lineWidthWithNextWord > tooltipMaxWidth) {
					tooltipArrayList.add(tooltip.trim());
					tooltip = tooltipWords[i] + " ";
				} else {
					tooltip += tooltipWords[i] + " ";
				}
			}

			tooltipArrayList.add(tooltip.trim());
		}

		String[] tooltipArray = new String[tooltipArrayList.size()];
		tooltipArrayList.toArray(tooltipArray);

		return tooltipArray;
	}

	/**
	 * Decodes any font codes into something useable by the FontRenderer.
	 * 
	 * @param s
	 *            E.x.: "Hello,_nI am your _ltooltip_r and you love me."
	 * @return E.x. output (html not included): <br>
	 *         "Hello,<br>
	 *         I am your <b>tooltip</b> and you love me."
	 */
	private String DecodeStringCodes(String s) {
		return s.replace("_0", FontCodes.BLACK).replace("_1", FontCodes.DARK_BLUE).replace("_2", FontCodes.DARK_GREEN).replace("_3", FontCodes.DARK_AQUA).replace("_4", FontCodes.DARK_RED).replace("_5", FontCodes.DARK_PURPLE).replace("_6", FontCodes.GOLD).replace("_7", FontCodes.GRAY).replace("_8", FontCodes.DARK_GREY).replace("_9", FontCodes.BLUE).replace("_a", FontCodes.GREEN).replace("_b", FontCodes.AQUA).replace("_c", FontCodes.RED).replace("_d", FontCodes.LIGHT_PURPLE).replace("_e", FontCodes.YELLOW).replace("_f", FontCodes.WHITE).replace("_k", FontCodes.OBFUSCATED).replace("_l", FontCodes.BOLD).replace("_m", FontCodes.STRIKETHROUGH).replace("_n", FontCodes.UNDERLINE).replace("_o", FontCodes.ITALICS).replace("_r", FontCodes.RESET);
	}

	/***
	 * Gets the width of the tooltip in pixels.
	 * 
	 * @param tooltipArray
	 * @return
	 */
	private int GetTooltipWidth(String[] tooltipArray) {
		int longestWidth = 0;
		for (String s : tooltipArray) {
			int width = mc.fontRenderer.getStringWidth(s);
			if (width > longestWidth)
				longestWidth = width;
		}
		return longestWidth;
	}

	/**
	 * Gets the height of the tooltip in pixels.
	 * 
	 * @param tooltipArray
	 * @return
	 */
	private int GetTooltipHeight(String[] tooltipArray) {
		int tooltipHeight = mc.fontRenderer.FONT_HEIGHT - 2;
		if (tooltipArray.length > 1) {
			tooltipHeight += (tooltipArray.length - 1) * LINE_HEIGHT;
		}
		return tooltipHeight;
	}

	/**
	 * Gets a protected/private field from a class using reflection.
	 * 
	 * @param <T>
	 *            The return type of the field you are getting
	 * @param <E>
	 *            The class the field is in
	 * @param classToAccess
	 *            The ".class" of the class the field is in
	 * @param instance
	 *            The instance of the class
	 * @param fieldNames
	 *            comma seperated names the field may have (i.e. obfuscated, non
	 *            obfuscated). Obfustated field names can be found in
	 *            fml/conf/fields.csv
	 * @return
	 */
	public static <T, E> T GetFieldByReflection(Class<? super E> classToAccess, E instance, String... fieldNames) {
		Field field = null;
		for (String fieldName : fieldNames) {
			try {
				field = classToAccess.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}

			if (field != null)
				break;
		}

		if (field != null) {
			field.setAccessible(true);
			T fieldT = null;
			try {
				fieldT = (T) field.get(instance);
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}

			return fieldT;
		}

		return null;
	}

	public class FontCodes {
		// color codes for rendered strings
		public static final String	BLACK			= "\2470";
		public static final String	DARK_BLUE		= "\2471";
		public static final String	DARK_GREEN		= "\2472";
		public static final String	DARK_AQUA		= "\2473";
		public static final String	DARK_RED		= "\2474";
		public static final String	DARK_PURPLE		= "\2475";
		public static final String	GOLD			= "\2476";
		public static final String	GRAY			= "\2477";
		public static final String	DARK_GREY		= "\2478";
		public static final String	BLUE			= "\2479";
		public static final String	GREEN			= "\247a";
		public static final String	AQUA			= "\247b";
		public static final String	RED				= "\247c";
		public static final String	LIGHT_PURPLE	= "\247d";
		public static final String	YELLOW			= "\247e";
		public static final String	WHITE			= "\247f";

		// font styles
		public static final String	OBFUSCATED		= "\247k";
		public static final String	BOLD			= "\247l";
		public static final String	STRIKETHROUGH	= "\247m";
		public static final String	UNDERLINE		= "\247n";
		public static final String	ITALICS			= "\247o";

		public static final String	RESET			= "\247r";
	}

	public void resetBools(String string) {
		switch (string) {
		case "bronze":
			ironbarselected = silverbarselected = steelbarselected = goldbarselected = mithrilbarselected = adamantbarselected = runebarselected = false;
		case "iron":
			bronzebarselected = silverbarselected = steelbarselected = goldbarselected = mithrilbarselected = adamantbarselected = runebarselected = false;
		case "silver":
			ironbarselected = bronzebarselected = steelbarselected = goldbarselected = mithrilbarselected = adamantbarselected = runebarselected = false;
		case "steel":
			ironbarselected = silverbarselected = bronzebarselected = goldbarselected = mithrilbarselected = adamantbarselected = runebarselected = false;
		case "gold":
			ironbarselected = silverbarselected = steelbarselected = bronzebarselected = mithrilbarselected = adamantbarselected = runebarselected = false;
		case "mithril":
			ironbarselected = silverbarselected = steelbarselected = goldbarselected = bronzebarselected = adamantbarselected = runebarselected = false;
		case "adamant":
			ironbarselected = silverbarselected = steelbarselected = goldbarselected = mithrilbarselected = bronzebarselected = runebarselected = false;
		case "rune":
			ironbarselected = silverbarselected = steelbarselected = goldbarselected = mithrilbarselected = adamantbarselected = bronzebarselected = false;
		}
	}

	public void barToolTips(String string, int varx, int vary) {
		ExtendedPlayer props = ExtendedPlayer.get(this.mc.thePlayer);
		switch (string) {
		case "bronze":// Have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.copperOre) && this.mc.thePlayer.inventory.hasItem(Items.tinOre) && props.currentSmithingLevel >= 1) {
				this.RenderTooltip(varx, vary, "_lBronze Bar:_r _p" + "\247c1x Copper Ore_r_p" + "1x Tin Ore_p" + "Requires lvl 1 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.copperOre) && !this.mc.thePlayer.inventory.hasItem(Items.tinOre) && props.currentSmithingLevel >= 1) {
				this.RenderTooltip(varx, vary, "_lBronze Bar:_r _p" + "1x Copper Ore_p" + "\247c1x Tin Ore_r_p" + "Requires lvl 1 Smithing");
				break;
			}
			if (!this.mc.thePlayer.inventory.hasItem(Items.copperOre) && !this.mc.thePlayer.inventory.hasItem(Items.tinOre) && props.currentSmithingLevel >= 1) {
				this.RenderTooltip(varx, vary, "_lBronze Bar:_r _p" + "\247c1x Copper Ore_r_p" + "\247c1x Tin Ore_r_p" + "Requires lvl 1 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.copperOre) && this.mc.thePlayer.inventory.hasItem(Items.tinOre) && props.currentSmithingLevel >= 1) {
				this.RenderTooltip(varx, vary, "_lBronze Bar:_r _p" + "1x Copper Ore_p" + "1x Tin Ore_p" + "Requires lvl 1 Smithing");
				break;
			}
			// Don't have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.copperOre) && this.mc.thePlayer.inventory.hasItem(Items.tinOre) && !(props.currentSmithingLevel >= 1)) {
				this.RenderTooltip(varx, vary, "_lBronze Bar:_r _p" + "\247c1x Copper Ore_r_p" + "1x Tin Ore_p" + "\247cRequires lvl 1 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.copperOre) && !this.mc.thePlayer.inventory.hasItem(Items.tinOre) && !(props.currentSmithingLevel >= 1)) {
				this.RenderTooltip(varx, vary, "_lBronze Bar:_r _p" + "1x Copper Ore_p" + "\247c1x Tin Ore_r_p" + "\247cRequires lvl 1 Smithing");
				break;
			}
			if (!this.mc.thePlayer.inventory.hasItem(Items.copperOre) && !this.mc.thePlayer.inventory.hasItem(Items.tinOre) && !(props.currentSmithingLevel >= 1)) {
				this.RenderTooltip(varx, vary, "_lBronze Bar:_r _p" + "\247c1x Copper Ore_r_p" + "\247c1x Tin Ore_r_p" + "\247cRequires lvl 1 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.copperOre) && this.mc.thePlayer.inventory.hasItem(Items.tinOre) && !(props.currentSmithingLevel >= 1)) {
				this.RenderTooltip(varx, vary, "_lBronze Bar:_r _p" + "1x Copper Ore_p" + "1x Tin Ore_p" + "\247cRequires lvl 1 Smithing");
				break;
			}
			break;
		case "iron": // Have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.ironOre) && props.currentSmithingLevel >= 15) {
				this.RenderTooltip(varx, vary, "_lIron Bar:_r _p" + "\247c1x Iron Ore_r_p" + "Requires lvl 15 Smithing_p" + "There is a chance that you will not create a bar.");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.ironOre) && props.currentSmithingLevel >= 15) {
				this.RenderTooltip(varx, vary, "_lIron Bar:_r _p" + "1x Iron Ore_p" + "Requires lvl 15 Smithing_p" + "There is a chance that you will not create a bar.");
				break;
			}

			// Don't have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.ironOre) && !(props.currentSmithingLevel >= 15)) {
				this.RenderTooltip(varx, vary, "_lIron Bar:_r _p" + "\247c1x Iron Ore_r_p" + "\247cRequires lvl 15 Smithing_p" + "There is a chance that you will not create a bar.");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.ironOre) && !(props.currentSmithingLevel >= 15)) {
				this.RenderTooltip(varx, vary, "_lIron Bar:_r _p" + "1x Iron Ore_p" + "\247cRequires lvl 15 Smithing_p" + "There is a chance that you will not create a bar.");
				break;
			}
			break;
		case "silver":// Have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.silverOre) && props.currentSmithingLevel >= 20) {
				this.RenderTooltip(varx, vary, "_lSilver Bar:_r _p" + "\247c1x Silver Ore_r_p" + "Requires lvl 20 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.silverOre) && props.currentSmithingLevel >= 20) {
				this.RenderTooltip(varx, vary, "_lSilver Bar:_r _p" + "1x Silver Ore_p" + "Requires lvl 20 Smithing");
				break;
			}

			// Don't have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.silverOre) && !(props.currentSmithingLevel >= 20)) {
				this.RenderTooltip(varx, vary, "_lSilver Bar:_r _p" + "\247c1x Silver Ore_r_p" + "\247cRequires lvl 20 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.silverOre) && !(props.currentSmithingLevel >= 20)) {
				this.RenderTooltip(varx, vary, "_lSilver Bar:_r _p" + "1x Silver Ore_p" + "\247cRequires lvl 20 Smithing");
				break;
			}
			break;
		case "steel":// Have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.ironOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 2) && props.currentSmithingLevel >= 30) {
				this.RenderTooltip(varx, vary, "_lSteel Bar:_r _p" + "\247c1x Iron Ore_r_p" + "2x Coal Ore_p" + "Requires lvl 30 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.ironOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 2) && props.currentSmithingLevel >= 30) {
				this.RenderTooltip(varx, vary, "_lSteel Bar:_r _p" + "1x Iron Ore_p" + "\247c2x Coal Ore_r_p" + "Requires lvl 30 Smithing");
				break;
			}
			if (!this.mc.thePlayer.inventory.hasItem(Items.ironOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 2) && props.currentSmithingLevel >= 30) {
				this.RenderTooltip(varx, vary, "_lSteel Bar:_r _p" + "\247c1x Iron Ore_r_p" + "\247c2x Coal Ore_r_p" + "Requires lvl 30 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.ironOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 2) && props.currentSmithingLevel >= 30) {
				this.RenderTooltip(varx, vary, "_lSteel Bar:_r _p" + "1x Iron Ore_p" + "2x Coal Ore_p" + "Requires lvl 30 Smithing");
				break;
			}
			// Don't have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.ironOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 2) && !(props.currentSmithingLevel >= 30)) {
				this.RenderTooltip(varx, vary, "_lSteel Bar:_r _p" + "\247c1x Iron Ore_r_p" + "2x Coal Ore_p" + "\247cRequires lvl 30 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.ironOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 2) && !(props.currentSmithingLevel >= 30)) {
				this.RenderTooltip(varx, vary, "_lSteel Bar:_r _p" + "1x Iron Ore_p" + "\247c2x Coal Ore_r_p" + "\247cRequires lvl 30 Smithing");
				break;
			}
			if (!this.mc.thePlayer.inventory.hasItem(Items.ironOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 2) && !(props.currentSmithingLevel >= 30)) {
				this.RenderTooltip(varx, vary, "_lSteel Bar:_r _p" + "\247c1x Iron Ore_r_p" + "\247c2x Coal Ore_r_p" + "\247cRequires lvl 30 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.ironOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 2) && !(props.currentSmithingLevel >= 30)) {
				this.RenderTooltip(varx, vary, "_lSteel Bar:_r _p" + "1x Iron Ore_p" + "2x Coal Ore_p" + "\247cRequires lvl 30 Smithing");
				break;
			}
			break;
		case "gold":
			if (!this.mc.thePlayer.inventory.hasItem(Items.goldOre) && props.currentSmithingLevel >= 40) {
				this.RenderTooltip(varx, vary, "_lGold Bar:_r _p" + "\247c1x Gold Ore_r_p" + "Requires lvl 40 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.goldOre) && props.currentSmithingLevel >= 40) {
				this.RenderTooltip(varx, vary, "_lGold Bar:_r _p" + "1x Gold Ore_p" + "Requires lvl 40 Smithing");
				break;
			}

			// Don't have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.goldOre) && !(props.currentSmithingLevel >= 40)) {
				this.RenderTooltip(varx, vary, "_lGold Bar:_r _p" + "\247c1x Gold Ore_r_p" + "\247cRequires lvl 40 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.goldOre) && !(props.currentSmithingLevel >= 40)) {
				this.RenderTooltip(varx, vary, "_lGold Bar:_r _p" + "1x Gold Ore_p" + "\247cRequires lvl 40 Smithing");
				break;
			}
			break;
		case "mithril":
			if (!this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 4) && props.currentSmithingLevel >= 50) {
				this.RenderTooltip(varx, vary, "_lMithril Bar:_r _p" + "\247c1x Mithril Ore_r_p" + "4x Coal Ore_p" + "Requires lvl 50 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 4) && props.currentSmithingLevel >= 50) {
				this.RenderTooltip(varx, vary, "_lMithril Bar:_r _p" + "1x Mithril Ore_p" + "\247c4x Coal Ore_r_p" + "Requires lvl 50 Smithing");
				break;
			}
			if (!this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 4) && props.currentSmithingLevel >= 50) {
				this.RenderTooltip(varx, vary, "_lMithril Bar:_r _p" + "\247c1x Mithril Ore_r_p" + "\247c4x Coal Ore_r_p" + "Requires lvl 50 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 4) && props.currentSmithingLevel >= 50) {
				this.RenderTooltip(varx, vary, "_lMithril Bar:_r _p" + "1x Mithril Ore_p" + "4x Coal Ore_p" + "Requires lvl 50 Smithing");
				break;
			}
			// Don't have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 4) && !(props.currentSmithingLevel >= 50)) {
				this.RenderTooltip(varx, vary, "_lMithril Bar:_r _p" + "\247c1x Mithril Ore_r_p" + "4x Coal Ore_p" + "\247cRequires lvl 50 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 4) && !(props.currentSmithingLevel >= 50)) {
				this.RenderTooltip(varx, vary, "_lMithril Bar:_r _p" + "1x Mithril Ore_p" + "\247c4x Coal Ore_r_p" + "\247cRequires lvl 50 Smithing");
				break;
			}
			if (!this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 4) && !(props.currentSmithingLevel >= 50)) {
				this.RenderTooltip(varx, vary, "_lMithril Bar:_r _p" + "\247c1x Mithril Ore_r_p" + "\247c4x Coal Ore_r_p" + "\247cRequires lvl 50 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.mithrilOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 4) && !(props.currentSmithingLevel >= 50)) {
				this.RenderTooltip(varx, vary, "_lMithril Bar:_r _p" + "1x Mithril Ore_p" + "4x Coal Ore_p" + "\247cRequires lvl 50 Smithing");
				break;
			}
			break;
		case "adamant":
			if (!this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 6) && props.currentSmithingLevel >= 70) {
				this.RenderTooltip(varx, vary, "_lAdamant Bar:_r _p" + "\247c1x Adamant Ore_r_p" + "6x Coal Ore_p" + "Requires lvl 70 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 6) && props.currentSmithingLevel >= 70) {
				this.RenderTooltip(varx, vary, "_lAdamant Bar:_r _p" + "1x Adamant Ore_p" + "\247c6x Coal Ore_r_p" + "Requires lvl 70 Smithing");
				break;
			}
			if (!this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 6) && props.currentSmithingLevel >= 70) {
				this.RenderTooltip(varx, vary, "_lAdamant Bar:_r _p" + "\247c1x Adamant Ore_r_p" + "\247c6x Coal Ore_r_p" + "Requires lvl 70 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 6) && props.currentSmithingLevel >= 70) {
				this.RenderTooltip(varx, vary, "_lAdamant Bar:_r _p" + "1x Adamant Ore_p" + "6x Coal Ore_p" + "Requires lvl 70 Smithing");
				break;
			}
			// Don't have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 6) && !(props.currentSmithingLevel >= 70)) {
				this.RenderTooltip(varx, vary, "_lAdamant Bar:_r _p" + "\247c1x Adamant Ore_r_p" + "6x Coal Ore_p" + "\247cRequires lvl 70 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 6) && !(props.currentSmithingLevel >= 70)) {
				this.RenderTooltip(varx, vary, "_lAdamant Bar:_r _p" + "1x Adamant Ore_p" + "\247c6x Coal Ore_r_p" + "\247cRequires lvl 70 Smithing");
				break;
			}
			if (!this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 6) && !(props.currentSmithingLevel >= 70)) {
				this.RenderTooltip(varx, vary, "_lAdamant Bar:_r _p" + "\247c1x Adamant Ore_r_p" + "\247c6x Coal Ore_r_p" + "\247cRequires lvl 70 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.adamantiteOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 6) && !(props.currentSmithingLevel >= 70)) {
				this.RenderTooltip(varx, vary, "_lAdamant Bar:_r _p" + "1x Adamant Ore_p" + "6x Coal Ore_p" + "\247cRequires lvl 70 Smithing");
				break;
			}
			break;
		case "rune":
			if (!this.mc.thePlayer.inventory.hasItem(Items.runiteOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 8) && props.currentSmithingLevel >= 85) {
				this.RenderTooltip(varx, vary, "_lRune Bar:_r _p" + "\247c1x Rune Ore_r_p" + "8x Coal Ore_p" + "Requires lvl 85 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.runiteOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 8) && props.currentSmithingLevel >= 85) {
				this.RenderTooltip(varx, vary, "_lRune Bar:_r _p" + "1x Rune Ore_p" + "\247c8x Coal Ore_r_p" + "Requires lvl 85 Smithing");
				break;
			}
			if (!this.mc.thePlayer.inventory.hasItem(Items.runiteOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 8) && props.currentSmithingLevel >= 85) {
				this.RenderTooltip(varx, vary, "_lRune Bar:_r _p" + "\247c1x Rune Ore_r_p" + "\247c8x Coal Ore_r_p" + "Requires lvl 85 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.runiteOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 8) && props.currentSmithingLevel >= 85) {
				this.RenderTooltip(varx, vary, "_lRune Bar:_r _p" + "1x Rune Ore_p" + "8x Coal Ore_p" + "Requires lvl 85 Smithing");
				break;
			}
			// Don't have level
			if (!this.mc.thePlayer.inventory.hasItem(Items.runiteOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 8) && !(props.currentSmithingLevel >= 85)) {
				this.RenderTooltip(varx, vary, "_lRune Bar:_r _p" + "\247c1x Rune Ore_r_p" + "8x Coal Ore_p" + "\247cRequires lvl 85 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.runiteOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 8) && !(props.currentSmithingLevel >= 85)) {
				this.RenderTooltip(varx, vary, "_lRune Bar:_r _p" + "1x Rune Ore_p" + "\247c8x Coal Ore_r_p" + "\247cRequires lvl 85 Smithing");
				break;
			}
			if (!this.mc.thePlayer.inventory.hasItem(Items.runiteOre) && !this.hasNumItems(this.mc.thePlayer, Items.coalOre, 8) && !(props.currentSmithingLevel >= 85)) {
				this.RenderTooltip(varx, vary, "_lRune Bar:_r _p" + "\247c1x Rune Ore_r_p" + "\247c8x Coal Ore_r_p" + "\247cRequires lvl 85 Smithing");
				break;
			}
			if (this.mc.thePlayer.inventory.hasItem(Items.runiteOre) && this.hasNumItems(this.mc.thePlayer, Items.coalOre, 8) && !(props.currentSmithingLevel >= 85)) {
				this.RenderTooltip(varx, vary, "_lRune Bar:_r _p" + "1x Rune Ore_p" + "8x Coal Ore_p" + "\247cRequires lvl 85 Smithing");
				break;
			}
			break;
		}
	}

	public boolean hasNumItems(EntityPlayer p, Item req, int n) {

		for (int i = 0; i < p.inventory.getSizeInventory(); i++) {
			ItemStack stack = p.inventory.getStackInSlot(i);
			if (stack != null && stack.getItem() == req) {
				n -= stack.stackSize;
				if (n < 1) {

					return true;
				}
			}

		}
		return false;

	}

	public int getNumItems(EntityPlayer p, Item req) {
		int n = 0;
		for (int i = 0; i < p.inventory.getSizeInventory(); i++) {
			ItemStack stack = p.inventory.getStackInSlot(i);
			if (stack != null && stack.getItem() == req) {
				n += stack.stackSize;

			}

		}
		return n;
	}

	protected void keyTyped(char var1, int p_73869_2_) {
		if (this.craftAmount.isFocused()) {
			if (((var1 < '0') || (var1 > '9')) && (var1 != '\b')) {
			} else {
				this.craftAmount.textboxKeyTyped(var1, p_73869_2_);
				if (bronzebarselected) {
					if (hasNumItems(this.mc.thePlayer, Items.copperOre, 1) && hasNumItems(this.mc.thePlayer, Items.tinOre, 1)) {
						int numberOfCopperOre, numberOfTinOre;

						numberOfCopperOre = getNumItems(this.mc.thePlayer, Items.copperOre);
						numberOfTinOre = getNumItems(this.mc.thePlayer, Items.tinOre);
						amountToCraft = Math.min(numberOfCopperOre, numberOfTinOre);
						try {
							if (Integer.parseInt(craftAmount.getText()) > amountToCraft) {
								craftAmount.setText(String.valueOf(amountToCraft));
							}
						} catch (Exception e) {

						}
					}

				} else if (ironbarselected) {
					if (hasNumItems(this.mc.thePlayer, Items.ironOre, 1)) {
						int numberOfIronOre;

						numberOfIronOre = getNumItems(this.mc.thePlayer, Items.ironOre);

						amountToCraft = numberOfIronOre;

						try {
							if (Integer.parseInt(craftAmount.getText()) > amountToCraft) {
								craftAmount.setText(String.valueOf(amountToCraft));
							}
						} catch (Exception e) {

						}

					}

				} else if (silverbarselected) {
					if (hasNumItems(this.mc.thePlayer, Items.silverOre, 1)) {
						int numberOfSilverOre;

						numberOfSilverOre = getNumItems(this.mc.thePlayer, Items.silverOre);

						amountToCraft = numberOfSilverOre;

						try {
							if (Integer.parseInt(craftAmount.getText()) > amountToCraft) {
								craftAmount.setText(String.valueOf(amountToCraft));
							}
						} catch (Exception e) {

						}

					}

				} else if (steelbarselected) {
					if (hasNumItems(this.mc.thePlayer, Items.ironOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 2)) {
						int numberOfIronOre, numberOfCoalOre;

						numberOfIronOre = getNumItems(this.mc.thePlayer, Items.ironOre);
						numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
						numberOfCoalOre = (int) numberOfCoalOre / 2;
						amountToCraft = Math.min(numberOfIronOre, numberOfCoalOre);

						try {
							if (Integer.parseInt(craftAmount.getText()) > amountToCraft) {
								craftAmount.setText(String.valueOf(amountToCraft));
							}
						} catch (Exception e) {

						}

					}

				} else if (goldbarselected) {
					if (hasNumItems(this.mc.thePlayer, Items.goldOre, 1)) {
						int numberOfGoldOre;

						numberOfGoldOre = getNumItems(this.mc.thePlayer, Items.goldOre);

						amountToCraft = numberOfGoldOre;

						try {
							if (Integer.parseInt(craftAmount.getText()) > amountToCraft) {
								craftAmount.setText(String.valueOf(amountToCraft));
							}
						} catch (Exception e) {

						}

					}

				} else if (mithrilbarselected) {
					if (hasNumItems(this.mc.thePlayer, Items.mithrilOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 4)) {
						int numberOfMithrilOre, numberOfCoalOre;

						numberOfMithrilOre = getNumItems(this.mc.thePlayer, Items.mithrilOre);
						numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
						numberOfCoalOre = (int) numberOfCoalOre / 4;
						amountToCraft = Math.min(numberOfMithrilOre, numberOfCoalOre);

						try {
							if (Integer.parseInt(craftAmount.getText()) > amountToCraft) {
								craftAmount.setText(String.valueOf(amountToCraft));
							}
						} catch (Exception e) {

						}

					}

				} else if (adamantbarselected) {
					if (hasNumItems(this.mc.thePlayer, Items.adamantiteOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 6)) {
						int numberOfAdamantiteOre, numberOfCoalOre;

						numberOfAdamantiteOre = getNumItems(this.mc.thePlayer, Items.adamantiteOre);
						numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
						numberOfCoalOre = (int) numberOfCoalOre / 6;
						amountToCraft = Math.min(numberOfAdamantiteOre, numberOfCoalOre);

						try {
							if (Integer.parseInt(craftAmount.getText()) > amountToCraft) {
								craftAmount.setText(String.valueOf(amountToCraft));
							}
						} catch (Exception e) {

						}

					}

				} else if (runebarselected) {
					if (hasNumItems(this.mc.thePlayer, Items.runiteOre, 1) && hasNumItems(this.mc.thePlayer, Items.coalOre, 8)) {
						int numberOfRuniteOre, numberOfCoalOre;

						numberOfRuniteOre = getNumItems(this.mc.thePlayer, Items.runiteOre);
						numberOfCoalOre = getNumItems(this.mc.thePlayer, Items.coalOre);
						numberOfCoalOre = (int) numberOfCoalOre / 8;
						amountToCraft = Math.min(numberOfRuniteOre, numberOfCoalOre);

						try {
							if (Integer.parseInt(craftAmount.getText()) > amountToCraft) {
								craftAmount.setText(String.valueOf(amountToCraft));
							}
						} catch (Exception e) {

						}

					}

				}
			}

		}

	}

	public void consumeMultipleOfOneItem(EntityPlayer p, Item item, int n) {
		for (int i = 0; i > n; i++) {
			p.inventory.consumeInventoryItem(item);
		}
	}

	public void consumeMultipleItems(EntityPlayer p, Item item1, Item item2) {
		p.inventory.consumeInventoryItem(item1);
		p.inventory.consumeInventoryItem(item2);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		int xSize = 256;
		int ySize = 256;
		int xStart = (width / 2) - (xSize / 2);
		int yStart = (height / 2) - (ySize / 2);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(resourceLocation);
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		int xSize = 256;
		int ySize = 256;
		int xStart = (width / 2) - (xSize / 2);
		int yStart = (height / 2) - (ySize / 2);
		int iconyStart = yStart + 22;
		int iconxStart = xStart + 17;

		rightBtn.width = leftBtn.width = 20;
		rightBtn.height = leftBtn.height = 20;
		onecraftBtn.width = fivecraftBtn.width = tencraftBtn.width = 30;
		onecraftBtn.height = fivecraftBtn.height = tencraftBtn.height = 20;
		craftallBtn.width = 30;
		craftallBtn.height = craftBtn.height = 20;

		craftBtn.width = 35;
		super.drawGuiContainerForegroundLayer(par1, par2);
		this.drawBackground(0);
		this.doesGuiPauseGame();
		for (Object o : buttonList) {
			((GuiButton) o).drawButton(mc, par1, par2);
		}
		this.drawBars();
		// this.DrawTooltipScreen();

		this.drawCenteredString(this.fontRendererObj, "Furnace", xStart + 40, yStart + 5, 16777215);

		this.craftAmount.drawTextBox();
		this.craftTime.drawTextBox();

		this.IsButtonMouseovered(par1, par2, null);

	}

	public void writeTime(int time) {
		int hours = 0, minutes = 0, seconds = 0;
		seconds = time;
		if (time > 0) {
			for(int i = 60; i < seconds;){
				seconds -=60;
				minutes++;
			}
			
			for (int i = 60; i < minutes;){
				minutes -=60;
				hours++;
			}
			

			if (time == 0) {
				seconds = 0;
			}

			StringBuilder sb = new StringBuilder();
			// Send all output to the Appendable object sb
			Formatter formatter = new Formatter(sb, Locale.US);

			// Explicit argument indices may be used to re-order output.
			formatter.format("%02d:%02d:%02d", hours, minutes, seconds);

			String timeString;
			System.out.printf(sb.toString());
			System.out.println();
			this.craftTime.setText(sb.toString());

		}
	}

}

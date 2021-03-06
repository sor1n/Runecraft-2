package com.callumhutchy.runecraft2.client.gui;

import handler.ExtendedPlayer;
import handler.Runecraft2EventHandler;

import java.awt.Image;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.callumhutchy.runecraft2.Runecraft2;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(Side.CLIENT)
public class GuiSpellBook extends GuiScreen {

	private static final ResourceLocation	resourceLocation			= new ResourceLocation("runecraft2:textures/gui/spellbook.png");
	public static final int					GUI_ID						= 20;

	private static final int				SPELL_ICON_SIZE				= 16;
	private static final int				SPELL_ICON_SPACING			= SPELL_ICON_SIZE + 4;
	private static final int				SPELL_ICON_BASE_U_OFFSET	= 0;
	private static final int				SPELL_ICON_BASE_V_OFFSET	= 198;
	private static final int				SPELL_ICONS_PER_ROW			= 11;
	protected static final ResourceLocation	widgetTextures				= new ResourceLocation("runecraft2:textures/gui/spells.png");

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

	public boolean							airstrikeselected,
			hometeleportselected, waterstrikeselected, earthstrikeselected,
			firestrikeselected = false;

	int										xSize						= 256;
	int										ySize						= 256;
	int										xStart						= (width / 2) - (xSize / 2);
	int										yStart						= (height / 2) - (ySize / 2);
	int										iconyStart					= yStart + 22;
	int										iconxStart					= xStart + 17;

	int										asminx, asmaxx, asminy, asmaxy;
	int										htminx, htmaxx, htminy, htmaxy;
	int										wsminx, wsmaxx, wsminy, wsmaxy;
	int										esminx, esmaxx, esminy, esmaxy;
	int										fsminx, fsmaxx, fsminy, fsmaxy;
	private GuiButton	doneBtn;

	public void initGui() {

		Keyboard.enableRepeatEvents(true);

		 this.buttonList.add(this.doneBtn = new GuiButton(1, this.width / 2 -
		 100, this.height / 4 + 96 + 12, "Done"));
		// this.codeTextField = new GuiTextField(this.fontRenderer, this.width /
		// 2 - 150, 60, 300, 20);
		// this.codeTextField.setMaxStringLength(32767);
		// this.codeTextField.setFocused(true);
	}


	protected void actionPerformed(GuiButton par1GuiButton) {
		switch (par1GuiButton.id) {
		case 1:
			System.out.println("BOOOBS");

			break;
		}
	}

	public void drawScreen(int par1, int par2, float par3) {
		int xSize = 256;
		int ySize = 256;
		int xStart = (width / 2) - (xSize / 2);
		int yStart = (height / 2) - (ySize / 2);
		int iconyStart = yStart + 22;
		int iconxStart = xStart + 17;
		this.drawCenteredString(this.fontRendererObj, "Spellbook:", this.width / 2, (this.height / 2) - (ySize / 2) - 20, 16777215);
		// this.codeTextField.drawTextBox();
		this.drawBackground(0);
		this.doesGuiPauseGame();
		this.IsButtonMouseovered(par1, par2, null);
		this.drawSkills();
		super.drawScreen(par1, par2, par3);
		
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

	public void drawSkills() {
		int xSize = 256;
		int ySize = 256;
		int xStart = (width / 2) - (xSize / 2);
		int yStart = (height / 2) - (ySize / 2);
		int iconyStart = yStart + 22;
		int iconxStart = xStart + 17;
		ExtendedPlayer props = ExtendedPlayer.get(this.mc.thePlayer);

		int currentMagic = Runecraft2EventHandler.currentMagicLevel;
		String currentSpell = props.currentSpell;
		int test = props.currentMagicLevel;

		System.out.println(airstrikeselected);

		if (currentMagic >= 0) {

			if (!airstrikeselected || currentSpell != "airstrike") {
				mc.getTextureManager().bindTexture(this.widgetTextures);
				this.drawTexturedModalRect(iconxStart, iconyStart, 0, 0, 16, 16);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

				asminx = iconxStart;
				asmaxx = iconxStart + 16;
				asminy = iconyStart;
				asmaxy = iconyStart + 16;
				System.out.println(currentSpell);
				System.out.println(currentMagic);

			}
			if (airstrikeselected || currentSpell == "airstrike") {
				mc.getTextureManager().bindTexture(this.widgetTextures);
				this.drawTexturedModalRect(iconxStart, iconyStart, 0, 16, 16, 16);
				airstrikeselected = true;
				hometeleportselected = false;
				waterstrikeselected = false;
				earthstrikeselected = false;
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				asminx = iconxStart;
				asmaxx = iconxStart + 16;
				asminy = iconyStart;
				asmaxy = iconyStart + 16;
				System.out.println(currentSpell);
			}
			if (!hometeleportselected || currentSpell != "hometeleport") {
				mc.getTextureManager().bindTexture(this.widgetTextures);
				this.drawTexturedModalRect(iconxStart + SPELL_ICON_SPACING, iconyStart, 64, 0, 16, 16);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				htminx = iconxStart + SPELL_ICON_SPACING;
				htmaxx = iconxStart + SPELL_ICON_SPACING + SPELL_ICON_SIZE;
				htminy = iconyStart;
				htmaxy = iconyStart + 16;

			}
			if (hometeleportselected || currentSpell == "hometeleport") {
				mc.getTextureManager().bindTexture(this.widgetTextures);
				this.drawTexturedModalRect(iconxStart + SPELL_ICON_SPACING, iconyStart, 64, 16, 16, 16);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				htminx = iconxStart + SPELL_ICON_SPACING;
				htmaxx = iconxStart + SPELL_ICON_SPACING + SPELL_ICON_SIZE;
				htminy = iconyStart;
				htmaxy = iconyStart + 16;
				airstrikeselected = false;
				waterstrikeselected = false;
				earthstrikeselected = false;

			}

		}
		if (currentMagic >= 2) {
			if (!waterstrikeselected || currentSpell != "waterstrike") {
				mc.getTextureManager().bindTexture(this.widgetTextures);
				this.drawTexturedModalRect(iconxStart + SPELL_ICON_SPACING * 2, iconyStart, 16, 0, 16, 16);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				wsminx = iconxStart + (SPELL_ICON_SPACING * 2);
				wsmaxx = iconxStart + (SPELL_ICON_SPACING * 2) + SPELL_ICON_SIZE;
				wsminy = iconyStart;
				wsmaxy = iconyStart + 16;
			}
			if (waterstrikeselected || currentSpell == "waterstrike") {
				mc.getTextureManager().bindTexture(this.widgetTextures);
				this.drawTexturedModalRect(iconxStart + SPELL_ICON_SPACING * 2, iconyStart, 16, 16, 16, 16);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				airstrikeselected = hometeleportselected = earthstrikeselected = false;
				wsminx = iconxStart + (SPELL_ICON_SPACING * 2);
				wsmaxx = iconxStart + (SPELL_ICON_SPACING * 2) + SPELL_ICON_SIZE;
				wsminy = iconyStart;
				wsmaxy = iconyStart + 16;
			}
		}
		if (currentMagic >= 5) {
			if (!earthstrikeselected) {
				mc.getTextureManager().bindTexture(this.widgetTextures);
				this.drawTexturedModalRect(iconxStart + SPELL_ICON_SPACING * 3, iconyStart, 32, 0, 16, 16);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				esminx = iconxStart + (SPELL_ICON_SPACING * 3);
				esmaxx = iconxStart + (SPELL_ICON_SPACING * 3) + SPELL_ICON_SIZE;
				esminy = iconyStart;
				esmaxy = iconyStart + 16;
			}
			if (earthstrikeselected) {
				mc.getTextureManager().bindTexture(this.widgetTextures);
				this.drawTexturedModalRect(iconxStart + SPELL_ICON_SPACING * 3, iconyStart, 32, 16, 16, 16);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				airstrikeselected = hometeleportselected = waterstrikeselected = false;
				esminx = iconxStart + (SPELL_ICON_SPACING * 3);
				esmaxx = iconxStart + (SPELL_ICON_SPACING * 3) + SPELL_ICON_SIZE;
				esminy = iconyStart;
				esmaxy = iconyStart + 16;
			}
		}
		if (currentMagic >= 8) {
			if (!firestrikeselected) {
				mc.getTextureManager().bindTexture(this.widgetTextures);
				this.drawTexturedModalRect(iconxStart + SPELL_ICON_SPACING * 4, iconyStart, 48, 0, 16, 16);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				fsminx = iconxStart + (SPELL_ICON_SPACING * 4);
				fsmaxx = iconxStart + (SPELL_ICON_SPACING * 4) + SPELL_ICON_SIZE;
				fsminy = iconyStart;
				fsmaxy = iconyStart + 16;
			}
			if (firestrikeselected) {
				mc.getTextureManager().bindTexture(this.widgetTextures);
				this.drawTexturedModalRect(iconxStart + SPELL_ICON_SPACING * 4, iconyStart, 48, 16, 16, 16);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				airstrikeselected = hometeleportselected = waterstrikeselected = false;
				fsminx = iconxStart + (SPELL_ICON_SPACING * 4);
				fsmaxx = iconxStart + (SPELL_ICON_SPACING * 4) + SPELL_ICON_SIZE;
				fsminy = iconyStart;
				fsmaxy = iconyStart + 16;
			}
		}
	}

	protected void mouseClicked(int varx, int vary, int p_73864_3_) {
		ExtendedPlayer props = ExtendedPlayer.get(this.mc.thePlayer);
		System.out.println("Mouse was click at x " + varx + " and y " + vary);
		if (asminx < varx && varx < asmaxx && asminy < vary && vary < asmaxy) {
			airstrikeselected = !airstrikeselected;
			System.out.println(airstrikeselected);
		}

		if (airstrikeselected) {
			props.currentSpell = "airstrike";
			hometeleportselected = false;
			waterstrikeselected = false;
		}
		if (!airstrikeselected) {
			props.currentSpell = "";
		}
		if (htminx < varx && varx < htmaxx && htminy < vary && vary < htmaxy) {
			hometeleportselected = !hometeleportselected;
		}
		if (hometeleportselected) {
			props.currentSpell = "hometeleport";
			airstrikeselected = false;
			waterstrikeselected = false;
		}
		if (wsminx < varx && varx < wsmaxx && wsminy < vary && vary < wsmaxy) {
			waterstrikeselected = !waterstrikeselected;
		}
		if (waterstrikeselected) {
			props.currentSpell = "waterstrike";
			airstrikeselected = false;
			hometeleportselected = false;
		}
		if (esminx < varx && varx < esmaxx && esminy < vary && vary < esmaxy) {
			earthstrikeselected = !earthstrikeselected;
		}
		if (earthstrikeselected) {
			props.currentSpell = "earthstrike";
			airstrikeselected = false;
			hometeleportselected = false;
			waterstrikeselected = false;
		}
		if (fsminx < varx && varx < fsmaxx && fsminy < vary && vary < fsmaxy) {
			firestrikeselected = !firestrikeselected;
		}
		if (firestrikeselected) {
			props.currentSpell = "firestrike";
			airstrikeselected = false;
			hometeleportselected = false;
			waterstrikeselected = false;
			earthstrikeselected = false;
		}
	}

	protected void mouseMovedOrUp(int varx, int vary, int which) {
		if (asminx < varx && varx < asmaxx && asminy < vary && vary < asmaxy) {

		}
		if (htminx < varx && varx < htmaxx && htminy < vary && vary < htmaxy) {

		}
	}

	protected boolean IsButtonMouseovered(int varx, int vary, GuiButton button) {
		if (asminx < varx && varx < asmaxx && asminy < vary && vary < asmaxy) {

			this.DrawTooltipScreen(varx, vary, "_lAir Strike:_r _p" + "1x Air Rune _p" + "Fires a weak bolt of air.");

		}
		if (htminx < varx && varx < htmaxx && htminy < vary && vary < htmaxy) {

			this.DrawTooltipScreen(varx, vary, "_lHome Teleport:_r _p" + "No rune cost. _p" + "Set your Home location with _o/sethometeleport_r _p" + "This will teleport you home.");
		}
		if (wsminx < varx && varx < wsmaxx && wsminy < vary && vary < wsmaxy) {
			this.DrawTooltipScreen(varx, vary, "_lWater Strike:_r _p" + "1x Water Rune _p" + "Fires a weak bolt of water.");
		}

		return true;
	}

	private void DrawTooltipScreen(int varx, int vary, String string) {
		RenderTooltip(varx, vary, string);

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

}

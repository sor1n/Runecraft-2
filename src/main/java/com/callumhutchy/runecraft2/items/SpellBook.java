package com.callumhutchy.runecraft2.items;

import com.callumhutchy.runecraft2.Runecraft2;
import com.callumhutchy.runecraft2.client.gui.GuiSpellBook;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpellBook extends BaseMagicItem {

	public SpellBook(String unlocalisedName, int maxSize) {
		super(unlocalisedName, maxSize);
		// TODO Auto-generated constructor stub
	}

	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
		player.openGui(Runecraft2.instance, GuiSpellBook.GUI_ID, world,0, 0, 0);
       
		return item;
    }
}

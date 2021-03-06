package com.callumhutchy.runecraft2.items.render;

import org.lwjgl.opengl.GL11;

import com.callumhutchy.runecraft2.items.models.StaffModel;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemStaffRenderer implements IItemRenderer {

	protected StaffModel model;
	
	public ItemStaffRenderer(){
		model = new StaffModel();
	}
	
	
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type){
		case EQUIPPED: return true;
		default: return false;
		}
		
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
	
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type){
		case EQUIPPED:{
			GL11.glPushMatrix();
			 ResourceLocation textures = (new ResourceLocation("runecraft2:textures/items/StaffTexture.png"));
			Minecraft.getMinecraft().renderEngine.bindTexture(textures);
			
			float scale = 1.4f;
			
			GL11.glScalef(scale, scale, scale);
			GL11.glRotatef(230, 0, 0, 1);
			GL11.glTranslatef(1, 0, 0);
			
			
			model.render((Entity) data[1], 0.0f, 0.0f,0.0f, 0.0f, 0.0f, 0.0625f);
			GL11.glPopMatrix();
		}
			default: break;
		}
		
	}

}

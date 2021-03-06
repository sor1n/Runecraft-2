package com.callumhutchy.runecraft2.blocks.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.callumhutchy.runecraft2.blocks.models.AltarModel;
import com.callumhutchy.runecraft2.blocks.models.FurnaceModel;
import com.callumhutchy.runecraft2.blocks.models.tileentities.TileEntityRCFurnace;

public class RCFurnaceRenderer extends TileEntitySpecialRenderer{

private FurnaceModel model;

    
    public String ResourceLocationvar = "runecraft2:textures/blocks/FurnaceTexture.png";
    
    
    public RCFurnaceRenderer() {
       
    	this.model = new FurnaceModel(); 
    }
    
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
            int meta = world.getBlockMetadata(x, y, z);
            GL11.glPushMatrix();
            GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
            GL11.glPopMatrix();
    }
    
    
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
    //The PushMatrix tells the renderer to "start" doing something.
            GL11.glPushMatrix();
    //This is setting the initial location.
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
    //This is the texture of your block. It's pathed to be the same place as your other blocks here.
            //Outdated bindTextureByName("/mods/roads/textures/blocks/TrafficLightPoleRed.png");
   //Use in 1.6.2  this
            ResourceLocation textures = (new ResourceLocation(ResourceLocationvar));
    //the ':' is very important
    //binding the textures
            Minecraft.getMinecraft().renderEngine.bindTexture(textures);
            TileEntityRCFurnace myTile = (TileEntityRCFurnace) te;
			int direction = myTile.direction;
			
    //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
            GL11.glPushMatrix();
            GL11.glRotatef(180f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(direction * 90, 0.0F, 1.0F, 0.0F);
    //A reference to your Model file. Again, very important.
            
//            int i = myTile.blockMetadata;
//            System.out.println(i + 180);
//            GL11.glRotatef(i * 90, 0.0F, 1.0F, 0.0F);
            this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            
    //Tell it to stop rendering for both the PushMatrix's
            GL11.glPopMatrix();
            GL11.glPopMatrix();
           
    }
    
    
    public void setRotationAngles(TileEntity tileEntity) {
    	TileEntityRCFurnace te = (TileEntityRCFurnace)tileEntity;
    		boolean open;
    		open = te.openDoors;
    		System.out.println("ASSSSSS");
    		if(open){
    			System.out.println("BOOOOOOB");
    			this.model.Door_Left.rotateAngleY = 130F;
    			this.model.Door_Right.rotateAngleY = -130F;
    		}
    	
    	
    		if(!open){
    			this.model.Door_Left.rotateAngleY = 0F;
    			this.model.Door_Right.rotateAngleY = 0F;
    		}
    	
    	
    	
    	
    }

}

package utility.commands;

import reference.ExpChart;
import handler.ExtendedPlayer;
import handler.Runecraft2EventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class HomeTeleportCMD extends CommandBase {

	@Override
	public String getCommandName() {
		
		return "sethometeleport";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return "This will set the location of your home teleport.";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] string) {
		if(icommandsender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) icommandsender;
			ExtendedPlayer props = ExtendedPlayer.get(player);
			props.currentHomeTPx = (int) player.posX;
			props.currentHomeTPy = (int) player.posY;
			props.currentHomeTPz = (int) player.posZ + 1;
			//player..sendChatMessage("You have just succesfully set your hometeleport to " + props.currentHomeTPx +"/"+ props.currentHomeTPy +"/"+ props.currentHomeTPz + " (x/y/z)");
			System.out.println(props.currentHomeTPx +"/"+ props.currentHomeTPy +"/"+ props.currentHomeTPz);
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You have set your home teleport."));
		}
		
	}

}

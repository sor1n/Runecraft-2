package handler;

import reference.ExpChart;

import com.callumhutchy.runecraft2.blocks.Altar;
import com.callumhutchy.runecraft2.proxy.CommonProxy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class Runecraft2EventHandler {

	public static int			currentMagicLevel			= 0;
	public static int			currentRunecraftingLevel	= 0;
	public static int			currentMiningLevel			= 0;
	public static int currentSmithingLevel = 0;

	public int					test						= 0;

	CommonProxy					proxy						= new CommonProxy();
	String						username;
	public final static String	EXT_PROP_NAME				= "ExtendedPlayer";

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		/*
		 * Be sure to check if the entity being constructed is the correct type
		 * for the extended properties you're about to add! The null check may
		 * not be necessary - I only use it to make sure properties are only
		 * registered once per entity
		 */
		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
			// This is how extended properties are registered using our
			// convenient method from earlier
			ExtendedPlayer.register((EntityPlayer) event.entity);
		// That will call the constructor as well as cause the init() method
		// to be called automatically

		// If you didn't make the two convenient methods from earlier, your code
		// would be
		// much uglier:
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME) == null)
			event.entity.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer((EntityPlayer) event.entity));
	}

	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {
		// we only want to save data for players (most likely, anyway)
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			// NOTE: See step 6 for a way to do this all in one line!!!
			// create a new NBT Tag Compound to store the
			// IExtendedEntityProperties data
			NBTTagCompound playerData = new NBTTagCompound();
			// write the data to the new compound
			((ExtendedPlayer) (event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME))).saveNBTData(playerData);
			// and store it in our proxy
			if (event.entity instanceof EntityLiving) {
				username = event.entity.getCommandSenderName();
			}
			proxy.storeEntityData(username, playerData);
			// call our handy static one-liner to save custom data to the proxy
			// save player data:
			proxy.storeEntityData(username + ExtendedPlayer.EXT_PROP_NAME, playerData);

			ExtendedPlayer.saveProxyData((EntityPlayer) event.entity);
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {

		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			// NOTE: See step 6 for a way to do this all in one line!!!
			// before syncing the properties, we must first check if the player
			// has some saved in the proxy
			// recall that 'getEntityData' also removes it from the map, so be
			// sure to store it locally
			if (event.entity instanceof EntityLiving) {
				username = event.entity.getCommandSenderName();
			}
			NBTTagCompound playerData = proxy.getEntityData(username);
			// make sure the compound isn't null
			if (playerData != null) {
				// then load the data back into the player's
				// IExtendedEntityProperties
				((ExtendedPlayer) (event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME))).loadNBTData(playerData);
			}
			// finally, we sync the data between server and client (we did this
			// earlier in 3.3)
			((ExtendedPlayer) (event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME))).syncProperties();
		}
		// load player data:
		NBTTagCompound playerData = proxy.getEntityData(username + ExtendedPlayer.EXT_PROP_NAME);
		if (event.entity instanceof EntityPlayer) {
			ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) event.entity);
			currentMagicLevel = props.currentMagicLevel;
			System.out.println("MAgic level set to " + currentMagicLevel + " " + event.entity.getCommandSenderName());
			currentRunecraftingLevel = props.currentRunecraftingLevel;
			currentMiningLevel = props.currentMiningLevel;
			currentSmithingLevel = props.currentSmithingLevel;

		}

	}

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {

		ExtendedPlayer props = ExtendedPlayer.get(event.player);

		if (props.currentMagicLevel > 1) {
			currentMagicLevel = props.currentMagicLevel;
		}
		if (props.currentRunecraftingLevel > 1) {
			currentRunecraftingLevel = props.currentRunecraftingLevel;
		}

		if (props.currentMiningLevel > 1) {
			currentMiningLevel = props.currentMiningLevel;
		}
		if(props.currentSmithingLevel > 1){
			currentSmithingLevel = props.currentSmithingLevel;
		}

		
		if (currentMagicLevel < 99) {
			if (props.currentMagicExp >= ExpChart.exparray[1][currentMagicLevel]) {
				// int tempexp = 0;
				if (props.currentMagicExp > ExpChart.exparray[1][currentMagicLevel]) {
					if (props.currentMagicExp - ExpChart.exparray[1][currentMagicLevel] < ExpChart.exparray[1][currentMagicLevel]) {
						props.increaseSkill("magic");
						props.currentMagicExp -= ExpChart.exparray[1][currentMagicLevel];
					}
				}
					if (props.currentMagicExp == ExpChart.exparray[1][currentMagicLevel]) {
						props.increaseSkill("magic");
						int var1 =currentMagicLevel;
						System.out.println("DING");
						props.currentMagicExp = 0;
						System.out.println(currentMagicLevel);
						currentMagicLevel = var1;
					}

				}

			
		}

		if (currentRunecraftingLevel < 99) {
			if (props.currentRunecraftingExp >= ExpChart.exparray[1][currentRunecraftingLevel]) {
				if (props.currentRunecraftingExp > ExpChart.exparray[1][currentRunecraftingLevel]) {
					if (props.currentRunecraftingExp - ExpChart.exparray[1][currentRunecraftingLevel] < ExpChart.exparray[1][currentRunecraftingLevel]) {
						props.increaseSkill("runecrafting");
						props.currentRunecraftingExp -= ExpChart.exparray[1][currentRunecraftingLevel];
					}
					while(props.currentRunecraftingExp - ExpChart.exparray[1][currentRunecraftingLevel] > ExpChart.exparray[1][currentRunecraftingLevel]){
						props.currentRunecraftingExp -= ExpChart.exparray[1][currentRunecraftingLevel];
						props.increaseSkill("runecrafting");
						
					}
				}
					if (props.currentRunecraftingExp == ExpChart.exparray[1][currentRunecraftingLevel]) {
						props.increaseSkill("runecrafting");
						int var1 = currentRunecraftingLevel;
						System.out.println("DING");
						props.currentRunecraftingExp = 0;
						System.out.println(props.currentRunecraftingLevel);
						currentRunecraftingLevel = var1;
					}

				}

			
		}

		if (currentMiningLevel < 99) {
			if (props.currentMiningExp >= ExpChart.exparray[1][currentMiningLevel]) {
				if (props.currentMiningExp > ExpChart.exparray[1][currentMiningLevel]) {
					if (props.currentMiningExp - ExpChart.exparray[1][currentMiningLevel] < ExpChart.exparray[1][currentMiningLevel]) {
						props.increaseSkill("mining");
						props.currentMiningExp -= ExpChart.exparray[1][currentMiningLevel];
					}
				}
					if (props.currentMiningExp == ExpChart.exparray[1][currentMiningLevel]) {
						props.increaseSkill("mining");
						int var1 = currentMiningLevel;
						System.out.println("DING");
						props.currentMiningExp = 0;
						System.out.println(currentMiningLevel);
						currentMiningLevel = var1;

					}
				}
			
		}
		if (currentSmithingLevel < 99) {
			if (props.currentSmithingExp >= ExpChart.exparray[1][currentSmithingLevel]) {
				if (props.currentSmithingExp > ExpChart.exparray[1][currentSmithingLevel]) {
					if (props.currentSmithingExp - ExpChart.exparray[1][currentSmithingLevel] < ExpChart.exparray[1][currentSmithingLevel]) {
						props.increaseSkill("smithing");
						props.currentSmithingExp -= ExpChart.exparray[1][currentSmithingLevel];
					}
					while(props.currentSmithingExp - ExpChart.exparray[1][currentSmithingLevel] > ExpChart.exparray[1][currentSmithingLevel]){
						props.currentSmithingExp -= ExpChart.exparray[1][currentSmithingLevel];
						props.increaseSkill("smithing");
						
					}
				}
					if (props.currentSmithingExp == ExpChart.exparray[1][currentSmithingLevel]) {
						props.increaseSkill("smithing");
						int var1 = currentSmithingLevel;
						System.out.println("DING");
						props.currentRunecraftingExp = 0;
						System.out.println(props.currentSmithingLevel);
						currentSmithingLevel = var1;
					}

				}

			
		}

	}

	@SubscribeEvent
	public void onTickInGame(WorldTickEvent event) {
		if (Altar.blocktick > 0) {
			Altar.blocktick--;
		}
	}
}

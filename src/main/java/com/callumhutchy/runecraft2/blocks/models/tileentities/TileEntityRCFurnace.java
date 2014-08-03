package com.callumhutchy.runecraft2.blocks.models.tileentities;

import java.util.Timer;
import java.util.TimerTask;

import com.callumhutchy.runecraft2.blocks.models.FurnaceModel;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRCFurnace extends TileEntity{
public static FurnaceModel model;
public Item furnaceProduct;
public int amountOfProduct;
public boolean doneCooking = false;
public int time = 0;
private static Timer timer;
private static int interval;

public boolean canUpdate() {
	return true;
}

@Override
public void updateEntity() {
if(time != 0){
	int delay = 1000;
    int period = 1000;
    timer = new Timer();
    interval = time;
    System.out.println(interval);
    timer.scheduleAtFixedRate(new TimerTask() {
    	public void run() {
    	System.out.println(setInterval());

        }
    }, delay, period);
    if(time == 0){
    	doneCooking = true;
    }
}


}
private static final int setInterval() {
    if (interval == 1)
        timer.cancel();
    return --interval;
}

}
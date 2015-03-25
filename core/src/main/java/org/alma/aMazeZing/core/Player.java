package org.alma.aMazeZing.core;


import org.alma.aMazeZing.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Player class
 * 
 * @author Maxime
 * @version 1.0 [12/02/2015]
 */
public class Player extends Observable {

	private int experience;
	
	private int gold;

    private int x;

    private int y;

	private List<ItemStack> inventory;

    public Player() {
        this.x = 0;
        this.y = 0;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.setChanged();
    }

    public int getX(){
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.setChanged();
    }

	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
        this.setChanged();
	}
	
	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
		this.setChanged();
	}
	
	public List<ItemStack> getInventory() {
		return inventory;
	}
	
	public void setInventory(List<ItemStack> inventory) {
		this.inventory = inventory;
        this.setChanged();
	}
	
	public void clearInventory() {
		this.inventory = new ArrayList<>();
        this.setChanged();
	}

}

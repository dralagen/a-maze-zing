package org.alma.aMazeZing.core;


import org.alma.aMazeZing.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Player class
 * 
 * @author Maxime
 * @version 1.0 [12/02/2015]
 */
public class Player {

	private int experience;
	
	private int gold;
	
	private List<ItemStack> inventory;
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public List<ItemStack> getInventory() {
		return inventory;
	}
	
	public void setInventory(List<ItemStack> inventory) {
		this.inventory = inventory;
	}
	
	public void clearInventory() {
		this.inventory = new ArrayList<>();
	}
}

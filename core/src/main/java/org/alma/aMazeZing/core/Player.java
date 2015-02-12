package org.alma.aMazeZing.core;

/**
 * Player class
 * 
 * @author Maxime
 * @version 1.0 [12/02/2015]
 */
public class Player {

	private int experience;
	
	private int gold;
	
	private ItemStack[] inventory;
	
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
	
	public ItemStack[] getInventory() {
		return inventory;
	}
	
	public void setInventory(ItemStack[] inventory) {
		this.inventory = inventory;
	}
	
	public void clearInventory() {
		this.inventory = new ItemStack[];
	}
}

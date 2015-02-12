package org.alma.aMazeZing.core;

/**
 * ItemStack class : imitates an inventory
 * 
 * @author Maxime
 * @version 1.0 [12/02/2015]
 */
public class ItemStack {
	
	private Item item;
	
	private int amount;
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	
}

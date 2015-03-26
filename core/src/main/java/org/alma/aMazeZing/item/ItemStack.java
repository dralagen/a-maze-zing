package org.alma.aMazeZing.item;

/**
 * ItemStack class : imitates an inventory
 * 
 * @author Maxime
 * @version 1.0 [12/02/2015]
 */
public class ItemStack {
	
	private Item item;
	
	private int amount;

	public ItemStack(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}

	public ItemStack(Item item) {
		this(item, 1);
	}

	public Item getItem() {
		return item;
	}

    public int getAmount() {
        return amount;
    }

	public int addItem(int number) {
		amount+= number;
		return amount;
	}

	public int removeItem(int number) {
		amount -= number;

		if (amount < 0) {
			amount = 0;
		}

		return amount;
	}
}

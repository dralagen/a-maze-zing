package org.alma.aMazeZing.core;


import org.alma.aMazeZing.item.Item;
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
        inventory = new ArrayList<ItemStack>();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.setChanged();
        this.notifyObservers();
    }

    public int getX(){
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.setChanged();
        this.notifyObservers();
    }

	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
        this.setChanged();
        this.notifyObservers();
	}
	
	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
		this.setChanged();
        this.notifyObservers();
	}
	
	public List<ItemStack> getInventory() {
		return inventory;
	}

    public void addInventory(ItemStack itemstack) {
        this.inventory.add(itemstack);
        this.setChanged();
        this.notifyObservers();
    }

    public void addInventory(Item item) {
        System.out.println("Tentative d'ajout d'un objet " + item.getName());
        boolean found = false;
        int i = inventory.size() - 1;
        while (i >= 0 && !item.getName().equals(inventory.get(i).getItem().getName())) {
            i--;
        }
        if (i >= 0) {
            System.out.println("MAJ de l'inventaire");
            inventory.get(i).addItem(1);
        }
        else
        {
            System.out.println("Nouvel emplacement pour l'objet");
            inventory.add(new ItemStack(item));
        }
        this.setChanged();
        this.notifyObservers(item);
    }

	public void setInventory(List<ItemStack> inventory) {
		this.inventory = inventory;
        this.setChanged();
        this.notifyObservers();
	}
	
	public void clearInventory() {
		this.inventory = new ArrayList<>();
        this.setChanged();
        this.notifyObservers();
	}

}

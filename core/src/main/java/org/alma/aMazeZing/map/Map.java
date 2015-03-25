package org.alma.aMazeZing.map;

import org.alma.aMazeZing.item.ItemStack;
import org.alma.aMazeZing.core.Pair;
import org.alma.aMazeZing.core.Position;
import org.alma.aMazeZing.core.Player;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Created on 2/12/15.
 *
 * @author dralagen
 */
public class Map extends Observable {

	public static int MIN_WIDTH = 10;
	public static int MIN_HEIGHT = 10;
	

	public Map(TreeMap<Position, ItemStack> its, int i, int j){
		items = its;
		width = i;
		height = j;
	}

    public void moveLeft(Player p) {
        if (p.getX() > 0) {
            p.setX(p.getX()-1);
        }
    }

    public void moveTop(Player p) {
        if (p.getY() > 0) {
            p.setY(p.getY() - 1);
        }
    }

    public void moveRight(Player p) {
        if (p.getX() < width-1) {
            p.setX(p.getX()+1);
        }
    }

    public void moveBottom(Player p) {
        if (p.getY() < height - 1) {
            p.setY(p.getY() + 1);
        }
    }

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public ItemStack getItem(int x, int y){
		return items.get(new Position(x,y));
	}

	private int width;
	private int height;

	private TreeMap<Position, ItemStack> items;

}

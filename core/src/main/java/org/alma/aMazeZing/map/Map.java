package org.alma.aMazeZing.map;

import org.alma.aMazeZing.item.ItemStack;
import org.alma.aMazeZing.core.Pair;
import org.alma.aMazeZing.core.Position;

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

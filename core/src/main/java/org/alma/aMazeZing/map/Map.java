package org.alma.aMazeZing.map;

import org.alma.aMazeZing.item.ItemStack;
import org.alma.aMazeZing.core.Pair;
import org.alma.aMazeZing.core.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Created on 2/12/15.
 *
 * @author dralagen
 */
public class Map extends Observable {

	public Map(List<Pair<ItemStack, Position>> o, int i, int j){
		obj = o;
		width = i;
		height = j;
	}

	public int getWidth(){
		return width;
	}
	public int getHeigth(){
		return height;
	}
	public ItemStack getItem(int x, int y){
		for (int i=0; i<obj.size(); i++){
			if (obj.get(i).second.x==x && obj.get(i).second.y==y)
				return obj.get(i).first;
		}
		return null;
	}

    public static List<Pair<ItemStack, Position>> generateFromItems(List<ItemStack> items, int width, int height) {
        Random rand = new Random();
        List<Pair<ItemStack, Position>> pos = new ArrayList<Pair<ItemStack, Position>>();
        for (ItemStack itemstack : items) {
            int x = rand.nextInt(width - 0 + 1) + 0;
            int y = rand.nextInt(height - 0 + 1) + 0;
            Position position = new Position(x, y);
            pos.add(new Pair<ItemStack, Position>(itemstack, position));
        }
        return pos;
    }

	private int width;
	private int height;
	private List<Pair<ItemStack, Position>> obj;
}

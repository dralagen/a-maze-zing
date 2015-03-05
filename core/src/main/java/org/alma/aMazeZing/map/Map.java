package org.alma.aMazeZing.map;

import org.alma.aMazeZing.item.ItemStack;
import org.alma.aMazeZing.core.Pair;
import org.alma.aMazeZing.core.Position;

import java.util.List;

/**
 * Created on 2/12/15.
 *
 * @author dralagen
 */
public class Map{

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

	private int width;
	private int height;
	private List<Pair<ItemStack, Position>> obj;
}

package org.alma.aMazeZing.map;

import org.alma.aMazeZing.plugins.*;
import org.alma.aMazeZing.core.*;
import org.alma.aMazeZing.quest.*;
import org.alma.aMazeZing.item.*;
import org.alma.aMazeZing.history.*;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.TreeMap;


/**
 * Created on 2/12/15.
 *
 * @author dralagen
 */
public class MapBuilderRandom implements MapBuilder{ 

	@Override
	public Map getMap(List<History> l){

		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        for(History h : l) {
            items.addAll(h.getItems());
        }

        int width = (int)Math.ceil(items.size()) +Map.MIN_WIDTH;
        int height = (int)Math.ceil(items.size())+Map.MIN_HEIGHT;

        Random rnd = new Random();

        TreeMap<Position, ItemStack> its = new TreeMap<Position, ItemStack>();
        //Place items randomly, not in 0,0 (Player will be on 0,0)
        for (int i=0; i<items.size(); i++){
        	boolean place=false;
        	Position p=new Position(0,0);
        	while (!place){
        		p = new Position(rnd.nextInt(width), rnd.nextInt(height));
        		place = (p.x!=0 || p.y!=0) && its.get(p)==null; 
        	}
            its.put(p, items.get(i));
        }

        return new Map(its, width, height);
	}
}

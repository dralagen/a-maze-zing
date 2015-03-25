package org.alma.aMazeZing.map;

import org.alma.aMazeZing.core.Pair;
import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.core.Position;
import org.alma.aMazeZing.item.ItemStack;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.plugins.MapBuilder;
import org.alma.aMazeZing.history.History;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Observable;

/**
 * Created on 2/12/15.
 *
 * @author dralagen
 */
public class BasicMap implements MapBuilder {

    @Override
    public Map getMap(List<History> l) {
    
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        for(History h : l) {
            items.addAll(h.getItems());
        }

        int width = (int)Math.ceil(Math.sqrt(items.size())) +Map.MIN_WIDTH;
        int height = (int)Math.ceil(Math.sqrt(items.size()))+Map.MIN_HEIGHT;

        TreeMap<Position, ItemStack> its = new TreeMap<Position, ItemStack>();
        //Place items next to each other begining by position 0,1 (Player will be on 0,0)
        for (int i=0; i<items.size(); i++){
            its.put(new Position((i+1)/Map.MIN_WIDTH, (i+1)%Map.MIN_WIDTH), items.get(i));
        }

        return new Map(its, width, height);
    }
}

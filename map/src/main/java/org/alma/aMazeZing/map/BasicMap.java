package org.alma.aMazeZing.map;

import org.alma.aMazeZing.core.Pair;
import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.core.Position;
import org.alma.aMazeZing.item.ItemStack;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.plugins.MapBuilder;
import org.alma.aMazeZing.history.History;

import java.util.ArrayList;
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
        int width = 50;
        int height = 50;

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        for(History h : l) {
            items.addAll(h.getItems());
        }
        return new Map(Map.generateFromItems(items, width, height), width, height);
    }
}

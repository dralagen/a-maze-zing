package org.alma.aMazeZing.history;

import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.item.ItemStack;
import org.alma.aMazeZing.map.Map;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by E130110Z on 23/03/15.
 */
public interface History extends Observer {

    public String getName();

    public List<ItemStack> getItems();

    public void setPlayer(Player player);

    public boolean isFinished();

    @Override
    public void update (Observable o, Object arg);

}

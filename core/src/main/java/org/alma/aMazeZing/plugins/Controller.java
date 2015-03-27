package org.alma.aMazeZing.plugins;

import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.map.Map;
import java.awt.event.KeyEvent;

/**
 * Created by Cr3aHal0 on 27/03/15.
 */
public interface Controller {

    public void loadPlayer(Player p);

    public void loadMap(Map m);

    public void updateEvent(KeyEvent event);

}

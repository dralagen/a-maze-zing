package org.alma.aMazeZing.plugins;

import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.map.Map;

import java.util.Observer;

/**
 * Created on 2/12/15.
 *
 * @author dralagen
 */
public interface UI extends Observer {

    public void loadUI(Player p, Map m);

}

package org.alma.aMazeZing.UI;

import org.alma.aMazeZing.core.Player;

import java.util.Observer;

/**
 * Created on 2/12/15.
 *
 * @author dralagen
 */
public interface UI extends Observer {

    public void loadUI(Player p);

}

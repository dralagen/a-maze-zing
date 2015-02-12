package org.alma.aMazeZing.UI;

import org.alma.aMazeZing.core.Player;

import java.util.Observable;

/**
 * Created on 2/12/15.
 *
 * @author dralagen
 */
public class ConsoleUI implements UI {

    @Override
    public void loadUI (Player p) {
        p.addObserver(this);
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println(arg);
    }
}

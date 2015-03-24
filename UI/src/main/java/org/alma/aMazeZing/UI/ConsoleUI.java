package org.alma.aMazeZing.UI;

import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.plugins.UI;
import org.alma.aMazeZing.map.Map;

import java.util.Observable;
import java.util.Observer;

/**
 * Created on 2/12/15.
 *
 * @author dralagen
 */
public class ConsoleUI implements UI, Observer {

    private Map map;

    private Player p;

    @Override
    public void loadUI (Player p, Map m) {
        System.out.println("--- Loading UI ---");

        p.addObserver(this);
        m.addObserver(this);

        this.map = m;
        this.p = p;
    }

    @Override
    public void update (Observable o, Object arg) {
        if (o instanceof Player) {
            printPlayer((Player) o);
        }
    }

    private void printPlayer (Player player) {
        System.out.println(player.getGold());
    }
}

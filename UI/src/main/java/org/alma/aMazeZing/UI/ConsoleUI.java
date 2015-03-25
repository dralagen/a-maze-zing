package org.alma.aMazeZing.UI;

import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.core.Core;
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

	private 	Core core;

	public void loadUI (Core c) {
		core = c;
		p = core.getPlayer();

		p.addObserver(this);
        this.p = p;

		//printPluginChoices

		//printPlayer
		//printMap

	}

	public void close(){}

    @Override
    public void update (Observable o, Object arg) {
		// addObservers

        if (o instanceof Player) {
            printPlayer((Player) o);
        }
		else if (o instanceof Map) {
            //printMap((Map) o);
        }

    }

    private void printPlayer (Player player) {
        System.out.println(player.getGold());
    }
}

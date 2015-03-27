package org.alma.aMazeZing.plugins;

import org.alma.aMazeZing.core.Core;
import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.platform.Launcher;

import java.util.List;
import java.util.Observer;

/**
 * Created on 2/12/15.
 *
 * @author dralagen
 */
public interface UI extends Observer {

	public void loadPlayer(Player c);

    public void loadMap(Map m);

    public void loadControllers(List<Controller> controller);

    public void paint();

    public void close();

    public boolean isGraphical();

}

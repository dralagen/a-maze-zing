package org.alma.aMazeZing.core;

import org.alma.aMazeZing.history.History;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.platform.Launcher;
import org.alma.aMazeZing.plugins.MapBuilder;
import org.alma.aMazeZing.plugins.UI;

import java.util.List;


/**
 * version 1.0 <12/03/15>
 */
public class Core implements Launcher {

	private Player p;
	private Map map;
    private List<History> histories;

	private MapBuilder mapBuilder;
	private UI ui;

	public Core(){

    }

    @Override
    public boolean run() { 
		
		//Do nothing

        return false;
    }
}

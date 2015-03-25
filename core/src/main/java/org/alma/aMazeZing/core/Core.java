package org.alma.aMazeZing.core;

import org.alma.aMazeZing.history.History;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.platform.Launcher;
import org.alma.aMazeZing.platform.ModuleLoader;
import org.alma.aMazeZing.plugins.MapBuilder;
import org.alma.aMazeZing.plugins.UI;

import java.util.ArrayList;
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
		System.out.println("LANCEMENT");
        ModuleLoader ml = ModuleLoader.getInstance();

		/* Player */
        p = new Player();
        System.out.println("Initialisation du joueur...");

		map=null;
		histories = new ArrayList<History>();

		List<String> l=ml.getPluginsForInterface(UI.class);
		
		/*
       	if (l.size()==0)
			exception;
		*/		

		UI ui = (UI) ml.load(l.get(0));
		ui.loadUI(this);
			
	} 

	public void setUI(UI newUi){
		ui.close();
		ui = newUi;
		ui.loadUI(this);
	}

	public void setMapBuiler(MapBuilder mb){
		mapBuilder = mb;
		map = mapBuilder.getMap(histories);
	}

	public Player getPlayer(){
		return p;
	}

	public Map getMap(){
		return map;
	}
	

    @Override
    public boolean run() { 
		
		Core c = new Core();

        return false;
    }
}

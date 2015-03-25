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

		/*		
        System.out.println("LANCEMENT");
        ModuleLoader ml = ModuleLoader.getInstance();

		// Player
        Player p = new Player();
        System.out.println("Initialisation du joueur...");


        // UI
		List<String> l=ml.getPluginsForInterface(UI.class);
        System.out.print(l.size()+" UI(s) : ");
        for (String s: l){
            System.out.println(s+", ");
        }

        Object o = ml.load("org.alma.aMazeZing.GUI.GUI");
        if (o == null) {
            return false;
        }
        System.out.println("GUI loaded");

        UI ui = (UI) o;
		ui.loadUI(p);

        /*
		List<String> l=ml.getPluginsForInterface(MapBuilder.class);
        System.out.print(l.size()+" MapBuilder(s) : ");
        for (String s: l){
            System.out.println(s+", ");
        }

        o =  ml.load("org.alma.aMazeZing.map.MapBuilderRandom");
        if (o == null) {
            return false;
        }

        // Map 
        MapBuilder map = (MapBuilder)  o;
        Map m = map.getMap(new ArrayList<History>());

        System.out.println("MapBuilderRandom chargée "+m.getWidth()+"-"+m.getHeight());
        ui.loadUI(p, m);*/

        return false;
    }
}

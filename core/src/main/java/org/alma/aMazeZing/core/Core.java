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

    @Override
    public boolean run() {
        System.out.println("LANCEMENT");
        ModuleLoader ml = ModuleLoader.getInstance();

        /* UI */
        Object o = ml.load("org.alma.aMazeZing.UI.ConsoleUI");
        if (o == null) {
            return false;
        }
        System.out.println("ConsoleUI loaded");

        UI ui = (UI) o;

        /* Player */
        Player p = new Player();
        System.out.println("Initialisation du joueur...");

        List<String> l=ml.getPluginsForInterface(MapBuilder.class);
        System.out.print(l.size()+" MapBuilder(s) : ");
        for (String s: l){
            System.out.println(s+", ");
        }

        o =  ml.load("org.alma.aMazeZing.map.MapBuilderRandom");
        if (o == null) {
            return false;
        }

        /* Map */
        MapBuilder map = (MapBuilder)  o;
        Map m = map.getMap(new ArrayList<History>());

        System.out.println("MapBuilderRandom chargée "+m.getWidth()+"-"+m.getHeight());
        ui.loadUI(p, m);

        return false;
    }
}

package org.alma.aMazeZing.core;

import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.platform.Launcher;
import org.alma.aMazeZing.platform.ModuleLoader;
import org.alma.aMazeZing.plugins.MapBuilder;
import org.alma.aMazeZing.plugins.UI;

/**
 * version 1.0 <12/03/15>
 */
public class Core implements Launcher {

    @Override
    public boolean run() {
        System.out.println("LANCEMENT");
        ModuleLoader ml = ModuleLoader.getInstance();
        Object o = ml.load("org.alma.aMazeZing.UI.ConsoleUI");
        if (o == null) {
            return false;
        }
        System.out.println("ConsoleUI loaded");

        UI ui = (UI) o;

        Player p = new Player();
        System.out.println("Initialisation du joueur...");
        ui.loadUI(p);

        o =  ml.load("org.alma.aMazeZing.map.BasicMap");
        if (o == null) {
            return false;
        }
        MapBuilder map = (MapBuilder)  o;

        System.out.println("BasicMap chargée");
        return false;
    }
}

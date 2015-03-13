package org.alma.aMazeZing.core;

import org.alma.aMazeZing.platform.Launcher;
import org.alma.aMazeZing.platform.ModuleLoader;

/**
 * version 1.0 <12/03/15>
 */
public class Core implements Launcher {

    @Override
    public boolean run() {
        System.out.println("LANCEMENT");
        ModuleLoader ml = ModuleLoader.getInstance();
        Object o = ml.load("org.alma.aMazeZing.UI.ConsoleUI");
        if (o != null) {
            System.out.println("ConsoleUI loaded");
        }

        return false;
    }
}

package org.alma.aMazeZing.core;

import org.alma.aMazeZing.UI.UI;

/**
 * Created on 2/9/15.
 *
 * @author dralagen
 */
public class main {

    public static void main (String[] args) throws Exception {

        ModuleLoader loader = new ModuleLoader();

        Player p = new Player();
        UI ui = (UI) loader.load("UI.class");
        ui.loadUI(p);

        p.setGold(5);
    }
}

package org.alma.aMazeZing.core;

import org.alma.aMazeZing.plugins.UI;

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

        /*
        MapBuilder mb = (MapBuilder) loader.load("MapBuilder");

        ArrayList<Quest> quests = new ArrayList<Quest>();
        quests.add( (Quest) loader.load("quest") );
        Map m = mb.getMap(quests);
        */

        PluginsHandler PA = new PluginsHandler(loader);
        PA.getPluginsForInterface(UI.class);
        PA.run();
    }

}

package org.alma.aMazeZing.core;

import org.alma.aMazeZing.quest.Quest;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.UI.UI;

import java.util.List;

/**
 * Standard plugins handler included in the platform
 *
 * The PluginsHandler should be an intermediate plugin that requests the platform and
 * is looking for plugins to load.
 *
 * @author Cr3aHal0
 * @version 1.0 <04/03/15>
 */
public class PluginsHandler {

    //Protected instance of ModuleLoader
    protected ModuleLoader loader;

    //Protected instance of the Player (monoplayer game)
    protected Player player;

    //Constructor
    public PluginsHandler(ModuleLoader loader) {
        this.loader = loader;
    }

    /**
     * Define which player the components will be observing
     * @param player the Player instance
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /*
     * Behaviour we want to implement for the PluginsHandler :
     * - checking dependencies between quests/maps/player...
     * - load the UI
     */
    public void loadPlugins() {
        //Load quests if required
        List<Quest> quests = (List<Quest>) loader.load("quests");
        /*
         * TODO : do something interesting with them :
         * - make them observe the player...
         * - etc...
         */

        //Load maps if required
        List<Map> maps = (List<Map>) loader.load("maps");
        /*
         * TODO : do something interesting with the maps :
         * - make them observe the player...
         * - etc...
         */

        //Load quests if required
        UI ui = (UI) loader.load("ui");
        /*
         * TODO : use the UI
         */
    }
}

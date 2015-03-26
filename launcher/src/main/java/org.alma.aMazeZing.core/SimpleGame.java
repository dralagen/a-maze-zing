package org.alma.aMazeZing.core;

import org.alma.aMazeZing.history.History;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.plugins.MapBuilder;
import org.alma.aMazeZing.plugins.UI;

import java.util.ArrayList;

/**
 * Created on 3/26/15.
 *
 * @author dralagen
 */
public class SimpleGame implements Runnable {

    private Player player;
    private UI ui;
    private MapBuilder mapBuilder;
    private History history;

    public SimpleGame() {

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public void setMapBuilder(MapBuilder mapBuilder) {
        this.mapBuilder = mapBuilder;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    @Override
    public void run() {
        System.out.println("--- Start ---");

        boolean finished = false;

        Map m = mapBuilder.getMap(new ArrayList<History>());

        ui.loadPlayer(player);
        ui.loadMap(m);

        while (!ui.isFinished()) {
            ui.paint();
        }

        System.out.println("--- Fin ---");
    }
}

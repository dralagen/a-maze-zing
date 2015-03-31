package org.alma.aMazeZing.core;

import org.alma.aMazeZing.history.History;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.plugins.Controller;
import org.alma.aMazeZing.plugins.MapBuilder;
import org.alma.aMazeZing.plugins.UI;

import java.util.ArrayList;
import java.util.List;

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
    private List<Controller> controllers;

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

    public void setControllers(List<Controller> controllers) {
        this.controllers = controllers;
    }

    public void setHistory(History history) {
        this.history = history;
        history.setPlayer(player);
    }

    @Override
    public void run() {
        System.out.println("--- Start ---");

        boolean finished = false;

        List<History> hist = new ArrayList<History>();
        hist.add(this.history);

        Map m = mapBuilder.getMap(hist);

        ui.loadPlayer(player);
        ui.loadMap(m);

        if (ui.isGraphical()) {
            for (Controller c : controllers) {
                c.loadMap(m);
                c.loadPlayer(player);
            }
            ui.loadControllers(controllers);
        }

        while (!history.isFinished()) {
            ui.paint();
        }

        //TODO : Display a "Game Over" scene

        ui.close();

        System.out.println("Félicitations, vous avez terminé toutes les quêtes !");
        System.out.println("--- Fin ---");
    }
}

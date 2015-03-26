package org.alma.aMazeZing.core;

import org.alma.aMazeZing.history.History;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.platform.Launcher;
import org.alma.aMazeZing.platform.ModuleLoader;
import org.alma.aMazeZing.plugins.MapBuilder;
import org.alma.aMazeZing.plugins.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SimpleLauncher implements Launcher, ActionListener
{
    private boolean loaded = false;

    private ModuleLoader ml;

    private PluginWindow pw;

    private Player player;
    private UI ui;
    private MapBuilder map;
    private History history;

    public boolean run() {
        System.out.println("SimpleLauncher running !");

        ml = ModuleLoader.getInstance();

        pw = new PluginWindow(this, ml);

        return true;

    }

    public void actionPerformed(ActionEvent e){
        System.out.println("--- Chargement ---");

        try {
            loadMyDependencies();
            start();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        pw.dispatchEvent(new WindowEvent(pw, WindowEvent.WINDOW_CLOSING));
    }

    private void loadMyDependencies() throws ClassNotFoundException {
        //Player
        player = new Player();

        //UI
        Object o = ml.load(pw.getUiPlugin());
        if (o == null) {
            throw new ClassNotFoundException(pw.getUiPlugin());
        }
        System.out.println("UI chargée");

        ui = (UI) o;

        //Map
        o =  ml.load(pw.getMapPlugin());
        if (o == null) {
            throw new ClassNotFoundException(pw.getMapPlugin());
        }
        System.out.println("Map chargée");
        map = (MapBuilder)  o;

        //History
        o = ml.load(pw.getHistoryPlugin());
        if (o == null) {
            throw new ClassNotFoundException(pw.getHistoryPlugin());
        }
        System.out.println("History chargée");

        history = (History) o;
    }

    public boolean start() {

        boolean finished = false;

        Map m = map.getMap(new ArrayList<History>());

        ui.loadPlayer(player);
        ui.loadMap(m);

        while (!ui.isFinished()) {
            ui.paint();
        }

        System.out.println("--- Fin ---");
        return true;
    }
}
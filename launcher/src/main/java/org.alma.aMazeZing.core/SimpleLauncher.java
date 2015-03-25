package org.alma.aMazeZing.core;

import org.alma.aMazeZing.history.History;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.plugins.MapBuilder;
import org.alma.aMazeZing.plugins.UI;
import org.alma.aMazeZing.platform.ModuleLoader;
import org.alma.aMazeZing.platform.Launcher;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SimpleLauncher implements Launcher, ActionListener
{
    private boolean loaded = false;

    private ModuleLoader ml;

    private PluginWindow pw;

    public boolean run() {
        System.out.println("SimpleLauncher running !");

        ml = ModuleLoader.getInstance();

        pw = new PluginWindow(this, ml);

        return true;

    }

    public void actionPerformed(ActionEvent e){
        System.out.println("--- Chargement ---");
        loaded = true;

        start();

        pw.dispatchEvent(new WindowEvent(pw, WindowEvent.WINDOW_CLOSING));
    }

    public boolean start() {

        //UI
        Object o = ml.load(pw.getUiPlugin());
        if (o == null) {
            return false;
        }
        System.out.println("UI chargée");
        UI ui = (UI) o;

        //Map
        o =  ml.load(pw.getMapPlugin());
        if (o == null) {
            return false;
        }
        System.out.println("Map chargée");
        MapBuilder map = (MapBuilder)  o;
        Map m = map.getMap(new ArrayList<History>());

        //History
        o = ml.load(pw.getHistoryPlugin());
        if (o == null) {
            return false;
        }
        System.out.println("History chargée");
        History history = (History) o;

        boolean finished = false;

        /*while (!finished) {

        }*/

        System.out.println("--- Fin ---");
        return true;
    }
}
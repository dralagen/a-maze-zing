package org.alma.aMazeZing.core;

import org.alma.aMazeZing.history.History;
import org.alma.aMazeZing.platform.Launcher;
import org.alma.aMazeZing.platform.ModuleLoader;
import org.alma.aMazeZing.plugins.MapBuilder;
import org.alma.aMazeZing.plugins.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class SimpleLauncher implements Launcher, ActionListener
{
    private boolean loaded = false;

    private ModuleLoader ml;

    private PluginWindow pw;
    private Thread tpw;
    private SimpleGame game;

    public boolean run() {
        System.out.println("SimpleLauncher running !");

        ml = ModuleLoader.getInstance();

        pw = new PluginWindow(this, ml);
        tpw = new Thread(pw);

        game = new SimpleGame();

        tpw.start();

        try {
            tpw.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        game.run();

        return true;

    }

    public void actionPerformed(ActionEvent e){
        System.out.println("--- Chargement ---");

        try {
            loadMyDependencies();
            pw.stopWait();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        pw.dispatchEvent(new WindowEvent(pw, WindowEvent.WINDOW_CLOSING));

    }

    private void loadMyDependencies() throws ClassNotFoundException {
        //Player
        game.setPlayer(new Player());

        //UI
        Object o = ml.load(pw.getUiPlugin());
        if (o == null) {
            throw new ClassNotFoundException(pw.getUiPlugin());
        }
        System.out.println("UI chargée");

        game.setUi((UI) o);

        //Map
        o =  ml.load(pw.getMapPlugin());
        if (o == null) {
            throw new ClassNotFoundException(pw.getMapPlugin());
        }
        System.out.println("Map chargée");
        game.setMapBuilder((MapBuilder) o);

        //History
        o = ml.load(pw.getHistoryPlugin());
        if (o == null) {
            throw new ClassNotFoundException(pw.getHistoryPlugin());
        }
        System.out.println("History chargée");

        game.setHistory((History) o);
    }
}
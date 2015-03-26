package org.alma.aMazeZing.UI;

import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.platform.Launcher;
import org.alma.aMazeZing.plugins.UI;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created on 2/12/15.
 *
 * @author dralagen
 */
public class ConsoleUI implements UI, Observer {

    private Map map;

    private Player player;

	private Launcher core;

	public void loadPlayer (Player p) {
        this.player = p;
        player.addObserver(this);
    }

    public void loadMap (Map m) {
        this.map = m;
        map.addObserver(this);
    }

    @Override
    public void paint() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < map.getHeight(); i++) {
            for(int j = 0; j < map.getWidth(); j++) {
                sb.append("+");
                if (i==0) {
                    sb.append("-");
                }
                else
                {
                    sb.append(" ");
                }
            }
            sb.append("+\n");

            for(int j = 0; j < map.getWidth(); j++) {
                if (j == 0) {
                    sb.append("|");
                }
                else
                {
                    sb.append(" ");
                }

                if (player.getX() == j && player.getY() == i) {
                    sb.append("P");
                }
                else {
                    sb.append(" ");
                }

                if (j == map.getWidth()-1) {
                    sb.append("|");
                }
            }
            sb.append("\n");

        }
        for (int j=0; j < map.getWidth(); j++) {
            sb.append("--");
        }
        sb.append("-\n");

        System.out.println(sb.toString());

        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);
        String line = "";

        while (!line.equalsIgnoreCase("s")
                && !line.equalsIgnoreCase("z")
                && !line.equalsIgnoreCase("q")
                && !line.equalsIgnoreCase("d")) {

            line = in.nextLine();

            if (line.equalsIgnoreCase("S")) {
                map.moveBottom(player);
            }
            else if (line.equalsIgnoreCase("Z")) {
                map.moveTop(player);
            }
            else if (line.equalsIgnoreCase("Q")) {
                map.moveLeft(player);
            }
            else if (line.equalsIgnoreCase("D")) {
                map.moveRight(player);
            }
            //do something
        }

    }

    public void close(){}

    @Override
    public boolean isFinished() {
        return (player.getX() == 2 && player.getY() == 1);
    }

    @Override
    public void update (Observable o, Object arg) {
		// addObservers

        if (o instanceof Player) {
            paint();
        }
		else if (o instanceof Map) {
            paint();
        }

    }

    public void updateEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) { // touche flèche droite
            map.moveRight(player);
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) { // touche flèche gauche
            map.moveLeft(player);
        } else if (event.getKeyCode() == KeyEvent.VK_UP) { // touche flèche haut
            map.moveTop(player);
        } else if (event.getKeyCode() == KeyEvent.VK_DOWN) { // touche flèche bas
            map.moveBottom(player);
        }
    }

}

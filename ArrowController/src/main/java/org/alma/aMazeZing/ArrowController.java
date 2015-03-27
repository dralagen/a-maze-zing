package org.alma.aMazeZing.controller;

import org.alma.aMazeZing.plugins.Controller;
import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.map.Map;

import java.awt.event.KeyEvent;

public class ArrowController implements Controller
{
    private Player player;

    private Map map;

    @Override
    public void loadPlayer(Player p) {
        this.player = p;
    }

    @Override
    public void loadMap(Map m) {
        this.map = m;
    }

    @Override
    public void updateEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            map.moveRight(player);
            System.out.println("Move to right");
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            map.moveLeft(player);
            System.out.println("Move to left");
        } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            map.moveBottom(player);
            System.out.println("Move to bottom");
        } else if (event.getKeyCode() == KeyEvent.VK_UP) {
            map.moveTop(player);
            System.out.println("Move to top");
        }
    }
}
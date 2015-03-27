package org.alma.aMazeZing.UI;

import org.alma.aMazeZing.plugins.UI;
import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.item.Item;
import org.alma.aMazeZing.plugins.Controller;

import java.lang.Override;
import java.util.Observer;
import java.util.Observable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GUI implements UI, Observer
{

    private List<Controller> controllers;

    private Map map;

    private Player player;

    private JFrame frame;

    private GUIPane pane = null;

    public GUI() {
        controllers = new ArrayList<Controller>();

        frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void loadPlayer(Player player) {
        this.player = player;
        this.player.addObserver(this);
    }

    public void loadControllers(List<Controller> controllers) {
        this.controllers.addAll(controllers);
    }

    @Override
    public void loadMap(Map m) {
        this.map = m;
        this.map.addObserver(this);
    }

    @Override
    public void paint() {
        if (pane == null) {
            GUIPane pane = new GUIPane(player, map);

            /** add listener for keyboard **/
            this.frame.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent e) {
                    for (Controller ctrl : controllers) {
                        ctrl.updateEvent(e);
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {}

                @Override
                public void keyTyped(KeyEvent e) {}
            });

            frame.add(pane);
            frame.pack();
            frame.setVisible(true);
        }
        frame.repaint();
    }

    @Override
    public boolean isGraphical() {
        return true;
    }

    @Override
    public void close() {

    }

    @Override
    public void update (Observable o, Object arg) {
        // addObservers

        if (o instanceof Player) {
            if (arg instanceof Item) {
                System.out.println("player receive an " + ((Item) arg).getName());
            }
        }
        else if (o instanceof Map) {
            System.out.println("map change");
        }

    }

    public class GUIPane extends JPanel {

        private int columnCount = 5;
        private int rowCount = 5;
        private List<Rectangle> cells;
        private Point selectedCell;

        public GUIPane(Player p, Map m) {
            cells = new ArrayList<>(columnCount * rowCount);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        @Override
        public void invalidate() {
            cells.clear();
            selectedCell = null;
            super.invalidate();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int width = getWidth();
            int height = getHeight();

            int cellWidth = width / columnCount;
            int cellHeight = height / rowCount;

            int xOffset = (width - (columnCount * cellWidth)) / 2;
            int yOffset = (height - (rowCount * cellHeight)) / 2;

            //TODO : draw the player and the map accordingly

            if (cells.isEmpty()) {
                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < columnCount; col++) {
                        Rectangle cell = new Rectangle(
                                xOffset + (col * cellWidth),
                                yOffset + (row * cellHeight),
                                cellWidth,
                                cellHeight);
                        cells.add(cell);
                    }
                }
            }

            if (selectedCell != null) {

                int index = selectedCell.x + (selectedCell.y * columnCount);
                Rectangle cell = cells.get(index);
                g2d.setColor(Color.BLUE);
                g2d.fill(cell);

            }

            g2d.setColor(Color.GRAY);
            for (Rectangle cell : cells) {
                g2d.draw(cell);
            }

            g2d.dispose();
        }
    }

}
package org.alma.aMazeZing.core;

import org.alma.aMazeZing.platform.ModuleLoader;

import javax.swing.*;
import java.awt.*;

public class PluginWindow extends JFrame implements Runnable
{
    private JTabbedPane onglet;
    //Compteur pour le nombre d'onglets
    private int nbreTab = 0;

    private Panel panel1;

    private Panel panel2;

    private Panel panel3;

    private SimpleLauncher sl;
    private ModuleLoader ml;

    private boolean wait = true;

    public PluginWindow(SimpleLauncher sl, ModuleLoader ml){
        this.sl = sl;
        this.ml = ml;
    }

    public String getUiPlugin() {
        return panel1.getValue();
    }

    public String getMapPlugin() {
        return panel2.getValue();
    }

    public String getHistoryPlugin() {
        return panel3.getValue();
    }

    public void stopWait() {
        wait = false;
    }

    @Override
    public void run() {

        this.setLocationRelativeTo(null);
        this.setTitle("Gestion des plugins");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 200);

        onglet = new JTabbedPane();

        panel1 = new Panel(ml.getPluginsForInterface(org.alma.aMazeZing.plugins.UI.class));
        onglet.addTab("UI", panel1);

        panel2 = new Panel(ml.getPluginsForInterface(org.alma.aMazeZing.plugins.MapBuilder.class));
        onglet.addTab("Maps", panel2);

        panel3 = new Panel(ml.getPluginsForInterface(org.alma.aMazeZing.history.History.class));
        onglet.addTab("History", panel3);

        this.getContentPane().add(onglet, BorderLayout.CENTER);

        //Ajout du bouton pour retirer l'onglet sélectionné
        JButton load = new JButton("Charger");
        load.addActionListener(sl);

        JPanel pan = new JPanel();
        pan.add(load);

        this.getContentPane().add(pan, BorderLayout.SOUTH);
        this.setVisible(true);

        while (wait) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

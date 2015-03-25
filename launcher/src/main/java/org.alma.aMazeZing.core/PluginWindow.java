package org.alma.aMazeZing.core;

import org.alma.aMazeZing.platform.ModuleLoader;
import org.alma.aMazeZing.platform.Launcher;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JList;

public class PluginWindow extends JFrame
{
    private JTabbedPane onglet;
    //Compteur pour le nombre d'onglets
    private int nbreTab = 0;

    private Panel panel1;

    private Panel panel2;

    private Panel panel3;

    public PluginWindow(SimpleLauncher sl, ModuleLoader ml){
        this.setLocationRelativeTo(null);
        this.setTitle("Gestion des plugins");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}

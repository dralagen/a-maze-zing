package org.alma.aMazeZing.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JList;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Panel extends JPanel implements ListSelectionListener {
    private List<String> intf;
    private static int COUNT = 0;

    private JList liste;

    public Panel(){}

    public Panel(List<String> list){
        this.intf = list;

        String[] choices = new String[list.size()];
        list.toArray(choices); // fill the array

        liste = new JList(choices);
        liste.addListSelectionListener(this);
    }

    public void valueChanged(ListSelectionEvent evt) {
        System.out.println("Plugin sélectionné : " + (String)liste.getSelectedValue());
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        this.add(liste);
    }

    public String getValue() {
        return (String)liste.getSelectedValue();
    }
}
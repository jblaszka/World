package gui;

import simulation.IWorldMap;
import simulation.Simulation;
import simulation.SimulationParams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {

    public MapPanel mapPanel;
    private final Timer timer;

    public MainPanel() {
        mapPanel = new MapPanel(Simulation.getWorldMap());
        mapPanel.setPreferredSize(new Dimension(700, 500));
        add(mapPanel);
        timer = new Timer(1000/ SimulationParams.getField("speed"), this);
        timer.start();
    }


    private void rerenderMap() {
        if (Simulation.getWorldMap().getAnimalsLocations().isEmpty()) {
            timer.stop();
            return;
        }
        Simulation.simulateDay();
        mapPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        rerenderMap();
    }

}

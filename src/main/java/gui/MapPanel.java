package gui;
import simulation.IWorldMap;

import javax.swing.*;

public class MapPanel extends JPanel{
    public IWorldMap map;

    public MapPanel(IWorldMap map) {
        this.map = map;
    }
}

package gui;

import simulation.IWorldMap;
import simulation.JsonParser;
import simulation.Simulation;
import simulation.SimulationParams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {

    public MapPanel mapPanel;
    private final Timer timer;
    private final GridBagConstraints constraints = new GridBagConstraints();
    private int nextRow = 0 ;
    private int MAP_COL = 2;
    private int MAP_ROW = 0;
    private CustomTextArea statsInput;
    private final static String STATS_FILE = "stats.json";

    private final static int DEAFAULT_COLUMN = 0;

    public MainPanel() {
        initLayout();

        CustomButton startBtn = createButton("Start new game");
        startBtn.addActionListener((ActionEvent e) -> startApp());
        CustomButton stopBtn = createButton("Pause game");
        stopBtn.addActionListener((ActionEvent e) -> stopApp());
        CustomButton continueBtn = createButton("Continue game");
        continueBtn.addActionListener((ActionEvent e) -> continueApp());
        CustomButton dumpToJsonBtn = createButton("Dump stats to JSON");
        SimulationParams.getParamsMap().forEach((fieldName, value) ->
                new CustomTextField(fieldName, value, nextRow++, DEAFAULT_COLUMN, constraints, this));
        statsInput = new CustomTextArea(
                "Press 'start new game'\nto reset game",
                nextRow++,
                DEAFAULT_COLUMN,
                LayoutConstants.TEXT_AREA_HEIGHT,
                LayoutConstants.TEXT_AREA_WIDTH,
                constraints,
                this
        );
        mapPanel = new MapPanel(Simulation.getWorldMap());
        addMapPanel();
        timer = new Timer(1000/ SimulationParams.getField("speed"), this);
        timer.start();
    }

    private void startApp(){
        if( timer.isRunning()) timer.stop();
        Simulation.setSimulation();
        mapPanel.repaint();
        timer.start();
    }

    private void stopApp(){
        if( timer.isRunning()) timer.stop();
    }

    private void continueApp(){
        if( !timer.isRunning()) timer.start();
    }



    private void rerenderMap() {
        if (Simulation.getWorldMap().getAnimalsLocations().isEmpty()) {
            timer.stop();
            return;
        }
        Simulation.simulateDay();
        mapPanel.repaint();
    }

    private void initLayout(){
        setLayout(new GridBagLayout());
        setBackground(CustomColors.BACKGROUND_COLOR);
        constraints.insets = new Insets(
                LayoutConstants.TOP_MARGIN,
                LayoutConstants.LEFT_MARGIN,
                LayoutConstants.BOTTOM_MARGIN,
                LayoutConstants.RIGHT_MARGIN);
        constraints.anchor = GridBagConstraints.LINE_START;
    }

    private CustomButton createButton(String text){
        return new CustomButton(text, nextRow++, DEAFAULT_COLUMN, true, this, constraints );
    }

    @Override
    public void actionPerformed(ActionEvent e){
        rerenderMap();
    }

    private void addMapPanel() {
        constraints.gridx = MAP_COL;
        constraints.gridy = MAP_ROW;
        constraints.gridwidth = 1;
        constraints.gridheight = nextRow + 1;
        constraints.ipadx = LayoutConstants.MAP_SCALE * Simulation.getWorldMap().getWidth();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        add(mapPanel, constraints);
    }

    private void dumpStatictics(){
        JsonParser.dumpStatisticToJsonFile(STATS_FILE, Simulation.getWorldMap().getStatistics());
    }

}

package gui;

import simulation.Animal;
import simulation.IWorldMap;
import simulation.Vector2D;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    public IWorldMap map;

    public MapPanel(IWorldMap map) {
        this.map = map;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setupLayout(graphics);
        drawPlants(graphics);
        drawAnimals(graphics);
        drawBorder(graphics);
    }

    private void setupLayout(Graphics graphics) {
        graphics.setColor(CustomColors.BACKGROUND_COLOR);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(CustomColors.MAP_BACKGROUND_COLOR);
        graphics.fillRect(
                0, 0,
                map.getWidth() * LayoutConstants.MAP_SCALE,
                map.getHeight() * LayoutConstants.MAP_SCALE
        );
    }

    private void drawPlants(Graphics graphics) {
        graphics.setColor(CustomColors.PLANT_COLOR);
        Polygon plant = createPlant();
        Vector2D prevPosition = new Vector2D(0,0);
        for (Vector2D position: map.getPlantsLocations().keySet()){
            Vector2D translation = position.subtract(prevPosition);
            plant.translate(
                    translation.x() * LayoutConstants.MAP_SCALE,
                    translation.y() * LayoutConstants.MAP_SCALE);
            graphics.fillPolygon(plant);
            prevPosition = position;
    }
    }

    private Polygon createPlant(){
        int[] leafX = {
                0,
                LayoutConstants.MAP_SCALE / 4,
                LayoutConstants.MAP_SCALE,
                3 * LayoutConstants.MAP_SCALE / 4
        };
        int[] leafY = {
                LayoutConstants.MAP_SCALE,
                LayoutConstants.MAP_SCALE / 4,
                0,
                3 * LayoutConstants.MAP_SCALE / 4
        };
        return new Polygon(leafX, leafY, leafX.length);
    }


    private void drawAnimals(Graphics graphics) {
        map.getAnimalsLocations().forEach((position, animals) -> {
            int maxEnergy = animals.stream().mapToInt(Animal::getEnergy).max().orElse(0);
            int energyIndex = Math.min(maxEnergy / 10, CustomColors.ENERGY_COLOR.length - 1);
            Color energyColor = CustomColors.ENERGY_COLOR[energyIndex];
            graphics.setColor(energyColor);
            graphics.fillOval(
                    position.x() * LayoutConstants.MAP_SCALE,
                    position.y() * LayoutConstants.MAP_SCALE,
                    LayoutConstants.MAP_SCALE,
                    LayoutConstants.MAP_SCALE
            );
            displayAnimalsNumber(animals.size(), graphics, position, energyColor);
        });
        }

    private void displayAnimalsNumber(int noOfAnimals, Graphics graphics, Vector2D position, Color energyColor){
        Color textColor = new Color(
                255 - energyColor.getRed(),
                255 - energyColor.getGreen(),
                255 - energyColor.getBlue()
        );
        String text = Integer.toString(noOfAnimals);
        int x = position.x() * LayoutConstants.MAP_SCALE + LayoutConstants.MAP_SCALE / 2;
        int y = position.y() * LayoutConstants.MAP_SCALE + LayoutConstants.MAP_SCALE / 2;
        int width = (int) graphics.getFontMetrics().getStringBounds(text, graphics).getWidth();
        int height = graphics.getFontMetrics().getMaxAscent();
        graphics.setColor(textColor);
        graphics.drawString(text, x - width / 2 , y + height / 2 );

    }

    private void drawBorder(Graphics graphics){
        graphics.setColor(CustomColors.BORDER_COLOR);
        graphics.drawRect(
                0,
                0,
                LayoutConstants.MAP_SCALE * map.getWidth(),
                LayoutConstants.MAP_SCALE * map.getHeight()
        );
    }
}
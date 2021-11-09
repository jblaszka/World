package simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class WorldMap extends AbstractWorldMap {
    private static final int ANIMALS_ND = 15, PLANTS_NUMBER = 100;
    private ArrayList<Animal> animals = new ArrayList<>();
    private LinkedList<Plant> plants = new LinkedList<>();
    private Random random;


    public WorldMap(int width, int height) {
        super(width, height);
        random = new Random();
        for (int i = 0; i < ANIMALS_ND; i++) {
            animals.add(new Animal(getRandomVector()));
        }

        for (int i = 0; i < PLANTS_NUMBER; i++) {
            addNewPlant();
        }
    }

    private Plant getPlantAtPosition(Vector2D position) {
        for (Plant plant : plants) {
            if (plant.getPosition().equals(position)) return plant;
        }
        return null;
    }

    public void eat() {
        for (Animal animal : animals) {
            Plant plant = getPlantAtPosition(animal.getPosition());
            if (plant != null){
                plants.remove(plant);
                System.out.println("Animal ate plant at position " + plant.getPosition());
                addNewPlant();

            }
        }
    }


    @Override
    public void run() {
        for (Animal animal : animals) {
            animal.move(MapDirection.values()[random.nextInt(MapDirection.values().length)]);
        }
    }

    private Vector2D getRandomVector() {
        return new Vector2D(random.nextInt(getWidth()), random.nextInt(getHeight()));
    }


    private void addNewPlant() {
        Vector2D pos = getRandomVector();
        while (PositionIsOccupied(pos)) pos = getRandomVector();
        plants.add(new Plant(pos));
    }

    public boolean PositionIsOccupied(Vector2D position) {
        return getPlantAtPosition(position) != null;
    }
}

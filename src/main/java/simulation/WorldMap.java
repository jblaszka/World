package simulation;
import java.util.*;

public class WorldMap extends AbstractWorldMap {
    private static final int ANIMALS_ND = 15, PLANTS_NUMBER = 100;
    private Map<Vector2D, List<Animal>> animalsPosition = new HashMap<>();
    private Map<Vector2D, Plant> plants = new HashMap<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    private Random random;
    private static final int INITIAL_ENERGY = 22;
    private int dayNumber = 1;
    private int PLANT_ENERGY = 10;


    public WorldMap(int width, int height) {
        super(width, height);
        random = new Random();
        for (int i = 0; i < ANIMALS_ND; i++) {
            Animal animal = new Animal(getRandomVector(), INITIAL_ENERGY);
            animals.add(animal);
            placeAnimalOnMap(animal);
        }
        for (int i = 0; i < PLANTS_NUMBER; i++) {
            addNewPlant();
        }
    }

    private void placeAnimalOnMap(Animal animal){
        animalsPosition.computeIfAbsent(animal.getPosition(), pos -> new LinkedList<>()).add(animal);
    }



    private Plant getPlantAtPosition(Vector2D position) {
        return plants.get(position);
    }

    public void eat() {
        animals.forEach(animal -> {
            if (PositionIsOccupied(animal.getPosition())){

            System.out.println("Animal ate plant at position " + animal.getPosition());
            animal.setEnergy(animal.getEnergy() + PLANT_ENERGY);
            plants.remove(animal.getPosition());
            addNewPlant();
            }
        });
    }


    @Override
    public void run() {
        System.out.println("Today is day number " + dayNumber);
        animalsPosition.clear();
        animals.forEach(animal -> {            animal.move(MapDirection.values()[random.nextInt(MapDirection.values().length)]);
            placeAnimalOnMap(animal);});
        }


    private Vector2D getRandomVector() {
        return new Vector2D(random.nextInt(getWidth()), random.nextInt(getHeight()));
    }


    private void addNewPlant() {
        Vector2D pos = getRandomVector();
        while (PositionIsOccupied(pos)) pos = getRandomVector();
        plants.put(pos, new Plant(pos));
    }

    public boolean PositionIsOccupied(Vector2D position) {
        return getPlantAtPosition(position) != null;

    }

}

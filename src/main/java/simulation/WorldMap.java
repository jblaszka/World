package simulation;
import java.util.*;

public class WorldMap extends AbstractWorldMap {
    private static final int ANIMALS_ND = 15, PLANTS_NUMBER = 100;
    private Map<Vector2D, List<Animal>> animalsPosition = new HashMap<>();
    private Map<Vector2D, Plant> plants = new HashMap<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    private Random random;


    public WorldMap(int width, int height) {
        super(width, height);
        random = new Random();
        for (int i = 0; i < ANIMALS_ND; i++) {
            Animal animal = new Animal(getRandomVector());
            animals.add(animal);
            placeAnimalOnMap(animal);
        }
        for (int i = 0; i < PLANTS_NUMBER; i++) {
            addNewPlant();
        }
    }

    private void placeAnimalOnMap(Animal animal){
            List<Animal> animalsAtPositions = animalsPosition.get(animal.getPosition());
            if (animalsAtPositions == null){
                animalsAtPositions = new LinkedList<>();
                animalsPosition.put(animal.getPosition(), animalsAtPositions);
            }
            animalsAtPositions.add(animal);
        }


    private Plant getPlantAtPosition(Vector2D position) {
        return plants.get(position);
    }

    public void eat() {
        for (Animal animal : animals) {
            if (PositionIsOccupied(animal.getPosition())){
                plants.remove(animal.getPosition());
                System.out.println("Animal ate plant at position " + animal.getPosition());
                addNewPlant();

            }
        }
    }


    @Override
    public void run() {
        animalsPosition.clear();
        for (Animal animal : animals) {
            animal.move(MapDirection.values()[random.nextInt(MapDirection.values().length)]);
            placeAnimalOnMap(animal);
        }
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

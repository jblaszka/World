package simulation;
import java.util.*;
import java.util.stream.Collectors;

public class WorldMap extends AbstractWorldMap{
    private static final int ANIMALS_ND = 15, PLANTS_NUMBER = 100;

    private Map<Vector2D, List<Animal>> animalsPosition = new HashMap<>();
    private Map<Vector2D, Plant> plants = new HashMap<>();
    private List<Animal> animals = new ArrayList<>();

    private Random random;
    private static final int ANIMAL_ENERGY = 22;
    private int dayNumber = 1;
    private int PLANT_ENERGY = 10;


    public WorldMap(int width, int height) {
        super(width, height);
        random = new Random();
        for (int i = 0; i < ANIMALS_ND; i++) {
            addNewAnimal(new Animal(getRandomVector(), ANIMAL_ENERGY));
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
        animalsPosition.forEach((position, animals) -> {
            if (PositionIsOccupied(position)) {
                animals.stream().max(Animal :: compareTo).ifPresent(this::eatPlant);
            }
        });
    }

    private void eatPlant(Animal animal){
        System.out.println("Animal ate plant at position " + animal.getPosition());
        animal.setEnergy(animal.getEnergy() + PLANT_ENERGY);
        plants.remove(animal.getPosition());
        addNewPlant();
    }

    @Override
    public void run() {
        System.out.println("Today is day number " + dayNumber);
        animalsPosition.clear();
        animals.forEach(animal -> {
            animal.move(MapDirection.values()[random.nextInt(MapDirection.values().length)]);
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

    @Override


    public void atTheEndOfDay() {
        animals = animals.stream()
                .map(Animal::aging)
                .map(animal -> animal.setEnergy(animal.getEnergy() - (ANIMAL_ENERGY / 2)))
                .collect(Collectors.toList());
        this.dayNumber++;
    }

    @Override
    public void reproduce(){
        List<Animal> children = new LinkedList<>();
        animalsPosition.forEach((position, animals) -> {
            List<Animal> parents = animals.stream()
                    .filter(a -> a.getEnergy() > ANIMAL_ENERGY / 2)
                    .sorted(Collections.reverseOrder())
                    .limit(2)
                    .collect(Collectors.toList());
            if (parents.size() == 2){
                Animal child = new Animal(parents.get(0), parents.get(1));
                System.out.println("Animal " + child.getAnimalId() + " was born on position " + position);
                children.add(child);
            }
            children.forEach(this::addNewAnimal );
        });
    }
    private void addNewAnimal(Animal animal){
        animals.add(animal);
        placeAnimalOnMap(animal);
    }
    }

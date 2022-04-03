package simulation;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WorldMap extends AbstractWorldMap{
    private int dayNumber = 1;

    private Map<Vector2D, List<Animal>> animalsPositions = new HashMap<>();
    private Map<Vector2D, Plant> plants = new HashMap<>();
    private List<Animal> animals = new ArrayList<>();

    private final Random random = new Random();
    private int animalEnergy;
    private int plantEnergy;
    private int noOfPlants;
    private static final String statsFile = "stats.json";


    public WorldMap(int width, int height, int noOfAnimals, int noOfPlants, int animalEnergy, int plantEnergy) {
        super(width, height);
        this.animalEnergy = animalEnergy;
        this.plantEnergy = plantEnergy;
        this.noOfPlants = noOfPlants;
        for (int i = 0; i < noOfAnimals; i++) {
            addNewAnimal(new Animal(getRandomVector(), animalEnergy));
        }
        for (int i = 0; i < noOfPlants; i++) {
            addNewPlant();
        }
    }


    private void placeAnimalOnMap(Animal animal) {
        animalsPositions
                .computeIfAbsent(animal.getPosition(), k -> new LinkedList<>())
                .add(animal);
    }


    private void placePlantOnMap() {
        Vector2D position = getRandomVector();
        while (isOccupiedByPlant(position)) position = getRandomVector();
        plants.put(position, new Plant(position));
    }


    private boolean isOccupiedByPlant(Vector2D position) {
        return getPlantAtPosition(position) != null;
    }

    private Plant getPlantAtPosition(Vector2D position) {
        return plants.get(position);
    }

    public void eat() {
        animalsPositions.forEach((position, animals) -> {
            if (PositionIsOccupied(position)) {
                animals.stream().max(Animal :: compareTo).ifPresent(this::eatPlant);
            }
        });
        IntStream.range(1,new Random().nextInt(noOfPlants/10)+1).forEach(i -> {
            if (plants.size() < getHeight() * getWidth()) placePlantOnMap();
        });
        }


    private void eatPlant(Animal animal){
        System.out.println("Animal ate plant at position " + animal.getPosition());
        animal.setEnergy(animal.getEnergy() + plantEnergy);
        plants.remove(animal.getPosition());
    }

    @Override
    public void run() {
        System.out.println("Today is day number " + dayNumber);
        animalsPositions.clear();
        animals.forEach(animal -> {
            animal.moveBasedOnGenome();
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
        dayNumber++;
        animals = animals.stream()
                .map(Animal::aging)
                .map(animal -> animal.setEnergy(animal.getEnergy() - (animalEnergy / 5)))
                .filter(animal -> animal.getEnergy() > 0)
                .collect(Collectors.toList());
        createStatisctic();
    }

    @Override
    public void reproduce() {
        List<Animal> children = new LinkedList<>();
        animalsPositions.forEach((position, animals) -> {
            List<Animal> parents = animals.stream()
                    .filter(a -> a.getEnergy() > animalEnergy / 2)
                    .sorted(Collections.reverseOrder())
                    .limit(2)
                    .collect(Collectors.toList());
            if (parents.size() == 2) {
                Animal child = new Animal(parents.get(0), parents.get(1));
                System.out.println("Animal " + child.getAnimalId() + " was born on position " + position);
                children.add(child);
            }
        });
        children.forEach(this::addNewAnimal);
    }

    private void addNewAnimal(Animal animal) {
        animals.add(animal);
        placeAnimalOnMap(animal);
    }

    private void createStatisctic(){
        SimulationStatistics statistics = new SimulationStatistics(
            dayNumber,
            animals.stream().mapToInt(Animal::getAge).average().orElse(0),
            animals.stream().mapToInt(Animal::getNumberOfChildren).average().orElse(0),
            animals.stream().mapToInt(Animal::getEnergy).average().orElse(0),
            animals.size(),
            plants.size());
        System.out.println(statistics);
        JsonParser.dumpStatisticToJsonFile(statsFile, statistics);
    }
    @Override
    public Map<Vector2D, List<Animal>> getAnimalsLocations(){
        return animalsPositions;
    }

    @Override
    public Map<Vector2D, Plant> getPlantsLocations(){
        return plants;
    }
    }

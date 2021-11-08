package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    private static final Random random = new Random();
    private static final int WIDTH = 20, HEIGHT = 30;

    public static void main(String[] args) {
        System.out.println("Start");
        Animal animal = new Animal(new Vector2D(random.nextInt(WIDTH), random.nextInt(HEIGHT)));
        moveAnimal(animal);
        System.out.println("Stop");
    }

    private static void moveAnimal(Animal animal) {
        for (int i = 0; i < 10; i++) {
            animal.move(MapDirection.values()[random.nextInt(MapDirection.values().length)], WIDTH, HEIGHT);
        }
    }

    private static void run(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "n" -> System.out.println("Animal moves north");
                case "e" -> System.out.println("Animal moves east");
                case "s" -> System.out.println("Animal moves south");
                case "w" -> System.out.println("Animal moves west");
                default -> System.out.println("Wrong argument");
            }
        }
    }

    private static void runFromDirections(List<MapDirection> directions) {
        for (MapDirection direction : directions) {
            switch (direction) {
                case NORTH -> System.out.println("Animal moves north");
                case EAST -> System.out.println("Animal moves east");
                case SOUTH -> System.out.println("Animal moves south");
                case WEST -> System.out.println("Animal moves west");
                default -> System.out.println("Wrong argument");
            }
        }
    }

    private static void runFromDirections2(List<MapDirection> directions) {
        for (MapDirection direction : directions) {
            System.out.println("Animal moves " + direction);
        }
    }

    private static List<MapDirection> parseToEnum(String[] args) {
        List<MapDirection> directions = new ArrayList<>();
        for (String arg : args) {
            switch (arg) {
                case "n" -> directions.add(MapDirection.NORTH);
                case "e" -> directions.add(MapDirection.EAST);
                case "s" -> directions.add(MapDirection.SOUTH);
                case "w" -> directions.add(MapDirection.WEST);
                default -> System.out.println("Wrong argument: " + arg);
            }
        }
        return directions;
    }
}

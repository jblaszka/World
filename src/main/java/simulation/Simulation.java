package simulation;

public class Simulation {
    private static final int HEIGHT = 20, WIDTH = 30;
    private static final IWorldMap worldMap;

    static {
        worldMap = new WorldMap(WIDTH, HEIGHT);
    }

    public static IWorldMap getWorldMap() {
        return worldMap;
    }

    public static void simulateDay() {
        worldMap.run();
    }
}

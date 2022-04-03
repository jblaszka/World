package simulation;

import java.util.List;
import java.util.Map;

public interface IWorldMap {
    int getWidth();
    int getHeight();
    void run();
    void eat();
    void atTheEndOfDay();
    void reproduce();
    void setSimulation();
    SimulationStatistics getStatistics();
    Map<Vector2D, List<Animal>> getAnimalsLocations();
    Map<Vector2D, Plant> getPlantsLocations();
}
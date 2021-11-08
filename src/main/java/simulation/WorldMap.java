package simulation;

import java.util.Random;

public class WorldMap extends AbstractWorldMap {
    private Animal animal;
    private Random random;

    public WorldMap(int width, int height) {
        super(width, height);
        this.animal = new Animal(new Vector2D(0, 0));
        this.random = new Random();
    }

    @Override
    public void run() {
        this.animal.move(MapDirection.values()[this.random.nextInt(MapDirection.values().length)], width, height);
    }
}

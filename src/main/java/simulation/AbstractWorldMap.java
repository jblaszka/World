package simulation;

public abstract class AbstractWorldMap implements IWorldMap {
    protected int width;
    protected int height;

    protected AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}

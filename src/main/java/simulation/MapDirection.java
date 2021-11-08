package simulation;

public enum MapDirection {
	NORTH (new Vector2D(0, 1)),
	EAST (new Vector2D(1, 0)),
	SOUTH (new Vector2D(0, -1)),
	WEST (new Vector2D(-1, 0));

	private final Vector2D unitVector;

	MapDirection(Vector2D unitVector) {
		this.unitVector = unitVector;
	}

	public Vector2D getUnitVector() {
		return unitVector;
	}
}

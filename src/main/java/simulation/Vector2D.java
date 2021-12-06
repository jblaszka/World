package simulation;

import java.util.Objects;

public record Vector2D(int x, int y){

	public Vector2D add(Vector2D otherVector) {
		return new Vector2D(x + otherVector.x, y + otherVector.y);
	}

	public Vector2D subtract(Vector2D otherVector) {
		return add(otherVector.opposite());
	}

	public Vector2D opposite() {
		return new Vector2D(-x, -y);
	}


	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}


}

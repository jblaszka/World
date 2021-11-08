package simulation;

import java.util.Objects;

public class Vector2D {
	private int x, y;

	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D add(Vector2D otherVector) {
		return new Vector2D(x + otherVector.x, y + otherVector.y);
	}

	public Vector2D subtract(Vector2D otherVector) {
		return add(otherVector.opposite());
	}

	public Vector2D opposite() {
		return new Vector2D(-x, -y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Vector2D vector2D)) return false;
		return x == vector2D.x && y == vector2D.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}

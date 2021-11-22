package simulation;

public class Animal {
	private Vector2D position;
	private int energy;
	private int age;
	private final int animalId;
	private static int counter = 0;

	public Animal(Vector2D position, int energy) {
		this.position = position;
		this.energy = energy;
		this.age = 1;
		this.animalId = counter++;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void move(MapDirection direction) {
		position = pbc(position.add(direction.getUnitVector()));
		System.out.println("Animal " + animalId + " moves " + direction + ": new position: " + position + "energy: " + energy + " age: " + age);
	}


	public Animal setEnergy(int energy){
		this.energy = energy;
		return this;
	}

	public int getAnimalId(){
		return animalId;
	}

	public int getEnergy(){
		return energy;
	}

	private Animal setAge(int age){
		this.age = age;
		return this;
	}

	public int getAge(){
		return age;
	}

	public Animal aging(){
		age ++;
		return this;
	}

	private Vector2D pbc(Vector2D position) {
		int width = Simulation.getWorldMap().getWidth();
		int height = Simulation.getWorldMap().getHeight();
		if (position.getX() < 0) return position.add(new Vector2D(width, 0));
		if (position.getX() >= width) return position.subtract(new Vector2D(width, 0));
		if (position.getY() < 0) return position.add(new Vector2D(0, height));
		if (position.getY() >= height) return position.subtract(new Vector2D(0, height));

		return position;
	}


}

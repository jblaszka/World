package simulation;

import java.util.Random;

public class Animal implements Comparable<Animal> {
	private Vector2D position;
	private int energy;
	private int age = 1;
	private final int animalId;
	private static int counter = 0;
	private final Genome genome;
	private int numberOfChildren = 0;


	public Animal(Vector2D position, int energy) {
		this.position = position;
		this.energy = energy;
		this.genome = new Genome();
		this.animalId = counter++;
	}

	public Animal(Animal mother, Animal father){
		Vector2D move = MapDirection.values()[new Random().nextInt(MapDirection.values().length)].getUnitVector();
		this.position = pbc(mother.getPosition().add(move));
		this.energy = (mother.getEnergy() + father.getEnergy()) / 4 ;
		this.animalId = counter++;
		this.genome = new Genome(mother.getGenome(), father.getGenome());
		mother.setEnergy(mother.getEnergy() * 3 / 4);
		father.setEnergy(mother.getEnergy() * 3 / 4);
		mother.increaseNumberOfChildren();
		father.increaseNumberOfChildren();
	}



	public Vector2D getPosition() {
		return position;
	}

	public void move(MapDirection direction) {
		position = pbc(position.add(direction.getUnitVector()));
		System.out.println("Animal " + animalId + " moves " + direction + ": new position: " + position + "energy: " + energy + " age: " + age);
	}

	public Genome getGenome(){
		return genome;
	}

	public int getNumberOfChildren(){
		return numberOfChildren;
	}

	public void increaseNumberOfChildren(){
		numberOfChildren++;
	}

	public int getEnergy(){

		return energy;
	}

	public Animal setEnergy(int newEnergy){
		this.energy = newEnergy;
		return this;
	}


	public int getAnimalId(){
		return animalId;
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


	@Override
	public int compareTo(Animal animal) {
		return getEnergy() == animal.getEnergy()
				? getAnimalId() - animal.getAnimalId()
				: getEnergy() - animal.getEnergy();
	}
}

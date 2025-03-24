/*
 * Represents the engine of a car in the racing game. - Bruna
 */

public class Engine{
	private int horsepower;
	private double fuelEfficiency;
	private double weight;

	//constructor
	public Engine(int horsepower, double fuelEfficiency, double weight){
		this.horsepower = horsepower;
		this.fuelEfficiency = fuelEfficiency;
		this.weight = weight;
	}

	public void startEngine(){ //starts engine (print out)
		System.out.println("Engine started.");
	}

	public int adjustAcceleration(int level){
		return horsepower * level;
	}

	public double getMaxSpeed(){
		return horsepower * 0.5;
	}

	public double getWeight(){
		return weight;
	}
}
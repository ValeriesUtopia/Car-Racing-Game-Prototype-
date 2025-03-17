/*
 * Represents a generic vehicle in the racing game. -Bruna
 */

public abstract class Vehicle {
	protected double currentSpeed;

	//constructor that initializes speed
	public Vehicle(){
		this.currentSpeed = 0;
	}

	public abstract void start(); //starts vehicle's movement

	public void stop(){ //stops the vehicle
		this.currentSpeed = 0;
	}

	public double getCurrentSpeed(){ //returns current speed of the vehicle
		return currentSpeed;
	}
}
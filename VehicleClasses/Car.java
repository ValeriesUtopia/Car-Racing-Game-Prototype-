/*
 * Represents a car in the racing game. - Bruna
 */

import java.util.*;

public class Car extends Vehicle{
	private Driver driver;
	private Wheel wheel;
	private Engine engine;
	private double totalWeight;
	private List<Stop> trackRoute; // randomizes a list of stops for this car.
	private int currentStopIndex; // Keeps track of the car's current position.
	private double speed = 0; //speed starts at 0m/s

	//constructor
	public Car(Driver driver, Wheel wheel, Engine engine){
		this.driver = driver;
		this.engine = engine;
		this.wheel = wheel;
		this.totalWeight = calculateTotalWeight();
		this.trackRoute = getRandomRoute(Stop.getRaceStops());
		this.currentStopIndex = 0; //starts at first
		this.speed = 0;
	}

	@Override
	public void start(){
		engine.startEngine();
	}

	//moves the car to the next stop and wears down tires.
	public void moveToNextStop(){
		if (currentStopIndex < trackRoute.size()-1){
			currentStopIndex++;
			wheel.wearOut();
		}

		double acceleration = getActualAcceleration();
		speed += acceleration * 0.5;

		speed = Math.max(speed, 5.0); //to not go negative
	}

	//returns car's current location
	public Stop getCurrentStop(){
		return trackRoute.get(currentStopIndex);
	}

	/*
	 * getRandomRoute method makes the order of available stops for each car random.
	 * ex.: A, B, C, and D  or B, A, D, and C...
	 */
	private List<Stop> getRandomRoute(List<Stop> availableStops){
		List<Stop> randomStops = new ArrayList<>(availableStops);
		Collections.shuffle(randomStops); //learned that this method works to randomize order

		return randomStops;
	}

	//calculates the total weight of the car
	private double calculateTotalWeight() {
		return engine.getWeight() + wheel.getGripLevel();
	}

	//changes acceleration, considering distance between each stop
	public double getActualAcceleration() {
	    double baseAcceleration = engine.adjustAcceleration(1) * wheel.getAccelerationModifier();

	    //checking if not at the last stop
	    if (currentStopIndex >= trackRoute.size() - 1) {
	        return Math.max(baseAcceleration, 5.0); //ensures minimun
	    }

	    //gets the distance between the current stop and the next stop
	    double currentDistance = trackRoute.get(currentStopIndex).getDistance();
	    double nextDistance = trackRoute.get(currentStopIndex + 1).getDistance();
	    double distanceToNextStop = Math.abs(nextDistance - currentDistance); //distance between those

	    //gives a percentage for the reduction (150 being the max distance from stops yet set)
	    double distanceFactor = distanceToNextStop / 150.0;
	    double acceleration = baseAcceleration * (1 - distanceFactor);

    	return Math.max(acceleration, 5.0);//minimun acceleration to prevent getting stuck
	}

	//returns the order of stops for this car
	public List<Stop> getTrackRoute() {
		return trackRoute;
	}

	//getters
	public Driver getDriver(){
		return driver;
	}

	public Engine getEngine(){
		return engine;
	}

	public Wheel getWheel(){
		return wheel;
	}

	public double getTotalWeight(){
		return totalWeight;
	}

	public double getSpeed() {
        return speed;
    }
}
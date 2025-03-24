/*
 * Represents a generic wheel for the car in the racing game. 
 * there will be several types of tires that extend this class. -Bruna
 */

public abstract class Wheel{
	protected double gripLevel;
	protected double tireWear;

	//constructor
	public Wheel(double gripLevel, double tireWear){
		this.gripLevel = gripLevel;
		this.tireWear = tireWear;
	}

	//returns the tire's grip level
	public double getGripLevel(){
		return gripLevel;
	}

	//method to simulate the wear out of a tire with usage
	public void wearOut(){
		this.tireWear += 0.1; //increases wear
		if (this.tireWear > 1.0) this.tireWear = 1.0; //makes sure it doesn't exceed max. wear.
	}

	//change of tire, resetting the wear.
	public void changeTire(){
		this.tireWear = 0; //reset
	}

	//modifies acceleration based on wear
	public double getAccelerationModifier(){
		return gripLevel * (1.0 - tireWear); //if wear is 1 (max), acceleration decresases fully
	}

	//method for specific behavior depending on the tire types.
	public abstract void describeFeature(); //will describe tire type to player
}

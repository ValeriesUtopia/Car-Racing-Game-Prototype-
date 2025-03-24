/*
 * Represents a car driver in the racing game. - Bruna
 */

public class Driver{
	private String name;
	private String drivingStyle;

	//constructor
	public Driver(String name, String drivingStyle){
		this.name = name;
		this.drivingStyle = drivingStyle;
	}

	public void controlCar(Car car){
		System.out.println(name + " is controlling the car.");
	}

	public void adjustDrivingStyle(){
		System.out.println(name + " is adjusting their driving style to " + drivingStyle);
	}

	//getters
	public String getName(){
		return name;
	}

	public String getDrivingStyle(){
		return drivingStyle;
	}
}
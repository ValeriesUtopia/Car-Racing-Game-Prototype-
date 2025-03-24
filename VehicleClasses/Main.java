/*
 * Testing main class
 *
 */
import java.util.*;

public class Main{
	public static void main(String[] args){

		//creates a driver (name, driving style)
		Driver driver = new Driver("Alice", "Defensive");

		//creates an engine (HP, fuel efficiency, weight)
		Engine engine = new Engine(400, 0.8, 150);

		//using different tires
		Wheel hardTire = new HardTire();
		Wheel wetTire = new WetTire();
		Wheel flatTire = new FlatTire();

		//creating cars with different tires
		Car car1 = new Car(driver, hardTire, engine);
		Car car2 = new Car(driver, wetTire, engine);
		Car car3 = new Car(driver, flatTire, engine);

		//displays each car's unique stop order
		System.out.println("\n--- Race Stops Order for Each Car ---");
		System.out.println("Car 1 (Hard Tire): " + car1.getTrackRoute());
		System.out.println("Car 2 (Wet Tire): " + car2.getTrackRoute());
		System.out.println("Car 3 (Flat Tire): " + car3.getTrackRoute());

		System.out.println("\n --- Race Simulator ---");

		for (int i=0; i<4; i++){ //4 stops
			System.out.println("\n Stop" + (i+1));

            //displays car's current stop and acceleration after tire wear
            System.out.println("Car 1 (Hard Tire) at: " + car1.getCurrentStop().getStopName()+ " | Speed: " + car1.getSpeed() + " m/s");
            System.out.println("Car 2 (Wet Tire) at: " + car2.getCurrentStop().getStopName()+ " | Speed: " + car2.getSpeed() + " m/s");
            System.out.println("Car 3 (Flat Tire) at: " + car3.getCurrentStop().getStopName()+ " | Speed: " + car3.getSpeed() + " m/s");
		
            //move each car to the next stop
            car1.moveToNextStop();
            car2.moveToNextStop();
            car3.moveToNextStop();
		}
	}
}
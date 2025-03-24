/*
 * Represents a checkpoint on the track. -Bruna
 */

import java.util.*;

public class Stop{

	private String name;
	private double distance; //distance from starting line

	//constructor
	public Stop(String name, double distance){
		this.name = name;
		this.distance = distance;
	}

	//gets name of stop
	public String getStopName(){
		return name;
	}

	//gets distance from the start of race
	public double getDistance(){
		return distance;
	}

	/*
	 * Creates the list of standard stops (A, B, C, and D) and sets their distance from starting line
	 */
	public static List<Stop> getRaceStops(){
		List<Stop> stops= new ArrayList<>();

		stops.add(new Stop("A", 100));
		stops.add(new Stop("B", 150));
		stops.add(new Stop("C", 200));
		stops.add(new Stop("D", 50));

		return stops;
	}

	//override toString() to return the stop name.
    @Override
    public String toString() {
        return name; //prints "A", "B", "C", or "D"
    }
}
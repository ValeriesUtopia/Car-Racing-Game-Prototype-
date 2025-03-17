/* 
 * WetTire wheel type
 * With this tire, the car has great grip, and is a little bit worn. -Bruna
 */

public class WetTire extends Wheel{

	//constructor
	public WetTire(){
		super(0.9, 0.2); //higher grip, and not worn out
	}

	@Override
	public void describeFeature(){
		System.out.println("Wet tires perform better on wet surfaces.");
	}
}
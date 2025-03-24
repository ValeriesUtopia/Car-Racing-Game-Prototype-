/* 
 * HardTire wheel type
 * With this tire, the car has moderate grip, but a good wear state. -Bruna
 */

public class HardTire extends Wheel{

	//constructor
	public HardTire(){
		super(0.7, 0.0); //lower grip, and not worn out
	}

	@Override
	public void describeFeature(){
		System.out.println("Hard tires provide durability but less grip.");
	}
}
/* 
 * FlatTire wheel type
 * With this tire, the car has no grip, and is worn out. -Bruna
 */

public class FlatTire extends Wheel{

	//constructor
	public FlatTire(){
		super(0.0, 1.0); //a tire that has no grip, and is completely worn out
	}

	@Override
	public void describeFeature(){
		System.out.println("Flat tires have no grip and it impacts race performance.");
	}
}
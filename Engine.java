// Purpose: This class is used to create an engine object with a type and horse power.
// Valerie Holland

public class Engine {
private String type;
private double horsePower;

public Engine(String type, double horsePower) {
    this.type = type;
    this.horsePower = horsePower;
    }
public String getType() {
    return type;
    }
public double getHorsePower() {
    return horsePower;
    }
}

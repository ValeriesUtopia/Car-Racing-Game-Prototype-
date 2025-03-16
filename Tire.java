// Purpose: Tire class for the Car class    
// Valerie Holland

public class Tire {
    private String type;
    private double grip;
    public Tire(String type, double grip) {
        this.type = type;
        this.grip = grip;
    }
    public String getType() {
        return type;
    }
    public double getGrip() {
        return grip;
    }
}

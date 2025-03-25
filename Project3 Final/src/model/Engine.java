package model;

public class Engine {
    private final String type;
    private final double acceleration;
    private final double maxSpeed;

    public Engine(String type, double acceleration, double maxSpeed) {
        this.type = type;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
    }

    public String getType() { return type; }
    public double getAcceleration() { return acceleration; }
    public double getMaxSpeed() { return maxSpeed; }
}

package model;

public abstract class Obstacle {
    private final String name;
    private final double x;
    private final double y;
    private final double radius;

    public Obstacle(String name, double x, double y, double radius) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public boolean isInRange(double carX, double carY) {
        double dx = carX - x;
        double dy = carY - y;
        return Math.sqrt(dx * dx + dy * dy) <= radius;
    }

    public String getName() { return name; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }

    public abstract void applyEffect(Car car);
}

/*
public class Obstacle {
    private double x;
    private double y;

    public Obstacle(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
}

 */

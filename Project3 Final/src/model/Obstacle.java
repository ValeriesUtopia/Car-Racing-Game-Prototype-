// Abstract base class for all obstacle types in the racing game.
// Each obstacle has a name, a position (x, y), and a radius that defines
// the area within which it can affect a car. Subclasses define specific effects.
// Author: Andrew Lightfoot

package model;

public abstract class Obstacle {

    private final String name;     // The name/type of the obstacle (e.g., "Slippery Zone")
    private final double x;        // X-coordinate of the obstacle's center
    private final double y;        // Y-coordinate of the obstacle's center
    private final double radius;   // Effective radius: how close a car must be to be affected

    /**
     * Constructs an obstacle with a given name, position, and radius.
     *
     * @param name the type or label of the obstacle
     * @param x x-coordinate of the obstacle
     * @param y y-coordinate of the obstacle
     * @param radius effective range of the obstacle
     */
    public Obstacle(String name, double x, double y, double radius) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * Determines whether a car is within the obstacle's range.
     * Uses Euclidean distance between the car and obstacle center.
     *
     * @param carX x-coordinate of the car
     * @param carY y-coordinate of the car
     * @return true if the car is within the radius; false otherwise
     */
    public boolean isInRange(double carX, double carY) {
        double dx = carX - x;
        double dy = carY - y;
        return Math.sqrt(dx * dx + dy * dy) <= radius;
    }

    // ---------- Getters ----------

    /** @return the obstacle's name or label */
    public String getName() { return name; }

    /** @return x-coordinate of the obstacle */
    public double getX() { return x; }

    /** @return y-coordinate of the obstacle */
    public double getY() { return y; }

    /** @return the radius of effect for this obstacle */
    public double getRadius() { return radius; }

    /**
     * Abstract method to define how this obstacle affects a car.
     * Each subclass must provide its own effect implementation.
     *
     * @param car the car being affected
     * @param deltaTime the time step (used for time-based updates)
     */
    public abstract void applyEffect(Car car, double deltaTime);
}

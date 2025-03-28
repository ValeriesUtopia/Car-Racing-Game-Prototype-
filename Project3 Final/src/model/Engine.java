// This class represents an engine component in the racing game.
// Each engine has a specific type, acceleration rate, and maximum speed.
// Engine objects are immutable and used by Car to determine performance.
// Author: Valeria Holland

package model;

public class Engine {

    private final String type;           // Label for the engine type (e.g., "V6", "V8")
    private final double acceleration;   // Rate at which the car increases speed (units per second²)
    private final double maxSpeed;       // Maximum speed this engine can reach (units per second)

    /**
     * Constructor to initialize an engine with its characteristics.
     *
     * @param type engine label or name (e.g., "V6")
     * @param acceleration how fast the car can accelerate
     * @param maxSpeed the maximum speed the car can reach with this engine
     */
    public Engine(String type, double acceleration, double maxSpeed) {
        this.type = type;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
    }

    /**
     * Returns the engine type (e.g., "V6", "V8").
     *
     * @return engine type as a string
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the acceleration value of this engine.
     *
     * @return acceleration in units per second²
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * Returns the maximum speed allowed by this engine.
     *
     * @return max speed in units per second
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }
}

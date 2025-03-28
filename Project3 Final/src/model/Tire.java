// This class represents a tire component used in a car.
// Each tire has a specific grip level, which determines the likelihood
// of sliding when encountering an obstacle.
// Tire objects are immutable and used by Car during race logic.
// Author: Valeria Holland
package model;

import java.util.Random;

public class Tire {

    private final String type;         // Tire type label (e.g., "Standard", "Racing")
    private final double gripLevel;    // Value between 0.0 and 1.0 representing grip strength

    /**
     * Constructs a tire with a given type and grip level.
     *
     * @param type the tire type (e.g., "Standard")
     * @param gripLevel the grip level (0 = no grip, 1 = perfect grip)
     */
    public Tire(String type, double gripLevel) {
        this.type = type;
        this.gripLevel = gripLevel;
    }

    /**
     * Returns the tire type as a string.
     *
     * @return the tire type label
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the tire's grip level.
     *
     * @return grip level between 0.0 and 1.0
     */
    public double getGripLevel() {
        return gripLevel;
    }

    /**
     * Determines whether the car will slide when encountering a slippery obstacle.
     * A random number is compared against the grip level — higher grip means less chance of sliding.
     *
     * @param random the Random object used for decision
     * @return true if the car will slide, false otherwise
     */
    public boolean willSlide(Random random) {
        return random.nextDouble() > gripLevel;
    }
}

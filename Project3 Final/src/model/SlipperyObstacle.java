// This class represents a slippery obstacle (e.g., an oil spill or ice patch).
// When a car enters the obstacle's range, its speed is reduced and it begins sliding.
// The slowdown effect is time-scaled based on deltaTime for smooth animation control.
// Author: Andrew Lightfoot

package model;

public class SlipperyObstacle extends Obstacle {

    /**
     * Constructs a slippery obstacle with a fixed name and given position and radius.
     *
     * @param x x-coordinate of the obstacle
     * @param y y-coordinate of the obstacle
     * @param radius area of effect for this slippery zone
     */
    public SlipperyObstacle(double x, double y, double radius) {
        super("Slippery Zone", x, y, radius);
    }

    /**
     * Applies the slippery effect to a car:
     * - Reduces speed exponentially based on deltaTime
     * - Sets the car to a sliding state
     *
     * @param car the car entering the obstacle's range
     * @param deltaTime time elapsed since last update (used for smoother slowdown)
     */
    @Override
    public void applyEffect(Car car, double deltaTime) {
        double slowdown = Math.pow(0.6, deltaTime); // Gradual slowdown factor
        car.setCurrentSpeed(car.getCurrentSpeed() * slowdown);
        car.setSliding(true);

        // Old version:
        // car.setCurrentSpeed(car.getCurrentSpeed() * 0.6); // Fixed-speed cut
    }
}

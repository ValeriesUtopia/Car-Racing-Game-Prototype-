package model;

public class SlipperyObstacle extends Obstacle {
    public SlipperyObstacle(double x, double y, double radius) {
        super("Slippery Zone", x, y, radius);
    }

    @Override
    public void applyEffect(Car car) {
        car.setSliding(true);
        car.setCurrentSpeed(car.getCurrentSpeed() * 0.6);
    }
}

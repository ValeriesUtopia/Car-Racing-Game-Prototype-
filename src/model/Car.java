package model;

public class Car {
    private int id;
    private double x, y;  // Position
    private double speed;  // Speed
    private double direction; // Direction (angle in degrees)

    private String wheel;  // Player-selected tire type
    private String engine; // Player-selected engine type

    public Car(int id, double x, double y, String wheel, String engine) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.wheel = wheel;
        this.engine = engine;
        this.speed = 0;
        this.direction = 0; // Initial direction facing right
    }

    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    // Get current position
    public double getX() { return x; }
    public double getY() { return y; }

    // Set the initial position of the car
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Set speed
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    // Get speed
    public double getSpeed() { return speed; }

    // Set direction
    public void setDirection(double direction) {
        this.direction = direction;
    }

    // Get direction
    public double getDirection() { return direction; }

    // Get tire and engine information
    public String getWheel() { return wheel; }
    public String getEngine() { return engine; }

    // Move the car forward
    public void moveForward() {
        x += speed * Math.cos(Math.toRadians(direction));
        y += speed * Math.sin(Math.toRadians(direction));
    }

    // Turn the car (adjust direction)
    public void turnLeft() {
        direction -= 10; // Rotate counterclockwise by 10 degrees
    }

    public void turnRight() {
        direction += 10; // Rotate clockwise by 10 degrees
    }
}

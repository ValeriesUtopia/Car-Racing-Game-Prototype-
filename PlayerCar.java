// Purpose: This class is a subclass of Car and represents the player's car in the game. It has a tire and engine object, and can move, turn left, turn right, accelerate, and decelerate. It also has a method to update its position and print its position to the console.
//Valerie Holland

public class PlayerCar extends Car {
    private Tire tire;
    private Engine engine;
    private double angle;

    public PlayerCar(double centerX, double centerY, double radius, double speed, double angle, Tire tire, Engine engine) {
        super(centerX, centerY, radius, speed, angle, engine.getHorsePower());
        this.tire = tire;
        this.engine = engine;
        this.angle = angle;
    }
    
    
    @Override
    public void move() {
        // Increase angle based on speed (speed should be small to ensure smooth movement)
        angle += speed * tire.getGrip();  
        if (angle >= 2 * Math.PI) angle -= 2 * Math.PI; // Keep within 360 degrees
    
        // Ensure movement happens around a fixed center (200,200) with a defined radius
        centerX = 200 + radius * Math.cos(angle);
        centerY = 200 + radius * Math.sin(angle);
    
        // Print position to verify movement
        System.out.printf("PlayerCar Position -> X: %.2f, Y: %.2f, Angle: %.2f%n", centerX, centerY, Math.toDegrees(angle));
    }

    @Override
    protected void updatePosition(double x, double y){
      System.out.println("PlayerCar is at x: " + x + " y: " + y);
    }

    public void turnLeft() {
        angle -= 0.1;
    }

    public void turnRight() {
        angle += 0.1;
    }

    public void accelerate() {
        speed += 0.1;
    }

    public void decelerate() {
        speed -= 0.1;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getRadius() {
        return radius;
    }
    public double getAngle() {
        return angle;
    }
    
}

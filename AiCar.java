// Purpose: This class is a subclass of Car.java and is used to create an AiCar object that can move around the screen.
// It has a move method that updates the position of the AiCar object and prints the position to the console.   
//Valerie Holland

public class AiCar extends Car {
    public AiCar(double centerX, double centerY, double radius, double speed, double angle, double horsePower){
        super(centerX, centerY, radius, speed, angle, horsePower);
    }
    @Override
    public void move() {
        // Increase angle based on speed (speed should be small to ensure smooth movement)
        angle += speed;  
        if (angle >= 2 * Math.PI) angle -= 2 * Math.PI; // Keep within 360 degrees
    
        // Ensure movement happens around a fixed center (200,200) with a defined radius
        centerX = 200 + radius * Math.cos(angle);
        centerY = 200 + radius * Math.sin(angle);
    
        // Print position to verify movement
        System.out.printf("PlayerCar Position -> X: %.2f, Y: %.2f, Angle: %.2f%n", centerX, centerY, Math.toDegrees(angle));
    }

    @Override
    protected void updatePosition(double x, double y){
      System.out.println("AiCar is at x: " + x + " y: " + y);
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

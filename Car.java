// Purpose: Abstract class for the car object
// Valerie Holland

public abstract class Car {
    
protected double centerX, centerY, radius;
protected double speed;
protected double angle;
protected double horsePower;



public Car(double centerX, double centerY, double radius, double speed, double angle, double horsePower) {
    this.centerX = centerX;
    this.centerY = centerY;
    this.radius = radius;
    this.speed = speed;
    this.angle = angle;
    this.horsePower = horsePower;
    }

public void move(){
    angle += speed;
    if (angle >=2*Math.PI){
        angle -= 2*Math.PI;

        double centerX = 200 + radius * Math.cos(angle);
        double centerY = 200 + radius * Math.sin(angle);
        
    }
}
protected abstract void updatePosition(double x, double y);{

    }
}




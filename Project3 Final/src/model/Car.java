package model;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private final String name;
    private final Engine engine;
    private final Tire tire;
    private final List<Stop> route;
    private final String color;

    private final List<Stop> visitedStops = new ArrayList<>();

    private int currentStopIndex = 0;
    private double currentSpeed = 0.0;
    private double positionX;
    private double positionY;
    private boolean isSliding = false;
    private boolean hasFinished = false;
    private double totalTime = 0.0;

    public Car(String name, Engine engine, Tire tire, List<Stop> route, String color) {
        this.name = name;
        this.engine = engine;
        this.tire = tire;
        this.route = route;
        this.color = color;
        if (!route.isEmpty()) {
            Stop start = route.get(0);
            this.positionX = start.getX();
            this.positionY = start.getY();
            visitedStops.add(start);
        }
    }

    public String getName() { return name; }
    public Engine getEngine() { return engine; }
    public Tire getTire() { return tire; }
    public List<Stop> getRoute() { return route; }
    public int getCurrentStopIndex() { return currentStopIndex; }
    public double getCurrentSpeed() { return currentSpeed; }
    public double getPositionX() { return positionX; }
    public double getPositionY() { return positionY; }
    public boolean isSliding() { return isSliding; }
    public boolean hasFinished() { return hasFinished; }
    public double getTotalTime() { return totalTime; }
    public String getColor() { return color; }
    public List<Stop> getVisitedStops() { return visitedStops; }

    public void setSliding(boolean sliding) { this.isSliding = sliding; }
    public void setCurrentSpeed(double speed) { this.currentSpeed = speed; }
    public void updateTime(double delta) { this.totalTime += delta; }
    public void markFinished() { this.hasFinished = true; }

    public Stop getCurrentStop() {
        return route.get(currentStopIndex);
    }

    public Stop getNextStop() {
        return currentStopIndex + 1 < route.size() ? route.get(currentStopIndex + 1) : null;
    }

    public void advanceToNextStop() {
        currentStopIndex++;
        if (currentStopIndex < route.size()) {
            visitedStops.add(route.get(currentStopIndex));
        } else {
            hasFinished = true;
        }
    }

    public void updatePosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    public String getRouteAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < route.size(); i++) {
            sb.append(route.get(i).getId());
            if (i < route.size() - 1) sb.append(" → ");
        }
        return sb.toString();
    }
}


/*可以运行版本
package model;

import java.util.List;

public class Car {
    private final String name;
    private final Engine engine;
    private final Tire tire;
    private final List<Stop> route;
    private final String color;

    private int currentStopIndex = 0;
    private double currentSpeed = 0.0;
    private double positionX;
    private double positionY;
    private boolean isSliding = false;
    private boolean hasFinished = false;
    private double totalTime = 0.0;

    public Car(String name, Engine engine, Tire tire, List<Stop> route, String color) {
        this.name = name;
        this.engine = engine;
        this.tire = tire;
        this.route = route;
        this.color = color;
        if (!route.isEmpty()) {
            this.positionX = route.get(0).getX();
            this.positionY = route.get(0).getY();
        }
    }

    public String getName() { return name; }
    public Engine getEngine() { return engine; }
    public Tire getTire() { return tire; }
    public List<Stop> getRoute() { return route; }
    public int getCurrentStopIndex() { return currentStopIndex; }
    public double getCurrentSpeed() { return currentSpeed; }
    public double getPositionX() { return positionX; }
    public double getPositionY() { return positionY; }
    public boolean isSliding() { return isSliding; }
    public boolean hasFinished() { return hasFinished; }
    public double getTotalTime() { return totalTime; }
    public String getColor() { return color; }

    public void setSliding(boolean sliding) { this.isSliding = sliding; }
    public void setCurrentSpeed(double speed) { this.currentSpeed = speed; }
    public void updateTime(double delta) { this.totalTime += delta; }
    public void markFinished() { this.hasFinished = true; }

    public Stop getCurrentStop() {
        return route.get(currentStopIndex);
    }

    public Stop getNextStop() {
        return currentStopIndex + 1 < route.size() ? route.get(currentStopIndex + 1) : null;
    }

    public void advanceToNextStop() {
        currentStopIndex++;
        if (currentStopIndex >= route.size()) {
            hasFinished = true;
        }
    }

    public void updatePosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }
}

 */

/*
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

 */

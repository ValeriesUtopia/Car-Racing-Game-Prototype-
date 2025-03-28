// This class represents a car in the racing game.
// Each car has an engine, tire, route, color, and internal state
// such as position, speed, sliding status, and total time.
// It supports updating movement, progress, and position over time.
// Author: Valeria Holland

package model;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private final String name;               // Car's name or label (e.g., "Car 1")
    private final Engine engine;             // Car's engine affecting acceleration and max speed
    private final Tire tire;                 // Car's tire affecting grip and sliding chance
    private final List<Stop> route;          // Ordered list of stops the car must follow
    private final String color;              // Color for UI display

    private final List<Stop> visitedStops = new ArrayList<>(); // List of stops already visited

    private int currentStopIndex = 0;        // Index of the current stop in the route
    private double currentSpeed = 0.0;       // Car's current speed
    private double positionX;                // Current X coordinate on the map
    private double positionY;                // Current Y coordinate on the map
    private boolean isSliding = false;       // Whether the car is currently sliding
    private boolean hasFinished = false;     // Whether the car has finished the race
    private double totalTime = 0.0;          // Total time the car has spent racing
    private double headingAngle = 0.0;       // Direction the car is facing (used for UI rotation)

    /**
     * Constructs a new Car object with the specified components and route.
     * Initializes position to the first stop on the route.
     *
     * @param name name of the car
     * @param engine engine object
     * @param tire tire object
     * @param route list of stops (route)
     * @param color UI color for the car
     */
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

    // ---------- Getters ----------
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
    public double getHeadingAngle() { return headingAngle; }

    // ---------- Setters / State Modifiers ----------
    public void setSliding(boolean sliding) { this.isSliding = sliding; }
    public void setCurrentSpeed(double speed) { this.currentSpeed = speed; }
    public void updateTime(double delta) { this.totalTime += delta; }
    public void markFinished() { this.hasFinished = true; }
    public void setHeadingAngle(double headingAngle) { this.headingAngle = headingAngle; }

    /**
     * Gets the current stop that the car is on.
     * @return current Stop object
     */
    public Stop getCurrentStop() {
        return route.get(currentStopIndex);
    }

    /**
     * Gets the next stop the car should move toward.
     * @return next Stop object, or null if at the end of the route
     */
    public Stop getNextStop() {
        return currentStopIndex + 1 < route.size() ? route.get(currentStopIndex + 1) : null;
    }

    /**
     * Advances the car to the next stop.
     * Adds the stop to visitedStops and updates finished state if needed.
     */
    public void advanceToNextStop() {
        currentStopIndex++;
        if (currentStopIndex < route.size()) {
            visitedStops.add(route.get(currentStopIndex));
        } else {
            hasFinished = true;
        }
    }

    /**
     * Updates the car's current position on the screen.
     * @param x new x-coordinate
     * @param y new y-coordinate
     */
    public void updatePosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    /**
     * Returns a human-readable representation of the car's route.
     * Example: "A → B → C"
     * @return string describing route
     */
    public String getRouteAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < route.size(); i++) {
            sb.append(route.get(i).getId());
            if (i < route.size() - 1) sb.append(" → ");
        }
        return sb.toString();
    }

    /**
     * Resets the car to its initial state for a new race.
     * Used when restarting a race without rebuilding the game.
     */
    public void resetProgress() {
        currentStopIndex = 0;
        currentSpeed = 0.0;
        totalTime = 0.0;
        isSliding = false;
        hasFinished = false;
        visitedStops.clear();
        if (!route.isEmpty()) {
            Stop start = route.get(0);
            positionX = start.getX();
            positionY = start.getY();
            visitedStops.add(start);
        }
    }
}

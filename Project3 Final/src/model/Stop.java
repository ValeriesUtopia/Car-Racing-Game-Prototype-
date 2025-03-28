// This class represents a stop (or checkpoint) on a racing track.
// Each stop has a unique ID and a 2D position (x, y).
// Cars follow a list of stops (route) to complete the race.
// Author: Andrew Lightfoot
package model;

public class Stop {

    private final String id;    // Unique identifier for the stop (e.g., "A", "B", "C")
    private final double x;     // X-coordinate of the stop on the track
    private final double y;     // Y-coordinate of the stop on the track

    /**
     * Constructs a Stop with the given ID and coordinates.
     *
     * @param id a unique label for the stop
     * @param x the x-coordinate of the stop
     * @param y the y-coordinate of the stop
     */
    public Stop(String id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the ID of this stop.
     *
     * @return the stop's label
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the X coordinate of this stop.
     *
     * @return x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the Y coordinate of this stop.
     *
     * @return y-coordinate
     */
    public double getY() {
        return y;
    }
}

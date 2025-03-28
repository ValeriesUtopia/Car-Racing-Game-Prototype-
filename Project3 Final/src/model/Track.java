// This class represents a racing track in the game.
// Each track has a difficulty level, a list of route stops (checkpoints),
// and a list of obstacles randomly or manually placed on the track.
// Author: Andrew Lightfoot

package model;

import model.Obstacle;
import model.Stop;

import java.util.ArrayList;
import java.util.List;

public class Track {

    /**
     * Enum to define the difficulty level of the track.
     */
    public enum Difficulty {
        EASY, MEDIUM, CHALLENGE
    }

    private final String name;                // Name of the track (e.g., "Easy Track")
    private final Difficulty difficulty;      // Difficulty level of the track
    private final List<Stop> stops;           // Ordered list of route stops
    private final List<Obstacle> obstacles;   // Obstacles present on this track

    /**
     * Constructs a Track object with a name, difficulty, and list of stops.
     *
     * @param name name of the track
     * @param difficulty difficulty level (EASY, MEDIUM, CHALLENGE)
     * @param stops list of route checkpoints (stops)
     */
    public Track(String name, Difficulty difficulty, List<Stop> stops) {
        this.name = name;
        this.difficulty = difficulty;
        this.stops = new ArrayList<>(stops);
        this.obstacles = new ArrayList<>();
    }

    // ---------- Getters ----------
    /**
     * Returns the name of the track.
     *
     * @return track name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the difficulty level of the track.
     *
     * @return track difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Returns the list of stops in the track.
     *
     * @return list of Stop objects
     */
    public List<Stop> getStops() {
        return stops;
    }

    /**
     * Returns the list of obstacles in the track.
     *
     * @return list of Obstacle objects
     */
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * Adds an obstacle to the track.
     *
     * @param obstacle the Obstacle object to add
     */
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    /**
     * Returns a summary string for debugging/logging.
     */
    @Override
    public String toString() {
        return "Track{name='" + name + "', difficulty=" + difficulty +
                ", stops=" + stops.size() +
                ", obstacles=" + obstacles.size() + "}";
    }
}

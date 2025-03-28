// This class holds all game-related state and shared data used by controllers and views.
// It includes the selected track, list of cars, selected difficulty, and utilities
// for resetting race progress when restarting or switching tracks.
// Author: Jing Pan, Andrew Lightfoot

package model;

import java.util.ArrayList;
import java.util.List;

public class GameData {

    private Track selectedTrack;           // The track selected for the current race
    private final List<Car> cars = new ArrayList<>(); // List of all cars participating
    private String difficulty;             // The chosen difficulty level ("EASY", "MEDIUM", etc.)

    // ---------- Track ----------
    /**
     * Sets the currently selected track.
     *
     * @param track the track object to use for the game
     */
    public void setSelectedTrack(Track track) {
        this.selectedTrack = track;
    }

    /**
     * Returns the currently selected track.
     *
     * @return selected Track object
     */
    public Track getSelectedTrack() {
        return selectedTrack;
    }

    // ---------- Cars ----------
    /**
     * Adds a car to the list of cars in the race.
     *
     * @param car the Car object to add
     */
    public void addCar(Car car) {
        cars.add(car);
    }

    /**
     * Returns the list of all cars currently in the race.
     *
     * @return list of Car objects
     */
    public List<Car> getCars() {
        return cars;
    }

    // ---------- Difficulty ----------
    /**
     * Gets the selected difficulty level as a string.
     *
     * @return difficulty level (e.g., "EASY")
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the game difficulty.
     *
     * @param difficulty difficulty level string
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // ---------- Race Reset ----------
    /**
     * Resets the state of all cars to their starting positions and clears progress.
     * Used when restarting a race or reusing the same track with new cars.
     */
    // Andrew Lightfoot helped to update
    public void resetRaceState() {
        List<Stop> stops = selectedTrack.getStops();
        for (Car car : cars) {
            car.resetProgress();
        }
    }
}

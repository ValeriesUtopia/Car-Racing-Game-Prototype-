// This class is the core race controller for the game.
// It handles the update logic of each car, obstacle interaction,
// sound playback, and determines when the race is finished.
// Author: Jing Pan, Andrew Lightfoot

package controller;

import javafx.stage.Stage;
import model.*;
import view.RaceView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RaceController {
    private final GameData gameData;         // Shared data model
    private final Random random = new Random(); // For future randomness (if needed)
    private final Stage primaryStage;        // Reference to main application window
    private MediaPlayer mediaPlayer;         // Background race sound
    private boolean isSoundPlaying = false;  // Flag to prevent repeated play
    private boolean hasCarMoved = false;     // Flag to detect race start

    /**
     * Constructor for RaceController.
     *
     * @param primaryStage the application window
     * @param gameData shared game state
     */
    public RaceController(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
    }

    /**
     * Launches the Race View (UI3).
     */
    public void startRace() {
        RaceView raceView = new RaceView(primaryStage, gameData);
        // mediaPlayer.play(); // optionally play sound immediately
        raceView.show(); // Launch race UI
    }

    /**
     * Loads and plays background race music in a loop.
     */
    public void startSound() {
        try {
            String musicFile = getClass().getResource("/images/racesound.mp3").toExternalForm();
            System.out.println("Music Path: " + musicFile); // Debug

            Media sound = new Media(musicFile);
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop music

            mediaPlayer.setOnReady(() -> {
                System.out.println("Playing Sound...");
                mediaPlayer.play();
            });

            mediaPlayer.setOnError(() -> {
                System.out.println("Error: " + mediaPlayer.getError());
            });
        } catch (NullPointerException e) {
            System.out.println("Resource not found! Check the file path.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the race state for each frame based on deltaTime.
     *
     * @param deltaTime the time elapsed since the last update
     */
    public void updateRace(double deltaTime) {
        boolean anyCarMoving = false;

        for (Car car : gameData.getCars()) {
            if (car.hasFinished()) continue;

            // Reset sliding each frame
            car.setSliding(false);

            // Acceleration logic
            double newSpeed = car.getCurrentSpeed() + car.getEngine().getAcceleration() * deltaTime;
            car.setCurrentSpeed(Math.min(newSpeed, car.getEngine().getMaxSpeed()));
            if (car.getCurrentSpeed() > 0) {
                anyCarMoving = true;
            }

            // Get current and next stop
            Stop current = car.getCurrentStop();
            Stop next = car.getNextStop();
            if (next == null) {
                car.markFinished();
                continue;
            }

            // Check for obstacle collisions
            // Andrew Lightfoot helped to update
            boolean sliding = false;
            for (Obstacle obstacle : gameData.getSelectedTrack().getObstacles()) {
                if (obstacle.isInRange(car.getPositionX(), car.getPositionY())) {
                    obstacle.applyEffect(car, deltaTime);
                    sliding = true;
                }
            }
            car.setSliding(sliding);

            // Movement logic
            double dx = next.getX() - car.getPositionX();
            double dy = next.getY() - car.getPositionY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance == 0) {
                car.advanceToNextStop();
                return;
            }

            double directionX = dx / distance;
            double directionY = dy / distance;
            double moveDistance = car.getCurrentSpeed() * deltaTime;
            double moveX = directionX * moveDistance;
            double moveY = directionY * moveDistance;

            if (moveDistance >= distance) {
                car.updatePosition(next.getX(), next.getY());
                car.advanceToNextStop();
            } else {
                car.updatePosition(car.getPositionX() + moveX, car.getPositionY() + moveY);
            }

            car.updateTime(deltaTime);
        }

        // Start music after the race actually begins
        if (anyCarMoving && !isSoundPlaying && !hasCarMoved) {
            // mediaPlayer.play();
            isSoundPlaying = true;
            hasCarMoved = true;
        }

        // Stop music when race ends
        if (isRaceFinished()) {
            stopMusic();
        }
    }

    /**
     * Checks if all cars have finished the race.
     *
     * @return true if all cars are done, false otherwise
     */
    public boolean isRaceFinished() {
        return gameData.getCars().stream().allMatch(Car::hasFinished);
    }

    /**
     * Returns a list of cars sorted by total race time.
     *
     * @return a list of Car objects in ascending order of race time
     */
    public List<Car> getSortedResults() {
        List<Car> result = new ArrayList<>(gameData.getCars());
        result.sort(Comparator.comparingDouble(Car::getTotalTime));
        return result;
    }

    /**
     * Stops the background music playback.
     */
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            isSoundPlaying = false;
        }
    }
}

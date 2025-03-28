// This class controls the Car Selection screen (UI2) in the racing game.
// It handles UI transitions, processes user input for car configuration,
// generates separate routes for each car, and starts the race using RaceController.
// Author: Jing Pan

package controller;

import javafx.stage.Stage;
import model.*;
import view.CarSelectionView;

import java.util.ArrayList;
import java.util.List;

public class CarSelectionController {
    private final Stage primaryStage;  // The main window of the application
    private final GameData gameData;   // Shared game data model
    private final String difficulty;   // Selected difficulty level

    /**
     * Constructor for CarSelectionController.
     *
     * @param primaryStage the application window
     * @param gameData the shared data model between screens
     */
    public CarSelectionController(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
        this.difficulty = gameData.getDifficulty();
    }

    /**
     * Launches the Car Selection screen (UI2).
     * Passes the current difficulty level to the view.
     */
    public void showSelectionScreen() {
        CarSelectionView carSelection = new CarSelectionView(primaryStage, this);
        carSelection.show(difficulty);
    }

    /**
     * Handles the "Back" button click.
     * Returns the user to the difficulty selection screen (UI1),
     * and re-initializes this controller if a new difficulty is selected.
     */
    public void handleBack() {
        view.DifficultySelectionView difficultyView = new view.DifficultySelectionView(primaryStage);
        difficultyView.setOnDifficultySelected(selectedDifficulty -> {
            gameData.setDifficulty(selectedDifficulty);
            CarSelectionController newController = new CarSelectionController(primaryStage, gameData);
            newController.showSelectionScreen();
        });
        difficultyView.show();
    }

    /**
     * Handles car configuration submitted by the user.
     * Creates two cars with different routes and starts the race.
     *
     * @param user1Engine engine type selected by player 1
     * @param user1Tire tire type selected by player 1
     * @param user2Engine engine type selected by player 2
     * @param user2Tire tire type selected by player 2
     */
    public void handleSelection(String user1Engine, String user1Tire, String user2Engine, String user2Tire) {
        System.out.println("Difficulty: " + difficulty);
        System.out.println("User1 - Wheel: " + user1Tire + ", Engine: " + user1Engine);
        System.out.println("User2 - Wheel: " + user2Tire + ", Engine: " + user2Engine);

        // 1. Create the selected track based on difficulty level
        Track track = switch (difficulty.toUpperCase()) {
            case "EASY" -> TrackFactory.createEasyTrack();
            case "MEDIUM" -> TrackFactory.createMediumTrack();
            case "CHALLENGE" -> TrackFactory.createChallengeTrack();
            default -> TrackFactory.createEasyTrack();
        };
        gameData.setSelectedTrack(track);

        // 2. Create independent but equivalent stop routes for each car
        List<Stop> original = track.getStops();
        List<Stop> route1 = new ArrayList<>(original);
        List<Stop> route2 = new ArrayList<>(original);

        // 3. Shift route2 until it has a different starting point from route1
        do {
            Stop first = route2.remove(0);
            route2.add(first);
        } while (route1.get(0).equals(route2.get(0)));

        // 4. Create the two cars using the selected components
        Car car1 = CarFactory.createCar("Car 1", user1Engine, user1Tire, route1, "red");
        Car car2 = CarFactory.createCar("Car 2", user2Engine, user2Tire, route2, "blue");

        // 5. Store the cars in the shared game data
        gameData.getCars().clear();
        gameData.addCar(car1);
        gameData.addCar(car2);

        // 6. Start the race (launch RaceView / UI3)
        RaceController raceController = new RaceController(primaryStage, gameData);
        raceController.startRace();
    }
}

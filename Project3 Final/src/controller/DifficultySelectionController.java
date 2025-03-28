// This class is the controller for the Difficulty Selection screen (UI1).
// It allows the user to choose a difficulty level (Easy, Medium, Challenge),
// saves the selected difficulty into GameData, and then transitions to the Car Selection screen (UI2).
// Author: Jing Pan

package controller;

import javafx.stage.Stage;
import model.GameData;

public class DifficultySelectionController {

    private final Stage primaryStage;  // Reference to the main application window
    private final GameData gameData;   // Shared data model for the game state

    /**
     * Constructor for DifficultySelectionController.
     *
     * @param primaryStage the application window
     * @param gameData the shared game data model
     */
    public DifficultySelectionController(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
    }

    /**
     * Shows the difficulty selection screen (UI1).
     * Waits for the user to choose a difficulty, stores the choice,
     * and then proceeds to the Car Selection screen (UI2).
     */
    public void showSelectionScreen() {
        view.DifficultySelectionView difficultySelection = new view.DifficultySelectionView(primaryStage);

        // Set a listener: When difficulty is selected in the UI, perform logic here
        difficultySelection.setOnDifficultySelected(difficulty -> {
            System.out.println("✅ Selected difficulty: " + difficulty);

            // Store difficulty in game data (no track is created yet)
            gameData.setDifficulty(difficulty);

            // Move to the car selection screen (UI2)
            CarSelectionController carSelection = new CarSelectionController(primaryStage, gameData);
            carSelection.showSelectionScreen();
        });

        // Display the UI
        difficultySelection.show();
    }
}

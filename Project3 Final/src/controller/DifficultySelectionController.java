package controller;

import javafx.stage.Stage;
import view.DifficultySelectionView;
import model.GameData;

/**
 * Controller for the difficulty selection screen (UI1).
 * Only sets the selected difficulty into GameData, without creating Track yet.
 * Next step: pass control to CarSelectionController (UI2).
 */
public class DifficultySelectionController {
    private final Stage primaryStage;
    private final GameData gameData;

    public DifficultySelectionController(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
    }

    public void showSelectionScreen() {
        DifficultySelectionView difficultySelection = new DifficultySelectionView(primaryStage);

        // When difficulty is selected, store it in GameData and move to next UI
        difficultySelection.setOnDifficultySelected(difficulty -> {
            System.out.println("✅ Selected difficulty: " + difficulty);
            gameData.setDifficulty(difficulty); // ✅ Only save difficulty

            // Proceed to car selection screen
            CarSelectionController carSelection = new CarSelectionController(primaryStage, gameData);
            carSelection.showSelectionScreen();
        });

        difficultySelection.show();
    }
}


/*
🔹 setOnDifficultySelected is a method in the View that allows the Controller to listen for UI events.
🔹 The Controller no longer directly depends on the View but instead waits for the View to trigger events.
🔹 Switching to CarSelectionController is handled by the Controller, not the View.
 */

/*
public class DifficultySelectionController {
    private Stage primaryStage;

    public DifficultySelectionController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showSelectionScreen() {
        DifficultySelectionView difficultySelection = new DifficultySelectionView(primaryStage);

        // Listen for the difficulty selection event from the View and handle the logic
        difficultySelection.setOnDifficultySelected(difficulty -> {
            System.out.println("Selected Difficulty: " + difficulty);

            // Create the next screen's Controller and switch the UI
            CarSelectionController carSelection = new CarSelectionController(primaryStage, difficulty);
            carSelection.showSelectionScreen();
        });

        difficultySelection.show();
    }
}
 */
package controller;

import javafx.stage.Stage;
import view.CarSelectionView;

public class CarSelectionController {
    private Stage primaryStage;
    private String difficulty;

    public CarSelectionController(Stage primaryStage, String difficulty) {
        this.primaryStage = primaryStage;
        this.difficulty = difficulty;
    }

    public void showSelectionScreen() {
        CarSelectionView carSelection = new CarSelectionView(primaryStage, this, difficulty);
        carSelection.show(); //
    }

    public void onCarSelectionCompleted(String user1Wheel, String user1Engine, String user2Wheel, String user2Engine) {
        System.out.println("Difficulty: " + difficulty);
        System.out.println("User1 - Wheel: " + user1Wheel + ", Engine: " + user1Engine);
        System.out.println("User2 - Wheel: " + user2Wheel + ", Engine: " + user2Engine);

        primaryStage.close();

        // enter Race UI and logic
        RaceController raceController = new RaceController(primaryStage, difficulty, user1Wheel, user1Engine, user2Wheel, user2Engine);
        raceController.startRace(); //  Race starts here

    }
}

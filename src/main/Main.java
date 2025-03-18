package main;

import javafx.application.Application;
import javafx.stage.Stage;
import controller.DifficultySelectionController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        DifficultySelectionController difficultySelection = new DifficultySelectionController(primaryStage);
        difficultySelection.showSelectionScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



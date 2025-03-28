
/**
 * Entry point for the Racing Game.
 * Launches the JavaFX application and displays the first screen (UI1 - Difficulty Selection).
 * Also plays background menu music on startup.
 */
// Author: Jing Pan

package main;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

import controller.DifficultySelectionController;
import model.GameData;
import controller.AudioController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create shared game data model
        GameData gameData = new GameData();

        // Start with Difficulty Selection screen (UI1)
        DifficultySelectionController controller = new DifficultySelectionController(primaryStage, gameData);
        controller.showSelectionScreen();

        // Play background menu music
        AudioController jazzMusic = new AudioController("resources/images/MenuJazz.mp3");
        /*try {
            String musicFile = "resources/images/MenuJazz.mp3"; // Path to music file
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            mediaPlayer.setOnReady(() -> {
                System.out.println("Playing Sound...");
                mediaPlayer.play();
            });

            mediaPlayer.setOnError(() -> {
                System.out.println("Error: " + mediaPlayer.getError());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Main method to launch the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

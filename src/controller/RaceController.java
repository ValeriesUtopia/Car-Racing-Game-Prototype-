package controller; // Ensure the correct package is used

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.*;
import view.RaceView;

import java.util.ArrayList;
import java.util.List;

public class RaceController {
    private Stage primaryStage;
    private String difficulty;
    private String user1Wheel, user1Engine;
    private String user2Wheel, user2Engine;
    private RaceView raceView; // UI component
    private Track track;
    private List<Car> cars;

    public RaceController(Stage primaryStage, String difficulty, String user1Wheel, String user1Engine, String user2Wheel, String user2Engine) {
        this.primaryStage = primaryStage;
        this.difficulty = difficulty;
        this.user1Wheel = user1Wheel;
        this.user1Engine = user1Engine;
        this.user2Wheel = user2Wheel;
        this.user2Engine = user2Engine;
    }

    public void startRace() {
        // Initialize the race track
        Track track = new Track(difficulty);

        // **Create two race cars using CarFactory**
        this.cars = new ArrayList<>();
        cars.add(CarFactory.createCar(1, track.getStops().get(0), user1Wheel, user1Engine)); // Player 1's car
        cars.add(CarFactory.createCar(2, track.getStops().get(1), user2Wheel, user2Engine)); // Player 2's car

        // **Create RaceView and pass Track and Cars**
        this.raceView = new RaceView(track, cars);

        // Display the race scene on primaryStage
        Scene raceScene = new Scene(raceView);
        primaryStage.setScene(raceScene);
        primaryStage.setTitle("Racing Game");
        primaryStage.show();

        // **Set up keyboard input listeners to control the race cars**
        setupKeyHandlers(raceScene);

        // Start game logic
        startGameLoop();
    }

    // Listen for keyboard events to control both race cars
    private void setupKeyHandlers(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W -> cars.get(0).move(0, -10); // Player 1 moves forward
                case S -> cars.get(0).move(0, 10);  // Player 1 moves backward
                case A -> cars.get(0).move(-10, 0); // Player 1 turns left
                case D -> cars.get(0).move(10, 0);  // Player 1 turns right

                case UP -> cars.get(1).move(0, -10); // Player 2 moves forward
                case DOWN -> cars.get(1).move(0, 10); // Player 2 moves backward
                case LEFT -> cars.get(1).move(-10, 0); // Player 2 turns left
                case RIGHT -> cars.get(1).move(10, 0);  // Player 2 turns right
            }
            raceView.updateRaceTrack(); // Update RaceTrackPanel to redraw the scene
        });
    }

    private void startGameLoop() {
        // Here, you can add a game loop using AnimationTimer to control car movement
    }
}


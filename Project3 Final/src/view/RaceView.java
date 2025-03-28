// This class represents the race screen (UI3) in the racing game.
// It handles race logic, UI updates, animations, and car rendering with dynamic status feedback.
// Author: Jing Pan, Andrew Lightfoot

package view;

import controller.RaceController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Car;
import model.GameData;
import model.Stop;
import model.Obstacle;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RaceView {
    private final Stage primaryStage;                      // Main stage for rendering
    private final GameData gameData;                       // Holds track and cars data

    // === UI Components ===
    private final VBox statusPanel = new VBox(10);         // Right-side panel for car info and logs
    private final VBox logPanel = new VBox(5);             // Scrollable log message panel
    private final VBox carStatusContainer = new VBox(10);  // Holds per-car status text areas
    private final Canvas mapCanvas = new Canvas(1000, 700);// Canvas for drawing map and cars
    private final Label logLabel = new Label();            // Label above log entries

    // === Core Logic ===
    private RaceController raceController;                 // Race logic controller
    private Timeline timeline;                             // Animation timeline for updates
    private MediaPlayer race;                              // Background sound player

    // === Image Resources ===
    private final Image obstacleImage = new Image("file:resources/images/obstacle.png");
    private final Image stopImage = new Image("file:resources/images/stop.png");
    private final Image carImage = new Image("file:resources/images/car1.png");
    private final Image carImage2 = new Image("file:resources/images/car2.png");
    private final Image backgroundImage = new Image("file:resources/images/circle.jpg");

    private ScrollPane scrollPane;                         // Scrollable container for logs
    private boolean isPaused = false;                      // Race pause state flag

    public RaceView(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
        this.raceController = new RaceController(primaryStage, gameData);
    }

    /**
     * Displays the full race screen and sets up all UI controls and timeline logic.
     */
    public void show() {
        // === Status Panel Setup ===
        statusPanel.setPadding(new Insets(10));
        statusPanel.setPrefWidth(300);
        statusPanel.setStyle("-fx-background-color:rgb(41, 121, 6); -fx-border-color: green;");

        Label statusLabel = new Label("\uD83D\uDCCA Game Status Board");
        statusLabel.setStyle("-fx-text-fill: white");
        statusLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 20));

        logLabel.setText("\uD83C\uDFC1 Game Log: ");
        logLabel.setStyle("-fx-text-fill: white");
        logLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 20));

        scrollPane = new ScrollPane(logPanel);
        scrollPane.setPrefHeight(300);
        scrollPane.setFitToWidth(true);

        statusPanel.getChildren().addAll(statusLabel, carStatusContainer, logLabel, scrollPane);

        // === Race Control Buttons ===
        HBox controlBox = new HBox(20);
        controlBox.setPadding(new Insets(10));
        controlBox.setStyle("-fx-background-color:rgb(41, 121, 6);");
        controlBox.setAlignment(javafx.geometry.Pos.CENTER);

        Button startBtn = new Button("▶ Start");
        Button pauseBtn = new Button("⏸ Pause");
        Button resetBtn = new Button("⟳ Restart");
        Button exitBtn = new Button("❌ Exit");

        controlBox.getChildren().addAll(startBtn, pauseBtn, resetBtn, exitBtn);

        // === Animation Timeline ===
        double deltaTime = 0.005;
        timeline = new Timeline(new KeyFrame(Duration.seconds(deltaTime), e -> {
            raceController.updateRace(deltaTime);
            drawMap();
            updateStatus();

            // Check if race has finished
            if (raceController.isRaceFinished()) {
                timeline.stop();
                List<Car> results = raceController.getSortedResults();
                String winner = results.get(0).getName();

                // Show winner in popup
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("🏆 Race Finished!");
                    alert.setHeaderText("Congratulations!");
                    alert.setContentText("The winner is " + winner + "!");
                    alert.getDialogPane().setStyle("-fx-font-size: 16px;");
                    alert.show();
                });
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        // === Button Actions ===
        // Andrew Lightfoot helped to update
        startBtn.setOnAction(e -> {
            timeline.play();
            try {
                Media sound = new Media(new File("resources/images/racesound.mp3").toURI().toString());
                race = new MediaPlayer(sound);
                race.setOnReady(() -> {
                    System.out.println("Playing Sound...");
                    race.play();
                });
                race.setOnError(() -> {
                    System.out.println("Error: " + race.getError());
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (isPaused) {
                startBtn.setText("▶ Resume");
                race.stop();
            }
            isPaused = false;
        });

        pauseBtn.setOnAction(e -> {
            timeline.pause();
            race.stop();
            isPaused = true;
            startBtn.setText("▶ Resume");
        });

        resetBtn.setOnAction(e -> {
            timeline.stop();
            if (race != null) {
                race.stop();
                race.dispose();
            }
            gameData.resetRaceState();
            race = new MediaPlayer(new Media(new File("resources/images/racesound.mp3").toURI().toString()));
            raceController = new RaceController(primaryStage, gameData);
            logLabel.setText("");
            logPanel.getChildren().clear();
            drawMap();
            updateStatus();
            startBtn.setText("▶ Start");
            isPaused = false;
        });

        exitBtn.setOnAction(e -> primaryStage.close());

        // === Layout Assembly ===
        BorderPane root = new BorderPane();
        root.setCenter(mapCanvas);
        root.setRight(statusPanel);
        root.setBottom(controlBox);

        Scene scene = new Scene(root, 1350, 750);
        primaryStage.setTitle("🏁 Racing Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        drawMap();
        updateStatus();
    }

    /**
     * Draws the track, obstacles, stops, and cars with correct orientation.
     */
    private void drawMap() {
        GraphicsContext gc = mapCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());
        gc.drawImage(backgroundImage, 0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());

        List<Stop> stops = gameData.getSelectedTrack().getStops();

        gc.setLineWidth(3);
        for (int i = 0; i < stops.size(); i++) {
            Stop s1 = stops.get(i);
            Stop s2 = stops.get((i + 1) % stops.size());

            double dx = s2.getX() - s1.getX();
            double dy = s2.getY() - s1.getY();
            double length = Math.sqrt(dx * dx + dy * dy);
            double offsetX = -dy / length * 8;
            double offsetY = dx / length * 8;

            gc.setStroke(Color.LIGHTBLUE);
            gc.strokeLine(s1.getX() + offsetX, s1.getY() + offsetY, s2.getX() + offsetX, s2.getY() + offsetY);
            gc.setStroke(Color.LIGHTPINK);
            gc.strokeLine(s1.getX() - offsetX, s1.getY() - offsetY, s2.getX() - offsetX, s2.getY() - offsetY);
        }

        for (Stop stop : stops) {
            gc.drawImage(stopImage, stop.getX() - 10, stop.getY() - 10, 20, 20);
            gc.setFill(Color.DARKGRAY);
            gc.fillText(stop.getId(), stop.getX() + 10, stop.getY());
        }

        for (Obstacle obstacle : gameData.getSelectedTrack().getObstacles()) {
            gc.drawImage(obstacleImage, obstacle.getX() - 15, obstacle.getY() - 15, 30, 30);
        }

        List<Car> cars = gameData.getCars();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            Image currentCarImage = (i == 0) ? carImage : (i == 1) ? carImage2 : carImage;

            double angle = 0.0;
            if (car.getNextStop() != null) {
                double dx = car.getNextStop().getX() - car.getCurrentStop().getX();
                double dy = car.getNextStop().getY() - car.getCurrentStop().getY();
                angle = Math.toDegrees(Math.atan2(dy, dx));
                car.setHeadingAngle(angle);
            }

            gc.save();
            gc.translate(car.getPositionX(), car.getPositionY());
            gc.rotate(car.getHeadingAngle());
            gc.drawImage(currentCarImage, -15, -15, 30, 30);
            gc.restore();

            gc.setFill(Color.BLACK);
            gc.fillText(car.getName(), car.getPositionX() - 10, car.getPositionY() - 20);
        }
    }

    /**
     * Updates the car info panel on the right side of the screen.
     */
    private void updateStatus() {
        carStatusContainer.getChildren().clear();
        for (Car car : gameData.getCars()) {
            StringBuilder sb = new StringBuilder();
            sb.append(car.getName()).append("\n")
                    .append("Speed: ").append(String.format("%.1f", car.getCurrentSpeed())).append("\n")
                    .append("Time: ").append(String.format("%.1f", car.getTotalTime())).append(" s\n")
                    .append("Route: ").append(car.getRouteAsString());

            TextArea carText = new TextArea(sb.toString());
            carText.setWrapText(true);
            carText.setEditable(false);
            carText.setPrefHeight(150);
            carStatusContainer.getChildren().add(carText);

            if (car.isSliding()) {
                log("💨 " + car.getName() + " is sliding...");
            }
        }
    }

    /**
     * Appends a new message to the game log with a timestamp.
     * @param message Message to log
     */
    private void log(String message) {
        String timeStamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Label entry = new Label("[" + timeStamp + "] " + message);
        logPanel.getChildren().add(entry);
        Platform.runLater(() -> scrollPane.setVvalue(1.0)); // Auto-scroll to bottom
    }
}
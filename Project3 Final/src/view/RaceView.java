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
import javafx.scene.input.KeyCode;
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
    private final Stage primaryStage;
    private final GameData gameData;
    private final VBox statusPanel = new VBox(10);
    private final VBox logPanel = new VBox(5);
    private final VBox carStatusContainer = new VBox(10);

    private final Canvas mapCanvas = new Canvas(1000, 700);
    private final Label logLabel = new Label();

    private RaceController raceController;
    private Timeline timeline;
    private MediaPlayer race;

    private final Image obstacleImage = new Image("file:resources/images/obstacle.png");
    private final Image stopImage = new Image("file:resources/images/stop.png");
    private final Image carImage = new Image("file:resources/images/car1.png");
    private final Image carImage2 = new Image("file:resources/images/car2.png");
    private final Image backgroundImage = new Image("file:resources/images/circle.jpg");
    private ScrollPane scrollPane;

    private boolean isPaused = false;

    public RaceView(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
        this.raceController = new RaceController(primaryStage, gameData);
    }

    public void show() {

        statusPanel.setPadding(new Insets(10));
        statusPanel.setPrefWidth(300);
        statusPanel.setStyle("-fx-background-color:rgb(41, 121, 6); -fx-border-color: green;");

        Label statusLabel = new Label("\uD83D\uDCCA Game Status Board");
        statusLabel.setStyle("-fx-text-fill: white");
        statusLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 20));


        scrollPane = new ScrollPane(logPanel);
        scrollPane.setPrefHeight(250);
        scrollPane.setFitToWidth(true);

        statusPanel.getChildren().addAll(statusLabel, carStatusContainer, logLabel, scrollPane);

        logLabel.setText("\uD83C\uDFC1 Game Log: " );
        logLabel.setStyle("-fx-text-fill: white");
        logLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 20));

        HBox controlBox = new HBox(20);
        controlBox.setPadding(new Insets(10));
        controlBox.setStyle("-fx-background-color:rgb(41, 121, 6);");
        controlBox.setAlignment(javafx.geometry.Pos.CENTER);

        Button startBtn = new Button("▶ Start");
        Button pauseBtn = new Button("⏸ Pause");
        Button resetBtn = new Button("⟳ Restart");
        Button exitBtn = new Button("❌ Exit");
        controlBox.getChildren().addAll(startBtn, pauseBtn, resetBtn, exitBtn);
       
        double deltaTime = 0.5;
        timeline = new Timeline(new KeyFrame(Duration.seconds(deltaTime), e -> {
            raceController.updateRace(deltaTime);
            drawMap();
            updateStatus();

            if (raceController.isRaceFinished()) {
                timeline.stop();
               // 
                List<Car> results = raceController.getSortedResults();
                String winner = results.get(0).getName();
                //winnerLabel.setText("\uD83C\uDFC1 Winner: " + winner);

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

        startBtn.setOnAction(e -> {
            timeline.play();
             try {
            // Removed unused variable musicFile2
            Media sound = new Media(new File("resources/images/racesound.mp3").toURI().toString());
            race = new MediaPlayer(sound);
            MediaPlayer race = new MediaPlayer(sound);
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
            Media sound = new Media(new File("resources/images/racesound.mp3").toURI().toString());
            race = new MediaPlayer(sound);
            raceController = new RaceController(primaryStage, gameData);
            logLabel.setText("");
            logPanel.getChildren().clear();
            drawMap();
            updateStatus();
            startBtn.setText("▶ Start");
            isPaused = false;
        });

        exitBtn.setOnAction(e -> primaryStage.close());

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
        for (int i = 0; i < cars.size(); i++){
            Car car = cars.get(i);
            Image currentCarImage = ( i == 0) ? carImage : (i == 1) ? carImage2 : carImage;

            double angle = 0.0;
            if (car.getNextStop() !=null) {
                double dx = car.getNextStop().getX() - car.getCurrentStop().getX();
                double dy = car.getNextStop().getY() - car.getCurrentStop().getY();
                angle = Math.toDegrees(Math.atan2(dy, dx));
                car.setHeadingAngle(angle);
            }
            gc.save();
            gc.translate(car.getPositionX(), car.getPositionY());
            gc.rotate(car.getHeadingAngle());


            gc.drawImage(currentCarImage,-15,-15,30,30); //, car.getPositionX() - 15, car.getPositionY() - 15, 30, 30);
            gc.restore();

            gc.setFill(Color.BLACK);
            gc.fillText(car.getName(), car.getPositionX() - 10, car.getPositionY() - 20);
        }

        //for (Car car : gameData.getCars()) {
        //    gc.drawImage(carImage, car.getPositionX() - 15, car.getPositionY() - 15, 30, 30);
        //    gc.setFill(Color.BLACK);
        //    gc.fillText(car.getName(), car.getPositionX() - 10, car.getPositionY() - 20);
        //}
    }

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
            carText.setPrefHeight(100);
            //carText.setStyle("-fx-font-family:Marlett ; -fx-padding: 5; -fx-border-color: #ccc;");
            carStatusContainer.getChildren().add(carText);

            if (car.isSliding()) {
                log("💨 " + car.getName() + " is sliding...");
            }
        }
    }

    private void log(String message) {
        String timeStamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Label entry = new Label("[" + timeStamp + "] " + message);
        logPanel.getChildren().add(entry);
        //logPanel.setStyle("-fx-font-family:Marlett ; -fx-padding: 5; -fx-border-color: #ccc;");
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }
}

/*比较完美版第二版
public class RaceView {
    private final Stage primaryStage;
    private final GameData gameData;
    private final VBox statusPanel = new VBox(10);
    private final VBox logPanel = new VBox(5);
    private final VBox carStatusContainer = new VBox(10);

    private final Canvas mapCanvas = new Canvas(1000, 700);
    private final Label winnerLabel = new Label();

    private RaceController raceController;
    private Timeline timeline;

    private final Image obstacleImage = new Image("file:resources/images/obstacle.png");
    private final Image stopImage = new Image("file:resources/images/stop.png");
    private final Image carImage = new Image("file:resources/images/car.png");

    private ScrollPane scrollPane;

    public RaceView(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
        this.raceController = new RaceController(primaryStage, gameData);
    }

    public void show() {
        statusPanel.setPadding(new Insets(10));
        statusPanel.setPrefWidth(300);
        statusPanel.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: gray;");

        Label statusLabel = new Label("\uD83D\uDCCA Game Status Board");
        winnerLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: darkgreen");

        scrollPane = new ScrollPane(logPanel);
        scrollPane.setPrefHeight(250);
        scrollPane.setFitToWidth(true);

        statusPanel.getChildren().addAll(statusLabel, carStatusContainer, winnerLabel, scrollPane);

        HBox controlBox = new HBox(20);
        controlBox.setPadding(new Insets(10));
        controlBox.setStyle("-fx-background-color: #e6e6e6;");
        controlBox.setAlignment(javafx.geometry.Pos.CENTER);

        Button startBtn = new Button("▶ Start");
        Button pauseBtn = new Button("⏸ Pause");
        Button resetBtn = new Button("⟳ Restart");
        Button exitBtn = new Button("❌ Exit");
        controlBox.getChildren().addAll(startBtn, pauseBtn, resetBtn, exitBtn);

        double deltaTime = 0.5;
        timeline = new Timeline(new KeyFrame(Duration.seconds(deltaTime), e -> {
            raceController.updateRace(deltaTime);
            drawMap();
            updateStatus();

            if (raceController.isRaceFinished()) {
                timeline.stop();
                List<Car> results = raceController.getSortedResults();
                String winner = results.get(0).getName();
                //winnerLabel.setText("🏁 Winner: " + winner);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        startBtn.setOnAction(e -> timeline.play());
        pauseBtn.setOnAction(e -> timeline.pause());
        resetBtn.setOnAction(e -> timeline.stop());
        exitBtn.setOnAction(e -> primaryStage.close());

        BorderPane root = new BorderPane();
        root.setCenter(mapCanvas);
        root.setRight(statusPanel);
        root.setBottom(controlBox);

        Scene scene = new Scene(root, 1400, 750);
        primaryStage.setTitle("🏁 Racing Game UI3");
        primaryStage.setScene(scene);
        primaryStage.show();

        drawMap();
        updateStatus();
    }

    private void drawMap() {
        GraphicsContext gc = mapCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());

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

        for (Car car : gameData.getCars()) {
            gc.drawImage(carImage, car.getPositionX() - 15, car.getPositionY() - 15, 30, 30);
            gc.setFill(Color.BLACK);
            gc.fillText(car.getName(), car.getPositionX() - 10, car.getPositionY() - 20);
        }
    }

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
            carText.setPrefHeight(100);
            carText.setStyle("-fx-font-family: monospace; -fx-padding: 5; -fx-border-color: #ccc;");
            carStatusContainer.getChildren().add(carText);

            if (car.isSliding()) {
                log(" \uD83D\uDED1  " + car.getName() + " is sliding...");
            }
        }
    }

    private void log(String message) {
        String timeStamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Label entry = new Label("[" + timeStamp + "] " + message);
        logPanel.getChildren().add(entry);

        Platform.runLater(() -> scrollPane.setVvalue(1.0)); // 滚动到底部
    }
}

 */


/*比较完美版本
package view;

import controller.RaceController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Car;
import model.GameData;
import model.Stop;
import model.Obstacle;

import java.util.List;
public class RaceView {
    private final Stage primaryStage;
    private final GameData gameData;
    private final VBox statusPanel = new VBox(10);
    private final VBox logPanel = new VBox(5);
    private final VBox carStatusContainer = new VBox(10);

    private final Canvas mapCanvas = new Canvas(1000, 700);
    private final Label winnerLabel = new Label();

    private RaceController raceController;
    private Timeline timeline;

    private final Image obstacleImage = new Image("file:resources/images/obstacle.png");
    private final Image stopImage = new Image("file:resources/images/stop.png");
    private final Image carImage = new Image("file:resources/images/car.png");

    public RaceView(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
        this.raceController = new RaceController(primaryStage, gameData);
    }

    public void show() {
        statusPanel.setPadding(new Insets(10));
        statusPanel.setPrefWidth(300);
        statusPanel.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: gray;");

        Label statusLabel = new Label("\uD83D\uDCCA Game Status Board");
        winnerLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: darkgreen");

        ScrollPane scrollPane = new ScrollPane(logPanel);
        scrollPane.setPrefHeight(250);
        scrollPane.setFitToWidth(true);

        statusPanel.getChildren().addAll(statusLabel, carStatusContainer, winnerLabel, scrollPane);

        HBox controlBox = new HBox(20);
        controlBox.setPadding(new Insets(10));
        controlBox.setStyle("-fx-background-color: #e6e6e6;");
        controlBox.setAlignment(javafx.geometry.Pos.CENTER);

        Button startBtn = new Button("▶ Start");
        Button pauseBtn = new Button("⏸ Pause");
        Button resetBtn = new Button("⟳ Restart");
        Button exitBtn = new Button("❌ Exit");
        controlBox.getChildren().addAll(startBtn, pauseBtn, resetBtn, exitBtn);

        double deltaTime = 0.5;
        timeline = new Timeline(new KeyFrame(Duration.seconds(deltaTime), e -> {
            raceController.updateRace(deltaTime);
            drawMap();
            updateStatus();

            if (raceController.isRaceFinished()) {
                timeline.stop();
                List<Car> results = raceController.getSortedResults();
                String winner = results.get(0).getName();
                //winnerLabel.setText("🏁 Winner: " + winner);

                // 弹窗不在动画线程内调用，使用 Platform.runLater
                javafx.application.Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("🏁 Race Finished!");
                    alert.setHeaderText(null);
                    alert.setContentText("The winner is " + winner + "!");
                    alert.showAndWait();
                });
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        startBtn.setOnAction(e -> timeline.play());
        pauseBtn.setOnAction(e -> timeline.pause());
        resetBtn.setOnAction(e -> timeline.stop());
        exitBtn.setOnAction(e -> primaryStage.close());

        BorderPane root = new BorderPane();
        root.setCenter(mapCanvas);
        root.setRight(statusPanel);
        root.setBottom(controlBox);

        Scene scene = new Scene(root, 1400, 750);
        primaryStage.setTitle("🏁 Racing Game UI3");
        primaryStage.setScene(scene);
        primaryStage.show();

        drawMap();
        updateStatus();
    }

    private void drawMap() {
        GraphicsContext gc = mapCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());

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

        for (Car car : gameData.getCars()) {
            gc.drawImage(carImage, car.getPositionX() - 15, car.getPositionY() - 15, 30, 30);
            gc.setFill(Color.BLACK);
            gc.fillText(car.getName(), car.getPositionX() - 10, car.getPositionY() - 20);
        }
    }

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
            carText.setStyle("-fx-font-family: monospace; -fx-padding: 5; -fx-border-color: #ccc;");
            carStatusContainer.getChildren().add(carText);

            if (car.isSliding()) log("⚠️  " + car.getName() + " is sliding!");
        }
    }

    private void log(String message) {
        logPanel.getChildren().add(new Label(message));
    }
}

 */

/* 有背景版本
public class RaceView {
    private final Stage primaryStage;
    private final GameData gameData;
    private final VBox statusPanel = new VBox(10);
    private final VBox logPanel = new VBox(5);
    private final VBox carStatusContainer = new VBox(10);

    private final Canvas mapCanvas = new Canvas(1000, 700);
    private final Label winnerLabel = new Label();

    private RaceController raceController;
    private Timeline timeline;

    private final Image obstacleImage = new Image("file:resources/images/obstacle.png");
    private final Image stopImage = new Image("file:resources/images/stop.png");
    private final Image backgroundImage = new Image("file:resources/images/background.png");
    private final Image carImage = new Image("file:resources/images/car.png");

    public RaceView(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
        this.raceController = new RaceController(primaryStage, gameData);
    }

    public void show() {
        statusPanel.setPadding(new Insets(10));
        statusPanel.setPrefWidth(250);
        statusPanel.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: gray;");

        Label statusLabel = new Label("\uD83D\uDCCA Game Status Board");
        winnerLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: darkgreen");

        ScrollPane scrollPane = new ScrollPane(logPanel);
        scrollPane.setPrefHeight(250);
        scrollPane.setFitToWidth(true);

        statusPanel.getChildren().addAll(statusLabel, carStatusContainer, winnerLabel, scrollPane);

        HBox controlBox = new HBox(20);
        controlBox.setPadding(new Insets(10));
        controlBox.setStyle("-fx-background-color: #e6e6e6;");
        controlBox.setAlignment(javafx.geometry.Pos.CENTER);

        Button startBtn = new Button("▶ Start");
        Button pauseBtn = new Button("⏸ Pause");
        Button resetBtn = new Button("⟳ Restart");
        Button exitBtn = new Button("❌ Exit");
        controlBox.getChildren().addAll(startBtn, pauseBtn, resetBtn, exitBtn);

        double deltaTime = 0.5;
        timeline = new Timeline(new KeyFrame(Duration.seconds(deltaTime), e -> {
            raceController.updateRace(deltaTime);
            drawMap();
            updateStatus();

            if (raceController.isRaceFinished()) {
                timeline.stop();
                List<Car> results = raceController.getSortedResults();
                String winner = results.get(0).getName();
                winnerLabel.setText("🏁 Winner: " + winner);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        startBtn.setOnAction(e -> timeline.play());
        pauseBtn.setOnAction(e -> timeline.pause());
        resetBtn.setOnAction(e -> timeline.stop());
        exitBtn.setOnAction(e -> primaryStage.close());

        BorderPane root = new BorderPane();
        root.setCenter(mapCanvas);
        root.setRight(statusPanel);
        root.setBottom(controlBox);

        Scene scene = new Scene(root, 1200, 750);
        primaryStage.setTitle("🏁 Racing Game UI3");
        primaryStage.setScene(scene);
        primaryStage.show();

        drawMap();
        updateStatus();
    }

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

        for (Car car : gameData.getCars()) {
            gc.drawImage(carImage, car.getPositionX() - 15, car.getPositionY() - 15, 30, 30);
            gc.setFill(Color.BLACK);
            gc.fillText(car.getName(), car.getPositionX() - 10, car.getPositionY() - 20);
        }
    }

    private void updateStatus() {
        carStatusContainer.getChildren().clear();
        for (Car car : gameData.getCars()) {
            StringBuilder sb = new StringBuilder();
            sb.append(car.getName()).append("\n")
                    .append("Speed: ").append(String.format("%.1f", car.getCurrentSpeed())).append("\n")
                    .append("Time: ").append(String.format("%.1f", car.getTotalTime())).append(" s\n")
                    .append("Route: ").append(car.getRouteAsString());

            Label carLabel = new Label(sb.toString());
            carLabel.setStyle("-fx-font-family: monospace; -fx-padding: 5; -fx-border-color: #ccc;");
            carStatusContainer.getChildren().add(carLabel);

            if (car.isSliding()) log("⚠️  " + car.getName() + " is sliding!");
        }
    }

    private void log(String message) {
        logPanel.getChildren().add(new Label(message));
    }
}

 */


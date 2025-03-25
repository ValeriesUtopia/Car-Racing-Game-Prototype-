package controller;
// import sun.audio.*; unneeded import
import javafx.stage.Stage;
import model.*;
import view.RaceView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RaceController {
    private final GameData gameData;
    private final Random random = new Random();
    private final Stage primaryStage;
    private MediaPlayer mediaPlayer;
    private boolean isSoundPlaying = false;
    private boolean hasCarMoved = false;

    public RaceController(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
    }

    public void startRace() {

        RaceView raceView = new RaceView(primaryStage, gameData);
        //mediaPlayer.play();
        raceView.show();
        //

    }
    // Start the sound
    public void startSound() {
        try {
            String musicFile = getClass().getResource("/images/racesound.mp3").toExternalForm();
            System.out.println("Music Path: " + musicFile); // Debugging
    
            Media sound = new Media(musicFile);
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the sound
    
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

    public void updateRace(double deltaTime) {
        boolean anyCarMoving = false; 
        for (Car car : gameData.getCars()) {
           // mediaPlayer.play();
            if (car.hasFinished()) continue;


            // added
            car.setSliding(false);
            // 加速逻辑
            double newSpeed = car.getCurrentSpeed() + car.getEngine().getAcceleration() * deltaTime;
            car.setCurrentSpeed(Math.min(newSpeed, car.getEngine().getMaxSpeed()));
            if (car.getCurrentSpeed() > 0) {
                anyCarMoving = true; // At least one car is moving
            }
            // 获取当前和下一个 stop
            Stop current = car.getCurrentStop();
            Stop next = car.getNextStop();
            if (next == null) {
                car.markFinished();
                continue;
            }

            // 检查是否碰到障碍
            boolean sliding = false;
            for (Obstacle obstacle : gameData.getSelectedTrack().getObstacles()) {
                if (obstacle.isInRange(car.getPositionX(), car.getPositionY())) {
                    obstacle.applyEffect(car);
                    sliding = true;
                }
            }
            car.setSliding(sliding);

            // 移动向量
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
        if (anyCarMoving && !isSoundPlaying && !hasCarMoved) {
           // mediaPlayer.play();
            isSoundPlaying = true;
            hasCarMoved = true;
        }
     
       //
        if (isRaceFinished()) {
            stopMusic();
        }

        }
    

    

    public boolean isRaceFinished() {
        return gameData.getCars().stream().allMatch(Car::hasFinished);
    }

    public List<Car> getSortedResults() {
        List<Car> result = new ArrayList<>(gameData.getCars());
        result.sort(Comparator.comparingDouble(Car::getTotalTime));
        return result;
    }
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            isSoundPlaying = false;
        }
    }
}


/* 可以正常运行版本
package controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.*;
import view.RaceView;

import java.util.ArrayList;
import java.util.List;

import java.util.Comparator;

import java.util.Random;

public class RaceController {
    private final GameData gameData;
    private final Random random = new Random();
    private final Stage primaryStage;

    public RaceController(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
    }

    public void startRace() {
        RaceView raceView = new RaceView(primaryStage, gameData);  // 你要提前写好 RaceView.java
        raceView.show(); // 显示 UI3 界面
    }


    public void updateRace(double deltaTime) {
        for (Car car : gameData.getCars()) {
            if (car.hasFinished()) continue;

            // 加速逻辑
            double newSpeed = car.getCurrentSpeed() + car.getEngine().getAcceleration() * deltaTime;
            car.setCurrentSpeed(Math.min(newSpeed, car.getEngine().getMaxSpeed()));

            // 获取当前和下一个 stop
            Stop current = car.getCurrentStop();
            Stop next = car.getNextStop();
            if (next == null) {
                car.markFinished();
                continue;
            }

            // 检查是否碰到障碍
            for (Obstacle obstacle : gameData.getSelectedTrack().getObstacles()) {
                if (obstacle.isInRange(car.getPositionX(), car.getPositionY())) {
                    obstacle.applyEffect(car);
                }
            }


            // 移动向量
            double dx = next.getX() - car.getPositionX();
            double dy = next.getY() - car.getPositionY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance == 0) {
                car.advanceToNextStop();
                return;
            }

            // 单位方向
            double directionX = dx / distance;
            double directionY = dy / distance;

            // 位移量
            double moveDistance = car.getCurrentSpeed() * deltaTime;
            double moveX = directionX * moveDistance;
            double moveY = directionY * moveDistance;

            // 判断是否到达下一个 Stop
            if (moveDistance >= distance) {
                car.updatePosition(next.getX(), next.getY());
                car.advanceToNextStop();
            } else {
                car.updatePosition(car.getPositionX() + moveX, car.getPositionY() + moveY);
            }

            car.updateTime(deltaTime);
        }
    }

    public boolean isRaceFinished() {
        return gameData.getCars().stream().allMatch(Car::hasFinished);
    }

    public List<Car> getSortedResults() {
        List<Car> result = new ArrayList<>(gameData.getCars());
        result.sort(Comparator.comparingDouble(Car::getTotalTime));
        return result;
    }
}

 */

/*
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

 */


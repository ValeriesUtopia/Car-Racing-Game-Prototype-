package controller;

import javafx.stage.Stage;
import model.*;
import view.CarSelectionView;
import view.DifficultySelectionView;

import java.util.ArrayList;
import java.util.List;

public class CarSelectionController {
    private final Stage primaryStage;
    private final GameData gameData;
    private final String difficulty;

    public CarSelectionController(Stage primaryStage, GameData gameData) {
        this.primaryStage = primaryStage;
        this.gameData = gameData;
        this.difficulty = gameData.getDifficulty();
    }

    public void showSelectionScreen() {
        CarSelectionView carSelection = new CarSelectionView(primaryStage, this);
        carSelection.show(difficulty); // 传递 difficulty 用于显示地图
    }

    public void handleBack() {
        DifficultySelectionView difficultyView = new DifficultySelectionView(primaryStage);
        difficultyView.setOnDifficultySelected(selectedDifficulty -> {
            gameData.setDifficulty(selectedDifficulty);
            CarSelectionController newController = new CarSelectionController(primaryStage, gameData);
            newController.showSelectionScreen();
        });
        difficultyView.show();
    }

    public void handleSelection(String user1Engine, String user1Tire, String user2Engine, String user2Tire) {
        System.out.println("Difficulty: " + difficulty);
        System.out.println("User1 - Wheel: " + user1Tire + ", Engine: " + user1Engine);
        System.out.println("User2 - Wheel: " + user2Tire + ", Engine: " + user2Engine);

        // 根据难度选择赛道
        Track track = switch (difficulty.toUpperCase()) {
            case "EASY" -> TrackFactory.createEasyTrack();
            case "MEDIUM" -> TrackFactory.createMediumTrack();
            case "CHALLENGE" -> TrackFactory.createChallengeTrack();
            default -> TrackFactory.createEasyTrack();
        };

        gameData.setSelectedTrack(track);

        List<Stop> original = track.getStops();
        List<Stop> route1 = new ArrayList<>(original);
        List<Stop> route2 = new ArrayList<>(original);

        // 确保起点不同
        do {
            Stop first = route2.remove(0);
            route2.add(first);
        } while (route1.get(0).equals(route2.get(0)));

        Car car1 = CarFactory.createCar("Car 1", user1Engine, user1Tire, route1, "red");
        Car car2 = CarFactory.createCar("Car 2", user2Engine, user2Tire, route2, "blue");

        gameData.getCars().clear();
        gameData.addCar(car1);
        gameData.addCar(car2);

        RaceController raceController = new RaceController(primaryStage, gameData);
        raceController.startRace();
    }
}

/* 可以显示版本
package controller;

import javafx.stage.Stage;
import view.CarSelectionView;



import model.*;
import view.CarSelectionView;
//import view.RaceView; // 如果你有比赛 UI3 的视图类

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CarSelectionController {
    private final Stage primaryStage;
    private final String difficulty;
    private final GameData gameData;

    public CarSelectionController(Stage primaryStage,  GameData gameData) {
        this.primaryStage = primaryStage;

        this.gameData = gameData;
        this.difficulty = gameData.getDifficulty();
    }

    public void showSelectionScreen() {
        CarSelectionView carSelection = new CarSelectionView(primaryStage, this);
        carSelection.show();
    }

    public void handleSelection(String user1Engine, String user1Tire, String user2Engine, String user2Tire) {
        System.out.println("Difficulty: " + difficulty);
        System.out.println("User1 - Wheel: " + user1Tire + ", Engine: " + user1Engine);
        System.out.println("User2 - Wheel: " + user2Tire + ", Engine: " + user2Engine);

        // 1. 根据 difficulty 选择赛道
        Track track = switch (difficulty.toUpperCase()) {
            case "EASY" -> TrackFactory.createEasyTrack();
            case "MEDIUM" -> TrackFactory.createMediumTrack();
            case "CHALLENGE" -> TrackFactory.createChallengeTrack();
            default -> TrackFactory.createEasyTrack();
        };

        gameData.setSelectedTrack(track); // raceController will get track
        List<Stop> stops = track.getStops();

        // 2. 为每辆车创建独立路径并打乱，确保首尾不同
        List<Stop> original = track.getStops();  // 原始顺序
        List<Stop> route1 = new ArrayList<>(original);  // A → B → C → D → E → F
        List<Stop> route2 = new ArrayList<>(original);

    // 旋转第二条路线，直到起点不同
        do {
            Stop first = route2.remove(0);
            route2.add(first);  // B → C → D → E → F → A
        } while (route1.get(0).equals(route2.get(0)));



        System.out.println("🚗 Car 1 route:");
        for (Stop s : route1) {
            System.out.print(s.getId() + " → ");
        }
        System.out.println("END");

        System.out.println("🚙 Car 2 route:");
        for (Stop s : route2) {
            System.out.print(s.getId() + " → ");
        }
        System.out.println("END");

        // 3. 创建车辆对象
        Car car1 = CarFactory.createCar("Car 1", user1Engine, user1Tire, route1, "red");
        Car car2 = CarFactory.createCar("Car 2", user2Engine, user2Tire, route2, "blue");

        // 4. 添加进 GameData
        gameData.getCars().clear();
        gameData.addCar(car1);
        gameData.addCar(car2);

        // 5. 启动比赛（UI3 待实现）
        RaceController controller = new RaceController(primaryStage, gameData);
        controller.startRace();

        System.out.println("🎯 Selected difficulty: " + difficulty);
        System.out.println("📍 Track stops: ");
        for (Stop s : track.getStops()) {
            System.out.println("- " + s.getId() + " (" + s.getX() + ", " + s.getY() + ")");
        }

    }
}

 */

/*
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

 */

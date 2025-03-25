package main;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

import controller.DifficultySelectionController;

import model.GameData;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameData gameData = new GameData(); // 创建共享的数据模型
        DifficultySelectionController controller = new DifficultySelectionController(primaryStage, gameData);
        controller.showSelectionScreen();  // ⬅️ 启动 UI1
              /* */ try {
            String musicFile = "resources/images/MenuJazz.mp3"; // Replace with the correct path
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer Jazz = new MediaPlayer(sound);
            Jazz.setOnReady(() -> {
                System.out.println("Playing Sound...");
                Jazz.setVolume(0); // Start with volume at 0
                Jazz.play(); // Start playing the media
                new Thread(() -> {
                    try {
                        for (int i = 0; i <= 100; i++) {
                            double volume = i / 100.0; // Gradually increase volume
                            Jazz.setVolume(volume);
                            Thread.sleep(50); // Adjust fade-in duration
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            });
            Jazz.setOnError(() -> {
                System.out.println("Error: " + Jazz.getError());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // 启动 JavaFX 应用
    }
}



// 测试model pacakge，之前racecontroller的constructor只有一个参数gamedata的时候是测试过的正常的
/*
public class Main {

    public static void main(String[] args) {
        // 创建 GameData
        GameData gameData = new GameData();

        // 创建简单赛道（不包含障碍）-->已测试，逻辑没问题
        //Track track = TrackFactory.createEasyTrack();
        //gameData.setSelectedTrack(track);

        // 开始测试中等赛道（有障碍物）
        Track track = TrackFactory.createMediumTrack();
        System.out.println("Debug: track - " + track);
        gameData.setSelectedTrack(track);// debug


        // 创建 3 个不同路线的车辆
        List<Stop> stops = track.getStops();

        // 假设三个车路线不同：
        List<Stop> route1 = Arrays.asList(stops.get(0), stops.get(1), stops.get(2)); // A -> B -> C
        List<Stop> route2 = Arrays.asList(stops.get(2), stops.get(0), stops.get(1)); // C -> A -> B
        List<Stop> route3 = Arrays.asList(stops.get(1), stops.get(2), stops.get(0)); // B -> C -> A

        Car car1 = CarFactory.createCar("Car 1", "V6", "Standard", route1, "blue");
        Car car2 = CarFactory.createCar("Car 2", "V8", "Racing", route2, "red");
        Car car3 = CarFactory.createCar("Car 3", "V6", "Racing", route3, "green");

        gameData.addCar(car1);
        gameData.addCar(car2);
        gameData.addCar(car3);

        // 创建 RaceController
        RaceController controller = new RaceController(gameData);

        double deltaTime = 0.5; // 每次模拟 0.5 秒
        double timeElapsed = 0.0;

        System.out.println("🏁 Starting Race Simulation (With Obstacles)...\n");

        while (!controller.isRaceFinished()) {
            controller.updateRace(deltaTime);
            timeElapsed += deltaTime;

            for (Car car : gameData.getCars()) {
                String status = car.hasFinished() ? "✅ Finished"
                        : String.format("🚗 At (%.1f, %.1f), speed: %.1f",
                        car.getPositionX(), car.getPositionY(), car.getCurrentSpeed());
                System.out.println("[" + timeElapsed + "s] " + car.getName() + ": " + status);

                // 如果车辆正在打滑，输出提示
                if (!car.hasFinished() && car.isSliding()) {
                    System.out.println("⚠️  " + car.getName() + " is sliding!");
                }
            }

            System.out.println();
        }


        while (!controller.isRaceFinished()) {
            controller.updateRace(deltaTime);
            timeElapsed += deltaTime;

            for (Car car : gameData.getCars()) {
                String status = car.hasFinished() ? "✅ Finished" : String.format("🚗 At (%.1f, %.1f), speed: %.1f",
                        car.getPositionX(), car.getPositionY(), car.getCurrentSpeed());
                System.out.println("[" + timeElapsed + "s] " + car.getName() + ": " + status);
            }

            System.out.println();
        }



        // 比赛结束，显示结果
        System.out.println("🎉 Race finished! Final Results:");
        List<Car> results = controller.getSortedResults();
        for (int i = 0; i < results.size(); i++) {
            Car car = results.get(i);
            System.out.printf("%d. %s - Time: %.2f seconds\n", i + 1, car.getName(), car.getTotalTime());
        }
    }
}

 */


/*
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

 */



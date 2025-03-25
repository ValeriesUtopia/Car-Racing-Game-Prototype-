package model;
import model.Obstacle;
import model.Stop;
import java.util.ArrayList;
import java.util.List;



/*
trackCoordinates：这是一个 List<double[]>，用来存储赛道的每一个坐标点。每个坐标是一个二维坐标 (x, y)，这些坐标用来绘制赛道的路径。
generateTrackCoordinates()：根据赛道的不同形状（圆形、S型、复杂路径）来生成不同的坐标列表。例如，圆形赛道通过三角函数来生成 (x, y) 坐标，S型赛道通过 sin 函数生成弯道坐标。
后续添加：障碍物和停靠点的位置可以基于这些坐标进行生成。
 */
// package: model

import java.util.ArrayList;
import java.util.List;

public class Track {
    public enum Difficulty {
        EASY, MEDIUM, CHALLENGE
    }

    private final String name;
    private final Difficulty difficulty;
    private final List<Stop> stops;
    private final List<Obstacle> obstacles;

    public Track(String name, Difficulty difficulty, List<Stop> stops) {
        this.name = name;
        this.difficulty = difficulty;
        this.stops = new ArrayList<>(stops);
        this.obstacles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }
    // for debug
    @Override
    public String toString() {
        return "Track{name='" + name + "', difficulty=" + difficulty +
                ", stops=" + stops.size() +
                ", obstacles=" + obstacles.size() + "}";
    }

}

/* 更新前的版本
import java.util.Random;

public class Track {
    private int length;  // 赛道长度
    private String difficulty;  // 难度
    private List<Obstacle> obstacles;  // 障碍物位置
    private List<Stop> stops;  // 停靠点位置
    private List<double[]> trackCoordinates;  // 赛道坐标
    private String shape;  // 赛道形状
    // 新增
    // 赛道坐标列表
    private List<List<double[]>> trackLayers;

    public Track(String difficulty) {
        this.difficulty = difficulty;
        this.length = generateTrackLength();
        this.shape = generateTrackShape();
        this.trackCoordinates = generateTrackCoordinates();  // 生成赛道坐标
        this.obstacles = generateObstacles();  // 生成障碍物位置
        this.stops = generateStops();  // 生成停靠点位置
        this.trackLayers = generateTrackLayers();// 新增

    }

    // 生成赛道长度
    private int generateTrackLength() {
        switch (difficulty) {
            case "Easy":
                return 800;
            case "Medium":
                return 1000;
            case "Challenging":
                return 1500;
            default:
                return 800;
        }
    }

    // 生成赛道形状
    private String generateTrackShape() {
        switch (difficulty) {
            case "Easy":
                return "Straight";  // 直线路径
            case "Medium":
                return "Oval";  // 椭圆赛道
            case "Challenging":
                return "SharpTurns";  // 五个直角弯赛道
            default:
                return "Straight";
        }
    }


    // 生成赛道坐标
    private List<double[]> generateTrackCoordinates() {
        List<double[]> coordinates = new ArrayList<>();

        if ("Straight".equals(shape)) {
            // **动态生成直线路径**
            int startX = 100;
            int startY = 300;
            int endX = 900;
            for (int x = startX; x <= endX; x += 10) {
                coordinates.add(new double[]{x, startY});
            }
        } else if ("Oval".equals(shape)) {
            // **动态生成椭圆赛道**
            int centerX = 500;
            int centerY = 300;
            int radiusX = 300;
            int radiusY = 150;
            for (int i = 0; i <= 360; i += 5) {
                double angle = Math.toRadians(i);
                double x = centerX + radiusX * Math.cos(angle);
                double y = centerY + radiusY * Math.sin(angle);
                coordinates.add(new double[]{x, y});
            }
        } else if ("SharpTurns".equals(shape)) {
            // **动态生成五个直角弯赛道**
            int startX = 100, startY = 300;
            int segmentLength = 200; // 每段赛道的长度
            int numTurns = 5; // 直角弯数量
            boolean horizontal = true;

            coordinates.add(new double[]{startX, startY}); // 起点
            for (int i = 0; i < numTurns; i++) {
                if (horizontal) {
                    startX += segmentLength;
                } else {
                    startY += (i % 2 == 0) ? segmentLength : -segmentLength;
                }
                coordinates.add(new double[]{startX, startY});
                horizontal = !horizontal; // 交替方向
            }
        }
        return coordinates;
    }

    // 生成障碍物
    private List<Obstacle> generateObstacles() {
        List<Obstacle> obstacles = new ArrayList<>();
        Random rand = new Random();

        int numObstacles;
        switch (difficulty) {
            case "Easy":
                numObstacles = 1;
                break;
            case "Medium":
                numObstacles = 2;
                break;
            case "Challenging":
                numObstacles = 3;
                break;
            default:
                numObstacles = 1;
        }

        for (int i = 0; i < numObstacles; i++) {
            int index = rand.nextInt(trackCoordinates.size()); // 确保索引合法
            double[] pos = trackCoordinates.get(index);
            obstacles.add(new Obstacle(pos[0], pos[1]));
        }

        return obstacles;
    }

    // 生成停靠点

    private List<Stop> generateStops() {
        List<Stop> stops = new ArrayList<>();
        int numStops = 3; // 设定固定的3个停靠点
        int trackSize = trackCoordinates.size();

        // 确保赛道长度足够长
        if (trackSize < numStops) {
            return stops;
        }

        // 选择 1/4、1/2、3/4 赛道长度的位置放置停靠点
        int[] stopPositions = {trackSize / 4, trackSize / 2, 3 * trackSize / 4};

        for (int index : stopPositions) {
            double[] pos = trackCoordinates.get(index);
            stops.add(new Stop(pos[0], pos[1]));
        }

        return stops;
    }


    // 获取赛道形状
    public String getShape() {
        return shape;
    }

    // 获取赛道坐标
    public List<double[]> getTrackCoordinates() {
        return trackCoordinates;
    }

    // 获取赛道长度
    public int getLength() {
        return length;
    }

    // 获取赛道的难度
    public String getDifficulty() {
        return difficulty;
    }

    // 获取障碍物
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    // 获取停靠点
    public List<Stop> getStops() {
        return stops;
    }

    // 新增
    private List<List<double[]>> generateTrackLayers() {
        List<List<double[]>> layers = new ArrayList<>();
        List<double[]> baseTrack = generateTrackCoordinates(); // 原始赛道

        int laneSpacing = 15; // 赛道间距

        for (int i = -1; i <= 1; i++) {
            List<double[]> layer = new ArrayList<>();
            for (double[] coord : baseTrack) {
                double x, y;
                if ("Straight".equals(shape)) {
                    // 直线路径的偏移仅在Y方向
                    x = coord[0];
                    y = coord[1] + i * laneSpacing;
                } else if ("Oval".equals(shape)) {
                    // 椭圆路径的偏移计算
                    double angle = Math.atan2(coord[1] - 300, coord[0] - 500);
                    x = coord[0] + i * laneSpacing * Math.cos(angle);
                    y = coord[1] + i * laneSpacing * Math.sin(angle);
                } else {
                    // 直角弯路径的偏移计算
                    x = coord[0] + i * laneSpacing;
                    y = coord[1] + i * laneSpacing;
                }
                layer.add(new double[]{x, y});
            }
            layers.add(layer);
        }

        return layers;
    }
    public List<List<double[]>> getTrackLayers() {
        return trackLayers;
    }

}

 */


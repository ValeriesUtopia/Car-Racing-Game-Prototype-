package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.Car;
import model.Track;
import model.Stop;
import model.Obstacle;

import java.util.List;
/*
public class RaceTrackPanel extends Canvas {
    private Track track;  // Track data
    private List<Car> cars; // List of race cars

    public RaceTrackPanel(Track track, List<Car> cars) {
        this.track = track;
        this.cars = cars;
        this.setWidth(1000);
        this.setHeight(600);
        drawTrack();
    }

    // Draw grid lines for alignment assistance
    private void drawGrid(GraphicsContext gc) {
        gc.setStroke(Color.LIGHTGRAY);  // Set grid color
        gc.setLineWidth(0.5);  // Thin lines

        int gridSize = 30;  // Grid size
        double width = getWidth();
        double height = getHeight();

        // Draw vertical grid lines
        for (int x = 0; x < width; x += gridSize) {
            gc.strokeLine(x, 0, x, height);
        }

        // Draw horizontal grid lines
        for (int y = 0; y < height; y += gridSize) {
            gc.strokeLine(0, y, width, y);
        }
    }

    // Draw the race track
    public void drawTrack() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        // Optional: Draw grid for better track alignment
        drawGrid(gc);

        // Set track color
        gc.setStroke(Color.DARKGRAY);
        gc.setLineWidth(10);

        List<double[]> trackCoordinates = track.getTrackCoordinates();

        // Draw multiple track layers if available
        List<List<double[]>> trackLayers = track.getTrackLayers();
        for (List<double[]> layer : trackLayers) {
            gc.strokePolyline(
                    layer.stream().mapToDouble(coord -> coord[0]).toArray(),
                    layer.stream().mapToDouble(coord -> coord[1]).toArray(),
                    layer.size()
            );
        }

        // Draw the main track path (optimized elliptical path)
        gc.strokePolyline(
                trackCoordinates.stream().mapToDouble(coord -> coord[0]).toArray(),
                trackCoordinates.stream().mapToDouble(coord -> coord[1]).toArray(),
                trackCoordinates.size()
        );

        // Draw obstacles and stop points
        drawObstacles(gc);
        drawStopPoints(gc);

        // Draw race cars
        drawCars(gc);
    }

    // Draw obstacles on the track
    private void drawObstacles(GraphicsContext gc) {
        gc.setFill(Color.DARKRED);
        gc.setStroke(Color.BLACK); // Add a black outline for better visibility

        for (Obstacle obstacle : track.getObstacles()) {
            gc.fillRect(obstacle.getX(), obstacle.getY(), 10, 10);
            gc.strokeRect(obstacle.getX(), obstacle.getY(), 10, 10);
        }
    }

    // Draw stop points on the track
    private void drawStopPoints(GraphicsContext gc) {
        gc.setFill(Color.LIGHTBLUE);
        gc.setStroke(Color.RED);

        for (Stop stop : track.getStops()) {
            gc.fillOval(stop.getX(), stop.getY(), 20, 20);
            gc.strokeOval(stop.getX(), stop.getY(), 20, 20);
        }
    }

    // Draw race cars on the track
    private void drawCars(GraphicsContext gc) {
        Color[] carColors = {Color.BLUE, Color.GREEN}; // Car colors
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            gc.setFill(carColors[i]);
            gc.fillOval(car.getX(), car.getY(), 20, 20);
        }
    }

    // Refresh the view by redrawing the track
    public void updateView() {
        drawTrack();
    }
}

 */

package model;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    private Track selectedTrack;
    private final List<Car> cars = new ArrayList<>();
    private String difficulty;

    public void setSelectedTrack(Track track) {
        this.selectedTrack = track;
    }

    public Track getSelectedTrack() {
        return selectedTrack;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}

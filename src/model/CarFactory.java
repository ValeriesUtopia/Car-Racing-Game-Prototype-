package model;

public class CarFactory {
    public static Car createCar(int id, Stop startStop, String wheelType, String engineType) {
        return new Car(id, startStop.getX(), startStop.getY(), wheelType, engineType);
    }
}

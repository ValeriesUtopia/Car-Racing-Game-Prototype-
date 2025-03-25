package model;

// package: model

import java.util.List;
import java.util.Random;

public class CarFactory {

    public static Car createCar(String name, String engineType, String tireType, List<Stop> route, String color) {
        Engine engine = switch (engineType) {
            case "V6" -> new Engine("V6", 20, 120);
            case "V8" -> new Engine("V8", 30, 150);
            default -> new Engine("Default", 25, 130);
        };

        Tire tire = switch (tireType) {
            case "Standard" -> new Tire("Standard", 0.85);
            case "Racing" -> new Tire("Racing", 0.65);
            default -> new Tire("Default", 0.75);
        };

        return new Car(name, engine, tire, route, color);
    }

    public static Car createRandomAICar(String name, List<Stop> route, String color) {
        String[] engineTypes = {"V6", "V8"};
        String[] tireTypes = {"Standard", "Racing"};
        Random rand = new Random();

        String engine = engineTypes[rand.nextInt(engineTypes.length)];
        String tire = tireTypes[rand.nextInt(tireTypes.length)];

        return createCar(name, engine, tire, route, color);
    }
}

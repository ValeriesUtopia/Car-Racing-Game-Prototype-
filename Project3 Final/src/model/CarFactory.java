// This class provides factory methods for creating Car objects in the racing game.
// It supports both specific user-selected car configurations and randomly generated AI cars.
// Author: Valeria Holland
package model;

import java.util.List;
import java.util.Random;

public class CarFactory {

    /**
     * Creates a Car object with specific engine and tire types selected by the player.
     *
     * @param name the car name (e.g., "Car 1")
     * @param engineType engine type as a string ("V6", "V8", or default)
     * @param tireType tire type as a string ("Standard", "Racing", or default)
     * @param route the list of stops this car will follow during the race
     * @param color the color used to represent the car in the UI
     * @return a configured Car object with the specified components
     */
    public static Car createCar(String name, String engineType, String tireType, List<Stop> route, String color) {
        // Select engine configuration based on input
        Engine engine = switch (engineType) {
            case "V6" -> new Engine("V6", 20, 120);       // Balanced engine
            case "V8" -> new Engine("V8", 30, 150);       // Faster acceleration and top speed
            default -> new Engine("Default", 25, 130);    // Fallback engine
        };

        // Select tire configuration based on input
        Tire tire = switch (tireType) {
            case "Standard" -> new Tire("Standard", 0.85); // High grip, less sliding
            case "Racing" -> new Tire("Racing", 0.65);     // Lower grip, higher speed
            default -> new Tire("Default", 0.75);          // Balanced fallback
        };

        return new Car(name, engine, tire, route, color);
    }

    /**
     * Creates an AI-controlled car with randomly selected engine and tire types.
     * Useful for simulating a second car or NPC in the game.
     *
     * @param name the AI car's name (e.g., "AI Bot")
     * @param route the list of stops the AI car will follow
     * @param color the color used to represent the car
     * @return a Car object with randomly selected configuration
     */
    public static Car createRandomAICar(String name, List<Stop> route, String color) {
        String[] engineTypes = {"V6", "V8"};
        String[] tireTypes = {"Standard", "Racing"};
        Random rand = new Random();

        String engine = engineTypes[rand.nextInt(engineTypes.length)];
        String tire = tireTypes[rand.nextInt(tireTypes.length)];

        return createCar(name, engine, tire, route, color);
    }
}


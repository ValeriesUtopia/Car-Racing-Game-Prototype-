package model;

import java.util.Random;

public class Tire {
    private final String type;
    private final double gripLevel;

    public Tire(String type, double gripLevel) {
        this.type = type;
        this.gripLevel = gripLevel;
    }

    public String getType() { return type; }
    public double getGripLevel() { return gripLevel; }

    public boolean willSlide(Random random) {
        return random.nextDouble() > gripLevel;
    }
}

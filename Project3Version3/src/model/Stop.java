package model;

public class Stop {
    private final String id;
    private final double x;
    private final double y;

    public Stop(String id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getId() { return id; }
    public double getX() { return x; }
    public double getY() { return y; }
}

/* 原来的版本
public class Stop {
    private double x;
    private double y;

    public Stop(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
}

 */

// Purpose: Main class to test the Car, PlayerCar, AiCar, Engine, Tire classes.
// Valerie Holland

public class Main {
    public static void main(String[] args) {
        Tire selectedTire = new Tire("Sport", 0.8);
        Engine selectedEngine = new Engine("V8", 500);
        PlayerCar playerCar = new PlayerCar(200, 200, 50, 0.05, 0, selectedTire, selectedEngine);
        AiCar aiCar = new AiCar(200, 250, 50, 0.1,Math.PI, 500);
        for (int i = 0; i < 20; i++) {
            playerCar.move();
            aiCar.move();
            System.out.printf("PlayerCar Position -> X: %.2f, Y: %.2f, Angle: %.2f%n", playerCar.getCenterX(), playerCar.getCenterY(), Math.toDegrees(playerCar.getAngle()));
            System.out.printf("AiCar Position -> X: %.2f, Y: %.2f, Angle: %.2f%n", aiCar.getCenterX(), aiCar.getCenterY(), Math.toDegrees(aiCar.getAngle()));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
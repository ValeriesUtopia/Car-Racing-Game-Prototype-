package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CarSelectionController extends Application {
    private String difficulty;
    private String user1Wheel, user1Engine;
    private String user2Wheel, user2Engine;

    public CarSelectionController(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public void start(Stage primaryStage) {
        // Title (Same as DifficultySelection)
        Text title = new Text("🚗 Select Your Car Setup");
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 32)); // Bold and italic font
        title.setFill(Color.DARKRED);

        // User 1 selection
        Text user1Text = new Text("User 1:");
        user1Text.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        user1Text.setFill(Color.DARKBLUE);

        ComboBox<String> wheelBox1 = new ComboBox<>();
        wheelBox1.getItems().addAll("Standard Wheels", "Carbon Fiber Wheels", "Alloy Wheels");
        wheelBox1.setValue("Standard Wheels");
        wheelBox1.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");  // Bigger text and larger width

        ComboBox<String> engineBox1 = new ComboBox<>();
        engineBox1.getItems().addAll("V8 Engine", "Electric Engine", "Turbo Engine");
        engineBox1.setValue("V8 Engine");
        engineBox1.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        // User 2 selection
        Text user2Text = new Text("User 2:");
        user2Text.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        user2Text.setFill(Color.DARKBLUE);

        ComboBox<String> wheelBox2 = new ComboBox<>();
        wheelBox2.getItems().addAll("Standard Wheels", "Carbon Fiber Wheels", "Alloy Wheels");
        wheelBox2.setValue("Standard Wheels");
        wheelBox2.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        ComboBox<String> engineBox2 = new ComboBox<>();
        engineBox2.getItems().addAll("V8 Engine", "Electric Engine", "Turbo Engine");
        engineBox2.setValue("V8 Engine");
        engineBox2.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        // Next button (Same as DifficultySelection)
        Button nextButton = new Button("Next");
        nextButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 8px 20px; -fx-border-radius: 5px;");

        // Hover effect (Same as DifficultySelection)
        // Add hover effect: change color on mouse hover
        nextButton.setOnMouseEntered(e -> nextButton.setStyle("-fx-background-color: #218838; -fx-text-fill: white; -fx-padding: 10px 20px;")); // Darker green
        nextButton.setOnMouseExited(e -> nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10px 20px;")); // Restore original color

        nextButton.setOnAction(e -> {
            // Store selections
            user1Wheel = wheelBox1.getValue();
            user1Engine = engineBox1.getValue();
            user2Wheel = wheelBox2.getValue();
            user2Engine = engineBox2.getValue();

            // Debugging output
            System.out.println("Difficulty: " + difficulty);
            System.out.println("User1 - Wheel: " + user1Wheel + ", Engine: " + user1Engine);
            System.out.println("User2 - Wheel: " + user2Wheel + ", Engine: " + user2Engine);

            // Show a confirmation window
            Stage confirmStage = new Stage();
            VBox confirmLayout = new VBox(10);
            confirmLayout.setAlignment(Pos.CENTER);
            confirmLayout.setStyle("-fx-padding: 20px;");

            Text confirmationText = new Text("Selections saved!\nRaceView is not implemented yet.");
            confirmationText.setFont(Font.font("Arial", 16));

            Button closeButton = new Button("Close");
            closeButton.setFont(Font.font("Arial", 14));
            closeButton.setOnAction(ev -> confirmStage.close());

            confirmLayout.getChildren().addAll(confirmationText, closeButton);
            Scene confirmScene = new Scene(confirmLayout, 300, 150);

            confirmStage.setScene(confirmScene);
            confirmStage.setTitle("Confirmation");
            confirmStage.show();
        });

        // Layout adjustments
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(50));
        layout.getChildren().addAll(title, user1Text, wheelBox1, engineBox1, user2Text, wheelBox2, engineBox2, nextButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #E6E6FA, #B0C4DE);");

        // Scene setup
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Selection");
        primaryStage.show();
    }
}


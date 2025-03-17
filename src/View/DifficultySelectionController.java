package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DifficultySelectionController extends Application {
    private String selectedDifficulty = "Easy"; // Default difficulty selection

    @Override
    public void start(Stage primaryStage) {
        // Welcome title
        Text welcomeTitle = new Text("🏁 Welcome to the Racing Game!");
        welcomeTitle.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 32)); // Bold and italic font
        welcomeTitle.setFill(Color.DARKRED); // Text color

        // Difficulty selection title
        Text difficultyTitle = new Text("Select Your Race Difficulty:");
        difficultyTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Bold font, larger size
        difficultyTitle.setFill(Color.DARKBLUE); // Text color

        // Create radio buttons for difficulty selection
        ToggleGroup difficultyGroup = new ToggleGroup();
        RadioButton easy = new RadioButton("Easy");
        RadioButton medium = new RadioButton("Medium");
        RadioButton challenging = new RadioButton("Challenging");

        // Add radio buttons to the toggle group to ensure only one can be selected at a time
        easy.setToggleGroup(difficultyGroup);
        medium.setToggleGroup(difficultyGroup);
        challenging.setToggleGroup(difficultyGroup);
        easy.setSelected(true); // Default selection

        // Set font size for difficulty options to improve readability
        easy.setStyle("-fx-font-size: 18px;");
        medium.setStyle("-fx-font-size: 18px;");
        challenging.setStyle("-fx-font-size: 18px;");

        // Listen for changes in selection
        difficultyGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            selectedDifficulty = ((RadioButton) newVal).getText();
        });

        // Create Next button
        Button nextButton = new Button("Next");
        nextButton.setFont(Font.font("Arial", FontWeight.BOLD, 18)); // Bold font for better visibility

        // Add hover effect: change color on mouse hover
        nextButton.setOnMouseEntered(e -> nextButton.setStyle("-fx-background-color: #218838; -fx-text-fill: white; -fx-padding: 10px 20px;")); // Darker green
        nextButton.setOnMouseExited(e -> nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10px 20px;")); // Restore original color
        nextButton.setOnAction(e -> {

            CarSelectionController carSelection = new CarSelectionController(selectedDifficulty);
            carSelection.start(new Stage());
            primaryStage.close(); // Close current window
        });

        // Improve layout spacing and styling
        VBox layout = new VBox(20); // Increased spacing for better readability
        layout.getChildren().addAll(welcomeTitle, difficultyTitle, easy, medium, challenging, nextButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #E6E6FA, #B0C4DE); -fx-padding: 50px;"); // Gradient background

        // Set up the scene
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Racing Game - Select Difficulty");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Launch JavaFX application
    }
}

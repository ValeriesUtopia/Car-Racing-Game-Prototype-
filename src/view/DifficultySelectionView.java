package view;

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
import java.util.function.Consumer;

/*
The View does not contain the Controller; it only exposes the setOnDifficultySelected() method,
which the Controller listens to as a callback.
The View handles the UI, while the Controller handles the logic—maintaining a clear separation of concerns.
After pressing the "Next" button, the View triggers the difficultySelectedListener,
and the Controller listens for this event to switch to CarSelectionController.
*/

public class DifficultySelectionView {
    private Stage primaryStage;
    private String selectedDifficulty = "Easy"; // Default difficulty
    private Consumer<String> difficultySelectedListener; // Listener

    public DifficultySelectionView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setOnDifficultySelected(Consumer<String> listener) {
        this.difficultySelectedListener = listener;
    }

    public void show() {
        // Title
        Text welcomeTitle = new Text("🏁 Welcome to the Racing Game!");
        welcomeTitle.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 32));
        welcomeTitle.setFill(Color.DARKRED);

        // Difficulty selection title
        Text difficultyTitle = new Text("Select Your Race Difficulty:");
        difficultyTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        difficultyTitle.setFill(Color.DARKBLUE);

        // Create radio buttons for difficulty selection
        ToggleGroup difficultyGroup = new ToggleGroup();
        RadioButton easy = new RadioButton("Easy");
        RadioButton medium = new RadioButton("Medium");
        RadioButton challenging = new RadioButton("Challenging");

        easy.setToggleGroup(difficultyGroup);
        medium.setToggleGroup(difficultyGroup);
        challenging.setToggleGroup(difficultyGroup);
        easy.setSelected(true);

        easy.setStyle("-fx-font-size: 18px;");
        medium.setStyle("-fx-font-size: 18px;");
        challenging.setStyle("-fx-font-size: 18px;");

        difficultyGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            selectedDifficulty = ((RadioButton) newVal).getText();
        });

        // "Next" button
        Button nextButton = new Button("Next");
        nextButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        // Add hover effect
        nextButton.setOnMouseEntered(e -> nextButton.setStyle("-fx-background-color: #218838; -fx-text-fill: white; -fx-padding: 10px 20px;"));
        nextButton.setOnMouseExited(e -> nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10px 20px;"));

        // Notify Controller upon button click
        nextButton.setOnAction(e -> {
            if (difficultySelectedListener != null) {
                difficultySelectedListener.accept(selectedDifficulty); // Trigger listener
            }
        });

        // Layout settings
        VBox layout = new VBox(20);
        layout.getChildren().addAll(welcomeTitle, difficultyTitle, easy, medium, challenging, nextButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #E6E6FA, #B0C4DE); -fx-padding: 50px;");

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Racing Game - Select Difficulty");
        primaryStage.show();
    }
}

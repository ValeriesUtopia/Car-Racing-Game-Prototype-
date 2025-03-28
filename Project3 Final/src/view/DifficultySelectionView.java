// This class represents the first screen (UI1) in the racing game.
// It allows the user to choose a difficulty level and preview each track visually.
// Once a selection is made and "Next" is clicked, it notifies the controller using a listener callback.
// Author: Jing Pan
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class DifficultySelectionView {

    private final Stage primaryStage;                    // Reference to the main stage (window)
    private String selectedDifficulty = "Easy";          // Default selected difficulty
    private Consumer<String> difficultySelectedListener; // Callback function (listener) for selection

    /**
     * Constructs the difficulty selection screen.
     *
     * @param primaryStage the JavaFX stage to render this view on
     */
    public DifficultySelectionView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Sets the listener to be triggered when a difficulty is selected and "Next" is clicked.
     *
     * @param listener callback that accepts selected difficulty as a string
     */
    public void setOnDifficultySelected(Consumer<String> listener) {
        this.difficultySelectedListener = listener;
    }

    /**
     * Displays the full difficulty selection UI.
     */
    public void show() {
        // === Title and Subtitle ===
        Text title = new Text("\uD83C\uDFC1 Select a Track to Begin");
        title.setFont(Font.font("Marlett", FontWeight.BOLD, FontPosture.ITALIC, 32));
        title.setFill(Color.DARKRED);

        Text subtitle = new Text("Please choose a difficulty level:");
        subtitle.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 22));
        subtitle.setFill(Color.DARKBLUE);

        // === Create Radio Buttons for Difficulty Selection ===
        ToggleGroup group = new ToggleGroup();

        // --- Easy Difficulty ---
        RadioButton easyBtn = new RadioButton("Easy");
        easyBtn.setToggleGroup(group);
        easyBtn.setSelected(true);
        easyBtn.setTextFill(Color.FORESTGREEN);
        easyBtn.setStyle("-fx-font-size: 16px ; -fx-font-weight: bold;");

        Label easyLabel = new Label("Circular track with smooth turns");
        easyLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 16));
        ImageView easyImg = new ImageView(new Image("file:resources/images/easy_preview.png"));
        easyImg.setFitWidth(280);
        easyImg.setPreserveRatio(true);
        VBox easyBox = new VBox(10, easyBtn, easyLabel, easyImg);
        easyBox.setAlignment(Pos.CENTER);
        easyBox.setPadding(new Insets(10));
        easyBox.setStyle("-fx-border-color: forestgreen; -fx-border-radius: 5;");

        // --- Medium Difficulty ---
        RadioButton medBtn = new RadioButton("Medium");
        medBtn.setToggleGroup(group);
        medBtn.setTextFill(Color.ORANGE);
        medBtn.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label medLabel = new Label("Zigzag track with moderate turns");
        medLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 16));
        ImageView medImg = new ImageView(new Image("file:resources/images/medium_preview.png"));
        medImg.setFitWidth(280);
        medImg.setPreserveRatio(true);
        VBox medBox = new VBox(10, medBtn, medLabel, medImg);
        medBox.setAlignment(Pos.CENTER);
        medBox.setPadding(new Insets(10));
        medBox.setStyle("-fx-border-color: orange; -fx-border-radius: 5;");

        // --- Challenge Difficulty ---
        RadioButton hardBtn = new RadioButton("Challenge");
        hardBtn.setToggleGroup(group);
        hardBtn.setTextFill(Color.CRIMSON);
        hardBtn.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label hardLabel = new Label("Expert track with sharp turns");
        hardLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 16));
        ImageView hardImg = new ImageView(new Image("file:resources/images/challenge_preview.png"));
        hardImg.setFitWidth(280);
        hardImg.setPreserveRatio(true);
        VBox hardBox = new VBox(10, hardBtn, hardLabel, hardImg);
        hardBox.setAlignment(Pos.CENTER);
        hardBox.setPadding(new Insets(10));
        hardBox.setStyle("-fx-border-color: crimson; -fx-border-radius: 5;");

        // === Update selection variable when user clicks ===
        group.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            selectedDifficulty = ((RadioButton) newVal).getText();
        });

        // === "Next" Button ===
        Button next = new Button("Next");
        next.setFont(Font.font("Marlett", FontWeight.BOLD, 16));
        next.setStyle("-fx-background-color: #E0E0E0; -fx-padding: 8 16; -fx-border-radius: 5;");
        next.setOnMouseEntered(e -> next.setStyle("-fx-background-color: #218838; -fx-text-fill: white; -fx-padding: 8 16;"));
        next.setOnMouseExited(e -> next.setStyle("-fx-background-color: #E0E0E0; -fx-padding: 8 16;"));

        // Notify the controller with the selected difficulty
        next.setOnAction(e -> {
            if (difficultySelectedListener != null) {
                difficultySelectedListener.accept(selectedDifficulty);
            }
        });

        // === Layout Setup ===
        HBox options = new HBox(30, easyBox, medBox, hardBox);
        options.setAlignment(Pos.CENTER);

        VBox layout = new VBox(30, title, subtitle, options, next);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle(
                "-fx-background-image: url('file:resources/images/racerMain.jpg'); " +
                        "-fx-background-size: cover; " +
                        "-fx-background-position: center;"
        );

        Scene scene = new Scene(layout, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Racing Game - Difficulty Selection");
        primaryStage.show();
    }
}

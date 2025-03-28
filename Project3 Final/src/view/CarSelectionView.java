// This class represents the Car Selection Screen (UI2) in the racing game.
// It allows both players to select their wheel and engine types before starting the race.
// It also displays a preview of the track based on the selected difficulty.
// Author: Jing Pan

package view;

import controller.CarSelectionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CarSelectionView {

    private final Stage primaryStage;                     // Reference to the main application window
    private final CarSelectionController controller;      // Controller handling UI logic and navigation
    private final Image MainImage = new Image("file:resources/images/MainScreen.jpg");  // Background image

    /**
     * Constructor to initialize the view with stage and controller.
     */
    public CarSelectionView(Stage primaryStage, CarSelectionController controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;
    }

    /**
     * Displays the car selection screen with UI elements for both players.
     *
     * @param selectedDifficulty the selected difficulty (used to display the correct track preview)
     */
    public void show(String selectedDifficulty) {

        // === Title ===
        Text title = new Text(" \uD83D\uDE98 Select Your Car Setup");
        title.setFont(Font.font("Marlett", FontWeight.BOLD, 32));
        title.setFill(Color.DARKRED);

        // === Player 1 Settings ===
        Text user1Text = new Text("Player 1:");
        user1Text.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 24));
        user1Text.setFill(Color.DARKBLUE);

        ComboBox<String> wheelBox1 = new ComboBox<>();
        wheelBox1.getItems().addAll("Standard", "Carbon", "Alloy");
        wheelBox1.setValue("Standard");
        wheelBox1.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        ComboBox<String> engineBox1 = new ComboBox<>();
        engineBox1.getItems().addAll("V6", "V8", "Turbo");
        engineBox1.setValue("V6");
        engineBox1.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        VBox user1Box = new VBox(10, user1Text, wheelBox1, engineBox1);
        user1Box.setAlignment(Pos.CENTER);

        // === Player 2 Settings ===
        Text user2Text = new Text("Player 2 :");
        user2Text.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 24));
        user2Text.setFill(Color.DARKBLUE);

        ComboBox<String> wheelBox2 = new ComboBox<>();
        wheelBox2.getItems().addAll("Standard", "Carbon", "Alloy");
        wheelBox2.setValue("Standard");
        wheelBox2.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        ComboBox<String> engineBox2 = new ComboBox<>();
        engineBox2.getItems().addAll("V6", "V8", "Turbo");
        engineBox2.setValue("V8");
        engineBox2.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        VBox user2Box = new VBox(10, user2Text, wheelBox2, engineBox2);
        user2Box.setAlignment(Pos.CENTER);

        HBox usersBox = new HBox(80, user1Box, user2Box);
        usersBox.setAlignment(Pos.CENTER);

        // === Map Preview Based on Selected Difficulty ===
        String imagePath = switch (selectedDifficulty) {
            case "Easy" -> "file:resources/images/easy_preview.png";
            case "Medium" -> "file:resources/images/medium_preview.png";
            case "Challenge" -> "file:resources/images/challenge_preview.png";
            default -> null;
        };

        ImageView mapPreview = new ImageView();
        if (imagePath != null) {
            mapPreview.setImage(new Image(imagePath));
            mapPreview.setFitWidth(300);
            mapPreview.setPreserveRatio(true);
        }

        Text mapLabel = new Text("You have picked:  " + selectedDifficulty + " track");
        mapLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 20));
        mapLabel.setFill(Color.RED);

        VBox mapBox = new VBox(10, mapLabel, mapPreview);
        mapBox.setAlignment(Pos.CENTER);

        // === Back Button ===
        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Marlett", FontWeight.BOLD, 16));
        backButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-padding: 8 16;");
        backButton.setFocusTraversable(false);

        backButton.setOnAction(e -> controller.handleBack());

        backButton.setOnMouseEntered(e ->
                backButton.setStyle("-fx-background-color: #c82333; -fx-text-fill: white; -fx-padding: 8 16;"));
        backButton.setOnMouseExited(e ->
                backButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-padding: 8 16;"));

        // === Next Button ===
        Button nextButton = new Button("Next");
        nextButton.setFont(Font.font("Marlett", FontWeight.BOLD, 16));
        nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 8 16;");
        nextButton.setFocusTraversable(false);

        nextButton.setOnMouseEntered(e ->
                nextButton.setStyle("-fx-background-color: #00cc66; -fx-text-fill: white; -fx-padding: 8 16;"));
        nextButton.setOnMouseExited(e ->
                nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 8 16;"));

        nextButton.setOnAction(e -> {
            String user1Wheel = wheelBox1.getValue();
            String user1Engine = engineBox1.getValue();
            String user2Wheel = wheelBox2.getValue();
            String user2Engine = engineBox2.getValue();
            controller.handleSelection(user1Engine, user1Wheel, user2Engine, user2Wheel);
        });

        HBox buttonBox = new HBox(30, backButton, nextButton);
        buttonBox.setAlignment(Pos.CENTER);

        // === Final Layout ===
        ImageView mainImageView = new ImageView(MainImage);
        VBox layout = new VBox(mainImageView, title, usersBox, mapBox, buttonBox);
        layout.setSpacing(40);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle(
                "-fx-background-image: url('file:resources/images/racerMain.jpg'); " +
                        "-fx-background-size: cover; " +
                        "-fx-background-position: center;"
        );

        Scene scene = new Scene(layout, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Selection");
        primaryStage.show();
    }
}

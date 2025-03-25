package view;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private final Stage primaryStage;
    private String selectedDifficulty = "Easy";
    private Consumer<String> difficultySelectedListener;

    public DifficultySelectionView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setOnDifficultySelected(Consumer<String> listener) {
        this.difficultySelectedListener = listener;
    }

    public void show() {
        // Title
        Text title = new Text("\uD83C\uDFC1 Select a Track to Begin");
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 32));
        title.setFill(Color.DARKRED);

        Text subtitle = new Text("Please choose a difficulty level:");
        subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        subtitle.setFill(Color.DARKBLUE);

        // ToggleGroup for difficulty
        ToggleGroup group = new ToggleGroup();

        // Easy option
        RadioButton easyBtn = new RadioButton("Easy");
        easyBtn.setToggleGroup(group);
        easyBtn.setSelected(true);
        easyBtn.setTextFill(Color.FORESTGREEN);
        easyBtn.setStyle("-fx-font-size: 16px;");

        Label easyLabel = new Label("Circular track with smooth turns");
        easyLabel.setStyle("-fx-font-size: 16px;");

        ImageView easyImg = new ImageView(new Image("file:resources/images/easy_preview.png"));
        easyImg.setFitWidth(280); // 图片变大
        easyImg.setPreserveRatio(true);
        VBox easyBox = new VBox(10, easyBtn, easyLabel, easyImg);
        easyBox.setAlignment(Pos.CENTER);
        easyBox.setPadding(new Insets(10));
        easyBox.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5;");

        // Medium option
        RadioButton medBtn = new RadioButton("Medium");
        medBtn.setToggleGroup(group);
        medBtn.setTextFill(Color.ORANGE);
        medBtn.setStyle("-fx-font-size: 16px;");

        Label medLabel = new Label("Zigzag track with moderate turns");
        medLabel.setStyle("-fx-font-size: 16px;");

        ImageView medImg = new ImageView(new Image("file:resources/images/medium_preview.png"));
        medImg.setFitWidth(280);
        medImg.setPreserveRatio(true);
        VBox medBox = new VBox(10, medBtn, medLabel, medImg);
        medBox.setAlignment(Pos.CENTER);
        medBox.setPadding(new Insets(10));
        medBox.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5;");

        // Challenge option
        RadioButton hardBtn = new RadioButton("Challenge");
        hardBtn.setToggleGroup(group);
        hardBtn.setTextFill(Color.CRIMSON);
        hardBtn.setStyle("-fx-font-size: 16px;");

        Label hardLabel = new Label("Expert track with sharp turns");
        hardLabel.setStyle("-fx-font-size: 16px;");

        ImageView hardImg = new ImageView(new Image("file:resources/images/challenge_preview.png"));
        hardImg.setFitWidth(280); // 图片变大
        hardImg.setPreserveRatio(true);
        VBox hardBox = new VBox(10, hardBtn, hardLabel, hardImg);
        hardBox.setAlignment(Pos.CENTER);
        hardBox.setPadding(new Insets(10));
        hardBox.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5;");

        // Listen to selection
        group.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            selectedDifficulty = ((RadioButton) newVal).getText();
        });

        // Next button
        Button next = new Button("Next");
        next.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        next.setStyle("-fx-background-color: #E0E0E0; -fx-padding: 8 16; -fx-border-radius: 5;");
        next.setOnMouseEntered(e -> next.setStyle("-fx-background-color: #218838; -fx-text-fill: white; -fx-padding: 8 16;"));
        next.setOnMouseExited(e -> next.setStyle("-fx-background-color: #E0E0E0; -fx-padding: 8 16;"));

        next.setOnAction(e -> {
            if (difficultySelectedListener != null) {
                difficultySelectedListener.accept(selectedDifficulty);
                
            }
        });

        HBox options = new HBox(30, easyBox, medBox, hardBox);
        options.setAlignment(Pos.CENTER);

        VBox layout = new VBox(30, title, subtitle, options, next);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #E6E6FA, #B0C4DE);");

        //layout.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f8ff, #cce0f5);");

        Scene scene = new Scene(layout, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Racing Game - Difficulty Selection");
        primaryStage.show();
    }
}




/*
The View does not contain the Controller; it only exposes the setOnDifficultySelected() method,
which the Controller listens to as a callback.
The View handles the UI, while the Controller handles the logic—maintaining a clear separation of concerns.
After pressing the "Next" button, the View triggers the difficultySelectedListener,
and the Controller listens for this event to switch to CarSelectionController.
*/
/*
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

 */

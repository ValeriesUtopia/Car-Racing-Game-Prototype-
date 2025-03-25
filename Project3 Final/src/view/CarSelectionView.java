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
    private final Stage primaryStage;
    private final CarSelectionController controller;

    public CarSelectionView(Stage primaryStage, CarSelectionController controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;
    }

    public void show(String selectedDifficulty) {
        // Title

        Text title = new Text(" \uD83D\uDE98 Select Your Car Setup");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setFill(Color.DARKRED);


        // === User 1 ===
        Text user1Text = new Text("User 1:");
        user1Text.setFont(Font.font("Arial", FontWeight.BOLD, 24));
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

        // === User 2 ===
        Text user2Text = new Text("User 2:");
        user2Text.setFont(Font.font("Arial", FontWeight.BOLD, 24));
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

        // === Map Preview ===
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
        mapLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        mapLabel.setFill(Color.DARKSLATEBLUE);

        VBox mapBox = new VBox(10, mapLabel, mapPreview);
        mapBox.setAlignment(Pos.CENTER);

        // === Buttons ===
        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-padding: 8 16;");
        backButton.setOnAction(e -> controller.handleBack());

        backButton.setFocusTraversable(false); // 可选：防止按 Tab 聚焦按钮

        // Hover 效果
        backButton.setOnMouseEntered(e ->
                backButton.setStyle("-fx-background-color: #c82333; -fx-text-fill: white; -fx-padding: 8 16;"));

        backButton.setOnMouseExited(e ->
                backButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-padding: 8 16;"));

        Button nextButton = new Button("Next");
        nextButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 8 16;");
        nextButton.setOnAction(e -> {
            String user1Wheel = wheelBox1.getValue();
            String user1Engine = engineBox1.getValue();
            String user2Wheel = wheelBox2.getValue();
            String user2Engine = engineBox2.getValue();
            controller.handleSelection(user1Engine, user1Wheel, user2Engine, user2Wheel);
        });
        nextButton.setFocusTraversable(false);
        nextButton.setOnMouseEntered(e -> nextButton.setStyle("-fx-background-color: #00cc66; -fx-text-fill: white; -fx-padding: 8 16;"));
        nextButton.setOnMouseExited(e -> nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 8 16;"));


        HBox buttonBox = new HBox(30, backButton, nextButton);
        buttonBox.setAlignment(Pos.CENTER);

        // === Final Layout ===
        VBox layout = new VBox(40, title, usersBox, mapBox, buttonBox);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #E6E6FA, #B0C4DE);");

        Scene scene = new Scene(layout, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Selection");
        primaryStage.show();
    }
}


/* 可以显示版本
package view;

import controller.CarSelectionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CarSelectionView {
    private final Stage primaryStage;
    private final CarSelectionController controller;

    public CarSelectionView(Stage primaryStage, CarSelectionController controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;
    }

    public void show() {
        // Title
        Text title = new Text("\uD83C\uDFCE Select Your Car Setup");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setFill(Color.DARKRED);

        // User 1
        Text user1Text = new Text("User 1:");
        user1Text.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        user1Text.setFill(Color.DARKBLUE);

        ComboBox<String> wheelBox1 = new ComboBox<>();
        wheelBox1.getItems().addAll("Standard", "Carbon", "Alloy");
        wheelBox1.setValue("Standard");
        wheelBox1.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        ComboBox<String> engineBox1 = new ComboBox<>();
        engineBox1.getItems().addAll("V6", "V8", "Turbo");
        engineBox1.setValue("V6");
        engineBox1.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        // User 2
        Text user2Text = new Text("User 2:");
        user2Text.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        user2Text.setFill(Color.DARKBLUE);

        ComboBox<String> wheelBox2 = new ComboBox<>();
        wheelBox2.getItems().addAll("Standard", "Carbon", "Alloy");
        wheelBox2.setValue("Standard");
        wheelBox2.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        ComboBox<String> engineBox2 = new ComboBox<>();
        engineBox2.getItems().addAll("V6", "V8", "Turbo");
        engineBox2.setValue("V8");
        engineBox2.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        // Next button
        Button nextButton = new Button("Next");
        nextButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10px 20px;");

        nextButton.setOnMouseEntered(e -> nextButton.setStyle("-fx-background-color: #218838; -fx-text-fill: white; -fx-padding: 10px 20px;"));
        nextButton.setOnMouseExited(e -> nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10px 20px;"));

        nextButton.setOnAction(e -> {
            String user1Wheel = wheelBox1.getValue();
            String user1Engine = engineBox1.getValue();
            String user2Wheel = wheelBox2.getValue();
            String user2Engine = engineBox2.getValue();

            controller.handleSelection(user1Engine, user1Wheel, user2Engine, user2Wheel);
        });

        // Layout
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(50));
        layout.getChildren().addAll(title, user1Text, wheelBox1, engineBox1, user2Text, wheelBox2, engineBox2, nextButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #E6E6FA, #B0C4DE);");

        // Set scene
        Scene scene = new Scene(layout, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Selection");
        primaryStage.show();
    }
}

 */


/*
public class CarSelectionView {
    private Stage primaryStage;
    private CarSelectionController controller;
    private String difficulty;
    private String user1Wheel, user1Engine;
    private String user2Wheel, user2Engine;

    public CarSelectionView(Stage primaryStage, CarSelectionController controller, String difficulty) {
        this.primaryStage = primaryStage;
        this.controller = controller;
        this.difficulty = difficulty; // difficulty parsed
    }

    public void show() {
        // title
        Text title = new Text("🚗 Select Your Car Setup");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setFill(Color.DARKRED);

        // user1
        Text user1Text = new Text("User 1:");
        user1Text.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        user1Text.setFill(Color.DARKBLUE);

        ComboBox<String> wheelBox1 = new ComboBox<>();
        wheelBox1.getItems().addAll("Standard Wheels", "Carbon Fiber Wheels", "Alloy Wheels");
        wheelBox1.setValue("Standard Wheels");
        wheelBox1.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        ComboBox<String> engineBox1 = new ComboBox<>();
        engineBox1.getItems().addAll("V8 Engine", "Electric Engine", "Turbo Engine");
        engineBox1.setValue("V8 Engine");
        engineBox1.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        // user2
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

        // Next button
        Button nextButton = new Button("Next");
        nextButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10px 20px;");

        // button hover
        nextButton.setOnMouseEntered(e -> nextButton.setStyle("-fx-background-color: #218838; -fx-text-fill: white; -fx-padding: 10px 20px;"));
        nextButton.setOnMouseExited(e -> nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10px 20px;"));

        nextButton.setOnAction(e -> {
            user1Wheel = wheelBox1.getValue();
            user1Engine = engineBox1.getValue();
            user2Wheel = wheelBox2.getValue();
            user2Engine = engineBox2.getValue();

            // parse 4 parameters
            controller.onCarSelectionCompleted(user1Wheel, user1Engine, user2Wheel, user2Engine);
        });

        // layout
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(50));
        layout.getChildren().addAll(title, user1Text, wheelBox1, engineBox1, user2Text, wheelBox2, engineBox2, nextButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #E6E6FA, #B0C4DE);");

        // set scene
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Selection");
        primaryStage.show();
    }
}


/*
public class CarSelectionView {
    private Stage primaryStage;
    private CarSelectionController controller;
    private String difficulty;
    private String user1Wheel, user1Engine;
    private String user2Wheel, user2Engine;

    public CarSelectionView(Stage primaryStage, CarSelectionController controller, String difficulty) {
        this.primaryStage = primaryStage;
        this.controller = controller;
        this.difficulty = difficulty; // difficulty parsed
    }

    public void show() {
        // title
        Text title = new Text("🚗 Select Your Car Setup");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setFill(Color.DARKRED);

        // user1
        Text user1Text = new Text("User 1:");
        user1Text.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        user1Text.setFill(Color.DARKBLUE);

        ComboBox<String> wheelBox1 = new ComboBox<>();
        wheelBox1.getItems().addAll("Standard Wheels", "Carbon Fiber Wheels", "Alloy Wheels");
        wheelBox1.setValue("Standard Wheels");
        wheelBox1.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        ComboBox<String> engineBox1 = new ComboBox<>();
        engineBox1.getItems().addAll("V8 Engine", "Electric Engine", "Turbo Engine");
        engineBox1.setValue("V8 Engine");
        engineBox1.setStyle("-fx-font-size: 18px; -fx-pref-width: 220px;");

        // user2
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

        // Next button
        Button nextButton = new Button("Next");
        nextButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10px 20px;");

        // button hover
        nextButton.setOnMouseEntered(e -> nextButton.setStyle("-fx-background-color: #218838; -fx-text-fill: white; -fx-padding: 10px 20px;"));
        nextButton.setOnMouseExited(e -> nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10px 20px;"));

        nextButton.setOnAction(e -> {
            user1Wheel = wheelBox1.getValue();
            user1Engine = engineBox1.getValue();
            user2Wheel = wheelBox2.getValue();
            user2Engine = engineBox2.getValue();

            // parse 4 parameters
            controller.onCarSelectionCompleted(user1Wheel, user1Engine, user2Wheel, user2Engine);
        });

        // layout
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(50));
        layout.getChildren().addAll(title, user1Text, wheelBox1, engineBox1, user2Text, wheelBox2, engineBox2, nextButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #E6E6FA, #B0C4DE);");

        // set scene
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Selection");
        primaryStage.show();
    }
}

 */

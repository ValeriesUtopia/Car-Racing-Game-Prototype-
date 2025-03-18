package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import model.*;
import java.util.List;


public class RaceView extends BorderPane {
    private TimerPanel timerPanel;      // Timer UI
    private ScorePanel scorePanel;      // Score UI
    private RaceTrackPanel trackPanel;  // Race track UI

    private Button startButton, pauseButton, restartButton; // Control buttons

    public RaceView(Track track, List<Car> cars) {
        // Initialize UI components
        this.timerPanel = new TimerPanel();
        this.scorePanel = new ScorePanel();
        this.trackPanel = new RaceTrackPanel(track, cars); // Pass cars to RaceTrackPanel

        // Initialize control buttons
        startButton = new Button("Start");
        pauseButton = new Button("Pause");
        restartButton = new Button("Restart");

        // Bind button actions
        startButton.setOnAction(e -> timerPanel.startTimer());
        pauseButton.setOnAction(e -> timerPanel.stopTimer());
        restartButton.setOnAction(e -> timerPanel.resetTimer());

        // Set up the right-side control panel
        VBox controlPanel = new VBox(15);
        controlPanel.getChildren().addAll(timerPanel, scorePanel, startButton, pauseButton, restartButton);
        controlPanel.setAlignment(Pos.CENTER);
        controlPanel.setPrefWidth(200);

        // Layout configuration
        this.setRight(controlPanel); // Timer & buttons on the right
        this.setCenter(trackPanel);  // Race track in the center
    }

    // Method to update the race track view
    public void updateRaceTrack() {
        trackPanel.updateView();
    }

    // Method to update the scoreboard
    public void displayScore(String user1Name, int user1Time, int user1Rank, int user1Score,
                             String user2Name, int user2Time, int user2Rank, int user2Score) {
        scorePanel.updateScoreBoard(user1Name, user1Time, user1Rank, user1Score,
                user2Name, user2Time, user2Rank, user2Score);
    }
}

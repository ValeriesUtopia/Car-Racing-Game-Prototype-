package view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ScorePanel extends VBox {
    private Label statusLabel;

    public ScorePanel() {
        statusLabel = new Label("Race in progress...");
        statusLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        this.getChildren().add(statusLabel);
        this.setStyle("-fx-padding: 10px; -fx-alignment: center;");
    }

    /**
     * Updates the final scores, called after the race ends.
     *
     * @param user1Name Player 1 name
     * @param user1Time Player 1 time (seconds)
     * @param user1Rank Player 1 rank
     * @param user1Score Player 1 score
     * @param user2Name Player 2 name
     * @param user2Time Player 2 time (seconds)
     * @param user2Rank Player 2 rank
     * @param user2Score Player 2 score
     */
    public void updateScoreBoard(String user1Name, int user1Time, int user1Rank, int user1Score,
                                 String user2Name, int user2Time, int user2Rank, int user2Score) {
        // Construct the race results text
        String finalResult = "Final Results:\n" +
                user1Name + " - Time: " + user1Time + "s, Rank: " + user1Rank + ", Score: " + user1Score + "\n" +
                user2Name + " - Time: " + user2Time + "s, Rank: " + user2Rank + ", Score: " + user2Score;
        // Update the status label text
        statusLabel.setText(finalResult);
    }

    /**
     * Retrieves the current status text (for debugging or other purposes).
     */
    public String getStatusText() {
        return statusLabel.getText();
    }
}

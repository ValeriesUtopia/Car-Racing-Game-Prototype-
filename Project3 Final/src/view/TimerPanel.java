package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * TimerPanel handles the race timer, allowing start, stop, and reset functionality.
 */
/*
public class TimerPanel extends VBox {
    private Label timerLabel;  // Label to display the timer
    private Timeline timeline; // Timeline for handling timer updates
    private int timeInSeconds; // Track elapsed time

    public TimerPanel() {
        // Initialize the timer label
        timerLabel = new Label("Time: 0s");
        timeInSeconds = 0;

        // Set up the timeline (runs every second)
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTime()));
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Style and add components
        this.getChildren().add(timerLabel);
        this.setStyle("-fx-padding: 10px; -fx-alignment: center;");
    }

    public void startTimer() {
        if (timeline.getStatus() != Timeline.Status.RUNNING) {
            timeline.play();
        }
    }

    public void stopTimer() {
        timeline.pause();
    }

    public void resetTimer() {
        timeline.stop();
        timeInSeconds = 0;
        timerLabel.setText("Time: 0s");
    }


    private void updateTime() {
        timeInSeconds++;
        timerLabel.setText("Time: " + timeInSeconds + "s");
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }
}

 */


// This class controls background music playback for the car racing game.
// It wraps around JavaFX's MediaPlayer and provides methods to play, pause,
// stop, reset, and simulate fade-in/fade-out effects.
// Author: Valeria Holland

package controller;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioController {
    private MediaPlayer mediaPlayer; // JavaFX media player instance

    /**
     * Constructor that initializes the media player with the provided audio file path.
     * Currently commented out — uncomment to enable automatic loading and playback.
     *
     * @param musicFile the file path to the music (e.g., "resources/audio/music.mp3")
     */
    public AudioController(String musicFile) {
        /*
        try {
            // Create media from file
            Media sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);

            // Automatically start playback when ready
            mediaPlayer.setOnReady(() -> {
                System.out.println("Playing Sound...");
                mediaPlayer.play();
            });

            // Log error if media fails to load
            mediaPlayer.setOnError(() -> {
                System.out.println("Error: " + mediaPlayer.getError());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }

    /**
     * Stops the music playback immediately.
     */
    public void stop() {
        mediaPlayer.stop();
    }

    /**
     * Starts or resumes music playback.
     */
    public void play() {
        mediaPlayer.play();
    }

    /**
     * Pauses the music without resetting its position.
     */
    public void pause() {
        mediaPlayer.pause();
    }

    /**
     * Resets the music playback to the beginning.
     */
    public void reset() {
        mediaPlayer.seek(mediaPlayer.getStartTime());
    }

    /**
     * Simulates a fade-in effect by gradually increasing volume from 0 to full.
     * Note: This uses a tight loop and may not produce a smooth visual fade-in without delays.
     */
    public void fadeIn() {
        mediaPlayer.setVolume(0);
        mediaPlayer.play();
        for (int i = 0; i < 100; i++) {
            mediaPlayer.setVolume(i / 100.0);
        }
    }

    /**
     * Simulates a fade-out effect by decreasing volume from full to 0, then stopping playback.
     * Note: Without timing delays, this happens nearly instantly.
     */
    public void fadeOut() {
        for (int i = 100; i > 0; i--) {
            mediaPlayer.setVolume(i / 100.0);
        }
        mediaPlayer.stop();
    }
}


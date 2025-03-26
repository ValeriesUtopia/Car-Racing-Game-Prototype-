package controller;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioController {
    private MediaPlayer mediaPlayer;
    //private boolean isSoundPlaying = false;
    public AudioController(String musicFile) {
        /*try {
            Media sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnReady(() -> {
                System.out.println("Playing Sound...");
                mediaPlayer.play();
            });
            mediaPlayer.setOnError(() -> {
                System.out.println("Error: " + mediaPlayer.getError());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void stop() {
        mediaPlayer.stop();
    }
    public void play() {
        mediaPlayer.play();
    }
    public void pause() {
        mediaPlayer.pause();
    }
    public void reset() {
        mediaPlayer.seek(mediaPlayer.getStartTime());
    }
    public void fadeIn() {
        mediaPlayer.setVolume(0);
        mediaPlayer.play();
        for (int i = 0; i < 100; i++) {
            mediaPlayer.setVolume(i / 100.0);
        }
    }
    public void fadeOut() {
        for (int i = 100; i > 0; i--) {
            mediaPlayer.setVolume(i / 100.0);
        }
        mediaPlayer.stop();
    }
}

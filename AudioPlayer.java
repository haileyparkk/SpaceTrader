package mediaPlayers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * plays sound for the game
 * @author Andrew Harris, Joseph Lukemire
 * @version 1.0
 */
public class AudioPlayer {
    private static File amazingGrace = new File("music/AmazingGrace.mp3");
    private static Media musicSound = new Media(amazingGrace.toURI().toString());
    private static MediaPlayer musicPlayer = new MediaPlayer(musicSound);

    public static void startMusic() {
        musicPlayer.play();
        musicPlayer.setVolume(0.1);
        //  musicPlayer.setStartTime(Duration.seconds(28)); ->
        //  this was the start time for amazing grace
    }

    public static void muteMusic() {
        musicPlayer.pause();
    }

    public static void unmuteMusic() {
        musicPlayer.play();
    }

    public static boolean getMusicPlaying() {
        return (musicPlayer.getStatus() == MediaPlayer.Status.PLAYING);
    }
}

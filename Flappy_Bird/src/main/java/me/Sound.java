package me;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    // Make an array of 4 URLs, each containing the path of one sound
    URL[] sounds = new URL[4];
    Clip clip;

    Sound() {
        sounds[0] = getClass().getResource("/sounds/flap.wav");
        sounds[1] = getClass().getResource("/sounds/hit_sound.wav");
        sounds[2] = getClass().getResource("/sounds/point.wav");
        sounds[3] = getClass().getResource("/sounds/background_music.wav");
    }

    // Select which sound should be played by assigning the clip variable to it
    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sounds[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-20f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Play that clip
    public void play() {
        clip.start();
    }

    // Stop playing the clip
    public void stop() {
        clip.stop();
    }

    // Play the clip indefinitely
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}

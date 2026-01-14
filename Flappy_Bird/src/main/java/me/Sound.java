package me;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    URL[] sounds = new URL[4];
    Clip clip;

    Sound() {
        sounds[0] = getClass().getResource("/sounds/flap.wav");
        sounds[1] = getClass().getResource("/sounds/hit_sound.wav");
        sounds[2] = getClass().getResource("/sounds/point.wav");
        sounds[3] = getClass().getResource("/sounds/background_music.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sounds[i]);
            clip = AudioSystem.getClip();
//            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//            control.setValue(0.1f);
            clip.open(ais);
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-20f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}

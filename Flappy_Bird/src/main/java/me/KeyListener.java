package me;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
public class KeyListener implements EventHandler<KeyEvent> {

    // Count the times escape is being pressed
    private static int escapeCounter = 0;

    // Get a Sound class instance
    private static final Sound sound = new Sound();

    @Override
    public void handle(KeyEvent e) {

        // If you hit space make the bird jump and play a flapping sound
        if (e.getCode() == KeyCode.SPACE) {
            GameLogic.setYVelocity(-300.0);
            sound.setFile(0);
            sound.play();
        }

        // If you hit escape show the pause screen, hit it again to get out of the pause screen again
        if (e.getCode() == KeyCode.ESCAPE) {
            if (!GameLogic.getDead()) {
                if (escapeCounter % 2 == 0) {
                    GameLogic.showPauseScreen();
                } else {
                    GameLogic.hidePauseScreen();
                }
                escapeCounter++;
            }
        }
    }

    // Set the escape counter
    public static void setEscapeCounter(int newValue) {
        escapeCounter = newValue;
    }
}

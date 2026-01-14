package me;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
public class KeyListener implements EventHandler<KeyEvent> {

    private static int escapeCounter = 0;
    private static final Sound sound = new Sound();

    @Override
    public void handle(KeyEvent e) {
        if (e.getCode() == KeyCode.SPACE) {
            GameLogic.setYVelocity(-300.0);
            sound.setFile(0);
            sound.play();
        }
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

    public static void setEscapeCounter(int newValue) {
        escapeCounter = newValue;
    }
}

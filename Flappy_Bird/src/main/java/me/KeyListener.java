package me;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
public class KeyListener implements EventHandler<KeyEvent> {

    private static int escapeCounter = 0;

    @Override
    public void handle(KeyEvent e) {
        if (e.getCode() == KeyCode.SPACE) {
            GameLogic.setYVelocity(-300.0);
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

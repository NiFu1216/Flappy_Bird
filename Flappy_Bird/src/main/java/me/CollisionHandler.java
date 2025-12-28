package me;

import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class CollisionHandler {

    public static void checkCollision() throws IOException {
        Rectangle[] rects = new Rectangle[(Pipe.getPipes().length * 2)];
        for (int i = 0; i < Pipe.getPipes().length; i++) {
            rects[2 * i] = new Rectangle(Pipe.getPipes()[i].getXPos(), 0, Pipe.getPipeWidth(), Pipe.getPipes()[i].getHeight());
            rects[2 * i + 1] = new Rectangle(Pipe.getPipes()[i].getXPos(), Pipe.getPipes()[i].getHeight() + Pipe.getPipeGap(), Pipe.getPipeWidth(), SceneSelector.getSceneHeight() - Pipe.getPipes()[i].getHeight() - Pipe.getPipeGap() - GameLogic.getFloorHeight());
        }
        for (Rectangle rect : rects) {
            if (rect.intersects(GameLogic.getXPos(), GameLogic.getYPos(), GameLogic.getBirdWidth(), GameLogic.getBirdHeight())) {
                GameLogic.gameOver();
            }
        }
        if (GameLogic.getYPos() + GameLogic.getBirdHeight() >= SceneSelector.getSceneHeight() - GameLogic.getFloorHeight()) {
            GameLogic.gameOver();
        }
    }
}

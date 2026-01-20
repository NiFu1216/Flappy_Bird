package me;

import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class CollisionHandler {

    // Get a Sound class instance
    private static final Sound sound = new Sound();

    public static void checkCollision() throws IOException {

        // Make an array that contains all pipes as rectangles to check if they hit the bird
        Rectangle[] rects = new Rectangle[(Pipe.getPipes().length * 2)];
        for (int i = 0; i < Pipe.getPipes().length; i++) {
            rects[2 * i] = new Rectangle(Pipe.getPipes()[i].getXPos(), 0, Pipe.getPipeWidth(), Pipe.getPipes()[i].getHeight());
            rects[2 * i + 1] = new Rectangle(Pipe.getPipes()[i].getXPos(), Pipe.getPipes()[i].getHeight() + Pipe.getPipeGap(), Pipe.getPipeWidth(), SceneSelector.getSceneHeight() - Pipe.getPipes()[i].getHeight() - Pipe.getPipeGap() - GameLogic.getFloorHeight());
        }

        // Check for intersection with pipes
        for (Rectangle rect : rects) {
            if (rect.intersects(GameLogic.getXPos(), GameLogic.getYPos(), GameLogic.getBirdWidth(), GameLogic.getBirdHeight())) {
                sound.setFile(1);
                sound.play();
                GameLogic.gameOver();
            }
        }

        // Check for intersection with ground
        if (GameLogic.getYPos() + GameLogic.getBirdHeight() >= SceneSelector.getSceneHeight() - GameLogic.getFloorHeight()) {
            sound.setFile(1);
            sound.play();
            GameLogic.gameOver();
        }
    }
}

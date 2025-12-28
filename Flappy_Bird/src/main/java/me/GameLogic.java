package me;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.*;
import java.util.Scanner;

public class GameLogic {

    private static int score;
    private static double yVelocity;
    private static final int BIRD_WIDTH = 68;
    private static final int BIRD_HEIGHT = 48;
    private static final int xPos = 100;
    private static double yPos;
    private static long last_update = 0;
    private static final double UPDATE_INTERVAL = 1_000_000_000.0 / 60.0;
    private static final int MAX_UPDATES = 5;
    private static final double GRAVITY = 1000.0;
    private static int pipeSpeed;
    private static final int FLOOR_HEIGHT = 100;
    private static double pipeCreationDelay;
    private static double accumulator = 0;
    private static final double dt = (1.0 / 60.0);
    private static final Image bird = new Image("images/Flappy_Bird_img.png");
    private static Timeline pipeSpawner;
    private static boolean dead = false;

    static Canvas canvas = new Canvas(SceneSelector.getSceneWidth(), SceneSelector.getSceneHeight());
    static GraphicsContext gc = canvas.getGraphicsContext2D();

    static AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long now) {

            if (last_update == 0) {
                last_update = now;
                return;
            }

            // Time since last frame
            double delta = now - last_update;
            last_update = now;

            // Updating accumulator
            accumulator += delta;

            // Run physics updates in fixed-size steps
            int updates = 0;

            while (accumulator >= UPDATE_INTERVAL && updates < MAX_UPDATES) {
                try {
                    update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                accumulator -= UPDATE_INTERVAL;
                updates++;
            }

            // Drawing the picture
            draw(gc);
        }
    };

    public static void update() throws IOException {

        //Game Logic

        // Check for collisions
        CollisionHandler.checkCollision();

        // Update birds velocity
        yVelocity += GRAVITY * dt;

        // Update birds y position
        yPos += yVelocity * dt;

        // Update pipes xPos & score
        for (Pipe p : Pipe.getPipes()) {
            p.setXPos((p.getXPos() - pipeSpeed * dt));
            if (((p.getXPos() + Pipe.getPipeWidth()) < xPos) && !p.getScoreAwarded()) {
                p.setScoreAwarded(true);
                score++;
                SceneSelector.getScore().setText("Score: " + score);
            }
        }

        if (Pipe.getPipes().length != 0) {
            if (Pipe.getPipes()[0].getXPos() + Pipe.getPipeWidth() <= 0) {
                Pipe.removePipeFromArray();
            }
        }
    }

    public static void draw(GraphicsContext gc) {

        // Clear Scene and draw bird
        gc.clearRect(0, 0, SceneSelector.getSceneWidth(), SceneSelector.getSceneHeight());
        gc.drawImage(bird, xPos, yPos);

        // Draw pipes
        for (Pipe p : Pipe.getPipes()) {
            gc.setFill(Color.GREEN);
            gc.fillRect(p.getXPos(), 0, Pipe.getPipeWidth(), p.getHeight());
            gc.fillRect(p.getXPos(), p.getHeight() + Pipe.getPipeGap(), Pipe.getPipeWidth(), SceneSelector.getSceneHeight() - p.getHeight() - Pipe.getPipeGap() - FLOOR_HEIGHT);
        }
    }

    public static void startGame(BorderPane bp) throws IOException {
        dead = false;
        score = 0;
        yVelocity = -350;
        yPos = 400;
        last_update = 0;
        accumulator = 0;
        SceneSelector.getScore().setText("Score: 0");
        SceneSelector.getHighscoreText().setText("Highscore: " + getHighscoreValue());
        bp.getChildren().add(gc.getCanvas());
        bp.setCenter(SceneSelector.getVBoxPause());
        bp.setTop(SceneSelector.getVBoxScores());
        at.start();

        // Make a timer that creates a pipe every 2.5 seconds
        pipeSpawner = new Timeline(
                new KeyFrame(Duration.seconds(pipeCreationDelay), e -> new Pipe())
        );
        pipeSpawner.setCycleCount(Timeline.INDEFINITE);
        pipeSpawner.play();
    }

    public static void restartGame() throws IOException {
        dead = false;
        score = 0;
        yVelocity = -350;
        yPos = 400;
        last_update = 0;
        accumulator = 0;
        SceneSelector.getScore().setText("Score: 0");
        SceneSelector.getHighscoreText().setText("Highscore: " + getHighscoreValue());
        Pipe.emptyArray();
        KeyListener.setEscapeCounter(0);
        SceneSelector.getPauseVBox().getChildren().removeAll(SceneSelector.getRetryQuitHBox(), SceneSelector.getPauseText());
        SceneSelector.getGameOverText().setOpacity(0);
        SceneSelector.getPauseText().setOpacity(1);
        pipeSpawner.stop();
        pipeSpawner.playFromStart();
        at.start();
    }

    public static void gameOver() throws IOException {
        dead = true;
        showPauseScreen();
        SceneSelector.getGameOverText().setOpacity(1);
        SceneSelector.getPauseText().setOpacity(0);
        if (score > getHighscoreValue()) {
            saveHighscore();
        }
    }

    public static void showPauseScreen() {
        GameLogic.getAnimationTimer().stop();
        GameLogic.getPipeSpawner().pause();
        SceneSelector.getPauseVBox().getChildren().addAll(SceneSelector.getRetryQuitHBox(), SceneSelector.getPauseText());
    }

    public static void hidePauseScreen() {
        last_update = 0;
        accumulator = 0;
        SceneSelector.getPauseVBox().getChildren().removeAll(SceneSelector.getRetryQuitHBox(), SceneSelector.getPauseText());
        GameLogic.getAnimationTimer().start();
        GameLogic.getPipeSpawner().play();
    }

    public static void setDifficulty(String difficulty) {
        switch (difficulty) {
            case "easy":
                Pipe.setPipeGap(220);
                pipeSpeed = 70;
                pipeCreationDelay = 2.8;
                Pipe.setLowestGap(450);
                Pipe.setHighestGap(250);
                break;
            case "medium":
                Pipe.setPipeGap(170);
                pipeSpeed = 100;
                pipeCreationDelay = 2.0;
                Pipe.setLowestGap(500);
                Pipe.setHighestGap(200);
                break;
            case "hard":
                Pipe.setPipeGap(140);
                pipeSpeed = 130;
                pipeCreationDelay = 1.7;
                Pipe.setLowestGap(530);
                Pipe.setHighestGap(230);
                break;
        }
    }

    public static void saveHighscore() throws IOException {

        File highscoreFile = new File("highscore/highscore.txt");
        FileOutputStream fos = new FileOutputStream(highscoreFile);

        fos.write(String.valueOf(getScore()).getBytes());
        fos.close();
    }

    public static int getHighscoreValue() throws IOException {

        File highscoreFile = new File("highscore/highscore.txt");
        FileOutputStream fos;
        highscoreFile.getParentFile().mkdirs();
        Scanner scan;

        if (!highscoreFile.exists()) {
            highscoreFile.createNewFile();
            fos = new FileOutputStream(highscoreFile, false);
            fos.write(String.valueOf(0).getBytes());
            fos.close();
            return 0;
        } else {
            scan = new Scanner(highscoreFile);
            if (scan.hasNextInt()) {
                return scan.nextInt();
            } else {
                return 0;
            }
        }
    }

    public static void setYVelocity(double yVelocity) {
        GameLogic.yVelocity = yVelocity;
    }

    public static int getScore() {
        return score;
    }

    public static int getXPos() {
        return xPos;
    }

    public static double getYPos() {
        return yPos;
    }

    public static int getBirdWidth() {
        return BIRD_WIDTH;
    }

    public static int getBirdHeight() {
        return BIRD_HEIGHT;
    }

    public static int getFloorHeight() {
        return FLOOR_HEIGHT;
    }

    public static boolean getDead() {
        return dead;
    }

    public static AnimationTimer getAnimationTimer() {
        return at;
    }

    public static Timeline getPipeSpawner() {
        return pipeSpawner;
    }

}

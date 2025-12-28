package me;

import java.util.Random;

public class Pipe {

    Random rand = new Random();
    private final int height;
    private double xPos;
    private boolean scoreAwarded;
    private static int PIPE_GAP;
    private static final int PIPE_WIDTH = 75;
    private static int highestGap;
    private static int lowestGap;
    private static Pipe[] pipes = new Pipe[0];

    Pipe() {
        xPos = SceneSelector.getSceneWidth();
        height = rand.nextInt(highestGap, lowestGap);
        addPipeToArray(pipes, this);
    }

    public void addPipeToArray(Pipe[] pipes, Pipe pipe) {
        Pipe[] newPipeArray = new Pipe[pipes.length + 1];
        for (int i = 0; i < pipes.length; i++) {
            newPipeArray[i] = pipes[i];
        }
        newPipeArray[pipes.length] = pipe;
        Pipe.pipes = newPipeArray;
    }

    public static void removePipeFromArray() {
        Pipe[] newPipeArray = new Pipe[pipes.length - 1];
        for (int i = 0; i < pipes.length - 1; i++) {
            newPipeArray[i] = pipes[i + 1];
        }
        Pipe.pipes = newPipeArray;
    }

    public static void setPipeGap(int gap) {
        PIPE_GAP = gap;
    }

    public static void setLowestGap(int bottom) {
        lowestGap = bottom;
    }

    public static void setHighestGap(int top) {
        highestGap = top;
    }

    public void setScoreAwarded(boolean yes_or_no) {
        scoreAwarded = yes_or_no;
    }

    public static void emptyArray() {
        Pipe.pipes = new Pipe[0];
    }

    public boolean getScoreAwarded() {
        return scoreAwarded;
    }

    public int getHeight() {
        return height;
    }

    public double getXPos(){
        return xPos;
    }

    public static int getPipeGap() {
        return PIPE_GAP;
    }

    public static int getPipeWidth() {
        return PIPE_WIDTH;
    }

    public static Pipe[] getPipes() {
        return pipes;
    }

    public void setXPos(double newXPos) {
        this.xPos = newXPos;
    }

}

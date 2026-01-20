package me;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FlappyBird extends Application {

    // Creating an instance for the getInstance() method to easily access the stylesheet without having to create an instance in SceneSelector
    private static final FlappyBird flappyBird = new FlappyBird();

    // CSS stylesheet
    String css = this.getClass().getResource("/style/style.css").toExternalForm();

    // Background music
    Sound sound = new Sound();

    @Override
    public void start(Stage stage) throws Exception {

        // <--- Creating a window & setting its color, size, icon and layout etc. --->

        // Basic stage config
        stage.setResizable(false);
        stage.setTitle("Flappy Bird");
        SceneSelector.selectStartingScene(stage);

        // Show the stage and play the background music
        stage.show();
        sound.setFile(3);
        sound.play();
        sound.loop();
    }

    public String getStyleSheet() {
        return css;
    }

    public static FlappyBird getInstance() {
        return flappyBird;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
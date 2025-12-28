package me;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSelector {

    private static Scene startingScene, difficultyScene, gameScene;
    private static final int SCENE_WIDTH = 900;
    private static final int SCENE_HEIGHT = 800;
    private static BorderPane bp;
    private static VBox vBoxScores;
    private static Text pauseText;
    private static HBox retryQuitHBox;
    private static VBox vBoxPause;
    private static Text gameOverText;
    private static Text score;
    private static Text highscore;
    private static final Image homeScreen = new Image("images/Home_Screen.png");
    private static final Image startButtonBackground = new Image("images/Start_Button_Background.png");
    private static final Image quitButtonBackground = new Image("images/Quit_Button_Background.png");
    private static final Image easyButtonBackground = new Image("images/Easy_Button_Background.png");
    private static final Image mediumButtonBackground = new Image("images/Medium_Button_Background.png");
    private static final Image hardButtonBackground = new Image("images/Hard_Button_Background.png");
    private static final Image icon = new Image("images/Flappy_Bird_img.png");
    private static final ImageView groundImg = new ImageView("images/Ground_img.png");
    private static final Paint skyPaint = Color.valueOf("#70c4d1");

    public static void selectStartingScene(Stage stage) {

        // <--- Starting Scene --->

        // BorderPane because I can easily center the Start and Quit buttons
        bp = new BorderPane();

        // Background img of starting scene
        BackgroundImage background = new BackgroundImage(homeScreen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        bp.setBackground(new Background(background));

        // Setting the icon
        stage.getIcons().add(icon);

        // Creating the Scene
        startingScene = new Scene(bp, SCENE_WIDTH, SCENE_HEIGHT);
        startingScene.getStylesheets().add(FlappyBird.getInstance().getStyleSheet());
        stage.setScene(startingScene);

        // Adding Start & Quit Button
        Button start = new Button("");
        Button quit = new Button("");
        HBox hbox = new HBox(200);
        hbox.setPadding(new Insets(420, 20, 20, 20));
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(start, quit);
        start.setFont(Font.font("Comic Sans MS", 40));
        start.setMinSize(225, 75);
        start.setMaxSize(225, 75);
        start.setFocusTraversable(false);
        start.setBackground(new Background(new BackgroundImage(startButtonBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        quit.setFont(Font.font("Comic Sans MS", 40));
        quit.setMinSize(225, 75);
        quit.setMaxSize(225, 75);
        quit.setFocusTraversable(false);
        quit.setBackground(new Background(new BackgroundImage(quitButtonBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        start.setOnAction(actionEvent -> {
            selectDifficultyScene(stage);
        });
        quit.setOnAction(actionEvent -> stage.close());
        bp.setCenter(hbox);
    }

    public static void selectDifficultyScene(Stage stage) {

        // <--- Difficulty Scene --->

        // BorderPane because I can easily adjust the buttons position
        bp = new BorderPane();

        // Background img of difficulty scene
        BackgroundImage background = new BackgroundImage(homeScreen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        bp.setBackground(new Background(background));

        // Creating the Scene
        difficultyScene = new Scene(bp, SCENE_WIDTH, SCENE_HEIGHT);
        difficultyScene.getStylesheets().add(FlappyBird.getInstance().getStyleSheet());
        stage.setScene(difficultyScene);

        // Creating the difficulty buttons and setting their background
        Button easyButton = new Button("");
        Button mediumButton = new Button("");
        Button hardButton = new Button("");

        VBox vBoxDifficultyButtons = new VBox();
        vBoxDifficultyButtons.getChildren().addAll(easyButton, mediumButton, hardButton);
        vBoxDifficultyButtons.setAlignment(Pos.CENTER);
        vBoxDifficultyButtons.setSpacing(150);

        easyButton.setFocusTraversable(false);
        easyButton.setMinSize(247, 75);
        easyButton.setBackground(new Background(new BackgroundImage(easyButtonBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        mediumButton.setFocusTraversable(false);
        mediumButton.setMinSize(247, 75);
        mediumButton.setBackground(new Background(new BackgroundImage(mediumButtonBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        hardButton.setFocusTraversable(false);
        hardButton.setMinSize(247, 75);
        hardButton.setBackground(new Background(new BackgroundImage(hardButtonBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        easyButton.setOnAction(e -> {
            selectGameScene(stage);
            GameLogic.setDifficulty("easy");
            try {
                GameLogic.startGame(bp);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        mediumButton.setOnAction(e -> {
            selectGameScene(stage);
            GameLogic.setDifficulty("medium");
            try {
                GameLogic.startGame(bp);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        hardButton.setOnAction(e -> {
            selectGameScene(stage);
            GameLogic.setDifficulty("hard");
            try {
                GameLogic.startGame(bp);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        easyButton.getStyleClass().add("DifficultyButton");
        mediumButton.getStyleClass().add("DifficultyButton");
        hardButton.getStyleClass().add("DifficultyButton");

        bp.setCenter(vBoxDifficultyButtons);
    }

    public static void selectGameScene(Stage stage) {

        // <--- Game Scene --->

        // BorderPane layout because it's simple & we can add the floor at the bottom as well as score at the top right
        bp = new BorderPane();

        // Basic Flappy Bird Background Color of the sky
        Background background = new Background(new BackgroundFill(skyPaint, new CornerRadii(0), new Insets(0)));
        bp.setBackground(background);

        // Creating the Scene
        gameScene = new Scene(bp, SCENE_WIDTH, SCENE_HEIGHT);

        // Setting the icon of the window in the top left
        stage.getIcons().add(icon);

        // Size of Ground Image: 900px x 100px for calculation of hitting the floor
        bp.setBottom(groundImg);
        stage.setScene(gameScene);

        // Score
        score = new Text("");
        score.setStroke(Color.WHITE);
        score.setFill(Color.WHITE);
        score.setFont(Font.font("Comic Sans MS", 30));
        highscore = new Text("");
        highscore.setStroke(Color.WHITE);
        highscore.setFill(Color.WHITE);
        highscore.setFont(Font.font("Comic Sans MS", 30));
        vBoxScores = new VBox();
        vBoxScores.setPadding(new Insets(5, 15, 0, 0));
        vBoxScores.setAlignment(Pos.TOP_RIGHT);
        vBoxScores.getChildren().addAll(highscore, score);

        // Pause text & buttons
        pauseText = new Text("Press ESC again to resume");
        pauseText.setFill(Color.WHITE);
        pauseText.setFont(Font.font("Comic Sans MS", 40));
        gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font("Comic Sans MS", 60));
        gameOverText.setFill(Color.valueOf("#da6815"));
        gameOverText.setOpacity(0);
        Button retryButton = new Button("Retry");
        retryButton.setFocusTraversable(false);
        retryButton.getStyleClass().add("PauseButton");
        retryButton.setOnAction(e -> {
            try {
                GameLogic.restartGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Button quitButton = new Button("Quit");
        quitButton.setLayoutX(550);
        quitButton.setLayoutY(290);
        quitButton.setFocusTraversable(false);
        quitButton.getStyleClass().add("PauseButton");
        quitButton.setOnAction(e -> {
            stage.close();
        });
        retryQuitHBox = new HBox();
        retryQuitHBox.setSpacing(50);
        retryQuitHBox.setAlignment(Pos.CENTER);
        retryQuitHBox.getChildren().addAll(retryButton, quitButton);
        vBoxPause = new VBox();
        vBoxPause.setPadding(new Insets(0, 0, 0, 0));
        vBoxPause.setAlignment(Pos.CENTER);
        vBoxPause.setSpacing(30);
        vBoxPause.getChildren().add(gameOverText);

        // Adding the KeyListenerClass
        gameScene.setOnKeyPressed(new KeyListener());

        // Adding the stylesheet
        gameScene.getStylesheets().add(FlappyBird.getInstance().getStyleSheet());
    }

    public static VBox getPauseVBox() {
        return vBoxPause;
    }

    public static int getSceneWidth() {
        return SCENE_WIDTH;
    }

    public static int getSceneHeight() {
        return SCENE_HEIGHT;
    }

    public static Text getPauseText() {
        return pauseText;
    }

    public static Text getGameOverText() {
        return gameOverText;
    }

    public static HBox getRetryQuitHBox() {
        return retryQuitHBox;
    }

    public static VBox getVBoxScores(){
        return vBoxScores;
    }

    public static VBox getVBoxPause() {
        return vBoxPause;
    }

    public static Text getScore() {
        return score;
    }

    public static Text getHighscoreText() {
        return highscore;
    }

}

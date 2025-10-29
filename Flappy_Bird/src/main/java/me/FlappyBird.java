package me;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FlappyBird extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // <--- Creating a window & setting its color, size, icon and layout etc. --->

        // BorderPane layout because it's simple and we can add the floor at the bottom as well as score at the top right
        BorderPane bp = new BorderPane();
        // Basic Flappy Bird Background Color of the sky
        Paint paint = Color.valueOf("#70c4d1");
        Background background = new Background(new BackgroundFill(paint, new CornerRadii(0), new Insets(0)));
        bp.setBackground(background);
        // Setting the bp as the parent and specifying the size of the window
        Scene scene = new Scene(bp, 900, 700);
        // Setting the title
        stage.setTitle("Flappy Bird");
        // Setting the icon of the window in the top left
        Image icon = new Image("images/Flappy_Bird_img.png");
        stage.getIcons().add(icon);
        // Size of Ground Image: 900px x 100px for calculation of hitting the floor
        ImageView ground_img = new ImageView("images/Ground_img.png");
        bp.setBottom(ground_img);
        // Basic stage config
        stage.setResizable(false);
        stage.setScene(scene);
        // Score
        Text score = new Text("Score: 0");
        score.setX(770);
        score.setY(30);
        score.setStroke(Color.WHITE);
        score.setFill(Color.WHITE);
        score.setScaleX(2.7);
        score.setScaleY(2.7);
        score.setFont(Font.font("Comic Sans MS"));



        // Adding all elements to the BorderPane & displaying the stage
        bp.getChildren().addAll(score);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
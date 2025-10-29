package me;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane bp = new BorderPane();
        Paint paint = Color.CYAN;
        Background background = new Background(new BackgroundFill(paint, new CornerRadii(0), new Insets(0)));
        bp.setBackground(background);
        Scene scene = new Scene(bp, 500, 500);
        stage.setTitle("Flappy Bird");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
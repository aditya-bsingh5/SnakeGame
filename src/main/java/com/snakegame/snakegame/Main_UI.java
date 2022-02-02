package com.snakegame.snakegame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_UI extends Application {

    Field f;
    static int block_size = 20;
    int width = 30, height = 30;
    int il = 5;
    long then;
    boolean changed = false;
    int nextUpdate;
    boolean hasNext;

    @Override
    public void start(Stage ps) throws IOException {
        VBox root = new VBox(10);
        root.setPadding(new Insets(5));

        f = new Field(width, height);
        f.addSnake(new Snake(il, f));

        Label score = new Label("Score: 0");
        score.setFont(Font.font("verdana",32));

        then =  System.nanoTime();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(now - then > 1000000000/f.var /*how fast it changes has to be made variable*/) {
                    f.update();
                    then = now;
                    score.setText("Score: " + f.score);
                    changed = false;
                    if(hasNext) {
                        setDirection(f.snake, nextUpdate);
                        hasNext = false;
                    }
                    if(f.isDead()) {
                        stop();
                        Alert al = new Alert(Alert.AlertType.INFORMATION);
                        al.setHeaderText("snake dead");
                        al.setContentText("your score is" + f.score);
                        Platform.runLater(al :: showAndWait);

                        al.setOnHidden(e -> {
                            root.getChildren().clear();
                            f = new Field(width, height);
                            f.addSnake(new Snake(il,f));
                            score.setText("Score: 0");
                            root.getChildren().addAll(f, score);
                            start();
                        });
                    }
                }
            }
        };
        timer.start();





        root.getChildren().addAll(f, score);

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(e -> {
            if(e.getCode().equals(KeyCode.UP) && f.snake.getDirection() != Block.DOWN) {
                setDirection(f.snake, Block.UP);
            }
            if(e.getCode().equals(KeyCode.RIGHT) && f.snake.getDirection() != Block.LEFT) {
                setDirection(f.snake, Block.RIGHT);
            }
            if(e.getCode().equals(KeyCode.DOWN) && f.snake.getDirection() != Block.UP) {
                setDirection(f.snake, Block.DOWN);
            }
            if(e.getCode().equals(KeyCode.LEFT) && f.snake.getDirection() != Block.RIGHT) {
                setDirection(f.snake, Block.LEFT);
            }
        });

        ps.setResizable(false);
        ps.setTitle("SnakeZ");
        Image icon = new Image("file:snake.jpg");
        ps.getIcons().add(icon);
        ps.setScene(scene);
        ps.show();

//        FXMLLoader fxmlLoader = new FXMLLoader(Main_UI.class.getResource("frontScene.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    }

    public void setDirection(Snake s, int d) {
        if(!changed) {
            s.setDirection(d);
        }
        else {
            hasNext = true;
            nextUpdate = d;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
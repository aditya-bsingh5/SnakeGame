package com.snakegame.snakegame;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Field extends Pane {

    private int w, h;

    ArrayList<Block> blocks = new ArrayList<>();
    Snake snake;

    Food f;
    int score = 0;
    int var = 8;

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public Field(int width, int height) {
        w = width;
        h = height;

        setMinSize(w * Main_UI.block_size, h * Main_UI.block_size);
        setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        setBorder((new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID, null, new BorderWidths(1)))));
        addFood();
    }

    public void addFood() {
        int randomX = (int)(Math.random() * w);
        int randomY = (int)(Math.random() * h);

        Food food = new Food(randomX, randomY);
        getChildren().add(food);
        getChildren().remove(f);
        f = food;
    }

    public void addSnake(Snake s) {
        snake = s;
        for(Block b : s.blocks) {
            addBlock(b);
        }
    }

    public void update() {
        for(Block b : blocks) {
            b.update();
        }

        if(isEaten(f)) {
            score += 20;
            addFood();
            addNewBlock();
            var *= 1.1;
        }
    }

    public void addNewBlock() {
        Block b = new Block(snake.tail.oldPosX, snake.tail.oldPosY, snake.tail, this);
        snake.tail = b;
        addBlock(b);
    }

    private void addBlock(Block b) {
        getChildren().add(b);
        blocks.add(b);
    }

    public boolean isEaten(Food f) {
        if(f == null) return false;
        return f.getPosX() == snake.head.posX && f.getPosY() == snake.head.posY;
    }

    public boolean isDead() {
        for(Block b : blocks) {
            if(b != snake.head) {
                if(b.posX == snake.head.posX && b.posY == snake.head.posY)
                    return true;
            }
        }
        return false;
    }

}

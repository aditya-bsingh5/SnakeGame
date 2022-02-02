package com.snakegame.snakegame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Food extends Rectangle {
    int posX;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    int posY;

    public Food(int x, int y) {
        super(Main_UI.block_size, Main_UI.block_size);
        posX = x;
        posY = y;
        setTranslateX(posX * Main_UI.block_size);
        setTranslateY(posY * Main_UI.block_size);

        setFill(Color.GREENYELLOW);
        setStroke(Color.BLACK);
    }
}

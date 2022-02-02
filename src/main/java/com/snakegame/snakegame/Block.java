package com.snakegame.snakegame;

import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {

    static final int UP = 0, RIGHT = 1, DOWN = 3, LEFT = 4;
    int posX, posY, oldPosX, oldPosY;

    Block previous;

    int direction = LEFT;

    int maxX, maxY;

    public Block(int x, int y, Block p, Field f) {
        super(Main_UI.block_size, Main_UI.block_size);
        posX = x;
        posY = y;

        setTranslateX(posX * Main_UI.block_size);
        setTranslateY(posY * Main_UI.block_size);

        previous = p;

        maxX = f.getW();
        maxY = f.getH();
    }

    public void update() {
        oldPosX = posX;
        oldPosY = posY;

        if(previous == null) {
            switch (direction) {
                case UP -> moveUp();
                case RIGHT -> moveRight();
                case DOWN -> moveDown();
                case LEFT -> moveLeft();
                default -> {
                }
            }
        }
        else {
            posX = previous.oldPosX;
            posY = previous.oldPosY;
        }

        updatePosition();
    }

    public void moveUp() {
        posY--;
        if(posY < 0) {
            posY = maxY-1;
        }
    }
    public void moveRight() {
        posX++;
        if(posX >= maxX) {
            posX = 0;
        }
    }
    public void moveDown() {
        posY++;
        if(posY >= maxY) {
            posY = 0;
        }
    }
    public void moveLeft() {
        posX--;
        if(posX < 0) {
            posX = maxX-1;
        }
    }

    public void updatePosition() {
        setTranslateX(posX * Main_UI.block_size);
        setTranslateY(posY * Main_UI.block_size);
    }
}

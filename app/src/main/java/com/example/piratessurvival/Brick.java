package com.example.piratessurvival;

public class Brick {
    int row, column, width, height;
    boolean visible;

    public Brick(int row, int column, int width, int height) {
        this.row = row;
        this.column = column;
        this.width = width;
        this.height = height;
        this.visible = true;
    }

    public boolean getVisibility() {
        return visible;
    }

    public void setInvisible() {
        visible = false;
    }
}
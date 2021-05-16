package com.agonzalez.hexchess;

public class IntRect {
    private int x;
    private int y;

    private int width;
    private int height;

    public int getHeight() {return height;}
    public int getWidth() {return width;}

    public void setHeight(int height) {this.height = height;}
    public void setWidth(int width) {this.width = width;}

    public int getX() {return x;}
    public int getY() {return y;}

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}


    public IntRect(int x, int y, int width, int height) {
        super();
        this.x = x;
        this.y = y;
        this.setWidth(width);
        this.height = height;
    }

}

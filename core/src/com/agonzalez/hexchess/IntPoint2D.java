package com.agonzalez.hexchess;

public class IntPoint2D {
    private int x;
    private int y;

    @Override
    public boolean equals(Object obj) {

        if(!( obj instanceof IntPoint2D)) {
            return false;
        }

        IntPoint2D other= (IntPoint2D)obj;

        return other.getX() == x && other.getY() == y;
    }

    public int getX() {return x;}
    public int getY() {return y;}

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}

    public IntPoint2D(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public IntPoint2D Transpose(int x, int y) { return new IntPoint2D(this.x + x, this.y + y); }
}

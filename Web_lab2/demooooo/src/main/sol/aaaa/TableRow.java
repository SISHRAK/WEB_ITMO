package aaaa;

import java.io.Serializable;

public class TableRow implements Serializable {
    private int x;
    private float y;
    private float r;
    private boolean isInside;
    private String localTime;

    //    public TableRow(int x, float y, float radius, boolean isInside, String localTime) {
//        this.x = x;
//        this.y = y;
//        this.r = radius;
//        this.isInside = isInside;
//        this.localTime = localTime;
//    }
    public TableRow(){

    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public boolean getInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

}
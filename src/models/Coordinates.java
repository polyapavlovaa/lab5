package models;

import annotations.LowerThan;
import annotations.NonNull;

public class Coordinates {

    @LowerThan(518)
    private double x;

    @LowerThan(332)
    @NonNull
    private Integer y;

    public Coordinates(double x, Integer y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

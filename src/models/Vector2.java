package models;

import models.worldMap.WorldMap;

public class Vector2 {
    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.x = x * (int)WorldMap.tileWidth;
        this.y = y * (int)WorldMap.tileHeight;
    }

    public void add(int x, int y){
        this.x += x;
        this.y += y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

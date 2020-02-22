package models;

import models.worldMap.WorldMap;

public class Vector2 {
    public static final int RELATIVE_TO_TILE = 0;
    public static final int RELATIVE_TO_AREA = 1;
    public static final int ABSOLUTE_PIXEL = 2;
    private int x;
    private int y;

    public Vector2(int x, int y, int relativeTo) {
        if(relativeTo == RELATIVE_TO_TILE){
            this.x = x * WorldMap.tileWidth;
            this.y = y * WorldMap.tileHeight;
        } else if (relativeTo == RELATIVE_TO_AREA){
            this.x = x * WorldMap.areaWidth;
            this.y = y * WorldMap.areaHeight;
        } else if (relativeTo == ABSOLUTE_PIXEL){
            this.x = x;
            this.y = y;
        }
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

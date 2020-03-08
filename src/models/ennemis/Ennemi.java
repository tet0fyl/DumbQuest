package models.ennemis;

import models.*;
import models.worldMap.Tile;

import java.util.ArrayList;

public abstract class Ennemi extends Moveable{

    private double respawnX;
    private double respawnY;
    protected boolean isAlive;

    public Ennemi(int areaX, int areaY, int tileX, int tileY, double boxWidth, double boxHeight, double attackBoxX, double attackBoxY, int vitesse) {
        super(areaX, areaY, tileX, tileY,boxWidth, boxHeight,attackBoxX,attackBoxY,vitesse);
        respawnX = getX();
        respawnY = getY();
        isAlive = true;

    }

    public void respawn(){
        x= respawnX;
        y = respawnY;
        update();
    }

    public double getRespawnX() {
        return respawnX;
    }

    public void setRespawnX(double respawnX) {
        this.respawnX = respawnX;
    }

    public double getRespawnY() {
        return respawnY;
    }

    public void setRespawnY(double respawnY) {
        this.respawnY = respawnY;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}

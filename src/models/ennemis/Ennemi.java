package models.ennemis;

import models.Moveable;

public abstract class Ennemi extends Moveable {

    protected boolean isAlive, isDying;
    protected int pv, pvMax;
    private double respawnX;
    private double respawnY;

    public Ennemi(int areaX, int areaY, int tileX, int tileY, double boxWidth, double boxHeight, double attackBoxX, double attackBoxY, double vitesse) {
        super(areaX, areaY, tileX, tileY, boxWidth, boxHeight, attackBoxX, attackBoxY, vitesse);
        respawnX = getX();
        respawnY = getY();
        isDying = false;
        isAlive = true;
    }

    public void respawn() {
        x = respawnX;
        y = respawnY;
        update();
    }

    public void subitDegat() {
        pv -= 1;
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

    public boolean isDying() {
        return isDying;
    }

    public void setDying(boolean dying) {
        isDying = dying;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getPvMax() {
        return pvMax;
    }

    public void setPvMax(int pvMax) {
        this.pvMax = pvMax;
    }
}

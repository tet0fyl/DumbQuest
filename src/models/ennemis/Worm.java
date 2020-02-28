package models.ennemis;

import javafx.scene.paint.Color;
import models.worldMap.Tile;

public class Worm extends Ennemi {
    public boolean isInTheGround,isStartingTracking, isOutSide;
    private int inTheGroundBuffer = 10;
    private int trackingBuffer = 5;
    private int outSideBuffer = 10;
    private int inTheGround = inTheGroundBuffer;
    private int tracking = trackingBuffer;
    private int outSide = outSideBuffer;
    private Tile targetTile;


    public Worm(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
        initModels();
        initView(Color.PINK);
        isStartingTracking = false;
        isInTheGround = true;
        vitesse = 2;
    }

    @Override
    public void animate() {
        super.animate();
        if(isInTheGround && !isStartingTracking){
            if(inTheGround != 0){
                inTheGround--;
                System.out.println("Je suis sous le sol");
            } else{
                isStartingTracking = true;
                inTheGround = inTheGroundBuffer;
            }
        }
        if(isStartingTracking && !isOutSide){
            if(tracking != 0){
                tracking--;
                System.out.println("Je me prepare");
            } else{
                isOutSide = true;
                tracking = trackingBuffer;
            }
        }
        if(isOutSide){
            if(outSide != 0){
                isAttacking = false;
                outSide--;
                System.out.println("Je suis dehors");
            } else{
                isOutSide = false;
                isStartingTracking = false;
                isInTheGround = true;
                outSide = outSideBuffer;
            }
        }
    }

    public void selectTargetTile(Tile targetTile){
        this.targetTile = targetTile;
    }

    public boolean isInTheGround() {
        return isInTheGround;
    }

    public void setInTheGround(boolean inTheGround) {
        isInTheGround = inTheGround;
    }

    public boolean isStartingTracking() {
        return isStartingTracking;
    }

    public void setStartingTracking(boolean startingTracking) {
        isStartingTracking = startingTracking;
    }

    public boolean isOutSide() {
        return isOutSide;
    }

    public void setOutSide(boolean outSide) {
        isOutSide = outSide;
    }

    public int getInTheGroundBuffer() {
        return inTheGroundBuffer;
    }

    public void setInTheGroundBuffer(int inTheGroundBuffer) {
        this.inTheGroundBuffer = inTheGroundBuffer;
    }

    public int getTrackingBuffer() {
        return trackingBuffer;
    }

    public void setTrackingBuffer(int trackingBuffer) {
        this.trackingBuffer = trackingBuffer;
    }

    public int getOutSideBuffer() {
        return outSideBuffer;
    }

    public void setOutSideBuffer(int outSideBuffer) {
        this.outSideBuffer = outSideBuffer;
    }

    public int getInTheGround() {
        return inTheGround;
    }

    public void setInTheGround(int inTheGround) {
        this.inTheGround = inTheGround;
    }

    public int getTracking() {
        return tracking;
    }

    public void setTracking(int tracking) {
        this.tracking = tracking;
    }

    public int getOutSide() {
        return outSide;
    }

    public void setOutSide(int outSide) {
        this.outSide = outSide;
    }

    public Tile getTargetTile() {
        return targetTile;
    }

    public void setTargetTile(Tile targetTile) {
        this.targetTile = targetTile;
    }
}

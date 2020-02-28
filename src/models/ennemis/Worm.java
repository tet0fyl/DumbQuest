package models.ennemis;

import javafx.scene.paint.Color;
import models.worldMap.Tile;

public class Worm extends Ennemi {
    public boolean isInTheGround, isTargeting, isTargetDone , isOutSide;
    private int inTheGroundBuffer = 10;
    private int trackingBuffer = 3;
    private int outSideBuffer = 10;
    private int inTheGround = inTheGroundBuffer;
    private int tracking = trackingBuffer;
    private int outSide = outSideBuffer;
    private Tile targetTile;


    public Worm(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
        initModels();
        initView(Color.PINK);
        isTargeting = false;
        isInTheGround = true;
        isTargetDone = false;
        vitesse = 2;
    }

    @Override
    public void animate() {
        super.animate();
        if(isInTheGround && !isTargeting){
            isTargetDone = false;
            if(inTheGround != 0){
                inTheGround--;
                System.out.println("Je suis sous le sol");
            } else{
                isTargeting = true;
                inTheGround = inTheGroundBuffer;
            }
        }
        if(isTargeting && !isOutSide){
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
                isTargeting = false;
                isInTheGround = true;
                outSide = outSideBuffer;
            }
        }
    }

    public void teleport(Tile targetTile){
        this.targetTile = targetTile;
        targetTile.getGraphic().setFill(Color.RED);
        x = targetTile.getLayoutX();
        y = targetTile.getLayoutY();
        update();
        isTargetDone = true;
    }

    public boolean isInTheGround() {
        return isInTheGround;
    }

    public void setInTheGround(boolean inTheGround) {
        isInTheGround = inTheGround;
    }

    public boolean isTargeting() {
        return isTargeting;
    }

    public void setTargeting(boolean targeting) {
        isTargeting = targeting;
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

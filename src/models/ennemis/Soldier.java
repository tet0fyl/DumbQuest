package models.ennemis;

import javafx.scene.paint.Color;
import models.Direction;
import models.GraphNode;
import models.worldMap.Tile;

import java.util.ArrayList;

public class Soldier extends Ennemi {

    private ArrayList<GraphNode> destinationPath;
    private Integer currentNodeDestinationPath = 0;
    private boolean isAttackRready, isPreparingAttack;
    private int waitingttackReadyBuffer = 5;
    private int waitingAttackReady = waitingttackReadyBuffer;

    public Soldier(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
        initModels(3,3,2,2);
        isAttackRready = false;
        isPreparingAttack = false;
        initView(Color.BISQUE);
        vitesse = 2;
    }

    @Override
    public void attackAnimation() {
        if(animationAttackFrame != 0){
            isAttackRready = false;
            isPreparingAttack = false;
            animationAttackFrame--;
        } else{
            isAttacking = false;
            animationAttackFrame = 4;
        }
    }

    @Override
    public void animate() {
        super.animate();
        if(isPreparingAttack){
            if(waitingAttackReady != 0){
                waitingAttackReady--;
            } else {
                isAttackRready = true;
                waitingAttackReady = waitingttackReadyBuffer;
            }
        }

    }

    public void setDestination(ArrayList<GraphNode> destinationPath){
        currentNodeDestinationPath = 1;
        this.destinationPath = destinationPath;
    }

    public void moveToTarget(){
        if(destinationPath != null){
            Tile tile = destinationPath.get(currentNodeDestinationPath).tile;
            if(Math.abs(tile.getTheCenterX() - (getTheCenterHitBoxX())) > 5){
                if (tile.getTheCenterX() < (getTheCenterHitBoxX())) move(Direction.GO_LEFT);
                if (tile.getTheCenterX() > (getTheCenterHitBoxX())) move(Direction.GO_RIGHT);
            }
            if(Math.abs(tile.getTheCenterY() - (getTheCenterHitBoxY())) > 5){
                if (tile.getTheCenterY() < (getTheCenterHitBoxY())) move(Direction.GO_UP);
                if (tile.getTheCenterY() > (getTheCenterHitBoxY())) move(Direction.GO_DOWN);
            }
            if(Math.abs(tile.getTheCenterX() - (getTheCenterHitBoxX())) <= 5 && Math.abs(tile.getTheCenterY() - (getTheCenterHitBoxY())) <= 5){
                currentNodeDestinationPath++;
            }
            if(destinationPath.size()-1 <= currentNodeDestinationPath){
                destinationPath = null;
            }
            update();
        }
    }


    public ArrayList<GraphNode> getDestinationPath() {
        return destinationPath;
    }

    public Tile getTheEndTile(){
        return destinationPath.get(destinationPath.size()-1).tile;
    }

    public int getTheRestNodePathDestination(){
        return  destinationPath.size() - currentNodeDestinationPath  ;
    }

    public int getCurrentNodeDestinationPath() {
        return currentNodeDestinationPath;
    }

    public void setDestinationPath(ArrayList<GraphNode> destinationPath) {
        this.destinationPath = destinationPath;
    }

    public void setCurrentNodeDestinationPath(Integer currentNodeDestinationPath) {
        this.currentNodeDestinationPath = currentNodeDestinationPath;
    }

    public boolean isAttackReady() {
        return isAttackRready;
    }

    public void setAttackRready(boolean attackRready) {
        isAttackRready = attackRready;
    }

    public int getWaitingttackReadyBuffer() {
        return waitingttackReadyBuffer;
    }

    public void setWaitingttackReadyBuffer(int waitingttackReadyBuffer) {
        this.waitingttackReadyBuffer = waitingttackReadyBuffer;
    }

    public int getAttackRready() {
        return waitingAttackReady;
    }

    public void setPreparingAttack(int preparingAttack) {
        this.waitingAttackReady = preparingAttack;
    }

    public boolean isAttackRready() {
        return isAttackRready;
    }

    public boolean isPreparingAttack() {
        return isPreparingAttack;
    }

    public void setPreparingAttackReady(boolean preparingAttackReady) {
        isPreparingAttack = preparingAttackReady;
    }

    public int getWaitingAttackReady() {
        return waitingAttackReady;
    }

    public void setWaitingAttackReady(int waitingAttackReady) {
        this.waitingAttackReady = waitingAttackReady;
    }
}

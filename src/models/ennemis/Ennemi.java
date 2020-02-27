package models.ennemis;

import models.*;
import models.worldMap.Tile;

import java.util.ArrayList;

public abstract class Ennemi extends Moveable {
    private ArrayList<GraphNode> destinationPath;
    private Integer currentNodeDestinationPath = 0;

    public Ennemi(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
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
                System.out.println("-----");
            }

            if(destinationPath.size()-1 <= currentNodeDestinationPath){
                destinationPath = null;
            }
            update();
        }
    }

    public void pointThePlayer(Player player){
            if (player.getTheCenterHitBoxX() < (getTheCenterHitBoxX())) move(Direction.GO_LEFT);
            if (player.getTheCenterHitBoxX() > (getTheCenterHitBoxX())) move(Direction.GO_RIGHT);
            if (player.getTheCenterHitBoxY() < (getTheCenterHitBoxY())) move(Direction.GO_UP);
            if (player.getTheCenterHitBoxY() > (getTheCenterHitBoxY())) move(Direction.GO_DOWN);
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
}

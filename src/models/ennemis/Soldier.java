package models.ennemis;

import javafx.scene.paint.Color;
import models.Direction;
import models.GraphNode;
import models.worldMap.Tile;

import java.util.ArrayList;

public class Soldier extends Ennemi {

    private ArrayList<GraphNode> destinationPath;
    private Integer currentNodeDestinationPath = 0;

    public Soldier(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
        initModels(3,3,2,2);
        initView(Color.BISQUE);
        vitesse = 2;
    }

    @Override
    public void attackAnimation() {
        super.attackAnimation();
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
}

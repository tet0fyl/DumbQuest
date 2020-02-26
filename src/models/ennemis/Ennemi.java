package models.ennemis;

import models.Direction;
import models.IA;
import models.Moveable;
import models.worldMap.Tile;

import java.util.ArrayList;

public abstract class Ennemi extends Moveable {
    private ArrayList<Tile> destinationPath;
    private Integer currentNodeDestinationPath;

    public Ennemi(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
    }

    public void setDestination(ArrayList<Tile> destinationPath){
        currentNodeDestinationPath = destinationPath.size() - 1;
        for(Tile tile: destinationPath){
        System.out.println(tile.getIndiceX() + " : " + tile.getTheCenterY());
        }
        this.destinationPath = destinationPath;
    }

    public void moveToTarget(){
        if(destinationPath != null){
            if(Math.abs(destinationPath.get(currentNodeDestinationPath).getTheCenterX() - x) != 0){
                if (destinationPath.get(currentNodeDestinationPath).getTheCenterX() < x) move(Direction.GO_LEFT);
                if (destinationPath.get(currentNodeDestinationPath).getTheCenterX() > x) move(Direction.GO_RIGHT);
            } else{
                currentNodeDestinationPath--;
            }
            if(currentNodeDestinationPath <= 0){
                currentNodeDestinationPath = null;
            }
            update();
        }
    }

    public ArrayList<Tile> getDestinationPath() {
        return destinationPath;
    }

    public int getCurrentNodeDestinationPath() {
        return currentNodeDestinationPath;
    }
}

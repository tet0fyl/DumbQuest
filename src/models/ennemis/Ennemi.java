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
        currentNodeDestinationPath = destinationPath.size() - 2;
        this.destinationPath = destinationPath;
    }

    public void moveToTarget(){
        if(destinationPath != null){
            if(Math.abs(destinationPath.get(currentNodeDestinationPath).getTheCenterX() - (x - getTheCenterHitBoxX())) > 5){
                if (destinationPath.get(currentNodeDestinationPath).getTheCenterX() < (x - getTheCenterHitBoxX())) move(Direction.GO_LEFT);
                if (destinationPath.get(currentNodeDestinationPath).getTheCenterX() > (x - getTheCenterHitBoxX())) move(Direction.GO_RIGHT);
            } else
            if(Math.abs(destinationPath.get(currentNodeDestinationPath).getTheCenterY() - (y - getTheCenterHitBoxY())) > 5){
                if (destinationPath.get(currentNodeDestinationPath).getTheCenterY() < (y - getTheCenterHitBoxY())) move(Direction.GO_UP);
                if (destinationPath.get(currentNodeDestinationPath).getTheCenterY() > (y - getTheCenterHitBoxY())) move(Direction.GO_DOWN);
            }
            if(Math.abs(destinationPath.get(currentNodeDestinationPath).getTheCenterX() - (x - getTheCenterHitBoxX())) < 10 && Math.abs(destinationPath.get(currentNodeDestinationPath).getTheCenterY() - (y - getTheCenterHitBoxY())) < 10){
                currentNodeDestinationPath--;
            }

            if(currentNodeDestinationPath < 0){
                System.out.println("on remet a null");
                destinationPath = null;
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

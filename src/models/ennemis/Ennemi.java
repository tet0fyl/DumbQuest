package models.ennemis;

import models.*;
import models.worldMap.Tile;

import java.util.ArrayList;

public abstract class Ennemi extends Moveable{

    public Ennemi(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY,1,1,1,1,1);
    }


}

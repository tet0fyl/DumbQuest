package models.ennemis;

import models.Moveable;

public abstract class Ennemi extends Moveable {
    public Ennemi(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
    }
}

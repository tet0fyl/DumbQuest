package models.ennemis;

import models.IA;
import models.Moveable;

public abstract class Ennemi extends Moveable {
    private IA ia = new IA();

    public Ennemi(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
    }
}

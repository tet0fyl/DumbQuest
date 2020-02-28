package models.ennemis;

import javafx.scene.paint.Color;
import models.Moveable;

public class Plant extends Ennemi {
    public Plant(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
        initModels();
        initView(Color.BLUE);
        vitesse = 2;
    }

    @Override
    public void attack(Moveable moveable) {
        super.attack(moveable);
    }
}

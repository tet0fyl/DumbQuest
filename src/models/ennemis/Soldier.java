package models.ennemis;

import javafx.scene.paint.Color;

public class Soldier extends Ennemi {

    public Soldier(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
        initModels();
        initView(Color.BISQUE);
        vitesse = 2;
    }
}

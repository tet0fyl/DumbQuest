package models;


public class Player extends Moveable {

    public Player(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
        initModels();
        initView();
    }
}

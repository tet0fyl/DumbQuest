package models.ennemis;

import models.Moveable;

import java.util.ArrayList;

public class Boss extends Ennemi {

    private int phase = 1;
    private boolean isTired,isReloading;
    private ArrayList<Projectile> projectiles;
    private int shootRate = 10;
    private int shootRateBuffer = shootRate;
    private int tired = 10;
    private int tiredBuffer = tired;

    public Boss(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
    }

    @Override
    public void animate() {
        super.animate();
    }
}

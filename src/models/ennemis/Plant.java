package models.ennemis;

import javafx.scene.paint.Color;
import models.Moveable;
import models.worldMap.Tile;

import java.util.ArrayList;

public class Plant extends Ennemi {
    private ArrayList<Projectile> projectiles;
    boolean isReloading;
    private int shootRate = 15;


    public Plant(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
        isReloading = false;
        projectiles = new ArrayList<Projectile>();
        initModels();
        initView(Color.BLUE);
        vitesse = 2;
    }

    public Projectile shoot(Tile tileTarget) {
            Projectile projectile = new Projectile(this, tileTarget);
            projectiles.add(projectile);
            isReloading = true;
        return projectile;
    }

    @Override
    public void animate() {
        super.animate();
        if(isReloading){
            if(shootRate != 0){
                shootRate--;
            } else{
                isReloading = false;
                shootRate = 15;
            }
        }
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public boolean isReloading() {
        return isReloading;
    }

    public void setReloading(boolean reloading) {
        isReloading = reloading;
    }

    public int getShootRate() {
        return shootRate;
    }

    public void setShootRate(int shootRate) {
        this.shootRate = shootRate;
    }


}

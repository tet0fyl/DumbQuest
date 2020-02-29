package models;


import javafx.scene.paint.Color;
import models.worldMap.WorldMap;

public class Player extends Moveable {
    private boolean isInvincible;

    public Player(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY);
        animationFrameDamageBuffer = 10;
        animationDamageFrame = animationFrameDamageBuffer;
        initModels();
        initView(Color.AZURE);
        System.out.println(WorldMap.tileWidth);
        System.out.println(WorldMap.tileHeight);
    }

    @Override
    public void animate() {
        super.animate();
    }

    @Override
    public void damageAnimation() {
        if(animationDamageFrame != 0){
            isInvincible = true;
            animationDamageFrame--;
            if(skin.getOpacity() == 0){
                skin.setOpacity(1);
            } else {
                skin.setOpacity(0);
            }
        } else{
            isAttacked = false;
            isInvincible = false;
            animationDamageFrame = animationFrameDamageBuffer;
        }
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }
}

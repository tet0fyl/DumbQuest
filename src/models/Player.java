package models;

import views.graphicalElements.PlayerView;

public class Player {
    private byte pv;
    private byte pvMax;
    private double x,y, vitesse;
    public enum Mouvement{ UP, DOWN, RIGHT, LEFT }
    private PlayerView playerView;


    public Player(){
        playerView = new PlayerView(this);
        pvMax = 3;
        pv=pvMax;
        x=0;
        y=0;
        vitesse=5;
    }

    public void move(Mouvement mouvement){
        if(mouvement.equals(Mouvement.UP)){
            y -= vitesse;
        }
        if(mouvement.equals(Mouvement.DOWN)){
            y += vitesse;
        }
        if(mouvement.equals(Mouvement.RIGHT)){
            x += vitesse;
        }
        if(mouvement.equals(Mouvement.LEFT)){
            x -= vitesse;
        }
        playerView.update();
    }

    public byte getPv() {
        return pv;
    }

    public byte getPvMax() {
        return pvMax;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVitesse() {
        return vitesse;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }
}

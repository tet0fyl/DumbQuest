package models;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.worldMap.Tile;
import models.worldMap.WorldMap;

public class Player extends Pane {
    private byte pv;
    private byte pvMax;
    private WorldMap worldMap;
    private Tile currentTile;
    private Tile previousTile;
    private double x,y, vitesse;
    private Circle hitBox;
    private ImageView skin;

    public Player(WorldMap worldMap){
        this.worldMap = worldMap;
        pvMax = 3;
        pv=pvMax;
        x=0;
        y=0;
        vitesse=5;

        hitBox = new Circle();
        setLayoutX(x);
        setLayoutY(y);
        hitBox.setFill(Color.RED);
        hitBox.setRadius(10);

        this.getChildren().addAll(hitBox);
        previousTile = updateTile();

        worldMap.setPlayerInWorldMap(this);
    }

    public void move(Mouvement mouvement){
        if(currentTile.isTraversable())switchTile() ;

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
        updateTile();
    }

    public void update(){
        hitBox.setCenterX(x);
        hitBox.setCenterY(y);
    }

    public void switchTile(){
        previousTile.setEmpty(true);
        previousTile = currentTile;
        currentTile.setEmpty(false);
    }

    public Tile updateTile(){
        return currentTile = worldMap.getAreaMap().getTiles()[(int)Math.floor(x/WorldMap.tileWidth)][(int)Math.floor(y/WorldMap.tileHeight)];
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

    public void setPosition(double x,double y) {
        this.x=x;
        this.y=y;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public Tile getPreviousTile() {
        return previousTile;
    }

    public void setPreviousTile(Tile previousTile) {
        this.previousTile = previousTile;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }
}

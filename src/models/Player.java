package models;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.worldMap.Tile;
import models.worldMap.WorldMap;

public class Player extends Pane {
    private byte pv;
    private byte pvMax;
    private Vector2 prevPosition;
    private Vector2 position;
    private CharacterState currentState;
    private int vitesse;
    private Integer rightConstraint, leftConstraint, topConstraint, bottomConstraint;
    private boolean isRightConstraint, isLeftConstraint, isTopConstraint, isBottomConstraint;
    private Rectangle hitBox;
    private int hitBoxWidth;
    private int hitBoxHeight;
    private int skinWidth;
    private int skinHeight;
    private Rectangle skin;
    private WorldMap worldMap;

    public Player(Vector2 position, WorldMap worldMap){
        this.worldMap = worldMap;
        this.position=position;
        prevPosition = position;
        isRightConstraint = false;
        isLeftConstraint = false;
        isTopConstraint = false;
        isBottomConstraint = false;
        currentState = CharacterState.STAND;
        pvMax = 3;
        pv=pvMax;
        vitesse=5;
        hitBoxWidth = 20;
        hitBoxHeight = 20;
        skinWidth = 30;
        skinHeight = 40;

        skin = new Rectangle();
        skin.setFill(Color.BLUE);
        skin.setOpacity(0.4);
        skin.setWidth(skinWidth);
        skin.setHeight(skinHeight);
        hitBox = new Rectangle();
        setLayoutX(position.getX());
        setLayoutY(position.getY());
        hitBox.setFill(Color.RED);
        hitBox.setWidth(hitBoxWidth);
        hitBox.setHeight(hitBoxHeight);
        hitBox.setX((skinWidth/2 - hitBoxWidth/2));
        hitBox.setY((skinHeight/2 - hitBoxHeight/2));

        getChildren().addAll(skin,hitBox);
        worldMap.getChildren().add(this);
    }

    public void move(KeyInput mouvement){
        checkTileNeighborhood();
        if(mouvement.equals(KeyInput.GO_UP)){
            if(!(topConstraint != null && topConstraint >= getTopConstrain()))
                position.add(0,-vitesse);
        }
        if(mouvement.equals(KeyInput.GO_DOWN)){
            if(!(bottomConstraint != null && bottomConstraint <= getBottomConstrain()))
                position.add(0,vitesse);
        }
        if(mouvement.equals(KeyInput.GO_RIGHT)){
            if(!(rightConstraint != null && rightConstraint <= getRightConstrain()))
                position.add(vitesse,0);
        }
        if(mouvement.equals(KeyInput.GO_LEFT)){
            if(!(leftConstraint != null && leftConstraint >= getLeftConstrain()))
                position.add(-vitesse,0);

        }
        update();
    }

    public void update(){
        setLayoutX(position.getX());
        setLayoutY(position.getY());
    }

    public void checkTileNeighborhood(){
        for (int i = - 1; i <= 1 ; i++) {
            for (int j = - 1; j <= 1 ; j++) {
                if(Math.abs(i) == Math.abs(j)) continue;
                if(getCoordX()+i <= -1 || getCoordY()+j <= -1 || getCoordX()+i >= WorldMap.tileXNumber || getCoordY() + j >= WorldMap.tileYNumber ) continue;
                Tile tile = worldMap.getAreaMap().getTiles()[getCoordX()+i][getCoordY()+j];
                if(!(tile.isTraversable())){
                    if(i==-1 && j==0){
                        leftConstraint = tile.getRightConstrain();
                    } else if(i==0 && j==-1){
                        topConstraint = tile.getBottomConstrain();
                    } else if(i==1 && j==0){
                        rightConstraint = tile.getLeftConstrain();
                    } else if(i==0 && j==1){
                        bottomConstraint = tile.getTopConstrain();
                    }
                } else {
                    if(i==-1 && j==0){
                        leftConstraint = null;
                    } else if(i==0 && j==-1){
                        topConstraint = null;
                    } else if(i==1 && j==0){
                        rightConstraint = null;
                    } else if(i==0 && j==1){
                        bottomConstraint = null;
                    }
                }
            }
        }
    }

    public int getCoordX(){
        return (int)Math.floor(getTheCenterX()/WorldMap.tileWidth);
    }

    public int getCoordY(){
        return (int)Math.floor(getTheCenterY()/WorldMap.tileHeight);
    }

    public int getTopConstrain(){
        return position.getY() + (int)hitBox.getY() ;
    }
    public int getBottomConstrain(){
        return (position.getY() +  skinHeight) - ((int)hitBox.getY());
    }
    public int getRightConstrain(){
        return position.getX() + skinWidth - ((int)hitBox.getX()) ;
    }
    public int getLeftConstrain(){
        return position.getX() + (int)hitBox.getX();
    }

    public int getTheCenterX(){
        return (skinWidth/2 + position.getX());
    }

    public int getTheCenterY(){
        return (skinHeight/2 + position.getY());
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public Rectangle getSkin() {
        return skin;
    }

    public byte getPv() {
        return pv;
    }

    public byte getPvMax() {
        return pvMax;
    }

    public double getVitesse() {
        return vitesse;
    }

}

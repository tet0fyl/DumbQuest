package models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.worldMap.AreaMap;
import models.worldMap.Tile;
import models.worldMap.WorldMap;

public class Player extends Pane {
    private byte pv;
    private byte pvMax;
    private CharacterState currentState;
    private int vitesse;
    private Double rightConstraint, leftConstraint, topConstraint, bottomConstraint;
    private boolean isRightConstraint, isLeftConstraint, isTopConstraint, isBottomConstraint;
    private Rectangle hitBox;
    private int hitBoxWidth;
    private int hitBoxHeight;
    private int skinWidth;
    private int skinHeight;
    private Rectangle skin;
    private double x;
    private double y;

    public Player(int areaX, int areaY, int tileX, int tileY){
        this.x = areaX * WorldMap.areaWidth + tileX * WorldMap.tileWidth;
        this.y = areaY * WorldMap.areaHeight + tileY * WorldMap.tileHeight;
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
        update();
        hitBox.setFill(Color.RED);
        hitBox.setWidth(hitBoxWidth);
        hitBox.setHeight(hitBoxHeight);
        hitBox.setX((skinWidth/2 - hitBoxWidth/2));
        hitBox.setY((skinHeight/2 - hitBoxHeight/2));
        getChildren().addAll(skin,hitBox);
    }

    public void move(Direction mouvement){
        if(mouvement.equals(Direction.GO_UP)){
                y -= vitesse;
        }
        if(mouvement.equals(Direction.GO_DOWN)){
                y += vitesse;
        }
        if(mouvement.equals(Direction.GO_RIGHT)){
                x += vitesse;
        }
        if(mouvement.equals(Direction.GO_LEFT)){
                x -= vitesse;
        }
        update();
    }

    public void update(){
        setLayoutX(x);
        setLayoutY(y);
    }

    public int getTileCoordX(){
        return (int)Math.floor((getTheCenterX())/WorldMap.tileWidth) % WorldMap.tileXNumber;
    }

    public int getTileCoordY(){
        return (int)Math.floor((getTheCenterY())/WorldMap.tileHeight) % WorldMap.tileYNumber;
    }

    public int getAreaCoordX(){
        return (int)Math.floor((getTheCenterX())/WorldMap.areaWidth) % WorldMap.areaXNumber;
    }

    public int getAreaCoordY(){
        return (int)Math.floor((getTheCenterY())/WorldMap.areaHeight) % WorldMap.areaYNumber;
    }

    public double getTopConstrain(){
        return (y + hitBox.getY()) % WorldMap.areaHeight ;
    }
    public double getBottomConstrain(){
        return (y + skinHeight - hitBox.getY()) % WorldMap.areaHeight;
    }
    public double getRightConstrain(){
        return (x + skinWidth - (hitBox.getX())) %  WorldMap.areaWidth;
    }
    public double getLeftConstrain(){
        return (x + hitBox.getX()) % WorldMap.areaWidth;
    }

    public boolean hasTopConstrain(){
        return (!(topConstraint != null && topConstraint >= getTopConstrain()));
    }

    public boolean hasRightConstrain(){
        return (!(rightConstraint != null && rightConstraint <= getRightConstrain()));
    }

    public boolean hasBottomConstrain(){
        return (!(bottomConstraint != null && bottomConstraint <= getBottomConstrain()));
    }

    public boolean hasLeftConstrain(){
        return (!(leftConstraint != null && leftConstraint >= getLeftConstrain()));
    }

    public double getTheCenterX(){
        return (skinWidth/2 + x);
    }

    public double getTheCenterY(){
        return (skinHeight/2 + y);
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

    public Double getRightConstraint() {
        return rightConstraint;
    }

    public void setRightConstraint(Double rightConstraint) {
        this.rightConstraint = rightConstraint;
    }

    public Double getLeftConstraint() {
        return leftConstraint;
    }

    public void setLeftConstraint(Double leftConstraint) {
        this.leftConstraint = leftConstraint;
    }

    public Double getTopConstraint() {
        return topConstraint;
    }

    public void setTopConstraint(Double topConstraint) {
        this.topConstraint = topConstraint;
    }

    public Double getBottomConstraint() {
        return bottomConstraint;
    }

    public void setBottomConstraint(Double bottomConstraint) {
        this.bottomConstraint = bottomConstraint;
    }
}

package models;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.worldMap.WorldMap;


public class Moveable extends Pane {

    protected int vitesse;
    protected Rectangle hitBox;
    protected double hitBoxWidth;
    protected double hitBoxHeight;
    protected Rectangle attackBox;
    protected double attackWidth;
    protected double attackHeight;
    protected double skinWidth;
    protected double skinHeight;
    protected Rectangle skin;
    protected double x;
    protected double y;
    protected double prevX;
    protected double prevY;

    public Moveable(int areaX, int areaY, int tileX, int tileY, double boxWidth, double boxHeight, double attackBoxX, double attackBoxY, int vitesse){
        this.x = areaX * WorldMap.areaWidth + tileX * WorldMap.tileWidth;
        this.y = areaY * WorldMap.areaHeight + tileY * WorldMap.tileHeight;
        this.vitesse=vitesse;
        skinWidth = WorldMap.tileWidth * boxWidth/4;
        skinHeight = WorldMap.tileHeight * boxHeight/4;
        attackWidth = WorldMap.tileWidth * attackBoxX/4;
        attackHeight = WorldMap.tileHeight * attackBoxY/4;;
        hitBoxWidth = skinWidth - 10;
        hitBoxHeight = skinHeight -10;

        attackBox = new Rectangle();
        attackBox.setFill(Color.GREY);
        attackBox.setOpacity(0.4);
        attackBox.setWidth(attackWidth);
        attackBox.setHeight(attackHeight);

        skin = new Rectangle();
        skin.setFill(Color.YELLOW);
        skin.setWidth(skinWidth);
        skin.setHeight(skinHeight);

        update();

        hitBox = new Rectangle();
        hitBox.setFill(Color.RED);
        centerARectangle(hitBox, hitBoxWidth, hitBoxHeight);
        getChildren().addAll( skin, hitBox, attackBox);
    }

    public void centerAnImage(ImageView img, double width){
        img.setFitHeight(width);
        img.setPreserveRatio(true);
        img.setLayoutX(skinWidth/2 - width/2);
        img.setLayoutY(skinHeight/2 - img.getFitHeight()/2);
    }

    public void centerARectangle(Rectangle rect, double width, double height){

        rect.setWidth(width);
        rect.setHeight(height);
        rect.setX((skinWidth/2 - width/2));
        rect.setY((skinHeight/2 - height/2));
    }

    public void memory(){
        prevX = x;
        prevY = y;
    }

    public void valideTheMove(boolean ifPlayerCanMove){
        if(!ifPlayerCanMove){
            x = prevX;
            y = prevY;
            }
    }

    public void update(){
        setLayoutX(x);
        setLayoutY(y);
    }

    public void animate(){
    }


    public int getXMin(){
        return (int)((x + hitBox.getX()));
    }

    public int getYMin(){
        return (int)((y + hitBox.getY()) );
    }

    public int getXMax(){
        return (int)((x + skinWidth - hitBox.getX()) );
    }

    public int getYMax(){
        return (int)((y + skinHeight - hitBox.getY()));
    }


    public boolean collision(Moveable other){
        if ( Math.abs(getTheCenterHitBoxX() - other.getTheCenterHitBoxX()) > getTheHalfSizeHitBoxX() + other.getTheHalfSizeHitBoxX() ) return false;
        if ( Math.abs(getTheCenterHitBoxY() - other.getTheCenterHitBoxY()) > getTheHalfSizeHitBoxY() + other.getTheHalfSizeHitBoxY() ) return false;
        return true;
    }

    public boolean attackTouch(Moveable other){
        if ( Math.abs(getTheCenterAttackX() - other.getTheCenterHitBoxX()) > getTheHalfSizeAttackX() + other.getTheHalfSizeHitBoxX() ) return false;
        if ( Math.abs(getTheCenterAttackY() - other.getTheCenterHitBoxY()) > getTheHalfSizeAttackY() + other.getTheHalfSizeHitBoxY() ) return false;
        return true;
    }

    public double getTheHalfSizeAttackX(){
        return hitBoxWidth/2;
    }

    public double getTheHalfSizeAttackY(){
        return hitBoxHeight/2;
    }

    public double getTheHalfSizeHitBoxX(){
        return attackWidth/2;
    }

    public double getTheHalfSizeHitBoxY(){
        return attackHeight/2;
    }

    public int getTileCoordX(){
        return (int)Math.floor((getTheCenterHitBoxX())/WorldMap.tileWidth) % WorldMap.tileXNumber;
    }

    public int getTileCoordY(){
        return (int)Math.floor((getTheCenterHitBoxY())/WorldMap.tileHeight) % WorldMap.tileYNumber;
    }

    public int getAreaCoordX(){
        return (int)Math.floor((getTheCenterHitBoxX())/WorldMap.areaWidth) % WorldMap.areaXNumber;
    }

    public int getAreaCoordY(){
        return (int)Math.floor((getTheCenterHitBoxY())/WorldMap.areaHeight) % WorldMap.areaYNumber;
    }
    public double getTheCenterHitBoxX(){
        return (x + skinWidth/2);
    }

    public double getTheCenterHitBoxY(){
        return (y + skinHeight/2);
    }

    public double getTheCenterAttackX(){
        return (attackWidth/2 + attackBox.getX() + x);
    }

    public double getTheCenterAttackY(){
        return (attackHeight/2 + attackBox.getY() + y);
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }


    public void setHitBoxWidth(int hitBoxWidth) {
        this.hitBoxWidth = hitBoxWidth;
    }


    public void setHitBoxHeight(int hitBoxHeight) {
        this.hitBoxHeight = hitBoxHeight;
    }

    public Rectangle getAttackBox() {
        return attackBox;
    }

    public void setAttackBox(Rectangle attackBox) {
        this.attackBox = attackBox;
    }


    public void setAttackWidth(int attackWidth) {
        this.attackWidth = attackWidth;
    }


    public void setAttackHeight(int attackHeight) {
        this.attackHeight = attackHeight;
    }


    public void setSkinWidth(int skinWidth) {
        this.skinWidth = skinWidth;
    }


    public void setSkinHeight(int skinHeight) {
        this.skinHeight = skinHeight;
    }

    public void setSkin(Rectangle skin) {
        this.skin = skin;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public double getHitBoxWidth() {
        return hitBoxWidth;
    }

    public void setHitBoxWidth(double hitBoxWidth) {
        this.hitBoxWidth = hitBoxWidth;
    }

    public double getHitBoxHeight() {
        return hitBoxHeight;
    }

    public void setHitBoxHeight(double hitBoxHeight) {
        this.hitBoxHeight = hitBoxHeight;
    }

    public double getAttackWidth() {
        return attackWidth;
    }

    public void setAttackWidth(double attackWidth) {
        this.attackWidth = attackWidth;
    }

    public double getAttackHeight() {
        return attackHeight;
    }

    public void setAttackHeight(double attackHeight) {
        this.attackHeight = attackHeight;
    }

    public double getSkinWidth() {
        return skinWidth;
    }

    public void setSkinWidth(double skinWidth) {
        this.skinWidth = skinWidth;
    }

    public double getSkinHeight() {
        return skinHeight;
    }

    public void setSkinHeight(double skinHeight) {
        this.skinHeight = skinHeight;
    }

    public Rectangle getSkin() {
        return skin;
    }

    public double getPrevX() {
        return prevX;
    }

    public void setPrevX(double prevX) {
        this.prevX = prevX;
    }

    public double getPrevY() {
        return prevY;
    }

    public void setPrevY(double prevY) {
        this.prevY = prevY;
    }
}

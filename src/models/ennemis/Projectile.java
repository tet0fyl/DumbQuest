package models.ennemis;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.Moveable;
import models.worldMap.Tile;
import models.worldMap.WorldMap;


public class Projectile extends Pane {

    public double a, b;
    public Circle hitBox;
    public Circle skin;
    public Circle blur;
    public double vitesse;
    public boolean hasExploded;
    private double x,y;
    private double targetX;
    private double targetY;
    private double angle;

    public Projectile(Moveable moveableLauncher, Tile target){
        hitBox = new Circle();
        skin = new Circle();
        blur = new Circle();
        blur.setRadius(15);
        hitBox.setRadius(10);
        skin.setRadius(10);
        blur.setOpacity(0.5);
        blur.setFill(Color.RED);
        hitBox.setFill(Color.RED);
        skin.setFill(Color.RED);
        hasExploded = false;
        x = moveableLauncher.getLayoutX();
        y = moveableLauncher.getLayoutY();
        update();
        getChildren().addAll(hitBox,skin,blur);
        targetX = target.getTheCenterX();
        targetY = target.getTheCenterY();
        vitesse = 5;
        angle = -Math.atan2(targetY - getTheCenterY(),
                targetX - getTheCenterX());
    }

    public boolean collision(Moveable other){
        if ( Math.abs(getTheCenterX() - other.getTheCenterHitBoxX()) > hitBox.getRadius() + other.getTheHalfSizeHitBoxX() ) return false;
        if ( Math.abs(getTheCenterY() - other.getTheCenterHitBoxY()) > hitBox.getRadius() + other.getTheHalfSizeHitBoxY() ) return false;
        return true;
    }

    public double getTheCenterX(){
        return  hitBox.getCenterX() + x;
    }

    public double getTheCenterY(){
        return  hitBox.getCenterY() + y;
    }


    public void move(){
        double  vx = Math.cos(angle) * vitesse,
                vy = Math.sin(angle) * vitesse;
        x += vx;
        y -= vy;
    }

    public void update(){
        setLayoutX(x);
        setLayoutY(y);
    }

    public int getTileCoordX(){
        return (int)Math.floor((getTheCenterX())/ WorldMap.tileWidth) % WorldMap.tileXNumber;
    }

    public int getTileCoordY(){
        return (int)Math.floor((getTheCenterY())/WorldMap.tileHeight) % WorldMap.tileYNumber;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public Circle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Circle hitBox) {
        this.hitBox = hitBox;
    }

    public Circle getSkin() {
        return skin;
    }

    public void setSkin(Circle skin) {
        this.skin = skin;
    }

    public Circle getBlur() {
        return blur;
    }

    public void setBlur(Circle blur) {
        this.blur = blur;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public boolean isHasExploded() {
        return hasExploded;
    }

    public void setHasExploded(boolean hasExploded) {
        this.hasExploded = hasExploded;
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


}

package models.ennemis;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.Moveable;
import models.worldMap.Tile;
import models.worldMap.WorldMap;
import utils.RessourcePath;


public class Projectile extends Pane {

    public double a, b;
    public Circle hitBox;
    public Circle skin;
    public Circle blur;
    public double vitesse;
    public boolean hasExploded, isDestroyed;
    private double x,y;
    private double targetX;
    private double targetY;
    private double angle;
    private ImageView[] sprites;
    private ImageView sprite;
    private int spriteFrame = 0;

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
        initSprite();
        hitBox.setOpacity(0);
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

    public void animate(){
        blur.setOpacity(0);
        skin.setOpacity(0);
        if(hasExploded){
            explosionAnimation();
        }
    }

    public void explosionAnimation(){
        if(spriteFrame < sprites.length - 1 ){
            spriteFrame++;
            sprite.setImage(sprites[spriteFrame].getImage());
        } else {
            isDestroyed = true;
        }
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

    protected void initSprite(){
        sprites = new ImageView[11];
        for (int i = 0; i < sprites.length ; i++) {
            sprites[i] = new ImageView(RessourcePath.urlSpriteExplo + "/" + i + ".png" );
        }
        sprite = new ImageView();
        centerAnImage(sprite,100);
        sprite.setImage(sprites[spriteFrame].getImage());
        getChildren().add(sprite);
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

    public void centerAnImage(ImageView img, double width){
        img.setFitHeight(width);
        img.setPreserveRatio(true);
        img.setLayoutX(10/2 - width/2);
        img.setLayoutY(10/2 - img.getFitHeight()/2);
    }
}

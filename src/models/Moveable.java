package models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.ennemis.Ennemi;
import models.worldMap.WorldMap;

public class Moveable extends Pane {
    protected byte pv;
    protected byte pvMax;
    protected int vitesse;
    protected Double rightConstraint, leftConstraint, topConstraint, bottomConstraint;
    protected boolean isRightConstraint, isLeftConstraint, isTopConstraint, isBottomConstraint, isAttacking, isStandingBy, isAttacked, isAlive;
    protected Rectangle hitBox;
    protected int hitBoxWidth;
    protected int hitBoxHeight;
    protected Rectangle attackBox;
    protected int attackWidth;
    protected int attackHeight;
    protected int skinWidth;
    protected int skinHeight;
    protected Rectangle skin;
    protected double x;
    protected double y;
    protected int animationAttackFrame = 4;
    protected int animationDamageFrame = 4;

    public Moveable(int areaX, int areaY, int tileX, int tileY){
        this.x = areaX * WorldMap.areaWidth + tileX * WorldMap.tileWidth;
        this.y = areaY * WorldMap.areaHeight + tileY * WorldMap.tileHeight;
        isRightConstraint = false;
        isLeftConstraint = false;
        isTopConstraint = false;
        isBottomConstraint = false;

    }

    protected void initModels(){
        pvMax = 3;
        pv=pvMax;
        vitesse=5;
    }

    protected void initView(Color color){
        skinWidth = (int)WorldMap.tileWidth * 3 / 4;
        skinHeight = (int)WorldMap.tileHeight * 3 / 4;
        hitBoxWidth = skinWidth - 10;
        hitBoxHeight = skinHeight -10;

        attackWidth = skinWidth;
        attackHeight = skinHeight;
        attackBox = new Rectangle();
        attackBox.setFill(Color.GREY);
        attackBox.setOpacity(0.4);
        attackBox.setX(skinWidth);
        attackBox.setWidth(attackWidth);
        attackBox.setHeight(attackHeight);

        skin = new Rectangle();
        skin.setFill(color);
        skin.setWidth(skinWidth);
        skin.setHeight(skinHeight);

        update();

        hitBox = new Rectangle();
        hitBox.setFill(Color.RED);
        hitBox.setWidth(hitBoxWidth);
        hitBox.setHeight(hitBoxHeight);
        hitBox.setX((skinWidth/2 - hitBoxWidth/2));
        hitBox.setY((skinHeight/2 - hitBoxHeight/2));
        getChildren().addAll(skin, hitBox, attackBox);
    }

    public void move(Direction mouvement){
        if(mouvement.equals(Direction.GO_UP)){
            y -= vitesse;
            attackBox.setX(0);
            attackBox.setY(-skinHeight);
        }
        if(mouvement.equals(Direction.GO_DOWN)){
            y += vitesse;
            attackBox.setX(0);
            attackBox.setY(skinHeight);
        }
        if(mouvement.equals(Direction.GO_RIGHT)){
            x += vitesse;
            attackBox.setX(skinWidth);
            attackBox.setY(0);
        }
        if(mouvement.equals(Direction.GO_LEFT)){
            x -= vitesse;
            attackBox.setX(-skinWidth);
            attackBox.setY(0);
        }
    }

    public void attack(Ennemi ennemie){
        ennemie.setAttacked(true);
    }

    public void update(){
        setLayoutX(x);
        setLayoutY(y);
    }

    public void animate(){
        if(isAttacking){
            attackAnimation();
        }
        if(isAttacked){
            damageAnimation();
        }
    }

    public void attackAnimation(){
        if(animationAttackFrame != 0){
            animationAttackFrame--;
        } else{
            isAttacking = false;
            animationAttackFrame = 4;
        }
    }

    public void damageAnimation(){
        if(animationDamageFrame != 0){
            animationDamageFrame--;
            if(skin.getOpacity() == 0){
                skin.setOpacity(1);
            } else {
                skin.setOpacity(0);
            }
        } else{
            isAttacked = false;
            animationDamageFrame = 4;
        }
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

    public double getTheCenterHitBoxX(){
        return (skinWidth/2 + x);
    }

    public double getTheCenterHitBoxY(){
        return (skinHeight/2 + y);
    }

    public double getTheCenterAttackX(){
        return (attackWidth/2 + attackBox.getX() + x);
    }

    public double getTheCenterAttackY(){
        return (attackHeight/2 + attackBox.getY() + y);
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

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public void setPv(byte pv) {
        this.pv = pv;
    }

    public void setPvMax(byte pvMax) {
        this.pvMax = pvMax;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public boolean isRightConstraint() {
        return isRightConstraint;
    }

    public void setRightConstraint(boolean rightConstraint) {
        isRightConstraint = rightConstraint;
    }

    public boolean isLeftConstraint() {
        return isLeftConstraint;
    }

    public void setLeftConstraint(boolean leftConstraint) {
        isLeftConstraint = leftConstraint;
    }

    public boolean isTopConstraint() {
        return isTopConstraint;
    }

    public void setTopConstraint(boolean topConstraint) {
        isTopConstraint = topConstraint;
    }

    public boolean isBottomConstraint() {
        return isBottomConstraint;
    }

    public void setBottomConstraint(boolean bottomConstraint) {
        isBottomConstraint = bottomConstraint;
    }

    public boolean isStandingBy() {
        return isStandingBy;
    }

    public void setStandingBy(boolean standingBy) {
        isStandingBy = standingBy;
    }

    public boolean isAttacked() {
        return isAttacked;
    }

    public void setAttacked(boolean attacked) {
        isAttacked = attacked;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public int getHitBoxWidth() {
        return hitBoxWidth;
    }

    public void setHitBoxWidth(int hitBoxWidth) {
        this.hitBoxWidth = hitBoxWidth;
    }

    public int getHitBoxHeight() {
        return hitBoxHeight;
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

    public int getAttackWidth() {
        return attackWidth;
    }

    public void setAttackWidth(int attackWidth) {
        this.attackWidth = attackWidth;
    }

    public int getAttackHeight() {
        return attackHeight;
    }

    public void setAttackHeight(int attackHeight) {
        this.attackHeight = attackHeight;
    }

    public int getSkinWidth() {
        return skinWidth;
    }

    public void setSkinWidth(int skinWidth) {
        this.skinWidth = skinWidth;
    }

    public int getSkinHeight() {
        return skinHeight;
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

    public int getAnimationAttackFrame() {
        return animationAttackFrame;
    }

    public void setAnimationAttackFrame(int animationAttackFrame) {
        this.animationAttackFrame = animationAttackFrame;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}

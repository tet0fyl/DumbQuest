package models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.worldMap.WorldMap;

public class Moveable extends Pane {
    protected byte pv;
    protected byte pvMax;
    protected int vitesse;
    protected Double rightConstraint, leftConstraint, topConstraint, bottomConstraint;
    protected boolean isBlockInAxisX, isBlockInAxisY, isRightConstraint, isLeftConstraint, isTopConstraint, isBottomConstraint, isAttacking, isStandingBy, isAttacked, isAlive, isBlocking;
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
    protected int animationFrameDamageBuffer = 4;
    protected int animationAttackFrame = 4;
    protected int animationDamageFrame = animationFrameDamageBuffer;
    protected boolean releaseAttack = false;

    public Moveable(int areaX, int areaY, int tileX, int tileY){
        this.x = areaX * WorldMap.areaWidth + tileX * WorldMap.tileWidth;
        this.y = areaY * WorldMap.areaHeight + tileY * WorldMap.tileHeight;

    }

    protected void initModels(double tileX, double tileY, double attackBoxX, double attackBoxY){
        pvMax = 3;
        pv=pvMax;
        vitesse=5;
        skinWidth = WorldMap.tileWidth * tileX/4;
        skinHeight = WorldMap.tileHeight * tileY/4;
        attackWidth = WorldMap.tileWidth * attackBoxX/4;
        attackHeight = WorldMap.tileHeight * attackBoxY/4;;
        hitBoxWidth = skinWidth - 10;
        hitBoxHeight = skinHeight -10;
    }

    protected void initModels(double tileX, double tileY){
        pvMax = 3;
        pv=pvMax;
        vitesse=5;
        skinWidth = WorldMap.tileWidth * tileX/4;
        skinHeight = WorldMap.tileHeight * tileY/4;
        attackWidth = skinWidth;
        attackHeight = skinHeight;
        hitBoxWidth = skinWidth - 10;
        hitBoxHeight = skinHeight -10;
    }

    protected void initModels(){
        pvMax = 3;
        pv=pvMax;
        vitesse=5;
        skinWidth = WorldMap.tileWidth * 3/4;
        skinHeight = WorldMap.tileHeight * 3/4;
        attackWidth = skinWidth;
        attackHeight = skinHeight;
        hitBoxWidth = skinWidth - 10;
        hitBoxHeight = skinHeight -10;
    }

    protected void initView(Color color){
        attackBox = new Rectangle();
        attackBox.setFill(Color.GREY);
        attackBox.setOpacity(0.4);
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
            attackBox.setX(skinWidth/2 - attackWidth/2);
            attackBox.setY(-skinHeight + attackHeight/2);
        }
        else if(mouvement.equals(Direction.GO_DOWN)){
            y += vitesse;
            attackBox.setX(skinWidth/2 - attackWidth/2);
            attackBox.setY(skinHeight);
        }
        else if(mouvement.equals(Direction.GO_RIGHT) ){
            x += vitesse;
            attackBox.setX(skinWidth);
            attackBox.setY(skinHeight/2 - attackHeight/2);
        }
        else if(mouvement.equals(Direction.GO_LEFT) ){
            x -= vitesse;
            attackBox.setX(-skinWidth+attackWidth/2);
            attackBox.setY(skinHeight/2 - attackHeight/2);
        }
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

    public void attack(Moveable moveable){
        isAttacking = true;
        if(moveable instanceof Player){
            if (!((Player) moveable).isInvincible()){
                moveable.setAttacked(true);
            }
        } else {
            moveable.setAttacked(true);
        }
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
            animationDamageFrame = animationFrameDamageBuffer;
        }
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

    public byte getPv() {
        return pv;
    }

    public byte getPvMax() {
        return pvMax;
    }

    public int getVitesse() {
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

    public boolean isBlockInAxisX() {
        return isBlockInAxisX;
    }

    public void setBlockInAxisX(boolean blockInAxisX) {
        isBlockInAxisX = blockInAxisX;
    }

    public boolean isBlockInAxisY() {
        return isBlockInAxisY;
    }

    public void setBlockInAxisY(boolean blockInAxisY) {
        isBlockInAxisY = blockInAxisY;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
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

    public int getAnimationFrameDamageBuffer() {
        return animationFrameDamageBuffer;
    }

    public void setAnimationFrameDamageBuffer(int animationFrameDamageBuffer) {
        this.animationFrameDamageBuffer = animationFrameDamageBuffer;
    }

    public int getAnimationDamageFrame() {
        return animationDamageFrame;
    }

    public void setAnimationDamageFrame(int animationDamageFrame) {
        this.animationDamageFrame = animationDamageFrame;
    }

    public boolean isReleaseAttack() {
        return releaseAttack;
    }

    public void setReleaseAttack(boolean releaseAttack) {
        this.releaseAttack = releaseAttack;
    }
}

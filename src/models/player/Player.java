package models.player;


import javafx.scene.image.ImageView;
import models.Direction;
import models.Moveable;
import models.ennemis.*;
import models.worldMap.WorldMap;
import utils.RessourcePath;

public class Player extends Moveable {
    protected byte pv;
    protected byte pvMax;
    protected boolean isInvincible, isMoving, isAttacking, isStandingBy, isAttacked, isAlive, isBlocking;
    private PlayerHud playerHud;
    private double mainImageWidth, mainImageHeight;
    private int animationFrameDamageBuffer = 10;
    private int animationAttackFrameBuffer = 3;
    private int animationAttackFrame = 0;
    private int animationDamageFrame = animationFrameDamageBuffer;
    private boolean releaseAttack = false;
    private int animationMoveFrameBuffer = 3;
    private int animationMoveFrame = 0;
    private ImageView mainImage;
    private ImageView[] currentSpriteMove;
    private ImageView[] currentSpriteAttack;
    private ImageView[] moveLeft, moveRight, moveUp, moveDown, attackUp, attackDown, attackLeft, attackRight;

    public Player(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY, 3, 3, 3, 3, (int) (WorldMap.tileWidth / 6));
        mainImageWidth = WorldMap.tileWidth * 2;
        pvMax = 20;
        pv = pvMax;
        playerHud = new PlayerHud(this);
        initSprite();
    }

    public void move(Direction mouvement) {
        isMoving = true;
        if (mouvement.equals(Direction.GO_UP)) {
            y -= vitesse;
            currentSpriteMove = moveUp;
            currentSpriteAttack = attackUp;
            attackBox.setX(skinWidth / 2 - attackWidth / 2);
            attackBox.setY(-skinHeight);
        } else if (mouvement.equals(Direction.GO_DOWN)) {
            y += vitesse;
            currentSpriteMove = moveDown;
            currentSpriteAttack = attackDown;
            attackBox.setX(skinWidth / 2 - attackWidth / 2);
            attackBox.setY(skinHeight);
        } else if (mouvement.equals(Direction.GO_RIGHT)) {
            x += vitesse;
            currentSpriteMove = moveRight;
            currentSpriteAttack = attackRight;
            attackBox.setX(skinWidth);
            attackBox.setY(skinHeight / 2 - attackHeight / 2);
        } else if (mouvement.equals(Direction.GO_LEFT)) {
            x -= vitesse;
            currentSpriteMove = moveLeft;
            currentSpriteAttack = attackLeft;
            attackBox.setX(-skinWidth);
            attackBox.setY(skinHeight / 2 - attackHeight / 2);
        }
    }

    public void attack(Ennemi ennemi) {
        isAttacking = true;
        if (ennemi instanceof Soldier) {
            ((Soldier) ennemi).setAttacked(true);
            ennemi.subitDegat();
        } else if (ennemi instanceof Plant) {
            ((Plant) ennemi).setAttacked(true);
            ennemi.subitDegat();
        } else if (ennemi instanceof Worm) {
            ((Worm) ennemi).setAttacked(true);
            ennemi.subitDegat();
        } else if (ennemi instanceof Boss) {
            ((Boss) ennemi).setAttacked(true);
        }
    }

    @Override
    public void animate() {
        if (isAttacking) {
            attackAnimation();
        } else if (isMoving) {
            moveAnimation();
        }
        if (isAttacked) {
            damageAnimation();
        }
    }

    public void damageAnimation() {
        if (animationDamageFrame != 0) {
            isInvincible = true;
            animationDamageFrame--;
            if (mainImage.getOpacity() == 0) {
                mainImage.setOpacity(1);
            } else {
                mainImage.setOpacity(0);
            }
        } else {
            isAttacked = false;
            isInvincible = false;
            animationDamageFrame = animationFrameDamageBuffer;
        }
    }

    public void attackAnimation() {
        if (animationAttackFrame != animationAttackFrameBuffer) {
            animationAttackFrame++;
            mainImage.setImage(currentSpriteAttack[animationAttackFrame].getImage());
        } else {
            isAttacking = false;
            animationAttackFrame = 0;
        }
    }

    public void moveAnimation() {
        if (animationMoveFrame != animationMoveFrameBuffer) {
            animationMoveFrame++;
            mainImage.setImage(currentSpriteMove[animationMoveFrame].getImage());
        } else {
            animationMoveFrame = 0;
        }
        isMoving = false;
    }

    protected void initSprite() {
        moveLeft = new ImageView[]{
                new ImageView(RessourcePath.urlSpritePlayer + "/left/move/0.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/left/move/1.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/left/move/2.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/left/move/3.png"),
        };

        moveRight = new ImageView[]{
                new ImageView(RessourcePath.urlSpritePlayer + "/right/move/0.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/right/move/1.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/right/move/2.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/right/move/3.png"),
        };
        moveUp = new ImageView[]{
                new ImageView(RessourcePath.urlSpritePlayer + "/up/move/0.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/up/move/1.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/up/move/2.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/up/move/3.png"),
        };

        moveDown = new ImageView[]{
                new ImageView(RessourcePath.urlSpritePlayer + "/down/move/0.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/down/move/1.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/down/move/2.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/down/move/3.png"),
        };

        attackDown = new ImageView[]{
                new ImageView(RessourcePath.urlSpritePlayer + "/down/attack/0.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/down/attack/1.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/down/attack/2.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/down/attack/3.png"),
        };
        attackUp = new ImageView[]{
                new ImageView(RessourcePath.urlSpritePlayer + "/up/attack/0.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/up/attack/1.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/up/attack/2.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/up/attack/3.png"),
        };
        attackRight = new ImageView[]{
                new ImageView(RessourcePath.urlSpritePlayer + "/right/attack/0.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/right/attack/1.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/right/attack/2.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/right/attack/3.png"),
        };
        attackLeft = new ImageView[]{
                new ImageView(RessourcePath.urlSpritePlayer + "/left/attack/0.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/left/attack/1.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/left/attack/2.png"),
                new ImageView(RessourcePath.urlSpritePlayer + "/left/attack/3.png"),
        };
        mainImage = new ImageView();
        centerAnImage(mainImage, mainImageWidth);
        currentSpriteAttack = attackRight;
        currentSpriteMove = moveRight;
        mainImage.setImage(currentSpriteMove[0].getImage());
        getChildren().add(mainImage);
    }

    public void subitDegat() {
        pv -= 1;
        playerHud.update();
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    public byte getPv() {
        return pv;
    }

    public void setPv(byte pv) {
        this.pv = pv;
    }

    public byte getPvMax() {
        return pvMax;
    }

    public void setPvMax(byte pvMax) {
        this.pvMax = pvMax;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
    }

    public double getMainImageWidth() {
        return mainImageWidth;
    }

    public void setMainImageWidth(double mainImageWidth) {
        this.mainImageWidth = mainImageWidth;
    }

    public double getMainImageHeight() {
        return mainImageHeight;
    }

    public void setMainImageHeight(double mainImageHeight) {
        this.mainImageHeight = mainImageHeight;
    }

    public int getAnimationFrameDamageBuffer() {
        return animationFrameDamageBuffer;
    }

    public void setAnimationFrameDamageBuffer(int animationFrameDamageBuffer) {
        this.animationFrameDamageBuffer = animationFrameDamageBuffer;
    }

    public int getAnimationAttackFrameBuffer() {
        return animationAttackFrameBuffer;
    }

    public void setAnimationAttackFrameBuffer(int animationAttackFrameBuffer) {
        this.animationAttackFrameBuffer = animationAttackFrameBuffer;
    }

    public int getAnimationAttackFrame() {
        return animationAttackFrame;
    }

    public void setAnimationAttackFrame(int animationAttackFrame) {
        this.animationAttackFrame = animationAttackFrame;
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

    public int getAnimationMoveFrameBuffer() {
        return animationMoveFrameBuffer;
    }

    public void setAnimationMoveFrameBuffer(int animationMoveFrameBuffer) {
        this.animationMoveFrameBuffer = animationMoveFrameBuffer;
    }

    public int getAnimationMoveFrame() {
        return animationMoveFrame;
    }

    public void setAnimationMoveFrame(int animationMoveFrame) {
        this.animationMoveFrame = animationMoveFrame;
    }

    public ImageView getMainImage() {
        return mainImage;
    }

    public void setMainImage(ImageView mainImage) {
        this.mainImage = mainImage;
    }

    public ImageView[] getCurrentSpriteMove() {
        return currentSpriteMove;
    }

    public void setCurrentSpriteMove(ImageView[] currentSpriteMove) {
        this.currentSpriteMove = currentSpriteMove;
    }

    public ImageView[] getCurrentSpriteAttack() {
        return currentSpriteAttack;
    }

    public void setCurrentSpriteAttack(ImageView[] currentSpriteAttack) {
        this.currentSpriteAttack = currentSpriteAttack;
    }

    public ImageView[] getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(ImageView[] moveLeft) {
        this.moveLeft = moveLeft;
    }

    public ImageView[] getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(ImageView[] moveRight) {
        this.moveRight = moveRight;
    }

    public ImageView[] getMoveUp() {
        return moveUp;
    }

    public void setMoveUp(ImageView[] moveUp) {
        this.moveUp = moveUp;
    }

    public ImageView[] getMoveDown() {
        return moveDown;
    }

    public void setMoveDown(ImageView[] moveDown) {
        this.moveDown = moveDown;
    }

    public ImageView[] getAttackUp() {
        return attackUp;
    }

    public void setAttackUp(ImageView[] attackUp) {
        this.attackUp = attackUp;
    }

    public ImageView[] getAttackDown() {
        return attackDown;
    }

    public void setAttackDown(ImageView[] attackDown) {
        this.attackDown = attackDown;
    }

    public ImageView[] getAttackLeft() {
        return attackLeft;
    }

    public void setAttackLeft(ImageView[] attackLeft) {
        this.attackLeft = attackLeft;
    }

    public ImageView[] getAttackRight() {
        return attackRight;
    }

    public void setAttackRight(ImageView[] attackRight) {
        this.attackRight = attackRight;
    }

    public PlayerHud getPlayerHud() {
        return playerHud;
    }
}

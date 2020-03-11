package models.ennemis;

import javafx.scene.image.ImageView;
import models.player.Player;
import models.worldMap.Tile;
import models.worldMap.WorldMap;
import utils.RessourcePath;

public class Worm extends Ennemi {
    public boolean isInTheGround, isTargeting, isTargetDone, isOutSide;
    protected boolean isMoving, isAttacking, isStandingBy, isAttacked;
    private int inTheGroundBuffer = 15;
    private int trackingBuffer = 7;
    private int outSideBuffer = 20;
    private int inTheGround = inTheGroundBuffer;
    private int tracking = trackingBuffer;
    private int outSide = outSideBuffer;
    private Tile targetTile;
    private double mainImageWidth, mainImageHeight;
    private int animationFrameDamageBuffer = 4;
    private int animationAttackFrameBuffer = 3;
    private int animationAttackFrame = 0;
    private int animationDamageFrame = animationFrameDamageBuffer;
    private int animationMain = 0;
    private boolean releaseAttack = false;
    private int animationMoveFrameBuffer = 3;
    private int animationMoveFrame = 0;
    private ImageView mainImage;
    private ImageView[] currentSpriteMove;
    private int deathAnimationFrame = 0;
    private ImageView[] deathSprite;


    public Worm(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY, 4, 4, 4, 4, 1);
        mainImageWidth = WorldMap.tileWidth * 3;
        pvMax = 2;
        pv = pvMax;
        initSprite();
        isTargeting = false;
        isInTheGround = true;
        isTargetDone = false;
        vitesse = 2;
    }

    @Override
    public void animate() {
        if (isDying) {
            deathAnimation();
        } else {
            if (isAttacking) {
                attackAnimation();
            }
            if (isAttacked) {
                damageAnimation();
            }
            if (isInTheGround && !isTargeting) {
                intheGroundAnimation();
            }
            if (isTargeting && !isOutSide) {
                trackingAnimation();
            }
            if (isOutSide) {
                outsideAnimation();
            }
            if (pv <= 0) {
                isDying = true;
            }
        }
    }

    public void deathAnimation() {
        if (deathAnimationFrame != deathSprite.length - 1) {
            deathAnimationFrame++;
            mainImage.setImage(deathSprite[deathAnimationFrame].getImage());
        } else {
            animationMoveFrame = 0;
            isAlive = false;
        }
    }


    public void attackAnimation() {
        if (animationAttackFrame != animationAttackFrameBuffer) {
            animationAttackFrame++;
        } else {
            isAttacking = false;
            animationAttackFrame = 0;
        }
    }

    public void damageAnimation() {
        if (animationDamageFrame != 0) {
            animationDamageFrame--;
            if (mainImage.getOpacity() == 0) {
                mainImage.setOpacity(1);
            } else {
                mainImage.setOpacity(0);
            }
        } else {
            isAttacked = false;
            animationDamageFrame = animationFrameDamageBuffer;
        }
    }

    public void attack(Player player) {
        isAttacking = true;
        player.setAttacked(true);
        player.subitDegat();
    }

    public void intheGroundAnimation() {
        isTargetDone = false;
        if (inTheGround != 0) {
            inTheGround--;
        } else {
            mainImage.setOpacity(1);
            isTargeting = true;
            inTheGround = inTheGroundBuffer;
        }
    }

    protected void initSprite() {
        currentSpriteMove = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteWorm + "/0.png"),
                new ImageView(RessourcePath.urlSpriteWorm + "/1.png"),
                new ImageView(RessourcePath.urlSpriteWorm + "/2.png"),
                new ImageView(RessourcePath.urlSpriteWorm + "/3.png"),
                new ImageView(RessourcePath.urlSpriteWorm + "/4.png"),
                new ImageView(RessourcePath.urlSpriteWorm + "/5.png"),
                new ImageView(RessourcePath.urlSpriteWorm + "/6.png"),
        };
        deathSprite = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteWorm + "/death/0.png"),
                new ImageView(RessourcePath.urlSpriteWorm + "/death/1.png"),
                new ImageView(RessourcePath.urlSpriteWorm + "/death/2.png"),
                new ImageView(RessourcePath.urlSpriteWorm + "/death/3.png"),
        };
        mainImage = new ImageView();
        centerAnImage(mainImage, mainImageWidth);
        mainImage.setImage(currentSpriteMove[0].getImage());
        getChildren().add(mainImage);
    }

    public void trackingAnimation() {
        if (tracking != 0) {
            tracking--;
            if (tracking > trackingBuffer - 3) {
                animationMain++;
                mainImage.setImage(currentSpriteMove[animationMain].getImage());
            }
        } else {
            isOutSide = true;
            isInTheGround = false;
            tracking = trackingBuffer;
        }
    }

    public void outsideAnimation() {
        if (outSide != 0) {
            outSide--;
            if (animationMain < currentSpriteMove.length - 1 && outSide > 5) {
                animationMain++;
                mainImage.setImage(currentSpriteMove[animationMain].getImage());
            }
            if (animationMain != 0 && outSide <= 5) {
                animationMain--;
                mainImage.setImage(currentSpriteMove[animationMain].getImage());
            }
        } else {
            mainImage.setOpacity(0);
            isOutSide = false;
            isTargeting = false;
            isInTheGround = true;
            outSide = outSideBuffer;
        }
    }

    public void teleport(Tile targetTile) {
        this.targetTile = targetTile;
        x = targetTile.getLayoutX();
        y = targetTile.getLayoutY();
        update();
        isTargetDone = true;
    }

    public boolean isInTheGround() {
        return isInTheGround;
    }

    public boolean isTargeting() {
        return isTargeting;
    }

    public void setTargeting(boolean targeting) {
        isTargeting = targeting;
    }

    public boolean isOutSide() {
        return isOutSide;
    }

    public int getInTheGroundBuffer() {
        return inTheGroundBuffer;
    }

    public void setInTheGroundBuffer(int inTheGroundBuffer) {
        this.inTheGroundBuffer = inTheGroundBuffer;
    }

    public int getTrackingBuffer() {
        return trackingBuffer;
    }

    public void setTrackingBuffer(int trackingBuffer) {
        this.trackingBuffer = trackingBuffer;
    }

    public int getOutSideBuffer() {
        return outSideBuffer;
    }

    public void setOutSideBuffer(int outSideBuffer) {
        this.outSideBuffer = outSideBuffer;
    }

    public int getInTheGround() {
        return inTheGround;
    }

    public void setInTheGround(boolean inTheGround) {
        isInTheGround = inTheGround;
    }

    public void setInTheGround(int inTheGround) {
        this.inTheGround = inTheGround;
    }

    public int getTracking() {
        return tracking;
    }

    public void setTracking(int tracking) {
        this.tracking = tracking;
    }

    public int getOutSide() {
        return outSide;
    }

    public void setOutSide(boolean outSide) {
        isOutSide = outSide;
    }

    public void setOutSide(int outSide) {
        this.outSide = outSide;
    }

    public Tile getTargetTile() {
        return targetTile;
    }

    public void setTargetTile(Tile targetTile) {
        this.targetTile = targetTile;
    }

    public boolean isTargetDone() {
        return isTargetDone;
    }

    public void setTargetDone(boolean targetDone) {
        isTargetDone = targetDone;
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

}

package models.ennemis;

import javafx.scene.image.ImageView;
import models.player.Player;
import models.worldMap.Tile;
import models.worldMap.WorldMap;
import utils.RessourcePath;

import java.util.ArrayList;

public class Plant extends Ennemi {
    protected boolean isAttacked, isAttacking;
    boolean isReloading;
    private ArrayList<Projectile> projectiles;
    private int shootRate = 10;
    private int shootRateBuffer = shootRate;
    private double mainImageWidth;
    private int animationFrameDamageBuffer = 4;
    private int animationAttackFrameBuffer = 3;
    private int animationAttackFrame = 0;
    private int animationDamageFrame = animationFrameDamageBuffer;
    private int animationJiggleFrame = 0;
    private boolean releaseAttack = false;
    private int animationMoveFrameBuffer = 3;
    private int animationMoveFrame = 0;
    private ImageView mainImage;
    private ImageView[] currentSpriteMove;
    private int deathAnimationFrame = 0;
    private ImageView[] deathSprite;

    public Plant(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY, 4, 4, 1, 1, 1);
        isReloading = false;
        pvMax = 2;
        pv = pvMax;
        projectiles = new ArrayList<Projectile>();
        mainImageWidth = WorldMap.tileWidth;
        initSprite();
        vitesse = 2;
    }

    protected void initSprite() {
        currentSpriteMove = new ImageView[]{
                new ImageView(RessourcePath.urlSpritePlant + "/0.png"),
                new ImageView(RessourcePath.urlSpritePlant + "/1.png"),
                new ImageView(RessourcePath.urlSpritePlant + "/2.png"),
                new ImageView(RessourcePath.urlSpritePlant + "/3.png"),
        };
        deathSprite = new ImageView[]{
                new ImageView(RessourcePath.urlSpritePlant + "/death/0.png"),
                new ImageView(RessourcePath.urlSpritePlant + "/death/1.png"),
                new ImageView(RessourcePath.urlSpritePlant + "/death/2.png"),
        };
        mainImage = new ImageView();
        centerAnImage(mainImage, mainImageWidth);
        mainImage.setImage(currentSpriteMove[0].getImage());
        getChildren().add(mainImage);
    }


    public Projectile shoot(Tile tileTarget) {
        Projectile projectile = new Projectile(this, tileTarget);
        projectiles.add(projectile);
        isReloading = true;
        return projectile;
    }

    @Override
    public void animate() {
        jiggling();
        if (isDying) {
            deathAnimation();
        } else {
            if (isAttacked) {
                damageAnimation();
            }
            if (isReloading) {
                waitingReload();
            }
            if (pv <= 0) {
                isDying = true;
            }


        }
    }

    public void deathAnimation() {
        if (deathAnimationFrame != deathSprite.length - 1) {
            deathAnimationFrame++;
            System.out.println(deathAnimationFrame);
            mainImage.setImage(deathSprite[deathAnimationFrame].getImage());
        } else {
            animationMoveFrame = 0;
            isAlive = false;
        }
    }

    public void jiggling() {
        if (animationJiggleFrame <= currentSpriteMove.length - 1) {
            mainImage.setImage(currentSpriteMove[animationJiggleFrame].getImage());
            animationJiggleFrame++;
        } else {
            animationJiggleFrame = 0;
        }
    }

    public void attack(Player player) {
        isAttacking = true;
        player.setAttacked(true);
        player.subitDegat();
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

    public void waitingReload() {
        if (shootRateBuffer != 0) {
            shootRateBuffer--;
        } else {
            isReloading = false;
            shootRateBuffer = shootRate;
        }
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public boolean isReloading() {
        return isReloading;
    }

    public void setReloading(boolean reloading) {
        isReloading = reloading;
    }

    public int getShootRate() {
        return shootRate;
    }

    public void setShootRate(int shootRate) {
        this.shootRate = shootRate;
    }

    public boolean isAttacked() {
        return isAttacked;
    }

    public void setAttacked(boolean attacked) {
        isAttacked = attacked;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public int getShootRateBuffer() {
        return shootRateBuffer;
    }

    public void setShootRateBuffer(int shootRateBuffer) {
        this.shootRateBuffer = shootRateBuffer;
    }

    public double getMainImageWidth() {
        return mainImageWidth;
    }

    public void setMainImageWidth(double mainImageWidth) {
        this.mainImageWidth = mainImageWidth;
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

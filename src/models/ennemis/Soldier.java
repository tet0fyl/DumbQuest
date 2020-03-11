package models.ennemis;

import javafx.scene.image.ImageView;
import models.Direction;
import models.ia.GraphNode;
import models.player.Player;
import models.worldMap.Tile;
import models.worldMap.WorldMap;
import utils.RessourcePath;

import java.util.ArrayList;

public class Soldier extends Ennemi {

    protected boolean isMoving, isAttacking, isStandingBy, isAttacked;
    private ArrayList<GraphNode> destinationPath;
    private Integer currentNodeDestinationPath = 0;
    private boolean isAttackRready, isPreparingAttack;
    private int waitingttackReadyBuffer = 5;
    private int waitingAttackReady = waitingttackReadyBuffer;
    private double mainImageWidth, mainImageHeight;
    private int animationFrameDamageBuffer = 4;
    private int animationAttackFrameBuffer = 3;
    private int animationAttackFrame = 0;
    private int animationDamageFrame = animationFrameDamageBuffer;
    private boolean releaseAttack = false;
    private int animationMoveFrameBuffer = 3;
    private int animationMoveFrame = 0;
    private int deathAnimationFrame = 0;
    private ImageView[] deathSprite;
    private ImageView mainImage;
    private ImageView[] currentSpriteMove;
    private ImageView[] currentSpriteAttack;
    private ImageView[] moveLeft, moveRight, moveUp, moveDown, attackUp, attackDown, attackLeft, attackRight;

    public Soldier(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY, 4, 4, 4, 4, WorldMap.tileWidth/20);
        mainImageWidth = WorldMap.tileWidth * 2;
        pvMax = 3;
        pv = pvMax;
        isAttackRready = false;
        isPreparingAttack = false;
        initSprite();
    }

    protected void initSprite() {
        moveLeft = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGoblin + "/left/move/0.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/left/move/1.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/left/move/2.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/left/move/3.png"),
        };

        moveRight = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGoblin + "/right/move/0.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/right/move/1.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/right/move/2.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/right/move/3.png"),
        };
        moveUp = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGoblin + "/up/move/0.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/up/move/1.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/up/move/2.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/up/move/3.png"),
        };

        moveDown = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGoblin + "/down/move/0.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/down/move/1.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/down/move/2.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/down/move/3.png"),
        };

        attackDown = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGoblin + "/down/attack/0.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/down/attack/1.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/down/attack/2.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/down/attack/3.png"),
        };
        attackUp = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGoblin + "/up/attack/0.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/up/attack/1.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/up/attack/2.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/up/attack/3.png"),
        };
        attackRight = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGoblin + "/right/attack/0.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/right/attack/1.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/right/attack/2.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/right/attack/3.png"),
        };
        attackLeft = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGoblin + "/left/attack/0.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/left/attack/1.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/left/attack/2.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/left/attack/3.png"),
        };
        deathSprite = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGoblin + "/death/0.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/death/1.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/death/2.png"),
                new ImageView(RessourcePath.urlSpriteGoblin + "/death/3.png"),
        };
        mainImage = new ImageView();
        centerAnImage(mainImage, mainImageWidth);
        currentSpriteAttack = attackRight;
        currentSpriteMove = moveRight;
        mainImage.setImage(currentSpriteMove[0].getImage());
        getChildren().add(mainImage);
    }

    public void animate() {
        if (isAttacking) {
            attackAnimation();
        } else if (isMoving) {
            moveAnimation();
        }
        if (isAttacked) {
            damageAnimation();
        }
        if (isPreparingAttack) {
            preparingAttack();
        }
        if (pv <= 0) {
            isDying = true;
        }
        if (isDying) {
            deathAnimation();
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

    public void preparingAttack() {
        if (waitingAttackReady != 0) {
            waitingAttackReady--;
        } else {
            isAttackRready = true;
            isPreparingAttack = false;
            waitingAttackReady = waitingttackReadyBuffer;
        }
    }

    public void moveAnimation() {
        waitingAttackReady = waitingttackReadyBuffer;
        if (animationMoveFrame != animationMoveFrameBuffer) {
            animationMoveFrame++;
            mainImage.setImage(currentSpriteMove[animationMoveFrame].getImage());
        } else {
            animationMoveFrame = 0;
        }
        isMoving = false;
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

    public void attackAnimation() {
        if (animationAttackFrame != animationAttackFrameBuffer) {
            animationAttackFrame++;
            mainImage.setImage(currentSpriteAttack[animationAttackFrame].getImage());
        } else {
            isAttacking = false;
            isAttackRready = false;
            animationAttackFrame = 0;
        }
    }

    public void setDestination(ArrayList<GraphNode> destinationPath) {
        currentNodeDestinationPath = 1;
        this.destinationPath = destinationPath;
    }

    public void moveToTarget() {
        if (destinationPath != null) {
            Tile tile = destinationPath.get(currentNodeDestinationPath).tile;
            if (Math.abs(tile.getTheCenterX() - (getTheCenterHitBoxX())) > 5) {
                if (tile.getTheCenterX() < (getTheCenterHitBoxX())) move(Direction.GO_LEFT);
                if (tile.getTheCenterX() > (getTheCenterHitBoxX())) move(Direction.GO_RIGHT);
            }
            if (Math.abs(tile.getTheCenterY() - (getTheCenterHitBoxY())) > 5) {
                if (tile.getTheCenterY() < (getTheCenterHitBoxY())) move(Direction.GO_UP);
                if (tile.getTheCenterY() > (getTheCenterHitBoxY())) move(Direction.GO_DOWN);
            }
            if (Math.abs(tile.getTheCenterX() - (getTheCenterHitBoxX())) <= 5 && Math.abs(tile.getTheCenterY() - (getTheCenterHitBoxY())) <= 5) {
                currentNodeDestinationPath++;
            }
            if (destinationPath.size() - 1 <= currentNodeDestinationPath) {
                destinationPath = null;
            }
            update();
        }
    }

    public void move(Direction mouvement) {
        isMoving = true;
        if (mouvement.equals(Direction.GO_UP)) {
            y -= vitesse;
            currentSpriteMove = moveUp;
            currentSpriteAttack = attackUp;
            attackBox.setX(skinWidth / 2 - attackWidth / 2);
            attackBox.setY(-skinHeight - (attackHeight - skinHeight));
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
            attackBox.setX(-skinWidth - (attackWidth - skinWidth));
            attackBox.setY(skinHeight / 2 - attackHeight / 2);
        }
    }

    public void attack(Player player) {
        isAttacking = true;
        player.setAttacked(true);
        player.subitDegat();
    }


    public ArrayList<GraphNode> getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(ArrayList<GraphNode> destinationPath) {
        this.destinationPath = destinationPath;
    }

    public Tile getTheEndTile() {
        return destinationPath.get(destinationPath.size() - 1).tile;
    }

    public int getTheRestNodePathDestination() {
        return destinationPath.size() - currentNodeDestinationPath;
    }

    public int getCurrentNodeDestinationPath() {
        return currentNodeDestinationPath;
    }

    public void setCurrentNodeDestinationPath(Integer currentNodeDestinationPath) {
        this.currentNodeDestinationPath = currentNodeDestinationPath;
    }

    public boolean isAttackReady() {
        return isAttackRready;
    }

    public int getWaitingttackReadyBuffer() {
        return waitingttackReadyBuffer;
    }

    public void setWaitingttackReadyBuffer(int waitingttackReadyBuffer) {
        this.waitingttackReadyBuffer = waitingttackReadyBuffer;
    }

    public int getAttackRready() {
        return waitingAttackReady;
    }

    public boolean isAttackRready() {
        return isAttackRready;
    }

    public void setAttackRready(boolean attackRready) {
        isAttackRready = attackRready;
    }

    public boolean isPreparingAttack() {
        return isPreparingAttack;
    }

    public void setPreparingAttack(int preparingAttack) {
        this.waitingAttackReady = preparingAttack;
    }

    public void setPreparingAttack(boolean preparingAttack) {
        isPreparingAttack = preparingAttack;
    }

    public void setPreparingAttackReady(boolean preparingAttackReady) {
        isPreparingAttack = preparingAttackReady;
    }

    public int getWaitingAttackReady() {
        return waitingAttackReady;
    }

    public void setWaitingAttackReady(int waitingAttackReady) {
        this.waitingAttackReady = waitingAttackReady;
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
}

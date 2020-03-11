package models.ennemis;

import javafx.scene.image.ImageView;
import models.Direction;
import models.ia.GraphNode;
import models.player.Player;
import models.worldMap.Tile;
import models.worldMap.WorldMap;
import utils.RessourcePath;

import java.util.ArrayList;

public class Boss extends Ennemi {

    private int phaseMax = 3;
    private int phaseCurrent = 1;
    private int phase = 1;
    private int numberAtckPerPhaseThreshold = 3;
    private int numberAtckPerPhase = 0;
    private int maxVitesse = 10;
    private boolean isTired,isReloading;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private int shootRate = 10;
    private int shootRateBuffer = shootRate;
    private int tired = 10;
    private int deathAnimationFrame = 0;
    private int tiredBuffer = tired;
    private ArrayList<GraphNode> destinationPath;
    private Integer currentNodeDestinationPath = 0;
    private boolean isAttackRready, isPreparingAttack;
    private int waitingttackReadyBuffer = 2;
    private int waitingAttackReady = 0;
    private ArrayList<Worm> friend = new ArrayList<>();
    private boolean phase3Left = true;

    protected boolean isDying, isAfterAttacking, isPivoting, isTargetReached, isMoving, isAttacking, isStandingBy, isAttacked, isTargeting, isTargetDone, isInvicible, isAttackInMovement;
    private double mainImageWidth,mainImageHeight;
    private int animationFrameDamageBuffer = 4;
    private int animationAttackFrameBuffer = 3;
    private int animationAfterAttackingFrameBuffer = 2;
    private int animationAfterAtacking = 0;
    private int animationAttackFrame = 0;
    private int animationNotInvicibleFrameBuffer = 20;
    private int animationNotInvicibleFrame = 0;
    private int animationDamageFrame = animationFrameDamageBuffer;
    private boolean releaseAttack = false;
    private int animationMoveFrameBuffer = 3;
    private int animationMoveFrame = 0;
    private ImageView mainImage;
    private ImageView[] currentSpriteMove;
    private ImageView[] currentSpriteAttack;
    private ImageView[] moveLeft, moveRight,moveUp,moveDown, attackUp, attackDown, attackLeft, attackRight, deathSprite;
    private Tile target;
    private double angle;

    public Boss(int areaX, int areaY, int tileX, int tileY) {
        super(areaX, areaY, tileX, tileY,8,8,4,4,10);
        mainImageWidth = WorldMap.tileWidth*3;
        isTargeting = true;
        isInvicible = true;
        initSprite();
    }

    @Override
    public void animate() {
        timerPhase();
        if(!isInvicible){
            notInvisibleAnimation();
        } else {
            if(isPreparingAttack){
                preparingAttackAnimation();
            }
            if(isAfterAttacking){
                afterAttackingAnimation();
            }
            if(isAttacking){
                attackAnimation();
            }
            else if(isMoving) {
                moveAnimation();
            }
        }
        if(isAttacked){
            damageAnimation();
        }
        if(isDying){
            deathAnimation();
        }
    }

    public void timerPhase(){
        if(numberAtckPerPhase > numberAtckPerPhaseThreshold){
            numberAtckPerPhase = 0;
            phase--;
            if(phase == 0){
                phase = phaseCurrent;
            }
        }
        if(phaseCurrent > 3){
            isDying = true;
        }
    }

    public void setTarget(Tile tile){
        this.target = tile;
    }

    public void move(){
        if(Math.abs(x - target.getX()) > 10 ) {
            if (x > target.getX()) move(Direction.GO_LEFT);
            if (x < target.getX()) move(Direction.GO_RIGHT);
        }
        else if(Math.abs(y - target.getY()) > 10) {
            if(y > target.getY()) move(Direction.GO_UP);
            if(y < target.getY()) move(Direction.GO_DOWN);
        }else {
            isTargetReached = true;
            isPivoting = true;
        }
        update();
    }

    public void pivotToTarget(Tile target, Tile ownTile){
        vitesse = 1;
        if( ownTile.getIndiceX() > target.getIndiceX()) move(Direction.GO_LEFT);
        if(ownTile.getIndiceX() < target.getIndiceX()) move(Direction.GO_RIGHT);
        if(ownTile.getIndiceY() > target.getIndiceY()) move(Direction.GO_UP);
        if(ownTile.getIndiceY() < target.getIndiceY()) move(Direction.GO_DOWN);
        isPivoting = false;
        isPreparingAttack = true;
        vitesse = maxVitesse;
    }

    public void deathAnimation(){
        if(deathAnimationFrame != deathSprite.length-1){
            deathAnimationFrame++;
            mainImage.setImage(deathSprite[deathAnimationFrame].getImage());
        } else {
            animationMoveFrame = 0;
            isAlive = false;
        }
    }

        public void moveAnimation(){
            if(animationMoveFrame != currentSpriteMove.length-1){
                animationMoveFrame++;
                mainImage.setImage(currentSpriteMove[animationMoveFrame].getImage());
            } else {
                animationMoveFrame = 0;
            }
            isMoving=false;
        }

        public void damageAnimation() {
            if(animationDamageFrame != 0){
                animationDamageFrame--;
                if(mainImage.getOpacity() == 0){
                    mainImage.setOpacity(1);
                } else {
                    mainImage.setOpacity(0);
                }
            } else{
                isAttacked = false;
                isInvicible = true;
                phaseCurrent++;
                phase=phaseCurrent;
                numberAtckPerPhase = 0;
                animationDamageFrame = animationFrameDamageBuffer;
            }
        }

        public void attackAnimation(){
            if(animationAttackFrame < currentSpriteAttack.length-1){
                animationAttackFrame++;
                mainImage.setImage(currentSpriteAttack[animationAttackFrame].getImage());
            } else{
                isAttacking = false;
                isAttackRready = false;
                animationAttackFrame = 0;
                isAfterAttacking = true;
                numberAtckPerPhase++;
            }
        }

        public void afterAttackingAnimation(){
            if(animationAfterAtacking != animationAfterAttackingFrameBuffer){
                animationAfterAtacking++;
            } else {
                if(numberAtckPerPhase == numberAtckPerPhaseThreshold && phase == 1){
                    isInvicible = false;
                } else {
                    animationAfterAtacking=0;
                    isAfterAttacking=false;
                    isTargeting = true;
                    isTargetReached = false;
                }
            }
        }

        public void notInvisibleAnimation(){
            if(animationNotInvicibleFrame != animationNotInvicibleFrameBuffer){
                animationNotInvicibleFrame++;
            } else {
                isInvicible = true;
                animationAfterAtacking=0;
                animationNotInvicibleFrame=0;
                isAfterAttacking=false;
                isTargeting = true;
                isTargetReached = false;
            }
        }

        public void preparingAttackAnimation(){
            if(waitingAttackReady <= waitingttackReadyBuffer){
                waitingAttackReady++;
                if(waitingAttackReady <= 3){
                    animationAttackFrame++;
                    mainImage.setImage(currentSpriteAttack[animationAttackFrame].getImage());
                }
            } else {
                waitingAttackReady = 0;
                animationAttackFrame = 0;
                isPreparingAttack = false;
                isAttackRready = true;
            }
        }


    public Projectile shoot(Tile tileTarget) {
        Projectile projectile = new Projectile(this, tileTarget);
        projectiles.add(projectile);
        isReloading = true;
        return projectile;
    }

    public void move(Direction mouvement){
        isMoving = true;
        if(mouvement.equals(Direction.GO_UP)){
            y -= vitesse;
            currentSpriteMove = moveUp;
            currentSpriteAttack = attackUp;
            attackBox.setX(skinWidth/2 - attackWidth/2);
            attackBox.setY(-skinHeight - (attackHeight - skinHeight));
        }
        else if(mouvement.equals(Direction.GO_DOWN)){
            y += vitesse;
            currentSpriteMove = moveDown;
            currentSpriteAttack = attackDown;
            attackBox.setX(skinWidth/2 - attackWidth/2);
            attackBox.setY(skinHeight);
        }
        else if(mouvement.equals(Direction.GO_RIGHT) ){
            x += vitesse;
            currentSpriteMove = moveRight;
            currentSpriteAttack = attackRight;
            attackBox.setX(skinWidth);
            attackBox.setY(skinHeight/2 - attackHeight/2);
        }
        else if(mouvement.equals(Direction.GO_LEFT) ){
            x -= vitesse;
            currentSpriteMove = moveLeft;
            currentSpriteAttack = attackLeft;
            attackBox.setX(-skinWidth - (attackWidth - skinWidth));
            attackBox.setY(skinHeight/2 - attackHeight/2);
        }
    }

    public void attack(Player player){
        isAttacking = true;
        player.setAttacked(true);
        player.subitDegat();
    }


    protected void initSprite(){
        moveLeft = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGolem + "/left/move/0.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/move/1.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/move/2.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/move/3.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/move/4.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/move/5.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/move/6.png" ),
        };

        moveRight = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGolem + "/right/move/0.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/move/1.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/move/2.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/move/3.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/move/4.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/move/5.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/move/6.png" ),
        };
        moveUp = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGolem + "/up/move/0.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/move/1.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/move/2.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/move/3.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/move/4.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/move/5.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/move/6.png" ),
        };

        moveDown = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGolem + "/down/move/0.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/move/1.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/move/2.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/move/3.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/move/4.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/move/5.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/move/6.png" ),
        };

        attackDown = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGolem + "/down/attack/0.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/attack/1.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/attack/2.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/attack/3.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/attack/4.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/attack/5.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/down/attack/6.png" ),
        };
        attackUp = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGolem + "/up/attack/0.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/attack/1.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/attack/2.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/attack/3.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/attack/4.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/attack/5.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/up/attack/6.png" ),
        };
        attackRight = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGolem + "/right/attack/0.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/attack/1.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/attack/2.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/attack/3.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/attack/4.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/attack/5.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/right/attack/6.png" ),
        };
        attackLeft = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGolem + "/left/attack/0.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/attack/1.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/attack/2.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/attack/3.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/attack/4.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/attack/5.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/left/attack/6.png" ),
        };
        deathSprite = new ImageView[]{
                new ImageView(RessourcePath.urlSpriteGolem + "/death/0.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/death/1.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/death/2.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/death/3.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/death/4.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/death/5.png" ),
                new ImageView(RessourcePath.urlSpriteGolem + "/death/6.png" ),
        };
        mainImage = new ImageView();
        centerAnImage(mainImage, mainImageWidth);
        currentSpriteAttack = attackRight;
        currentSpriteMove = moveRight;
        mainImage.setImage(currentSpriteMove[0].getImage());
        getChildren().add(mainImage);
    }

    public int getPhaseMax() {
        return phaseMax;
    }

    public void setPhaseMax(int phaseMax) {
        this.phaseMax = phaseMax;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public boolean isTired() {
        return isTired;
    }

    public void setTired(boolean tired) {
        isTired = tired;
    }

    public boolean isReloading() {
        return isReloading;
    }

    public void setReloading(boolean reloading) {
        isReloading = reloading;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public int getShootRate() {
        return shootRate;
    }

    public void setShootRate(int shootRate) {
        this.shootRate = shootRate;
    }

    public int getShootRateBuffer() {
        return shootRateBuffer;
    }

    public void setShootRateBuffer(int shootRateBuffer) {
        this.shootRateBuffer = shootRateBuffer;
    }

    public int getTired() {
        return tired;
    }

    public void setTired(int tired) {
        this.tired = tired;
    }

    public int getTiredBuffer() {
        return tiredBuffer;
    }

    public void setTiredBuffer(int tiredBuffer) {
        this.tiredBuffer = tiredBuffer;
    }

    public ArrayList<GraphNode> getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(ArrayList<GraphNode> destinationPath) {
        this.destinationPath = destinationPath;
    }

    public Integer getCurrentNodeDestinationPath() {
        return currentNodeDestinationPath;
    }

    public void setCurrentNodeDestinationPath(Integer currentNodeDestinationPath) {
        this.currentNodeDestinationPath = currentNodeDestinationPath;
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

    public void setPreparingAttack(boolean preparingAttack) {
        isPreparingAttack = preparingAttack;
    }

    public int getWaitingttackReadyBuffer() {
        return waitingttackReadyBuffer;
    }

    public void setWaitingttackReadyBuffer(int waitingttackReadyBuffer) {
        this.waitingttackReadyBuffer = waitingttackReadyBuffer;
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

    public boolean isTargeting() {
        return isTargeting;
    }

    public void setTargeting(boolean targeting) {
        isTargeting = targeting;
    }

    public boolean isTargetDone() {
        return isTargetDone;
    }

    public void setTargetDone(boolean targetDone) {
        isTargetDone = targetDone;
    }

    public Tile getTarget() {
        return target;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public boolean isInvicible() {
        return isInvicible;
    }

    public void setInvicible(boolean invicible) {
        isInvicible = invicible;
    }

    public boolean isTargetReached() {
        return isTargetReached;
    }

    public void setTargetReached(boolean targetReached) {
        isTargetReached = targetReached;
    }

    public boolean isPivoting() {
        return isPivoting;
    }

    public void setPivoting(boolean pivoting) {
        isPivoting = pivoting;
    }

    public boolean isAfterAttacking() {
        return isAfterAttacking;
    }

    public void setAfterAttacking(boolean afterAttacking) {
        isAfterAttacking = afterAttacking;
    }

    public int getPhaseCurrent() {
        return phaseCurrent;
    }

    public void setPhaseCurrent(int phaseCurrent) {
        this.phaseCurrent = phaseCurrent;
    }

    public int getNumberAtckPerPhaseThreshold() {
        return numberAtckPerPhaseThreshold;
    }

    public void setNumberAtckPerPhaseThreshold(int numberAtckPerPhaseThreshold) {
        this.numberAtckPerPhaseThreshold = numberAtckPerPhaseThreshold;
    }

    public int getNumberAtckPerPhase() {
        return numberAtckPerPhase;
    }

    public void setNumberAtckPerPhase(int numberAtckPerPhase) {
        this.numberAtckPerPhase = numberAtckPerPhase;
    }

    public int getMaxVitesse() {
        return maxVitesse;
    }

    public void setMaxVitesse(int maxVitesse) {
        this.maxVitesse = maxVitesse;
    }

    public ArrayList<Worm> getFriend() {
        return friend;
    }

    public void setFriend(ArrayList<Worm> friend) {
        this.friend = friend;
    }

    public int getAnimationAfterAttackingFrameBuffer() {
        return animationAfterAttackingFrameBuffer;
    }

    public void setAnimationAfterAttackingFrameBuffer(int animationAfterAttackingFrameBuffer) {
        this.animationAfterAttackingFrameBuffer = animationAfterAttackingFrameBuffer;
    }

    public int getAnimationAfterAtacking() {
        return animationAfterAtacking;
    }

    public void setAnimationAfterAtacking(int animationAfterAtacking) {
        this.animationAfterAtacking = animationAfterAtacking;
    }

    public int getAnimationNotInvicibleFrameBuffer() {
        return animationNotInvicibleFrameBuffer;
    }

    public void setAnimationNotInvicibleFrameBuffer(int animationNotInvicibleFrameBuffer) {
        this.animationNotInvicibleFrameBuffer = animationNotInvicibleFrameBuffer;
    }

    public int getAnimationNotInvicibleFrame() {
        return animationNotInvicibleFrame;
    }

    public void setAnimationNotInvicibleFrame(int animationNotInvicibleFrame) {
        this.animationNotInvicibleFrame = animationNotInvicibleFrame;
    }

    public boolean isPhase3Left() {
        return phase3Left;
    }

    public void setPhase3Left(boolean phase3Left) {
        this.phase3Left = phase3Left;
    }

    public boolean isAttackInMovement() {
        return isAttackInMovement;
    }

    public void setAttackInMovement(boolean attackInMovement) {
        isAttackInMovement = attackInMovement;
    }

    public int getDeathAnimationFrame() {
        return deathAnimationFrame;
    }

    public void setDeathAnimationFrame(int deathAnimationFrame) {
        this.deathAnimationFrame = deathAnimationFrame;
    }

    public boolean isDying() {
        return isDying;
    }

    public void setDying(boolean dying) {
        isDying = dying;
    }

    public ImageView[] getDeathSprite() {
        return deathSprite;
    }

    public void setDeathSprite(ImageView[] deathSprite) {
        this.deathSprite = deathSprite;
    }
}

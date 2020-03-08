package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import models.*;
import models.ennemis.*;
import models.worldMap.Tile;
import models.worldMap.WorldMap;
import timeline.GameTL;
import views.ViewHandler;

import java.util.ArrayList;

public class ControllerGame implements EventHandler<MouseEvent> {
    private ViewHandler viewHandler;
    private ControllerKeyBoard controllerKeyBoard;
    private GameTL gameTL;
    private Player player;
    private Partie partie;
    private WorldMap worldMap;
    private boolean memoControllerKeyBoardSpacePressed;

    public ControllerGame(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.controllerKeyBoard = new ControllerKeyBoard(this);
        this.gameTL = new GameTL(this);
        memoControllerKeyBoardSpacePressed = false;
        partie = new Partie();
        player = partie.getPlayer();
        worldMap = partie.getWorldMap();

        this.viewHandler.getViewGame().addWorldMap(partie.getWorldMap());
        this.viewHandler.getViewGame().getRoot().getChildren().add(player.getPlayerHud());
        this.viewHandler.getViewGame().setEvent(this, controllerKeyBoard);
        this.gameTL.start();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }

    public void handlePlayer() {
        player.memory();
        if (controllerKeyBoard.isUpPressed()) player.move(Direction.GO_UP);
        if (controllerKeyBoard.isDownPressed()) player.move(Direction.GO_DOWN);
        if (controllerKeyBoard.isRightPressed()) player.move(Direction.GO_RIGHT);
        if (controllerKeyBoard.isLeftPressed()) player.move(Direction.GO_LEFT);
        if (spaceBarrePlayerAttackImpulse()) handerPlayerAttack();
        player.valideTheMove(playerColliderMap());
        player.update();
    }

    public boolean spaceBarrePlayerAttackImpulse() {
        boolean frontMontantAttack = false;
        if ((controllerKeyBoard.isSpaceBarrePressed()) && (!memoControllerKeyBoardSpacePressed)) {
            memoControllerKeyBoardSpacePressed = !memoControllerKeyBoardSpacePressed;
            frontMontantAttack = true;
        } else if (!controllerKeyBoard.isSpaceBarrePressed() && memoControllerKeyBoardSpacePressed) {
            memoControllerKeyBoardSpacePressed = !memoControllerKeyBoardSpacePressed;
            frontMontantAttack = false;
        }
        return frontMontantAttack;
    }

    public void handerPlayerAttack() {
        player.setAttacking(true);
        for (Ennemi ennemi : worldMap.getEnnemisOfTheCurrentArea()) {
            if(ennemi instanceof Worm){
                if(player.attackTouch(ennemi) && ((Worm) ennemi).isOutSide) player.attack(ennemi);
            } else if (ennemi instanceof Boss) {
                if(player.attackTouch(ennemi) && !((Boss) ennemi).isInvicible()) {
                    player.attack(ennemi);
                }
            } else if (player.attackTouch(ennemi)) {
                    player.attack(ennemi);
            }
        }
    }

    public void handleEnnemiAttack(){
        for(Ennemi ennemi: worldMap.getCurrentArea().getEnnemiArrayList()){
            if(ennemi instanceof Soldier){
                    if(ennemi.attackTouch(player) && !((Soldier) ennemi).isAttacking() && ((Soldier) ennemi).isAttackReady()){
                        ((Soldier) ennemi).attack(player);
                    }
                    if(ennemi.attackTouch(player) && !((Soldier) ennemi).isPreparingAttack()) {
                        ((Soldier) ennemi).setPreparingAttackReady(true);
                    }
                    if(((Soldier) ennemi).isAttackReady()){
                        ((Soldier) ennemi).setAttackRready(false);
                    }
            }
            else if (ennemi instanceof Plant){
                if(!((Plant)ennemi).isReloading()){
                    Projectile projectile = ((Plant) ennemi).shoot(worldMap.getPlayerCurrentTile());
                    worldMap.addElement(projectile);
                }
                if(((Plant) ennemi).getProjectiles().size() != 0 ){
                    for(Projectile projectile: ((Plant) ennemi).getProjectiles()){
                        if(!projectile.isHasExploded()){
                            projectile.move();
                            projectile.update();
                            if(projectile.collision(player)) {
                                ((Plant) ennemi).attack(player);
                                projectile.setHasExploded(true);
                            }
                            if(projectileColliderMap(projectile)){
                                projectile.setHasExploded(true);
                            }
                        } else if(!projectile.isDestroyed){
                            projectile.animate();
                        } else {
                            worldMap.getChildren().remove(projectile);
                        }
                    }
                }
            } else if (ennemi instanceof Worm){
                if (ennemi.collision(player) && !((Worm) ennemi).isAttacking() && ((Worm) ennemi).isOutSide ){
                    ((Worm) ennemi).attack(player);
                }
            }
            else if(ennemi instanceof Boss){
                if(((Boss) ennemi).getPhase() == 1){
                    if(((Boss) ennemi).isAttackRready() && !((Boss) ennemi).isAttacking()) {
                        ((Boss) ennemi).setAttacking(true);
                        worldMap.makeEarthQuake();
                        if (ennemi.attackTouch(player) || ennemi.collision(player)) {
                            ((Boss) ennemi).attack(player);
                        }
                    }
                } else if (((Boss) ennemi).getPhase() == 2) {
                    if (((Boss) ennemi).isAttackRready() && !((Boss) ennemi).isAttacking()) {
                        ((Boss) ennemi).setAttacking(true);
                        Projectile projectile = ((Boss) ennemi).shoot(worldMap.getPlayerCurrentTile());
                        projectile.setVitesse(5);
                        worldMap.addElement(projectile);
                        projectile = ((Boss) ennemi).shoot(worldMap.getCurrentArea().getTileByCoord(player.getTileCoordX() + 3, player.getTileCoordY()));
                        projectile.setVitesse(8);
                        worldMap.addElement(projectile);
                        projectile = ((Boss) ennemi).shoot(worldMap.getCurrentArea().getTileByCoord(player.getTileCoordX() - 3, player.getTileCoordY()));
                        projectile.setVitesse(10);
                        worldMap.addElement(projectile);
                    }
                } else if ( ((Boss) ennemi).getPhase() == 3) {
                    if(((Boss) ennemi).isTargetReached()){
                        ((Boss) ennemi).setTargeting(true);
                        ((Boss) ennemi).setTargetReached(false);
                        ((Boss) ennemi).setNumberAtckPerPhase(((Boss) ennemi).getNumberAtckPerPhase()+1);
                    }
                    if(ennemi.collision(player) && !((Boss) ennemi).isAttacking()){
                        ((Boss) ennemi).attack(player);
                    }
                }
                    if(((Boss) ennemi).getProjectiles().size() != 0 ) {
                        for (Projectile projectile : ((Boss) ennemi).getProjectiles()) {
                            if (!projectile.isHasExploded()) {
                                projectile.move();
                                projectile.update();
                                if (projectile.collision(player)) {
                                    ((Boss) ennemi).attack(player);
                                    projectile.setHasExploded(true);
                                }
                                if (projectileColliderMap(projectile)) {
                                    projectile.setHasExploded(true);
                                }
                            } else if(!projectile.isDestroyed){
                                projectile.animate();
                            } else {
                                worldMap.getChildren().remove(projectile);
                            }
                        }
                    }
                    if(!ennemi.isAlive()){
                        partie.setYouWin(true);
                    }
            }
        }
    }

    public synchronized void moveEnnemi(){
        for(Ennemi enemi: worldMap.getCurrentArea().getEnnemiArrayList()){
            if(enemi instanceof Soldier){
                if(worldMap.playerHasMoveToAnOtherTile()){
                    ArrayList<GraphNode> path = IA.aStarPathFinding(worldMap.getCurrentArea().getTiles(),worldMap.getTileByCoord(enemi.getTileCoordX(),enemi.getTileCoordY()),worldMap.getPlayerCurrentTile());
                    ((Soldier) enemi).setDestination(path);
                }
                if(((Soldier) enemi).getDestinationPath() != null && ((Soldier) enemi).getDestinationPath().size() > 2){
                    ((Soldier) enemi).moveToTarget();
                }
            }
            if(enemi instanceof Worm){
                if(((Worm) enemi).isTargeting && !((Worm) enemi).isTargetDone){
                    ((Worm) enemi).teleport(worldMap.getPlayerCurrentTile());
                }
            } else if(enemi instanceof Boss) {
                if (((Boss) enemi).getPhase() == 1) {
                    if (((Boss) enemi).isTargeting()) {
                        Tile destination = null;
                        for (int i = -1 + worldMap.getPlayerCurrentTile().getIndiceX(); i < 1 + worldMap.getPlayerCurrentTile().getIndiceX(); i++) {
                            for (int j = -1 + worldMap.getPlayerCurrentTile().getIndiceY(); j < 1 + worldMap.getPlayerCurrentTile().getIndiceY(); j++) {
                                if (i == worldMap.getPlayerCurrentArea().getIndiceX() && j == worldMap.getPlayerCurrentArea().getIndiceY())
                                    continue;
                                if (worldMap.getTileByCoord(i, j).isTraversable()) {
                                    destination = worldMap.getTileByCoord(i, j);
                                    break;
                                }
                            }
                        }
                        if (destination != null) {
                            ((Boss) enemi).setTarget(destination);
                            ((Boss) enemi).setTargeting(false);
                        }
                    } else if (!((Boss) enemi).isTargetReached()) {
                        ((Boss) enemi).move();
                    } else if (((Boss) enemi).isPivoting()) {
                        ((Boss) enemi).pivotToTarget(worldMap.getPlayerCurrentTile(), worldMap.getCurrentArea().getTileByPixel(enemi));
                        ((Boss) enemi).setPreparingAttack(true);
                    }
                } else if (((Boss) enemi).getPhase() == 2) {
                    if (((Boss) enemi).isTargeting()) {
                        Tile destination = worldMap.getTileByCoord(player.getTileCoordX(), 1);
                        ((Boss) enemi).setTarget(destination);
                        ((Boss) enemi).setTargeting(false);

                    } else if (!((Boss) enemi).isTargetReached()) {
                        ((Boss) enemi).move();
                    } else if (((Boss) enemi).isPivoting()) {
                        ((Boss) enemi).pivotToTarget(worldMap.getPlayerCurrentTile(), worldMap.getCurrentArea().getTileByPixel(enemi));
                        ((Boss) enemi).setPreparingAttack(true);
                    }
                } else if (((Boss) enemi).getPhase() == 3){
                    if (((Boss) enemi).isTargeting()) {
                        Tile destination;
                        if(((Boss) enemi).isPhase3Left()){
                            destination = worldMap.getTileByCoord(0, player.getTileCoordY());
                            ((Boss) enemi).setPhase3Left(false);
                        } else {
                            destination = worldMap.getTileByCoord(WorldMap.tileXNumber-1, player.getTileCoordY());
                            ((Boss) enemi).setPhase3Left(true);
                        }
                        ((Boss) enemi).setTarget(destination);
                        ((Boss) enemi).setTargeting(false);
                    } else if (!((Boss) enemi).isTargetReached()) {
                        ((Boss) enemi).move();
                    }
            }
            }
        }
    }

    public void majAnimation() {
        player.animate();
        for (Ennemi ennemi : worldMap.getEnnemisOfTheCurrentArea()) {
            ennemi.animate();
        }
    }

    public boolean playerColliderMap() {
        for (int i = player.getXMin(); i <= player.getXMax(); i++) {
            for (int j = player.getYMin(); j <= player.getYMax(); j++) {
                Tile tile = worldMap.getAreaMap(i,j).getTileByPixel(i,j);
                if (!tile.isTraversable()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean projectileColliderMap(Projectile projectile) {
        Tile projectilTile = worldMap.getTileByCoord(projectile.getTileCoordX(),projectile.getTileCoordY());
        return !projectilTile.isTraversable()
                ||  projectilTile.getIndiceX() < 0 ||  projectilTile.getIndiceX() > WorldMap.tileXNumber
                ||  projectilTile.getIndiceY() < 0 ||  projectilTile.getIndiceY() > WorldMap.tileYNumber;

    }

    public void worldMapWatcher(){
        worldMap.watchPlayer(player);

    }

    public void updateCamera(){
        if(worldMap.playerHasMoveToAnOtherArea()){
            worldMap.moveCamera();
        }
    }


    public Partie getPartie() {
        return partie;
    }

    public Player getPlayer() {
        return player;
    }

    public ViewHandler getViewHandler() {
        return viewHandler;
    }
}

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
import java.util.Collections;

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
        partie.getWorldMap().initCamera(viewHandler.getCamera());
        player = partie.getPlayer();
        worldMap = partie.getWorldMap();

        this.viewHandler.getViewGame().addWorldMap(partie.getWorldMap());
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
            } else
            if (player.attackTouch(ennemi)) {
                    player.attack(ennemi);
            }
        }
    }

    public void handleEnnemiAttack(){
        for(Ennemi ennemi: worldMap.getCurrentArea().getEnnemiArrayList()){
            if(ennemi instanceof Soldier){
                if(ennemi.attackTouch(player) && !ennemi.isAttacking()){
                    ennemi.attack(player);
                    System.out.println("Ouch Degat");
                }
            } else if (ennemi instanceof Plant){
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
                                ennemi.attack(player);
                                projectile.setHasExploded(true);
                                worldMap.getChildren().remove(projectile);
                            }
                            if(projectileColliderMap(projectile)){
                                projectile.setHasExploded(true);
                                worldMap.getChildren().remove(projectile);
                            }
                        }
                    }
                }
            } else if (ennemi instanceof Worm){
                if (ennemi.collision(player) && !ennemi.isAttacking() && ((Worm) ennemi).isOutSide ){
                    ennemi.attack(player);
                    System.out.println("Ouch j'ai pris des degat");
                }
            }
        }
    }

    public synchronized void moveEnnemi(){
        for(Ennemi enemi: worldMap.getCurrentArea().getEnnemiArrayList()){
            if(enemi instanceof Soldier){
                if(worldMap.playerHasMoveToAnOtherTile()){
                    ArrayList<GraphNode> path = IA.aStarPathFinding(worldMap.getCurrentArea().getTiles(),worldMap.getTileByCoord(enemi.getTileCoordX(),enemi.getTileCoordY()),worldMap.getPlayerCurrentTile());
                    enemi.setDestination(path);
                }
                if(enemi.getDestinationPath() != null){
                    enemi.moveToTarget();
                }
            }
            if(enemi instanceof Soldier){

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

    //TODO: Transition camera fluide et lancer le calcule si le joueur a changer d area
    public void updateCamera(){
        if(worldMap.playerHasMoveToAnOtherArea()){
            worldMap.getCamera().setTranslateX(worldMap.getCamera().translateXProperty().doubleValue() + (player.getAreaCoordX()*WorldMap.areaWidth - worldMap.getCamera().getTranslateX()));
            worldMap.getCamera().setTranslateY(worldMap.getCamera().translateYProperty().doubleValue() + (player.getAreaCoordY()*WorldMap.areaHeight - worldMap.getCamera().getTranslateY()));
        }
    }


    public Partie getPartie() {
        return partie;
    }

    public Player getPlayer() {
        return player;
    }
}

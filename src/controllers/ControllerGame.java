package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import models.*;
import models.ennemis.Ennemi;
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

    public ArrayList<Tile> convertGraphNodeToTile(ArrayList<GraphNode> path){
        ArrayList<Tile> tiles = new ArrayList<>();
        for (GraphNode graphNode: path){
            tiles.add(worldMap.getCurrentArea().getTiles()[graphNode.indiceX][graphNode.indiceY]);
        }
        return tiles;
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
            if (player.attackTouch(ennemi)) {
                player.attack(ennemi);
            }
        }
    }

    public synchronized void moveEnemi(){
        for(Ennemi enemi: worldMap.getCurrentArea().getEnnemiArrayList()){
            if(worldMap.playerHasMoveToAnOtherTile()){
                ArrayList<GraphNode> path = IA.aStarPathFinding(worldMap.getCurrentArea().getTiles(),worldMap.getTileByCoord(enemi.getTileCoordX(),enemi.getTileCoordY()),worldMap.getTileByCoord(player.getTileCoordX(),player.getTileCoordY()));
                enemi.setDestination(convertGraphNodeToTile(path));
            }
            if(enemi.getDestinationPath() != null){
                enemi.moveToTarget();
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

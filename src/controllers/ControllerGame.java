package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import models.Direction;
import models.Partie;
import models.Player;
import models.worldMap.Tile;
import models.worldMap.WorldMap;
import timeline.GameTL;
import views.ViewHandler;

public class ControllerGame implements EventHandler<MouseEvent> {
    private ViewHandler viewHandler;
    private ControllerKeyBoard controllerKeyBoard;
    private GameTL gameTL;
    private Player player;
    private Partie partie;
    private WorldMap worldMap;

    public ControllerGame(ViewHandler viewHandler){
        this.viewHandler = viewHandler;
        this.controllerKeyBoard = new ControllerKeyBoard(this);
        this.gameTL = new GameTL(this);
        partie = new Partie();
        partie.getWorldMap().initCamera(viewHandler.getCamera());
        player = partie.getPlayer();
        worldMap = partie.getWorldMap();

        this.viewHandler.getViewGame().addWorldMap(partie.getWorldMap());
        this.viewHandler.getViewGame().setEvent(this,controllerKeyBoard);
        this.gameTL.start();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }

    public void handlePlayer() {

        checkTileNeighborhood();
        if( controllerKeyBoard.isUpPressed() && player.hasTopConstrain()) partie.getPlayer().move(Direction.GO_UP);
        if( controllerKeyBoard.isDownPressed() && player.hasBottomConstrain()) partie.getPlayer().move(Direction.GO_DOWN);
        if( controllerKeyBoard.isRightPressed() && player.hasRightConstrain()) partie.getPlayer().move(Direction.GO_RIGHT);
        if( controllerKeyBoard.isLeftPressed() && player.hasLeftConstrain()) partie.getPlayer().move(Direction.GO_LEFT);
    }

    public void checkTileNeighborhood(){
        if(player.hasMoveToAnOtherTile()){
        for (int i = - 1; i <= 1 ; i++) {
            for (int j = - 1; j <= 1 ; j++) {
                if(Math.abs(i) == Math.abs(j)) continue;
                if(player.getTileCoordX()+i <= -1 || player.getTileCoordY()+j <= -1 || player.getTileCoordX()+i >= WorldMap.tileXNumber || player.getTileCoordY() + j >= WorldMap.tileYNumber ) continue;
                Tile tile = worldMap.getAreaMap(player.getAreaCoordX(),player.getAreaCoordY()).getTiles()[player.getTileCoordX()+i][player.getTileCoordY()+j];
                if(!(tile.isTraversable())){
                    if(i==-1 && j==0){
                        player.setLeftConstraint(tile.getRightConstrain());
                    } else if(i==0 && j==-1){
                        player.setTopConstraint(tile.getBottomConstrain());
                    } else if(i==1 && j==0){
                        player.setRightConstraint(tile.getLeftConstrain());
                    } else if(i==0 && j==1){
                        player.setBottomConstraint(tile.getTopConstrain());
                    }
                } else {
                    if(i==-1 && j==0){
                        player.setLeftConstraint(null);
                    } else if(i==0 && j==-1){
                        player.setTopConstraint(null);
                    } else if(i==1 && j==0){
                        player.setRightConstraint(null);;
                    } else if(i==0 && j==1){
                        player.setBottomConstraint(null);
                    }
                }
            }
            player.updateTile();
            }
        }
    }

    public void worldMapWatcher(){
        player.setCurrentTile(worldMap.getTile(player.getTileCoordX(),player.getTileCoordY()));
    }

    //TODO: Transition camera fluide et seulement au changement d'area
    public void updateCamera(){
        worldMap.getCamera().setTranslateX(worldMap.getCamera().translateXProperty().doubleValue() + (player.getAreaCoordX()*WorldMap.areaWidth - worldMap.getCamera().getTranslateX()));
        worldMap.getCamera().setTranslateY(worldMap.getCamera().translateYProperty().doubleValue() + (player.getAreaCoordY()*WorldMap.areaHeight - worldMap.getCamera().getTranslateY()));
    }


    public Partie getPartie() {
        return partie;
    }
}

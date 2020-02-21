package controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import models.Mouvement;
import models.Partie;
import models.Player;
import models.worldMap.Tile;
import timeline.GameTL;
import views.ViewHandler;

public class ControllerGame implements EventHandler<MouseEvent> {
    private ViewHandler viewHandler;
    private ControllerKeyBoard controllerKeyBoard;
    private GameTL gameTL;
    private Player player;
    private Partie partie;
    private BooleanProperty playerMovingUp, playerMovingRight, playerMovingDown, playerMovingLeft;

    public ControllerGame(ViewHandler viewHandler){
        playerMovingUp = new SimpleBooleanProperty(false);
        playerMovingRight = new SimpleBooleanProperty(false);
        playerMovingDown = new SimpleBooleanProperty(false);
        playerMovingLeft = new SimpleBooleanProperty(false);
        this.viewHandler = viewHandler;
        this.controllerKeyBoard = new ControllerKeyBoard(this);
        this.gameTL = new GameTL(this);
        partie = new Partie();
        player = partie.getPlayer();


        this.viewHandler.getViewGame().addWorldMap(partie.getWorldMap());
        this.viewHandler.getViewGame().setEvent(this,controllerKeyBoard);
        this.gameTL.start();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }

    public void verifyIfPlayerCanMove() {
        playerMovingUp.set(controllerKeyBoard.isUpPressed());
        playerMovingDown.set(controllerKeyBoard.isDownPressed());
        playerMovingRight.set(controllerKeyBoard.isRightPressed());
        playerMovingLeft.set(controllerKeyBoard.isLeftPressed());
    }

    public void updateFreePlayerPosition() {
        if( playerMovingUp.get() && collision(player.getPreviousTile(),player.getCurrentTile(), Mouvement.UP) ) partie.getPlayer().move(Mouvement.UP);
        if( playerMovingDown.get() && collision(player.getPreviousTile(),player.getCurrentTile(), Mouvement.DOWN) ) partie.getPlayer().move(Mouvement.DOWN);
        if( playerMovingRight.get() && collision(player.getPreviousTile(),player.getCurrentTile(), Mouvement.RIGHT) ) partie.getPlayer().move(Mouvement.RIGHT);
        if( playerMovingLeft.get() && collision(player.getPreviousTile(),player.getCurrentTile(), Mouvement.LEFT)) partie.getPlayer().move(Mouvement.LEFT);
        player.update();
    }

    public boolean collision(Tile prevTile, Tile currentTile, Mouvement mouvement){
        if(!currentTile.isTraversable()){
            if(mouvement.equals(Mouvement.RIGHT)) return prevTile.getX() >= currentTile.getX();
            else if (mouvement.equals(Mouvement.LEFT)) return prevTile.getX() <= currentTile.getX();
            else if (mouvement.equals(Mouvement.DOWN)) return prevTile.getY() >= currentTile.getY();
            else if (mouvement.equals(Mouvement.UP)) return prevTile.getY() <= currentTile.getY();
        }
        return true;
    }

    public void updateTilePlayerPosition(){

    }


    public Partie getPartie() {
        return partie;
    }
}

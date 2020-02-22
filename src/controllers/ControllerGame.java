package controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import models.KeyInput;
import models.Partie;
import models.Player;
import timeline.GameTL;
import views.ViewHandler;

public class ControllerGame implements EventHandler<MouseEvent> {
    private ViewHandler viewHandler;
    private ControllerKeyBoard controllerKeyBoard;
    private GameTL gameTL;
    private Player player;
    private Partie partie;

    public ControllerGame(ViewHandler viewHandler){
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

    public void handlePlayer() {
        if( controllerKeyBoard.isUpPressed() ) partie.getPlayer().move(KeyInput.GO_UP);
        if( controllerKeyBoard.isDownPressed() ) partie.getPlayer().move(KeyInput.GO_DOWN);
        if( controllerKeyBoard.isRightPressed() ) partie.getPlayer().move(KeyInput.GO_RIGHT);
        if( controllerKeyBoard.isLeftPressed()) partie.getPlayer().move(KeyInput.GO_LEFT);
    }

    public void updateTilePlayerPosition(){

    }


    public Partie getPartie() {
        return partie;
    }
}

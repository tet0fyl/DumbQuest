package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import models.Direction;
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
        partie.getWorldMap().initCamera(viewHandler.getCamera());
        player = partie.getPlayer();


        this.viewHandler.getViewGame().addWorldMap(partie.getWorldMap());
        this.viewHandler.getViewGame().setEvent(this,controllerKeyBoard);
        this.gameTL.start();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }

    public void handlePlayer() {
        if( controllerKeyBoard.isUpPressed() ) partie.getPlayer().move(Direction.GO_UP);
        if( controllerKeyBoard.isDownPressed() ) partie.getPlayer().move(Direction.GO_DOWN);
        if( controllerKeyBoard.isRightPressed() ) partie.getPlayer().move(Direction.GO_RIGHT);
        if( controllerKeyBoard.isLeftPressed()) partie.getPlayer().move(Direction.GO_LEFT);
    }

    public void handleCamera(){

    }


    public Partie getPartie() {
        return partie;
    }
}

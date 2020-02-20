package controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import models.Partie;
import models.Player;
import timeline.GameTL;
import views.ViewHandler;
import views.graphicalElements.PlayerView;

public class ControllerGame implements EventHandler<MouseEvent> {
    private ViewHandler viewHandler;
    private ControllerKeyBoard controllerKeyBoard;
    private GameTL gameTL;
    private Partie partie;
    private BooleanProperty movingUp, movingRight, movingDown, movingLeft;
    private PlayerView playerView;

    public ControllerGame(ViewHandler viewHandler){
        movingUp = new SimpleBooleanProperty(false);
        movingRight = new SimpleBooleanProperty(false);
        movingDown = new SimpleBooleanProperty(false);
        movingLeft = new SimpleBooleanProperty(false);
        this.viewHandler = viewHandler;
        this.controllerKeyBoard = new ControllerKeyBoard(this);
        this.gameTL = new GameTL(this);
        partie = new Partie();

        this.viewHandler.getViewGame().AddPlayer(partie.getPlayer().getPlayerView());
        this.viewHandler.getViewGame().setEvent(this,controllerKeyBoard);
        this.gameTL.start();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }

    public void deplacementPossible() {
        movingUp.set(controllerKeyBoard.isUpPressed());
        movingDown.set(controllerKeyBoard.isDownPressed());
        movingRight.set(controllerKeyBoard.isRightPressed());
        movingLeft.set(controllerKeyBoard.isLeftPressed());
    }

    public void updatePlayerPosition() {
        if( movingUp.get() ) partie.getPlayer().move(Player.Mouvement.UP);
        if( movingRight.get() ) partie.getPlayer().move(Player.Mouvement.RIGHT);
        if( movingDown.get() ) partie.getPlayer().move(Player.Mouvement.DOWN);
        if( movingLeft.get() ) partie.getPlayer().move(Player.Mouvement.LEFT);
    }

    public Partie getPartie() {
        return partie;
    }
}

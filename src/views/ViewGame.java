package views;

import controllers.ControllerGame;
import controllers.ControllerKeyBoard;
import javafx.scene.Group;
import models.Player;
import views.graphicalElements.PlayerView;

public class ViewGame {
    private Group root;

    public ViewGame(Group root) {
        this.root = root;
    }

    public void clearAndInitRoot(){
        root.getChildren().clear();
    }

    public void setEvent(ControllerGame controllerGame, ControllerKeyBoard controllerKeyBoard){
        root.setOnKeyPressed(controllerKeyBoard);
        root.setOnKeyReleased(controllerKeyBoard);
        root.requestFocus();

    }

    public void AddPlayer(PlayerView playerView){
        root.getChildren().add(playerView);
    }
}

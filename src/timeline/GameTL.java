package timeline;

import controllers.ControllerGame;
import controllers.ControllerKeyBoard;
import javafx.animation.AnimationTimer;

public class GameTL extends AnimationTimer {
    private ControllerGame controllerGame;

    public GameTL(ControllerGame controllerGame) {
        this.controllerGame = controllerGame;
    }

    @Override
    public void handle(long l) {
        controllerGame.updatePlayerPosition();
    }
}

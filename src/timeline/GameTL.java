package timeline;

import controllers.ControllerGame;
import javafx.animation.AnimationTimer;

public class GameTL extends AnimationTimer {
    private ControllerGame controllerGame;

    public GameTL(ControllerGame controllerGame) {
        this.controllerGame = controllerGame;
    }

    @Override
    public void handle(long l) {
        controllerGame.worldMapWatcher();
        controllerGame.handlePlayer();
        controllerGame.updateCamera();
    }
}

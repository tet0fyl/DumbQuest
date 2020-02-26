package timeline;

import controllers.ControllerGame;
import javafx.animation.AnimationTimer;

public class GameTL extends AnimationTimer {
    private ControllerGame controllerGame;
    private Long playerAttackTimerRealease;
    private boolean firstTime;
    private long now, lu150ms;

    public GameTL(ControllerGame controllerGame) {
        this.controllerGame = controllerGame;
        playerAttackTimerRealease = null;
        firstTime=true;
    }

    @Override
    public void handle(long now) {
        this.now = now;
        if( firstTime ) {
            firstTime = false;
            return;
        }

        controllerGame.worldMapWatcher();
        controllerGame.handlePlayer();
        controllerGame.updateCamera();
        controllerGame.moveEnemi();

        if( now - lu150ms >= 150_000_000 ) {
                controllerGame.majAnimation();
                lu150ms = now;
            }
    }
}

package timeline;

import controllers.ControllerGame;
import javafx.animation.AnimationTimer;
import views.ViewGame;

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

        if(!controllerGame.getPartie().isYouWin() && !controllerGame.getPartie().isYouLose()){
            controllerGame.worldMapWatcher();
            controllerGame.handlePlayer();
            controllerGame.updateCamera();
            controllerGame.moveEnnemi();
            controllerGame.handleEnnemiAttack();

            if( now - lu150ms >= 150_000_000 ) {
                controllerGame.majAnimation();
                lu150ms = now;
            }
        } else if (controllerGame.getPartie().isYouWin()){
            controllerGame.getViewHandler().getViewGame().initPopUp(ViewGame.PopUpType.WIN);
            stop();
        } else if(controllerGame.getPartie().isYouLose()){
            controllerGame.getViewHandler().getViewGame().initPopUp(ViewGame.PopUpType.LOSE);
            stop();
        }

    }
}

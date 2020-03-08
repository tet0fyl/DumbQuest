package controllers;


import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerKeyBoard implements EventHandler<KeyEvent> {

    private boolean upPressed = false;
    private boolean rightPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean spaceBarrePressed = false;
    private ControllerGame controllerGame;

    public ControllerKeyBoard(ControllerGame controllerGame){
        this.controllerGame = controllerGame;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if( keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED) ) {
            if( keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.KP_UP || keyEvent.getCode() == KeyCode.Z ) {
                upPressed = true;
            }
            if( keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.KP_RIGHT || keyEvent.getCode() == KeyCode.D ) {
                rightPressed = true;
            }
            if( keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.KP_DOWN || keyEvent.getCode() == KeyCode.S ) {
                downPressed = true;
            }
            if( keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.KP_LEFT || keyEvent.getCode() == KeyCode.Q ) {
                leftPressed = true;
            }
            if( keyEvent.getCode() == KeyCode.SPACE ) {
                spaceBarrePressed=true;
            }
            if( keyEvent.getCode() == KeyCode.ESCAPE || keyEvent.getCode() == KeyCode.P ) {
                controllerGame.handlePauseKeyPressed();
            }
        } else if( keyEvent.getEventType().equals(KeyEvent.KEY_RELEASED) ) {
            if( keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.KP_UP || keyEvent.getCode() == KeyCode.Z ) {
                upPressed = false;
            }
            if( keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.KP_RIGHT || keyEvent.getCode() == KeyCode.D ) {
                rightPressed = false;
            }
            if( keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.KP_DOWN || keyEvent.getCode() == KeyCode.S ) {
                downPressed = false;
            }
            if( keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.KP_LEFT || keyEvent.getCode() == KeyCode.Q ) {
                leftPressed = false;
            }
            if( keyEvent.getCode() == KeyCode.SPACE ) {
                spaceBarrePressed=false;
            }
        }
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isSpaceBarrePressed() {
        return spaceBarrePressed;
    }
}

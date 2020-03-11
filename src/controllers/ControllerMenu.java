package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import timeline.MenuTL;
import views.ViewHandler;


public class ControllerMenu implements EventHandler<InputEvent> {
    private ViewHandler viewHandler;
    private MenuTL menuTL;

    public ControllerMenu(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.menuTL = new MenuTL(this);
        viewHandler.getViewMenu().setEvent(this);
    }

    @Override
    public void handle(InputEvent inputEvent) {
        if (inputEvent.getEventType().equals(KeyEvent.KEY_PRESSED) && ((KeyEvent) inputEvent).getCode().equals(KeyCode.ESCAPE)) {
            viewHandler.exit();
        } else {
            viewHandler.setViewGame();
        }
    }

    public ViewHandler getViewHandler() {
        return viewHandler;
    }

    public MenuTL getMenuTL() {
        return menuTL;
    }
}

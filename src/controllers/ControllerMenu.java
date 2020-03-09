package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import timeline.MenuTL;
import views.ViewHandler;


public class ControllerMenu implements EventHandler<InputEvent> {
    private ViewHandler viewHandler;
    private MenuTL menuTL;

    public ControllerMenu(ViewHandler viewHandler){
        this.viewHandler = viewHandler;
        this.menuTL = new MenuTL(this);
        viewHandler.getViewMenu().setEvent(this);
    }

    @Override
    public void handle(InputEvent inputEvent) {

    }

    public ViewHandler getViewHandler() {
        return viewHandler;
    }

    public MenuTL getMenuTL() {
        return menuTL;
    }
}

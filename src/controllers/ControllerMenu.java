package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import views.ViewHandler;


public class ControllerMenu implements EventHandler<MouseEvent> {
    private ViewHandler viewHandler;

    public ControllerMenu(ViewHandler viewHandler){
        this.viewHandler = viewHandler;

        viewHandler.getViewMenu().setEvent(this);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}

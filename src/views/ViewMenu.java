package views;

import controllers.ControllerMenu;
import javafx.scene.Group;
import javafx.scene.control.Label;

public class ViewMenu {
    private Group root;

    public ViewMenu(Group root) {
        this.root = root;
    }

    public void clearAndInitRoot(){
        root.getChildren().clear();
        root.getChildren().addAll(new Label("CEST LA VIEW MENU"));
    }

    public void setEvent(ControllerMenu controllerMenu){
    }
}

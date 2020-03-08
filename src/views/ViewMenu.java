package views;

import controllers.ControllerMenu;
import javafx.scene.Group;

public class ViewMenu {
    private Group root;

    public ViewMenu(Group root) {
        this.root = root;
    }

    public void clearAndInitRoot(){
        root.getChildren().clear();
    }

    public void setEvent(ControllerMenu controllerMenu){
    }
}

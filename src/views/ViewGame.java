package views;

import controllers.ControllerGame;
import controllers.ControllerKeyBoard;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.worldMap.WorldMap;



public class ViewGame {
    private Group root;
    private VBox popUp;
    private VBox screen;

    public ViewGame(Group root) {
        this.root = root;
        screen = new VBox();
    }

    public void clearAndInitRoot(){
        root.getChildren().clear();
        root.getChildren().add(screen);
    }

    public void setEvent(ControllerGame controllerGame, ControllerKeyBoard controllerKeyBoard){
        root.setOnKeyPressed(controllerKeyBoard);
        root.setOnKeyReleased(controllerKeyBoard);
        root.requestFocus();
    }

    public void initPopUp(){
        refreshScreen();
        if(screen.getChildren().contains(popUp)) root.getChildren().remove(popUp);
        popUp = new VBox();
        Label title = new Label("YOU WIN");
        popUp.getChildren().add(title);
        screen.getChildren().add(popUp);
    }

    public void refreshScreen(){
        root.getChildren().remove(screen);
        root.getChildren().add(screen);
    }

    public void addWorldMap(WorldMap worldMap){
        root.getChildren().add(worldMap);
    }

    public Group getRoot() {
        return root;
    }

    public VBox getPopUp() {
        return popUp;
    }

    public VBox getScreen() {
        return screen;
    }
}

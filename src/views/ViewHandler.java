package views;

import controllers.ControllerGame;
import controllers.ControllerKeyBoard;
import controllers.ControllerMenu;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Config;
import utils.RessourcePath;

public class ViewHandler extends Application {
    private Stage primaryStage;
    private Scene scene;
    private Group root;
    private ViewGame viewGame;
    private ViewMenu viewMenu;
    private ControllerGame controllerGame;
    private ControllerKeyBoard controllerKeyBoard;
    private ControllerMenu controllerMenu;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        root = new Group();
        scene = new Scene(root, Config.gameWindowWidth,Config.gameWindowHeight);
        root.getStylesheets().add(RessourcePath.urlStylesheet);

        //setViewMenu();
        setViewGame();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setViewGame(){
        if(controllerMenu != null){
            controllerMenu.getMenuTL().stop();
            controllerMenu = null;
        }
        viewGame = new ViewGame(root);
        viewGame.clearAndInitRoot();
        controllerGame = new ControllerGame(this);
        controllerKeyBoard = new ControllerKeyBoard(controllerGame);
        controllerGame.getGameTL().start();
    }

    public void setViewMenu(){
        if(controllerGame != null){
            controllerGame.getGameTL().stop();
            controllerGame = null;
            controllerKeyBoard=null;
        }
        viewMenu = new ViewMenu(root);
        viewMenu.clearAndInitRoot();
        controllerMenu = new ControllerMenu(this);
        controllerMenu.getMenuTL().start();
    }

    public ViewGame getViewGame() {
        return viewGame;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getScene() {
        return scene;
    }

    public Group getRoot() {
        return root;
    }

    public ViewMenu getViewMenu() {
        return viewMenu;
    }

    public ControllerGame getControllerGame() {
        return controllerGame;
    }

    public ControllerKeyBoard getControllerKeyBoard() {
        return controllerKeyBoard;
    }

    public ControllerMenu getControllerMenu() {
        return controllerMenu;
    }
}

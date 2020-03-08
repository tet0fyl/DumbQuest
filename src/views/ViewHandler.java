package views;

import controllers.ControllerGame;
import controllers.ControllerKeyBoard;
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
    private ControllerGame controllerGame;
    private ControllerKeyBoard controllerKeyBoard;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        root = new Group();
        scene = new Scene(root, Config.gameWindowWidth,Config.gameWindowHeight);
        root.getStylesheets().add(RessourcePath.urlStylesheet);
        setViewGame();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setViewGame(){
        viewGame = new ViewGame(root);
        viewGame.clearAndInitRoot();
        controllerGame = new ControllerGame(this);
        controllerKeyBoard = new ControllerKeyBoard(controllerGame);
    }

    public ViewGame getViewGame() {
        return viewGame;
    }
}

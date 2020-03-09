package views;

import controllers.ControllerMenu;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import utils.Config;
import utils.RessourcePath;

public class ViewMenu {
    private Group root;
    private VBox container;
    private VBox topBox, bottomBox, backgroundWindowBox;
    private ImageView backgroundMoving;
    private Label lblStartMsg;

    public ViewMenu(Group root) {
        this.root = root;
        container = new VBox();
        topBox = new VBox();
        bottomBox = new VBox();
        backgroundMoving = new ImageView(RessourcePath.urlImgBackGroundMenu);
        backgroundMoving.setFitWidth(Config.gameWindowWidth*2);
        backgroundMoving.setPreserveRatio((true));
        backgroundWindowBox = new VBox();
        topBox.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        bottomBox.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        topBox.setPrefHeight(Config.gameWindowHeight/4);
        bottomBox.setPrefHeight(Config.gameWindowHeight/4);
        backgroundWindowBox.setPrefHeight(Config.gameWindowHeight/2);
        backgroundMoving.setLayoutY(topBox.getPrefHeight()-10);
        lblStartMsg = initLbl(15,"PRESS ANY KEY");
        container.setPrefWidth(Config.gameWindowWidth);
        container.setPrefHeight(Config.gameWindowHeight);
        container.getChildren().addAll(topBox, backgroundWindowBox,bottomBox);
        bottomBox.getChildren().add(lblStartMsg);
        bottomBox.setAlignment(Pos.CENTER);
        backgroundMoving.setEffect(new GaussianBlur());

    }

    public void clearAndInitRoot(){
        root.getChildren().clear();
        root.getChildren().addAll(backgroundMoving,container);
    }

    public void setEvent(ControllerMenu controllerMenu){
    }

    public static Label initLbl(int fontSize, String textContent){
        Label t = new Label();
        t.setText(textContent);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.loadFont(ViewGame.class.getResourceAsStream(RessourcePath.fontPixel), fontSize));
        return t;
    }

    public Group getRoot() {
        return root;
    }

    public VBox getContainer() {
        return container;
    }

    public VBox getTopBox() {
        return topBox;
    }

    public VBox getBottomBox() {
        return bottomBox;
    }

    public VBox getBackgroundWindowBox() {
        return backgroundWindowBox;
    }

    public ImageView getBackgroundMoving() {
        return backgroundMoving;
    }

    public Label getLblStartMsg() {
        return lblStartMsg;
    }
}

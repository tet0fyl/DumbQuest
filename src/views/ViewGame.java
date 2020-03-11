package views;

import controllers.ControllerGame;
import controllers.ControllerKeyBoard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import models.player.PlayerHud;
import models.worldMap.WorldMap;
import utils.Config;
import utils.RessourcePath;


public class ViewGame {
    private Group root;
    private VBox popUp;
    private VBox screen;
    private WorldMap worldMap;
    private PlayerHud playerHud;
    private Button btnReprendre, btnQuitter, btnRecommencer;

    ;
    public ViewGame(Group root) {
        this.root = root;
        btnRecommencer = initBtn(15, "RECOMMENCER", "btn-secondary");
        btnQuitter = initBtn(15, "QUITTER", "btn-secondary");
        btnReprendre = initBtn(15, "REPRENDRE", "btn-secondary");
        VBox.setMargin(btnRecommencer, new Insets(10,0,10,0));
        VBox.setMargin(btnQuitter, new Insets(10,0,10,0));
        VBox.setMargin(btnReprendre, new Insets(10,0,10,0));
        screen = new VBox();
        screen.setPrefHeight(Config.gameWindowHeight);
        screen.setPrefWidth(Config.gameWindowWidth);
        screen.setPadding(new Insets(50));
        screen.setAlignment(Pos.CENTER);
    }

    public static Button initBtn(int fontSize, String textContent, String styleClass) {
        Button b = new Button(textContent);
        b.setMinWidth(150);
        b.setFont(Font.loadFont(ViewGame.class.getResourceAsStream(RessourcePath.fontPixel), fontSize));
        b.getStyleClass().add(styleClass);
        return b;
    }

    public static Label initTitle(int fontSize, String textContent) {
        Label t = new Label();
        t.setText(textContent);
        t.setFont(Font.loadFont(ViewGame.class.getResourceAsStream(RessourcePath.fontPixel), fontSize));
        t.setRotate(15);
        return t;
    }

    public void clearAndInitRoot() {
        root.getChildren().clear();
        root.getChildren().add(screen);
    }

    public void setEvent(ControllerGame controllerGame, ControllerKeyBoard controllerKeyBoard) {
        root.setOnKeyPressed(controllerKeyBoard);
        root.setOnKeyReleased(controllerKeyBoard);
        btnReprendre.setOnMouseClicked(controllerGame);
        btnRecommencer.setOnMouseClicked(controllerGame);
        btnQuitter.setOnMouseClicked(controllerGame);
        root.requestFocus();
    }

    public void initPopUp(PopUpType popUpType) {
        refreshScreen();
        worldMap.setEffect(new GaussianBlur());
        playerHud.setEffect(new GaussianBlur());
        if (screen.getChildren().contains(popUp)) root.getChildren().remove(popUp);
        popUp = new VBox();
        popUp.getStyleClass().add("popUp");
        popUp.setAlignment(Pos.CENTER);
        popUp.setPrefHeight(Config.gameWindowHeight / 2);
        VBox choiceVBox = new VBox();
        choiceVBox.setAlignment(Pos.CENTER);
        Label title = initTitle(30, "");
        VBox.setMargin(title, new Insets(0,0,20,0));
        choiceVBox.setPadding(new Insets(20));
        if (popUpType.equals(PopUpType.PAUSE)) {
            title.setText("PAUSE");
            choiceVBox.getChildren().addAll(btnReprendre, btnRecommencer, btnQuitter);
        } else if (popUpType.equals(PopUpType.LOSE)) {
            title.setText("YOU DIED");
            choiceVBox.getChildren().addAll(btnRecommencer, btnQuitter);
        } else if (popUpType.equals(PopUpType.WIN)) {
            title.setText("YOU WIN");
            choiceVBox.getChildren().addAll(btnRecommencer, btnQuitter);
        }
        popUp.getChildren().addAll(title, choiceVBox);
        screen.getChildren().add(popUp);
    }

    public void removePopUp() {
        worldMap.setEffect(null);
        playerHud.setEffect(null);
        screen.getChildren().remove(popUp);
    }

    public void refreshScreen() {
        root.getChildren().remove(screen);
        root.getChildren().add(screen);
    }

    public void addWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
        root.getChildren().add(worldMap);
    }

    public void addPlayerHud(PlayerHud playerHud) {
        this.playerHud = playerHud;
        root.getChildren().add(playerHud);
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

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public PlayerHud getPlayerHud() {
        return playerHud;
    }

    public Button getBtnReprendre() {
        return btnReprendre;
    }

    public Button getBtnQuitter() {
        return btnQuitter;
    }

    public Button getBtnRecommencer() {
        return btnRecommencer;
    }

    public enum PopUpType {LOSE, WIN, PAUSE}
}

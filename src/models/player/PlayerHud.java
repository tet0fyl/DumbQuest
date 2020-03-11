package models.player;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import models.worldMap.WorldMap;
import utils.RessourcePath;
import views.ViewGame;


public class PlayerHud extends Pane {
    private VBox container;
    private Player player;
    private Group grpLifeBarre;
    private Rectangle rectContainerContainer;
    private Rectangle rectLifeBarreFill;
    private double widthLifeBarre = WorldMap.tileWidth * 3;
    private double heightLifeBarre = 10;
    private Label lifePointLbl;

    public PlayerHud(Player player) {
        this.player = player;
        container = new VBox();
        grpLifeBarre = new Group();
        rectContainerContainer = new Rectangle();
        rectLifeBarreFill = new Rectangle();

        rectContainerContainer.setFill(Color.BLACK);
        rectLifeBarreFill.setFill(Color.GREEN);
        rectContainerContainer.setHeight(heightLifeBarre);
        rectLifeBarreFill.setHeight(heightLifeBarre);
        rectContainerContainer.setWidth(widthLifeBarre);
        rectLifeBarreFill.setWidth(widthLifeBarre);

        Label lifeTitle = initLbl(10, "LIFE :");
        lifePointLbl = initLbl(10, player.getPv() + " / " + player.getPvMax());
        HBox hBoxLifeBarreContainerInfoEtBarre = new HBox();
        HBox.setMargin(grpLifeBarre, new Insets(0, 5, 0, 0));
        hBoxLifeBarreContainerInfoEtBarre.setAlignment(Pos.CENTER);

        grpLifeBarre.getChildren().addAll(rectContainerContainer, rectLifeBarreFill);
        hBoxLifeBarreContainerInfoEtBarre.getChildren().addAll(grpLifeBarre, lifePointLbl);

        update();
        container.getChildren().addAll(lifeTitle, hBoxLifeBarreContainerInfoEtBarre);
        getChildren().add(container);
        container.getStyleClass().add("hud");
    }

    public static Label initLbl(int fontSize, String textContent) {
        Label t = new Label();
        t.setText(textContent);
        t.setFont(Font.loadFont(ViewGame.class.getResourceAsStream(RessourcePath.fontPixel), fontSize));
        return t;
    }

    public void update() {
        double newWitdh = player.getPv() * widthLifeBarre / player.getPvMax();
        lifePointLbl.setText(player.getPv() + "/" + player.getPvMax());
        if (player.getPv() > player.getPvMax() / 2) {
            rectLifeBarreFill.setFill(Color.GREEN);
        } else if (player.getPv() > player.getPvMax() / 3) {
            rectLifeBarreFill.setFill(Color.ORANGE);
        } else {
            rectLifeBarreFill.setFill(Color.RED);
        }
        rectLifeBarreFill.setWidth(newWitdh);
    }
}

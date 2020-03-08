package models;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.worldMap.WorldMap;


public class PlayerHud extends Pane {
    private VBox container;
    private Player player;
    private Group grpLifeBarre;
    private Rectangle rectContainerContainer;
    private Rectangle rectLifeBarreFill;
    private double widthLifeBarre = WorldMap.tileWidth*3;
    private double heightLifeBarre = 10;

    public PlayerHud(Player player){
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

        Label lifeTitle = new Label("LIFE : ");
        grpLifeBarre.getChildren().addAll(rectContainerContainer,rectLifeBarreFill);

        update();
        container.getChildren().addAll(lifeTitle,grpLifeBarre);
        getChildren().add(container);
    }

    public void update(){
        double newWitdh = player.getPv() *  widthLifeBarre / player.getPvMax();
        if(player.getPv() > player.getPvMax()/2){
            rectLifeBarreFill.setFill(Color.GREEN);
        }else if(player.getPv() > player.getPvMax()/3){
            rectLifeBarreFill.setFill(Color.ORANGE);
        }else{
            rectLifeBarreFill.setFill(Color.RED);
        }
        rectLifeBarreFill.setWidth(newWitdh);
    }
}

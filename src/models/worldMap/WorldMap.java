package models.worldMap;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import models.Player;
import utils.Config;

public class WorldMap extends Pane {

    public static final int tileXNumber = 16;
    public static final int tileYNumber = 12;
    public static final int tileWidth = (int)Config.gameWindowWidth /tileXNumber;
    public static final int tileHeight = (int)Config.gameWindowHeight /tileYNumber;

    private int worldMapWidth;
    private int worldMapHeigth;
    private int currentArea;

    private AreaMap areaMap;
    private Group gridWorldMap;

    public WorldMap() {
        currentArea = 0;
        gridWorldMap = new Group();
        areaMap = new AreaMap(null);
        gridWorldMap.getChildren().add(areaMap);

        getChildren().add(gridWorldMap);
    }

    public void addPlayer(Player player){
        getChildren().add(player);
    }

    public void area0(){

    }


    public AreaMap getAreaMap() {
        return areaMap;
    }

}

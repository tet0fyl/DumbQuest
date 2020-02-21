package models.worldMap;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import models.Player;
import utils.Config;

public class WorldMap extends Pane {

    public static final int tileXNumber = 16;
    public static final int tileYNumber = 12;
    public static final double tileWidth = Config.gameWindowWidth /tileXNumber;
    public static final double tileHeight = Config.gameWindowHeight /tileYNumber;

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

    public void setPlayerInWorldMap(Player player){
        player.setCurrentTile(areaMap.getTiles()[WorldMap.tileXNumber/2][WorldMap.tileYNumber/2]);
        player.setPosition(player.getCurrentTile().getLayoutX(),player.getCurrentTile().getLayoutY());
        player.update();
        gridWorldMap.getChildren().add(player);
    }

    public void area0(){

    }

    public AreaMap getAreaMap() {
        return areaMap;
    }

}

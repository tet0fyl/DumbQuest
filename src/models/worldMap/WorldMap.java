package models.worldMap;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import models.Player;
import models.Vector2;
import utils.Config;

public class WorldMap extends Pane {

    public static final int tileXNumber = 16;
    public static final int tileYNumber = 12;
    public static final int tileWidth = (int)Config.gameWindowWidth /tileXNumber;
    public static final int tileHeight = (int)Config.gameWindowHeight /tileYNumber;
    public static final int areaXNumber = 2;
    public static final int areaYNumber = 1;
    public static final int areaWidth = (int)Config.gameWindowWidth * areaXNumber;
    public static final int areaHeight = (int)Config.gameWindowWidth * areaYNumber;


    private int worldMapWidth;
    private int worldMapHeigth;
    private int currentArea;

    private AreaMap[][] areaMap;
    private Group gridWorldMap;

    public WorldMap() {
        currentArea = 0;
        gridWorldMap = new Group();
        areaMap = new AreaMap[areaXNumber][areaYNumber];
        areaMap[0][0] = new AreaMap(tileSetsArea0(), new Vector2(0,0, Vector2.RELATIVE_TO_AREA));
        areaMap[1][0] = new AreaMap(tileSetsArea0(), new Vector2(1,0, Vector2.RELATIVE_TO_AREA));
        gridWorldMap.getChildren().add(areaMap[0][0]);
        gridWorldMap.getChildren().add(areaMap[1][0]);

        getChildren().add(gridWorldMap);
    }

    public void addPlayer(Player player){
        getChildren().add(player);
    }

    public TileSet[][] tileSetsArea0(){
        return new TileSet[][]{
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK},
        };
    }

    public TileSet[][] tileSetsArea1(){
        return new TileSet[][]{
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK},
        };
    }


    public AreaMap getAreaMap(int currentArea) {
        if(currentArea == 0 ){
            return areaMap[0][0];
        } else if (currentArea == 1){
            return areaMap[1][0];
        }
        return null;
    }

}

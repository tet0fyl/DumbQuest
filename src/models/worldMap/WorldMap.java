package models.worldMap;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.Pane;
import models.Player;
import models.ennemis.Ennemi;
import models.ennemis.Soldier;
import utils.Config;

import java.util.ArrayList;


public class WorldMap extends Pane {

    public static final int tileXNumber = 16;
    public static final int tileYNumber = 12;
    public static final double tileWidth = Config.gameWindowWidth /tileXNumber;
    public static final double tileHeight = Config.gameWindowHeight /tileYNumber;
    public static final int areaXNumber = 3;
    public static final int areaYNumber = 2;
    public static final double areaWidth = Config.gameWindowWidth;
    public static final double areaHeight = Config.gameWindowHeight;

    private PerspectiveCamera camera;
    private int cameraVitesse;
    private AreaMap[][] areaMap;
    private Group gridWorldMap;
    private AreaMap playerCurrentArea;
    private AreaMap playerPrevArea;
    private Tile playerCurrentTile;
    private Tile playerPrevTile;

    public WorldMap() {
        cameraVitesse = 2;
        gridWorldMap = new Group();
        areaMap = new AreaMap[areaXNumber][areaYNumber];
        areaMap[0][0] = new AreaMap(tileSetsArea0(), 0,0);
        //areaMap[0][0] = new AreaMap(tileSetsArea2(), 0,0);
        areaMap[0][0].setEnnemiArrayList(ennemisArea2());
        areaMap[1][0] = new AreaMap(tileSetsArea1(), 1,0);
        areaMap[1][1] = new AreaMap(tileSetsArea2(), 1,1);
        areaMap[2][1] = new AreaMap(tileSetsArea3(), 2,1);
        areaMap[2][0] = new AreaMap(tileSetsArea4(), 2,0);

        gridWorldMap.getChildren().add(areaMap[0][0]);
        gridWorldMap.getChildren().add(areaMap[1][0]);
        gridWorldMap.getChildren().add(areaMap[1][1]);
        gridWorldMap.getChildren().add(areaMap[2][1]);
        gridWorldMap.getChildren().add(areaMap[2][0]);
        getChildren().add(gridWorldMap);
    }

    public void initCamera(PerspectiveCamera camera){
        this.camera = camera;
    }

    public void addPlayer(Player player){
        getChildren().add(player);
    }

    public Ennemi[] ennemisArea2(){
        return new Ennemi[]{
                new Soldier(0, 0, 10, 10)
        };
    }

    public TileSet[][] tileSetsArea0(){
        return new TileSet[][]{
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
        };
    }

    public TileSet[][] tileSetsArea1(){
        return new TileSet[][]{
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND},
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK},
        };
    }
    public TileSet[][] tileSetsArea2(){
        return new TileSet[][]{
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.ROCK},
                {TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK},
        };
    }



    public TileSet[][] tileSetsArea3(){
        return new TileSet[][]{
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK},
        };
    }

    public TileSet[][] tileSetsArea4(){
        return new TileSet[][]{
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK},
        };
    }

    public AreaMap getAreaMap(int currentAreaX, int currentAreaY) {
            return areaMap[currentAreaX][currentAreaY];
    }

    public AreaMap getCurrentArea(){
        return playerCurrentArea;
    }

    public void watchPlayer(Player player){
        playerCurrentArea = getAreaMap(player.getAreaCoordX(), player.getAreaCoordY());
        playerCurrentTile = getTile(player.getTileCoordX(),player.getTileCoordY());
    }

    public boolean playerHasMoveToAnOtherTile(){
        if(!playerCurrentTile.equals(playerPrevTile)){
            playerPrevTile=playerCurrentTile;
            return true;
        }
        return false;
    }

    public boolean playerHasMoveToAnOtherArea(){
        if(!playerCurrentArea.equals(playerPrevArea)){
            playerPrevArea=playerCurrentArea;
            return true;
        }
        return false;
    }

    public Tile getTile(int currentTileX, int currentTileY){
        return getCurrentArea().getTiles()[currentTileX][currentTileY];
    }

    public Ennemi[] getEnnemisOfTheCurrentArea(){
        return getCurrentArea().getEnnemiArrayList();
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }
}

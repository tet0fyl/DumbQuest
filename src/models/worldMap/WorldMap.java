package models.worldMap;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.Pane;
import models.Direction;
import models.Player;
import models.Vector2;
import utils.Config;

public class WorldMap extends Pane {

    public static final int tileXNumber = 16;
    public static final int tileYNumber = 12;
    public static final double tileWidth = Config.gameWindowWidth /tileXNumber;
    public static final double tileHeight = Config.gameWindowHeight /tileYNumber;
    public static final int areaXNumber = 2;
    public static final int areaYNumber = 1;
    public static final double areaWidth = Config.gameWindowWidth;
    public static final double areaHeight = Config.gameWindowWidth;

    private int currentArea;
    private Player player;
    private PerspectiveCamera camera;
    private int cameraVitesse;
    private AreaMap[][] areaMap;
    private Group gridWorldMap;

    public WorldMap() {
        cameraVitesse = 20;
        gridWorldMap = new Group();
        areaMap = new AreaMap[areaXNumber][areaYNumber];
        areaMap[0][0] = new AreaMap(tileSetsArea0(), new Vector2(0,0, Vector2.RELATIVE_TO_AREA));
        areaMap[1][0] = new AreaMap(tileSetsArea1(), new Vector2(1,0, Vector2.RELATIVE_TO_AREA));
        gridWorldMap.getChildren().add(areaMap[0][0]);
        gridWorldMap.getChildren().add(areaMap[1][0]);

        getChildren().add(gridWorldMap);
    }

    public void initCamera(PerspectiveCamera camera){
        this.camera = camera;
    }

    public void addPlayer(Player player){
        this.player = player;
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
                {TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK},
                {TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.ROCK, TileSet.GROUND, TileSet.GROUND, TileSet.ROCK, TileSet.ROCK},
        };
    }

    public void updateCamera(Player player){
        if( camera.getTranslateX() != (player.getAreaCoordX() * areaWidth) || camera.getTranslateY() != player.getAreaCoordY() * areaHeight){
            if (player.getAreaCoordX() * areaWidth > camera.getTranslateX()){
                camera.setTranslateX(camera.translateXProperty().doubleValue() + cameraVitesse);
            }
            if (player.getAreaCoordX() * areaWidth < camera.getTranslateX()){
                camera.setTranslateX(camera.translateXProperty().doubleValue() - cameraVitesse);
            }
            if (player.getAreaCoordY() * areaHeight < camera.getTranslateY()){
                camera.setTranslateY(camera.translateYProperty().doubleValue() + cameraVitesse);
            }
            if (player.getAreaCoordY() * areaHeight > camera.getTranslateY()){
                camera.setTranslateY(camera.translateYProperty().doubleValue() - cameraVitesse);
            }
        };
    }


    public AreaMap getAreaMap(int currentAreaX, int currentAreaY) {
            return areaMap[currentAreaX][currentAreaY];
    }

}

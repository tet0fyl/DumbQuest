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
    public static final int areaXNumber = 3;
    public static final int areaYNumber = 2;
    public static final double areaWidth = Config.gameWindowWidth;
    public static final double areaHeight = Config.gameWindowHeight;

    private int currentArea;
    private Player player;
    private PerspectiveCamera camera;
    private int cameraVitesse;
    private AreaMap[][] areaMap;
    private Group gridWorldMap;

    public WorldMap() {
        cameraVitesse = 2;
        gridWorldMap = new Group();
        areaMap = new AreaMap[areaXNumber][areaYNumber];
        areaMap[0][0] = new AreaMap(tileSetsArea0(), new Vector2(0,0, Vector2.RELATIVE_TO_AREA));
        areaMap[1][0] = new AreaMap(tileSetsArea1(), new Vector2(1,0, Vector2.RELATIVE_TO_AREA));
        areaMap[1][1] = new AreaMap(tileSetsArea2(), new Vector2(1,1, Vector2.RELATIVE_TO_AREA));
        areaMap[2][1] = new AreaMap(tileSetsArea3(), new Vector2(2,1, Vector2.RELATIVE_TO_AREA));
        areaMap[2][0] = new AreaMap(tileSetsArea4(), new Vector2(2,0, Vector2.RELATIVE_TO_AREA));
        gridWorldMap.getChildren().add(areaMap[0][0]);
        gridWorldMap.getChildren().add(areaMap[1][0]);
        gridWorldMap.getChildren().add(areaMap[1][1]);
        gridWorldMap.getChildren().add(areaMap[2][1]);
        gridWorldMap.getChildren().add(areaMap[2][0]);

        getChildren().add(gridWorldMap);
    }

    public void initCamera(PerspectiveCamera camera){
        this.camera = camera;
        camera.setTranslateZ(-1600);

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

    //TODO: Transition camera avec regulation PID
    public void updateCamera(Player player){
                camera.setTranslateX(camera.translateXProperty().doubleValue() + (player.getAreaCoordX()*areaWidth - camera.getTranslateX()));
                camera.setTranslateY(camera.translateYProperty().doubleValue() + (player.getAreaCoordY()*areaHeight - camera.getTranslateY()));
    }


    public AreaMap getAreaMap(int currentAreaX, int currentAreaY) {
            return areaMap[currentAreaX][currentAreaY];
    }

}

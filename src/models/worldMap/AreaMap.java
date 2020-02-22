package models.worldMap;

import javafx.scene.Group;
import javafx.scene.layout.Pane;


public class AreaMap extends Pane {
    private Group graphic;
    private Tile[][] tiles;
    private double x;
    private double y;

    public AreaMap(TileSet[][] tileSets, int areaX, int areaY){
        this.x = areaX * WorldMap.areaWidth;
        this.y = areaY * WorldMap.areaHeight;
        tiles = new Tile[WorldMap.tileXNumber][WorldMap.tileYNumber];
        setLayoutX(x);
        setLayoutY(y);
        setWidth(WorldMap.areaWidth);
        setHeight(WorldMap.areaHeight);
        graphic = new Group();
        for (int i = 0; i < WorldMap.tileXNumber; i++) {
            for (int j = 0; j < WorldMap.tileYNumber; j++) {
                Tile tile = new Tile(tileSets[i][j],i,j);
                tiles[i][j] = tile;

                graphic.getChildren().add(tile);
            }
        }
        getChildren().add(graphic);
    }

    public Tile getTilePositionByCoord(int x, int y){
        return tiles[x * (int)WorldMap.tileWidth][y * (int)WorldMap.tileHeight];
    };

    public Tile[][] getTiles() {
        return tiles;
    }
}

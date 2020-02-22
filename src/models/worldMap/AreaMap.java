package models.worldMap;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import models.Vector2;


public class AreaMap extends Pane {
    private Group graphic;
    private Vector2 position;
    private Tile[][] tiles;

    public AreaMap(TileSet[][] tileSets, Vector2 position){
        this.position = position;
        tiles = new Tile[WorldMap.tileXNumber][WorldMap.tileYNumber];
        setLayoutX(position.getX());
        setLayoutY(position.getY());
        setWidth(WorldMap.areaWidth);
        setHeight(WorldMap.areaHeight);
        graphic = new Group();
        for (int i = 0; i < WorldMap.tileXNumber; i++) {
            for (int j = 0; j < WorldMap.tileYNumber; j++) {
                Tile tile = new Tile(tileSets[i][j],new Vector2(i,j, Vector2.RELATIVE_TO_TILE));
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

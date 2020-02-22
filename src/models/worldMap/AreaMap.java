package models.worldMap;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import models.Vector2;


public class AreaMap extends Pane {
    private Group graphic;
    private Tile[][] tiles;

    public AreaMap(TileSet[] tileSets){
        tiles = new Tile[WorldMap.tileXNumber][WorldMap.tileYNumber];
        graphic = new Group();
        for (int i = 0; i < WorldMap.tileXNumber; i++) {
            for (int j = 0; j < WorldMap.tileYNumber; j++) {
                Tile tile = new Tile(null,new Vector2(i,j));
                tiles[i][j] = tile;

                graphic.getChildren().add(tile);
            }
        }
        getChildren().add(graphic);
    }

    public Tile getTilePositionByCoord(int x, int y){
        return tiles[x * WorldMap.tileWidth][y * WorldMap.tileHeight];
    };

    public Tile[][] getTiles() {
        return tiles;
    }
}

package models.worldMap;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import models.ennemis.Ennemi;

import java.util.ArrayList;

public class AreaMap extends Pane {
    private Group graphic;
    private Tile[][] tiles;
    private double x;
    private double y;
    private int indiceX;
    private int indiceY;
    private ArrayList<Ennemi> ennemiArrayList;

    public AreaMap(String[][] tileSets, int areaX, int areaY){
        indiceX = areaX;
        indiceY = areaY;
        this.x = areaX * WorldMap.areaWidth;
        this.y = areaY * WorldMap.areaHeight;
        ennemiArrayList = new ArrayList<>();
        tiles = new Tile[WorldMap.tileXNumber][WorldMap.tileYNumber];
        setLayoutX(x);
        setLayoutY(y);
        setWidth(WorldMap.areaWidth);
        setHeight(WorldMap.areaHeight);
        graphic = new Group();
        for (int i = 0; i < WorldMap.tileXNumber; i++) {
            for (int j = 0; j < WorldMap.tileYNumber; j++) {
                //System.out.println(i + " : " + j + " => " + tileSets[i][j]);
                Tile tile = new Tile(tileSets[j][i],i,j);
                tiles[i][j] = tile;
                graphic.getChildren().add(tile);
            }
        }
        getChildren().add(graphic);
    }

    public Tile getTilePositionByCoord(int x, int y){
        return tiles[x / (int)WorldMap.tileWidth][y / (int)WorldMap.tileHeight];
    };

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTileByPixel(double x, double y) {
        return tiles[(int)((x / WorldMap.tileWidth) % WorldMap.tileXNumber)][(int)((y / WorldMap.tileHeight) % WorldMap.tileYNumber)];
    }

    public Tile getTileByCoord(int x, int y) {
        return tiles[x][y];
    }

    public ArrayList<Ennemi> getEnnemiArrayList() {
        return ennemiArrayList;
    }

    public void setEnnemiArrayList(ArrayList<Ennemi> ennemiArrayList) {
        this.ennemiArrayList = ennemiArrayList;
        for(Ennemi ennemi: ennemiArrayList)
            getChildren().add(ennemi);
    }

    public int getIndiceX() {
        return indiceX;
    }

    public int getIndiceY() {
        return indiceY;
    }
}

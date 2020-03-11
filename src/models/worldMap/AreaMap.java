package models.worldMap;

import exception.AreaMapConstructionException;
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

    public AreaMap(String[][] tileSets, int areaX, int areaY) {
        /* Exception */
        try{
            for (int i = 0; i < tileSets.length; i++) {
                for (int j = 0; j < tileSets[i].length; j++) {
                    if(tileSets[i].length > WorldMap.tileXNumber) throw new AreaMapConstructionException("TileSet en trop row : " + i + " ! limite de la row a : " + WorldMap.tileXNumber);
                }
            }
            if(tileSets.length > WorldMap.tileYNumber) throw new AreaMapConstructionException("Tile set en trop row : " + tileSets.length + " limite des cols a : " + WorldMap.tileYNumber);
        } catch (AreaMapConstructionException err){
            err.printStackTrace();
        }

        indiceX = areaX;
        indiceY = areaY;
        this.x = areaX * WorldMap.areaWidth;
        this.y = areaY * WorldMap.areaHeight;
        ennemiArrayList = new ArrayList<>();
        tiles = new Tile[WorldMap.tileXNumber][WorldMap.tileYNumber];
        //setLayoutX(x);
        //setLayoutY(y);
        setWidth(WorldMap.areaWidth);
        setHeight(WorldMap.areaHeight);
        graphic = new Group();
        for (int i = 0; i < WorldMap.tileXNumber; i++) {
            for (int j = 0; j < WorldMap.tileYNumber; j++) {
                Tile tile = new Tile(tileSets[j][i],areaX,areaY,i,j);
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

    public Tile getTileByPixel(Ennemi ennemi) {
        return tiles[(int)((ennemi.getX() / WorldMap.tileWidth) % WorldMap.tileXNumber)][(int)((ennemi.getY() / WorldMap.tileHeight) % WorldMap.tileYNumber)];
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

    public void ennemisRespawn(){
        for(Ennemi ennemi: ennemiArrayList){
            if(ennemi.isAlive()){
                ennemi.respawn();
            }
        }
    }

    public int getIndiceX() {
        return indiceX;
    }

    public int getIndiceY() {
        return indiceY;
    }

    public Group getGraphic() {
        return graphic;
    }

    public void setGraphic(Group graphic) {
        this.graphic = graphic;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setIndiceX(int indiceX) {
        this.indiceX = indiceX;
    }

    public void setIndiceY(int indiceY) {
        this.indiceY = indiceY;
    }
}

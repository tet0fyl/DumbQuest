package models.worldMap;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utils.RessourcePath;


public class Tile extends Pane {
    private boolean traversable;
    private boolean empty;
    private ImageView graphic;
    private String tileSet;
    private double x;
    private double y;
    private int indiceX;
    private int indiceY;

    public Tile(String tileSet, int areaX, int areaY, int tileX, int tileY) {
        indiceX = tileX;
        indiceY = tileY;
        this.tileSet = tileSet;
        this.x = tileX * WorldMap.tileWidth + areaX * WorldMap.areaWidth;
        this.y = tileY * WorldMap.tileHeight + areaY * WorldMap.areaHeight;
        graphic = new ImageView(RessourcePath.urlTileSet + tileSet + ".png");

        graphic.setFitWidth(WorldMap.tileWidth);
        graphic.setFitHeight(WorldMap.tileHeight);
        setLayoutX(x);
        setLayoutY(y);
        int valueTile = (tileSet.charAt(0) == '0') ? Character.getNumericValue(tileSet.charAt(1)) : Integer.valueOf(tileSet);
        if (valueTile >= 50) {
            traversable = false;
        } else {
            traversable = true;
        }
        getChildren().add(graphic);
    }

    public double getBottomConstrain() {
        return y + WorldMap.tileHeight;
    }

    public double getTopConstrain() {
        return y;
    }

    public double getRightConstrain() {
        return x + WorldMap.tileWidth;
    }

    public double getLeftConstrain() {
        return x;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isTraversable() {
        return traversable;
    }

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public int getIndiceX() {
        return indiceX;
    }

    public void setIndiceX(int indiceX) {
        this.indiceX = indiceX;
    }

    public int getIndiceY() {
        return indiceY;
    }

    public void setIndiceY(int indiceY) {
        this.indiceY = indiceY;
    }

    public double getTheCenterX() {
        return x + WorldMap.tileWidth / 2;
    }

    public double getTheCenterY() {
        return y + WorldMap.tileHeight / 2;
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

    public ImageView getGraphic() {
        return graphic;
    }

    public void setGraphic(ImageView graphic) {
        this.graphic = graphic;
    }

    public String getTileSet() {
        return tileSet;
    }

    public void setTileSet(String tileSet) {
        this.tileSet = tileSet;
    }
}

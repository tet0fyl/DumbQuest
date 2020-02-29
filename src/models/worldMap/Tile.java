package models.worldMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

    public Tile(String tileSet, int tileX, int tileY){
        indiceX = tileX;
        indiceY = tileY;
        this.tileSet = tileSet;
        this.x = tileX * WorldMap.tileWidth;
        this.y = tileY * WorldMap.tileHeight;

        graphic = new ImageView(RessourcePath.urlTileSet + tileSet +  ".png");

        graphic.setFitWidth(WorldMap.tileWidth);
        graphic.setFitHeight(WorldMap.tileHeight);
        setLayoutX(x);
        setLayoutY(y);
        if(tileSet != "00"){
            traversable = false;
        } else {
            traversable = true;
        }
        getChildren().add(graphic);
    }

    public double getBottomConstrain(){
        return y + WorldMap.tileHeight;
    }
    public double getTopConstrain(){
        return y;
    }
    public double getRightConstrain(){
        return x + WorldMap.tileWidth;
    }
    public double getLeftConstrain(){
        return x;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isTraversable() {
        return traversable;
    }

    public int getIndiceX() {
        return indiceX;
    }

    public int getIndiceY() {
        return indiceY;
    }

    public double getTheCenterX(){
        return x + WorldMap.tileWidth / 2;
    }

    public double getTheCenterY(){
        return y + WorldMap.tileHeight / 2;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public ImageView getGraphic() {
        return graphic;
    }

    public void setGraphic(ImageView graphic) {
        this.graphic = graphic;
    }


    public void setX(double x) {
        this.x = x;
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

    public String getTileSet() {
        return tileSet;
    }

    public void setTileSet(String tileSet) {
        this.tileSet = tileSet;
    }
}

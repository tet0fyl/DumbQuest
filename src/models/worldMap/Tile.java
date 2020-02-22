package models.worldMap;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Vector2;


public class Tile extends Pane {
    private boolean traversable;
    private boolean empty;
    private Vector2 position;
    private Rectangle graphic;
    private TileSet tileSet;

    public Tile(TileSet tileSet, Vector2 position){
        this.tileSet = tileSet;
        this.position = position;

        graphic = new Rectangle();

        graphic.setWidth(WorldMap.tileWidth);
        graphic.setHeight(WorldMap.tileHeight);
        setLayoutX(position.getX());
        setLayoutY(position.getY());
        if(tileSet.equals(TileSet.ROCK)){
            graphic.setFill(Color.BROWN);
            traversable = false;
        } else {
            graphic.setFill(Color.YELLOW);
            traversable = true;
        }
        graphic.setStroke(Color.BLACK);
        getChildren().add(graphic);
    }

    public double getBottomConstrain(){
        return position.getY() + WorldMap.tileHeight;
    }
    public double getTopConstrain(){
        return position.getY();
    }
    public double getRightConstrain(){
        return position.getX() + WorldMap.tileWidth;
    }
    public double getLeftConstrain(){
        return position.getX();
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

    public Rectangle getGraphic() {
        return graphic;
    }
}

package models.worldMap;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile extends Pane {
    private boolean traversable;
    private boolean empty;
    private Rectangle graphic;
    private TileSet tileSet;
    private double x;
    private double y;

    public Tile(TileSet tileSet, int tileX, int tileY){
        this.tileSet = tileSet;
        this.x = tileX * WorldMap.tileWidth;
        this.y = tileY * WorldMap.tileHeight;

        graphic = new Rectangle();

        graphic.setWidth(WorldMap.tileWidth);
        graphic.setHeight(WorldMap.tileHeight);
        setLayoutX(x);
        setLayoutY(y);
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

    public Rectangle getGraphic() {
        return graphic;
    }
}

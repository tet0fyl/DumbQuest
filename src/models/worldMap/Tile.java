package models.worldMap;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile extends Pane {
    private boolean traversable;
    private boolean empty;
    private int x;
    private int y;
    private Rectangle graphic;

    public Tile(TileSet tileSet, int x, int y){
        this.x=x;
        this.y=y;
        graphic = new Rectangle();
        graphic.setWidth(WorldMap.tileWidth);
        graphic.setHeight(WorldMap.tileHeight);
        setLayoutX(x * WorldMap.tileWidth);
        setLayoutY(y * WorldMap.tileHeight);
        if(Math.random() < 0.25){
            graphic.setFill(Color.BROWN);
            traversable = false;
        } else {
            graphic.setFill(Color.YELLOW);
            traversable = true;
        }
        graphic.setStroke(Color.BLACK);
        getChildren().add(graphic);
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getGraphic() {
        return graphic;
    }
}

package views.graphicalElements;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.Player;


public class PlayerView extends Pane {
    private Player model;
    private Circle shape;
    private ImageView skin;

    public PlayerView(Player model){
        this.model = model;
        shape = new Circle();
        shape.setCenterX(model.getX());
        shape.setCenterY(model.getY());
        shape.setFill(Color.RED);
        shape.setRadius(20);
        this.getChildren().add(shape);
    }

    public void update(){
        shape.setCenterX(model.getX());
        shape.setCenterY(model.getY());
    }
}

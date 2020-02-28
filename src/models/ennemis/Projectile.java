package models.ennemis;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;


public class Projectile extends Pane {

    public Circle hitBox;
    public Circle skin;

    public Projectile(){
        hitBox.setRadius(20);
        skin.setRadius(20);
    }
}

package models;

import java.awt.*;
import java.util.ArrayList;

public class AreaMap {
    private int tileXNumber;
    private int tileYNumber;
    private int tileSize;
    public enum TileSet { ROCK, GRASS, GROUND, THREE, WATER }
    private ArrayList<ArrayList<Rectangle>> tileGraphics;

    public AreaMap(ArrayList<ArrayList<TileSet>> tileSets){

    }

}

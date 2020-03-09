package models.ia;

import models.worldMap.Tile;

public class GraphNode {
    public int f;
    public int g;
    public int h;
    public int indiceX;
    public int indiceY;
    public GraphNode parent;
    public Tile tile;

    public GraphNode(Tile tile){
        this.tile = tile;
        this.indiceX = tile.getIndiceX();
        this.indiceY = tile.getIndiceY();
        f=0;
        g=0;
        h=0;
    }

    public boolean graphEquals(GraphNode o) {
        return indiceX == o.indiceX &&
                indiceY == o.indiceY;
    }
}

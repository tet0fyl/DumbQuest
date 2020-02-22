package models;

import models.worldMap.WorldMap;

public class Partie {
    private Player player;
    private WorldMap worldMap;

    public Partie(){
        worldMap = new WorldMap();
        player = new Player(new Vector2(Math.round(WorldMap.tileXNumber/2),Math.round(WorldMap.tileYNumber/2),Vector2.RELATIVE_TO_TILE), worldMap);
    }

    public Player getPlayer() {
        return player;
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }
}

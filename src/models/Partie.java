package models;

import models.worldMap.WorldMap;

public class Partie {
    private Player player;
    private WorldMap worldMap;

    public Partie(){
        worldMap = new WorldMap();
        player = new Player(0,0,2,2);
        worldMap.addPlayer(player);
    }

    public Player getPlayer() {
        return player;
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }
}

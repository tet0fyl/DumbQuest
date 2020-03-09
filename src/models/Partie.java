package models;

import models.player.Player;
import models.worldMap.WorldMap;

public class Partie {
    private Player player;
    private WorldMap worldMap;
    private boolean gameEnd = false;
    private boolean youWin = false;
    private boolean youLose = false;
    private boolean onPause = false;

    public Partie(){
        worldMap = new WorldMap();
        player = new Player(0,0,2,4);
        worldMap.addPlayer(player);
    }

    public Player getPlayer() {
        return player;
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public boolean isYouWin() {
        return youWin;
    }

    public void setYouWin(boolean youWin) {
        this.youWin = youWin;
    }

    public boolean isYouLose() {
        return youLose;
    }

    public void setYouLose(boolean youLose) {
        this.youLose = youLose;
    }

    public boolean isOnPause() {
        return onPause;
    }

    public void setOnPause(boolean onPause) {
        this.onPause = onPause;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }
}

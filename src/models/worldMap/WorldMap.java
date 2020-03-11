package models.worldMap;

import exception.AreaMapConstructionException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import models.player.Player;
import models.ennemis.*;
import utils.Config;

import java.util.ArrayList;

public class WorldMap extends Pane {

    public static final int tileXNumber = 16;
    public static final int tileYNumber = 11;
    public static final double tileWidth = Config.gameWindowWidth /tileXNumber;
    public static final double tileHeight = Config.gameWindowHeight /tileYNumber;
    public static final int areaXNumber = 3;
    public static final int areaYNumber = 2;
    public static final double areaWidth = Config.gameWindowWidth;
    public static final double areaHeight = Config.gameWindowHeight;

    private AreaMap[][] areaMap;
    private Group gridWorldMap;
    private AreaMap playerCurrentArea;
    private AreaMap playerPrevArea;
    private Tile playerCurrentTile;
    private Tile playerPrevTile;
    private Timeline translateTimeline;
    private Timeline earthQuake;
    private int x = 0;
    private int y = 0;

    public WorldMap() {
        gridWorldMap = new Group();
        areaMap = new AreaMap[areaXNumber][areaYNumber];
        playerCurrentArea = areaMap[0][0];
        playerPrevArea = playerCurrentArea;
        areaMap[0][0] = new AreaMap(tileSetsArea0(), 0,0);
        areaMap[1][0] = new AreaMap(tileSetsArea1(), 1,0);
        areaMap[1][0].setEnnemiArrayList(ennemisArea1());
        areaMap[1][1] = new AreaMap(tileSetsArea2(), 1,1);
        areaMap[1][1].setEnnemiArrayList(ennemisArea2());
        areaMap[2][1] = new AreaMap(tileSetsArea3(), 2,1);
        areaMap[2][1].setEnnemiArrayList(ennemisArea3());
        areaMap[2][0] = new AreaMap(tileSetsArea4(), 2,0);
        areaMap[2][0].setEnnemiArrayList(ennemisArea4());

        gridWorldMap.getChildren().add(areaMap[0][0]);
        gridWorldMap.getChildren().add(areaMap[1][0]);
        gridWorldMap.getChildren().add(areaMap[1][1]);
        gridWorldMap.getChildren().add(areaMap[2][1]);
        gridWorldMap.getChildren().add(areaMap[2][0]);
        playerCurrentArea = areaMap[0][0];
        playerPrevArea = playerCurrentArea;
        getChildren().addAll(gridWorldMap);
    }

    public void addPlayer(Player player){
        getChildren().add(player);
    }

    public void addElement(Projectile projectile){
        getChildren().add(projectile);
    }

    public String[][] tileSetsArea0(){
        return new String[][]
                {       {"65","62","63","62","63","62","63","62","63","62","63","62","63","62","63","64"},
                        {"65","00","00","00","00","00","00","00","00","00","10","11","11","11","12","64"},
                        {"65","00","00","00","00","00","00","00","00","00","13","50","50","50","14","64"},
                        {"65","00","00","00","00","00","00","00","00","00","15","16","16","16","17","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","62"},
                        {"65","00","66","67","68","00","00","00","00","00","00","00","00","02","03","03"},
                        {"65","00","69","70","71","76","77","00","00","00","00","00","00","05","01","01"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","07","08","08"},
                        {"65","60","61","60","61","60","61","60","61","60","61","60","61","60","61","60"}
                };

    }

    public String[][] tileSetsArea1(){
        return new String[][]
                {       {"65","62","63","62","63","62","63","62","63","62","63","62","63","62","63","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"65","00","00","00","00","75","76","77","00","00","00","00","00","00","00","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"63","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"03","03","04","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"01","01","06","00","00","00","00","00","00","00","00","02","03","03","04","64"},
                        {"08","08","09","00","00","00","00","00","00","00","00","05","01","01","06","64"},
                        {"61","60","61","60","61","60","61","60","61","60","61","05","01","01","06","64"}
                };

    }

    public ArrayList<Ennemi> ennemisArea1(){
        ArrayList<Ennemi> ennemis = new ArrayList<>();
        ennemis.add(new Soldier(1, 0, 14, 2));
        return ennemis;
    }

    public String[][] tileSetsArea2(){
        return new String[][]
                {       {"65","62","63","62","63","62","63","62","63","62","63","05","01","01","06","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","05","01","01","06","64"},
                        {"65","00","00","85","86","86","80","00","00","00","00","07","08","08","09","64"},
                        {"65","00","00","00","00","00","83","00","00","00","00","00","00","00","00","64"},
                        {"65","85","86","86","80","00","81","86","86","86","86","86","86","86","87","64"},
                        {"65","00","00","00","83","00","00","00","00","00","82","00","00","00","00","64"},
                        {"65","00","00","00","81","86","86","86","87","00","83","00","00","00","00","62"},
                        {"65","00","00","00","00","00","00","00","00","00","83","00","00","02","03","03"},
                        {"65","75","00","00","00","00","00","00","00","00","84","00","00","05","01","01"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","07","08","08"},
                        {"65","60","61","60","61","60","61","60","61","60","61","60","61","60","61","60"}
                };

    }

    public ArrayList<Ennemi> ennemisArea2(){
        ArrayList<Ennemi> ennemis = new ArrayList<>();
        ennemis.add(new Soldier(1, 1, 14, 10));
        ennemis.add(new Plant(1, 1, 1, 1));
        ennemis.add(new Plant(1, 1, 1, 9));
        return ennemis;
    }

    public String[][] tileSetsArea3(){
        return new String[][]
                {       {"65","62","63","62","63","62","63","00","00","00","62","63","62","63","62","63"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","60"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"63","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"03","03","04","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"01","01","06","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"08","08","09","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"61","60","61","60","61","60","61","60","61","60","61","60","61","60","61","64"}
                };

    }



    public ArrayList<Ennemi> ennemisArea3(){
        ArrayList<Ennemi> ennemis = new ArrayList<>();
        ennemis.add(new Soldier(2, 1, 5, 5));
        ennemis.add(new Plant(2, 1, 1, 1));
        ennemis.add(new Plant(2, 1, 14, 9));
        ennemis.add(new Worm(2, 1, 3, 10));
        return ennemis;
    }

    public String[][] tileSetsArea4(){
        return new String[][]
                {       {"65","62","63","62","63","62","63","62","63","62","63","62","63","62","63","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","64"},
                        {"65","00","02","03","03","03","03","03","03","03","03","03","03","04","00","64"},
                        {"65","00","05","01","01","01","01","01","01","01","01","01","01","06","00","64"},
                        {"65","00","05","01","01","01","01","01","01","01","01","01","01","06","00","64"},
                        {"65","00","05","01","01","01","01","01","01","01","01","01","01","06","00","64"},
                        {"65","00","05","01","01","01","01","01","01","01","01","01","01","06","00","64"},
                        {"65","00","05","01","01","01","01","01","01","01","01","01","01","06","00","64"},
                        {"65","00","07","08","08","08","08","08","08","08","08","08","08","09","00","64"},
                        {"65","00","00","00","00","00","00","00","00","00","00","00","00","00","00","62"},
                        {"65","60","61","60","61","60","61","00","00","00","60","61","60","61","60","61"}
                };
    }



    public ArrayList<Ennemi> ennemisArea4(){
        ArrayList<Ennemi> ennemis = new ArrayList<>();
        ennemis.add(new Boss(2, 0, 10, 2));
        return ennemis;
    }

    public void moveCamera() {
        translateTimeline = new Timeline();
        translateTimeline.getKeyFrames().addAll(
                new KeyFrame(new Duration(750), new KeyValue(layoutXProperty(), -getCurrentArea().getX())),
                new KeyFrame(new Duration(750), new KeyValue(layoutYProperty(), -getCurrentArea().getY())));
        translateTimeline.setCycleCount(1);
        translateTimeline.play();
    }

    public void makeEarthQuake() {
        earthQuake = new Timeline();
        double posZero = getCurrentArea().getIndiceX() * WorldMap.areaWidth;
        System.out.println("POS Z");
        System.out.println(posZero);
        System.out.println(layoutXProperty());
        earthQuake.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(layoutXProperty(), posZero - 10)),
                new KeyFrame(new Duration(5), new KeyValue(layoutXProperty(), posZero + 10)),
                new KeyFrame(new Duration(10), new KeyValue(layoutXProperty(), posZero)));
        earthQuake.setCycleCount(150);
        earthQuake.play();
    }

    public AreaMap getAreaMap(double x, double y) {
            return areaMap[(int)(x / WorldMap.areaWidth)][(int)(y / WorldMap.areaHeight)];
    }

    public AreaMap getAreaMapByCoord(int x, int y) {
            return areaMap[x][y];
    }

    public AreaMap getCurrentArea(){
        return playerCurrentArea;
    }

    public void watchPlayer(Player player){
        playerCurrentArea = getAreaMapByCoord(player.getAreaCoordX(), player.getAreaCoordY());
        playerCurrentTile = getTileByCoord(player.getTileCoordX(),player.getTileCoordY());
    }

    public boolean playerHasMoveToAnOtherTile(){
        if(!playerCurrentTile.equals(playerPrevTile)){
            playerPrevTile=playerCurrentTile;
            return true;
        }
        return false;
    }

    public boolean playerHasMoveToAnOtherArea(){
        if(!playerCurrentArea.equals(playerPrevArea)){
            playerPrevArea.ennemisRespawn();
            playerPrevArea=playerCurrentArea;
            return true;
        }
        return false;
    }

    public Tile getTileByCoord(int currentTileX, int currentTileY){
        return getCurrentArea().getTiles()[currentTileX][currentTileY];
    }

    public ArrayList<Ennemi> getEnnemisOfTheCurrentArea(){
        return getCurrentArea().getEnnemiArrayList();
    }

    public void setEnnemisOfTheCurrentArea(ArrayList<Ennemi> ennemis){
         getCurrentArea().setEnnemiArrayList(ennemis);
    }

    public static int getTileXNumber() {
        return tileXNumber;
    }

    public static int getTileYNumber() {
        return tileYNumber;
    }

    public static double getTileWidth() {
        return tileWidth;
    }

    public static double getTileHeight() {
        return tileHeight;
    }

    public static int getAreaXNumber() {
        return areaXNumber;
    }

    public static int getAreaYNumber() {
        return areaYNumber;
    }

    public static double getAreaWidth() {
        return areaWidth;
    }

    public static double getAreaHeight() {
        return areaHeight;
    }

    public AreaMap[][] getAreaMap() {
        return areaMap;
    }

    public Group getGridWorldMap() {
        return gridWorldMap;
    }

    public AreaMap getPlayerCurrentArea() {
        return playerCurrentArea;
    }

    public AreaMap getPlayerPrevArea() {
        return playerPrevArea;
    }

    public Tile getPlayerCurrentTile() {
        return playerCurrentTile;
    }

    public Tile getPlayerPrevTile() {
        return playerPrevTile;
    }
}

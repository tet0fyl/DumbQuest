package models;

import models.worldMap.Tile;
import models.worldMap.WorldMap;

import java.util.ArrayList;

public class IA {

    public ArrayList<GraphNode> path;
    public int currentPathNode = 0;

    public void aStarPathFinding(Tile[][] maze, Tile start, Tile end){
        GraphNode startNode = new GraphNode(start);
        GraphNode endNode = new GraphNode(end);
        ArrayList<GraphNode> openList = new ArrayList<>();
        ArrayList<GraphNode> closedList = new ArrayList<>();;

        openList.add(startNode);

        while(openList.size() > 0){
            GraphNode currentNode = openList.get(0);
            for(int i = 0 ; i < openList.size(); i++){
                if(openList.get(i).f < currentNode.f)
                    currentNode = openList.get(i);
            }
            openList.remove(currentNode);
            closedList.add(currentNode);

            if(currentNode.graphEquals(endNode)){
                ArrayList<GraphNode> path = new ArrayList<>();
                while(currentNode != null){
                    path.add(currentNode);
                    currentNode = currentNode.parent;
                }
                currentPathNode = path.size();
                //path = path.size();
            }

            for (int i = -1 + currentNode.indiceX ; i <= 1 + currentNode.indiceX ; i++) {
                for (int j = -1 + currentNode.indiceY ; j <= 1 + currentNode.indiceY ; j++) {
                    if(i == currentNode.indiceX && j == currentNode.indiceY) continue;
                    if(i < 0 || j < 0 || i >= WorldMap.tileXNumber || j >= WorldMap.tileYNumber) continue;
                    GraphNode child = new GraphNode(maze[i][j]);
                    for(GraphNode closedNode: closedList){
                        if(child.graphEquals(closedNode))continue;
                    }
                    child.g = currentNode.g + 1;
                    child.h = (Math.abs(child.indiceX - endNode.indiceX * 2)) + (Math.abs(child.indiceY - endNode.indiceY) * 2);
                    child.f = child.g + child.h;
                    child.parent = currentNode;
                    for(GraphNode openNode : openList){
                        if(child.graphEquals(openNode) || child.g > openNode.g)continue;
                    }
                    openList.add(child);
                }
            }
        }
    }

    public void findAPath(Tile[][] maze, Tile start, Tile end){

    }

    public void goToTheNexPathtNode(){

    }
}

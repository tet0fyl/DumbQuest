package exception;

import models.worldMap.AreaMap;

public class AreaMapConstructionException extends Exception{
    private AreaMap areaMap;

    public AreaMapConstructionException(AreaMap areaMap){
        this.areaMap = areaMap;
    }

    @Override
    public String toString() {
        return "Erreur AreaMap Out Of Bound " ;
    }
}

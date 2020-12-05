package Model;


import Controller.Node;
import Model.MapSettings.MapSettings;
import View.MapView;

import java.util.Map;

public class MapModel extends Model {
    MapView view;
    int[][] map;

    public MapModel(MapView view) {
        this.view = view;
        this.map = new int[][]{
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 3}
        };
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public void addPath(Node child) {
        int[][] currentMap = getMap();
        while (child.getParent() != null) {
            int x = child.getX();
            int y = child.getY();
            if(currentMap[x][y] == MapSettings.PASSABLE) {
                currentMap[x][y] = MapSettings.PATH;
            }
            child = child.getParent();
        }
        setMap(currentMap);
    }

    public void updateTile(int x, int y, int changeTo) {
        this.map[x][y] = changeTo;
    }

    public void removePath() {
        for(int x = 0; x < this.map.length; x++) {
            for(int y = 0; y < this.map[x].length; y++) {
                if(this.map[x][y] == MapSettings.PATH) {
                    this.map[x][y] = MapSettings.PASSABLE;
                }
            }
        }
    }
}

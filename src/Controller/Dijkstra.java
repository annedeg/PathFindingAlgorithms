package Controller;

import java.util.ArrayList;

public class Dijkstra {

    public Dijkstra() {
    }

    private int minDistance(int[] dist, Boolean[] sptset) {
        int lowest_distance = Integer.MAX_VALUE;
        int lowest_index = -1;
        for (int i = 0; i < sptset.length; i++) {
            if (sptset[i] == false && dist[i] <= lowest_distance) {
                lowest_distance = dist[i];
                lowest_index = i;
            }
        }
        return lowest_index;
    }

    private ArrayList<Object> getShortestPaths(int[][] map, int source) {
        int[] route = new int[map.length];
        Boolean[] sptset = new Boolean[map.length];
        int[] sptDistance = new int[map.length];
        for (int i = 0; i < map.length; i++) {
            sptDistance[i] = Integer.MAX_VALUE;
            sptset[i] = false;
        }
        sptDistance[source] = 0;
        for (int count = 0; count < map.length - 1; count++) {
            int vertex = minDistance(sptDistance, sptset);
            sptset[vertex] = true;
            for (int v = 0; v < map.length; v++) {
                if (!sptset[v] && map[vertex][v] != 0 && sptDistance[vertex] != Integer.MAX_VALUE && sptDistance[vertex] + map[vertex][v] < sptDistance[v]) {
                    sptDistance[v] = sptDistance[vertex] + map[vertex][v];
                    route[vertex] = v;
                }
            }
        }
        ArrayList<Object> list = new ArrayList<>();
        list.add(sptDistance);
        list.add(route);
        return list;
    }

    public static void main(String[] args) {
        /* Let us create the example graph discussed above */
        Dijkstra t = new Dijkstra();
        int start = 0;
        int end = 4;
        String result = t.getShortestPath(start, end);
        System.out.println(result);
    }

    public String getShortestPath(int begin, int end) {
        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                                    {4, 0, 8, 0, 0, 0, 0, 11, 0},
                                    {0, 8, 0, 7, 0, 4, 0, 0, 2},
                                    {0, 0, 7, 0, 9, 14, 0, 0, 0},
                                    {0, 0, 0, 9, 0, 10, 0, 0, 0},
                                    {0, 0, 4, 14, 10, 0, 2, 0, 0},
                                    {0, 0, 0, 0, 0, 2, 0, 1, 6},
                                    {8, 11, 0, 0, 0, 0, 1, 0, 7},
                                    {0, 0, 2, 0, 0, 0, 6, 7, 0}};

        ArrayList<Object> list = getShortestPaths(graph, begin);
        int[] result = (int[]) list.get(1);
        int next = -1;
        String finalRoute = "" + begin;
        while (true) {
            if (next == -1) {
                next = begin;
            }

            next = result[next];
            if (next != 0) {
                finalRoute += "-->" + next;
                if (next == end) {
                    return finalRoute;
                }
            }
        }
    }
}


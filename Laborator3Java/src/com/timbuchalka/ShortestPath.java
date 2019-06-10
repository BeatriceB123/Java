package com.timbuchalka;

import java.util.ArrayList;

public class ShortestPath {
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private int graph[][];
    private int distances[][];
    private int nodeNumber;

    /**
     * The constructor of this class
     * @param travelMap The TravelMap object it uses
     */
    ShortestPath(TravelMap travelMap) {
        this.nodes = travelMap.getNodes();
        this.edges = travelMap.getEdges();
        this.nodeNumber = nodes.size();
        graph = new int[nodeNumber][nodeNumber];
        distances = new int[nodeNumber][nodeNumber];
        for (int i = 0; i < edges.size(); ++i) {
            Node u = edges.get(i).u;
            Node v = edges.get(i).v;
            System.out.println(u.getName() + " " + v.getName());

            int pozU = 0;
            int pozV = 0;
            if (nodes.contains(u))
                pozU = nodes.indexOf(u);
            if (nodes.contains(v))
                pozV = nodes.indexOf(v);
            if (edges.get(i).leftToRight)
                graph[pozU][pozV] = edges.get(i).cost;
            if (edges.get(i).rightToLeft)
                graph[pozV][pozU] = edges.get(i).cost;
        }
        initializeDistanceBetweenAny();
    }

    /**
     * This method prints the graph
     */
    public void printGraph() {
        System.out.println("Matricea: ");
        for (int i = 0; i < nodeNumber; i++) {
            for (int j = 0; j < nodeNumber; j++)
                System.out.print(graph[i][j] + " ");
            System.out.println();
        }
    }

    /**
     * This method prints the distance between any two nodes.
     * It will return MAX_INTEGER in case there is no path between them.
     */
    public void printDistancesBetweenAny() {
        System.out.println("All distances: ");
        for (int i = 0; i < nodeNumber; i++) {
            for (int j = 0; j < nodeNumber; j++)
                System.out.print(distances[i][j] + " ");
            System.out.println();
        }
    }

    /**
     * Initializes the distance for every node
     */
    public void initializeDistanceBetweenAny() {
        for (int i = 0; i < nodeNumber; i++)
            dijkstra(graph, i);
    }

    public int getDistanceBetween(int i, int j) {
        return distances[i][j];
    }

    /**
     * Gets the distance between two nodes
     * @param u the first node
     * @param v the second node
     * @return the distance between the two
     */
    public int getDistanceBetween(Node u, Node v) {
        int pozU = 0;
        int pozV = 0;
        if (nodes.contains(u))
            pozU = nodes.indexOf(u);
        if (nodes.contains(v))
            pozV = nodes.indexOf(v);

        return getDistanceBetween(pozU, pozV);
    }


    int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < nodeNumber; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }

    /**
     * This method implements Dijkstra's single source shortest path algorithm for a graph represented using adjacent matrix representation
     * @param graph the graph that contains the nodes and edges
     * @param src the node given from which the distances will be calculated
     */
    void dijkstra(int graph[][], int src) {
        int dist[] = new int[nodeNumber];
        // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will be true if the vertex i is included in shortest the
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[nodeNumber];

        // Initialize all the distances as MAX_VALUE of integer and stpSet[] as false
        for (int i = 0; i < nodeNumber; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance from source vertex to itself is always 0
        dist[src] = 0;

        // Finds the shortest path for all the vertices
        for (int count = 0; count < nodeNumber - 1; count++) {
            // Picks the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in the first iteration.
            int u = minDistance(dist, sptSet);

            // Marks the picked vertex as processed
            sptSet[u] = true;

            // Updates dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < nodeNumber; v++)

                // Updates dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // uses the constructed distance array
        for (int i = 0; i < nodeNumber; i++)
            distances[src][i] = dist[i];
    }
}

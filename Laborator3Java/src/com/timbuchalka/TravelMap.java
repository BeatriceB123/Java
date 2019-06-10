package com.timbuchalka;

import java.util.*;

public class TravelMap {
    private ArrayList <Node> nodes = new ArrayList <Node> ();
    private ArrayList <Edge> edges = new ArrayList <Edge> ();
    private ArrayList <String> nodesNames = new ArrayList <String> ();

    /**
     * This method adds the given node to the designated ArrayList if
     * it is not a duplicate and then sorts it
     * @param nodeU The node that will be added to the ArrayList
     */
    public void addNode ( Node nodeU ) {
        if ( !nodes.contains (nodeU) ) {
            nodes.add (nodeU);
            nodesNames.add (nodeU.getName ());
            nodesNames.sort (String::compareTo);
        }
    }

    public ArrayList <Edge> getEdges () {
        return edges;
    }

    public ArrayList <Node> getNodes () {
        return nodes;
    }

    public ArrayList <String> getNodesNames () {
        //all the locations sorted by their names
        //we can declare and sort the list here for every declaration, but it's not efficient
        return nodesNames;
    }


    /**
     * This method adds the given edge to the designated ArrayList if
     * it is not a duplicate
     * @param edge The edge that will be added to the ArrayList
     */
    public void addEdge ( Edge edge ) {
        if ( !edges.contains (edge) ) {
            edges.add (edge);
        }
    }

    /**
     * This method adds two nodes and the cost of the edge formed.
     * @param nodeU The first node
     * @param nodeV The second node
     * @param edgeCost The cost between the first and the second node
     * @param rightToLeft If the value is true then the nodes can be visited either way.
     *                    Otherwise, they can only be visited from left to right
     */
    public void addEdge ( Node nodeU, Node nodeV, int edgeCost, boolean rightToLeft ) {
        addNode (nodeU);
        addNode (nodeV);
        Edge edge = new Edge (edgeCost, nodeU, nodeV, true, rightToLeft);
        addEdge (edge);
    }

    /**
     * This method adds two nodes and the cost of the edge formed.
     * @param nodeU The first node
     * @param nodeV The second node
     * @param edgeCost The cost between the first and the second node
     */
    public void addEdge ( Node nodeU, Node nodeV, int edgeCost ) {
        addEdge (nodeU, nodeV, edgeCost, true);
    }

    @Override
    public String toString () {
        return "This map is formed by : \nedges :  " + edges + "\nnodes : " + nodes + '\n';
    }

}

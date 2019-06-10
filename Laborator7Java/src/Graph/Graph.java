package Graph;

import java.util.*;

public class Graph {
    private ArrayList<Edge> edgeList;

    public Graph() {
        edgeList = new ArrayList<>();
    }

    /**
     * This method checks if a player has a spanning tree graph
     *
     * @param board used for obtaining the number of nodes
     * @return true if the player has a spanning tree graph, false otherwise
     */
    public boolean isSpanningTree(Board board) {
        int numberOfNodes = board.getNodeNr();
        Set<Integer> nodes = new HashSet<>();
        for (int i = 0; i < edgeList.size(); i++) {
            nodes.add(edgeList.get(i).getN());
            nodes.add(edgeList.get(i).getM());
        }
        if (nodes.size() == numberOfNodes) return true;
        return false;
    }

    /**
     * This method vhecks if a player has a clique
     *
     * @return true if the player has a clique, false otherwise
     */
    public boolean isClique() {
        for (Edge edge : edgeList)
            if (edge.getM() <= 6 || edge.getN() <= 6) {
                for (int i = 1; i <= 19; i++)
                    if ((i != edge.getN() || i != edge.getM()) &&
                            ((edgeList.contains(new Edge(edge.getN(), i)) && edgeList.contains(new Edge(i, edge.getM()))) ||
                                    (edgeList.contains(new Edge(edge.getN(), i)) && edgeList.contains(new Edge(edge.getM(), i))) ||
                                    (edgeList.contains(new Edge(i, edge.getN())) && edgeList.contains(new Edge(i, edge.getM()))) ||
                                    (edgeList.contains(new Edge(i, edge.getN())) && edgeList.contains(new Edge(edge.getM(), i)))))
                        return true;
            }
        return false;
    }

    /**
     * This method calculates the size of the biggest partial tree from the player's graph
     *
     * @return a score according to the size of the tree
     */
    public int emptyBoardScoring() {
        Map<Integer, Integer> edges = new HashMap<Integer, Integer>();
        int size = edgeList.size();
        int aux;
        int[] treeSize = new int[size];
        edges.put(edgeList.get(0).getN(), edgeList.get(0).getM());
        for (int i = 1; i < size; i++)
            for (int j = 0; j < i; j++) {
                if (edgeList.get(i).getN() == edgeList.get(j).getN() ||
                        edgeList.get(i).getN() == edgeList.get(j).getM() ||
                        edgeList.get(i).getM() == edgeList.get(j).getN() ||
                        edgeList.get(i).getM() == edgeList.get(j).getM()) {
                    aux = treeSize[j];
                    if (treeSize[i] == 0) treeSize[j]++;
                    else treeSize[j] += treeSize[i];
                    if (treeSize[j] == 0) treeSize[i]++;
                    else treeSize[i] += aux;
                }
            }
        int maxSize = -1;
        for (int i = 0; i < size; i++)
            if (treeSize[i] > maxSize) maxSize = treeSize[i];
        return ((maxSize + 2) * 2500); //the score is 2500 for every vertex. The +2 is because of the initial edge.
    }

    public void add(Edge edge) {
        edgeList.add(edge);
    }

    /**
     * Takes the first edge from the graph and then removes it
     *
     * @return the edge
     */
    public Edge pullFirst() {
        Edge edgeToReturn = edgeList.get(0);
        edgeList.remove(0);
        return edgeToReturn;
    }

    /**
     * Takes given edge from the graph and then removes it
     *
     * @param edge edge to remove
     */
    public boolean pullEdge(Edge edge) {
        int index = edgeList.indexOf(edge);
        if (index != -1) {
            edgeList.remove(index);
            return true;
        } else {
            System.out.println("Invalid edge input");
            return false;
        }
    }

    /**
     * Takes given edge from the graph and then removes it. Doesn't print any error message.
     *
     * @param edge edge to remove
     */
    public boolean pullEdgeSilently(Edge edge) {
        int index = edgeList.indexOf(edge);
        if (index != -1) {
            edgeList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the edge has a connection to the graph. If it does, it adds the edge and the edges between to the graph.
     * @param edge the edge to be added
     * @param graph the graph to check
     * @return true if the operation was successful, false otherwise
     */
    public boolean pullEdgeClique(Edge edge, Graph graph, int turn) {
        int index = edgeList.indexOf(edge);
        boolean ok = false;
        if (index != -1) {
            for (Edge auxEdge : graph.edgeList) {
                if (auxEdge.getM() == edge.getM() ||
                        auxEdge.getM() == edge.getN() ||
                        auxEdge.getN() == edge.getM() ||
                        auxEdge.getN() == edge.getN()) ok = true;
            }
            if(turn == 1) ok = true;
            if (ok) {
                edgeList.remove(index);
                graph.add(edge);
                if(edge.getN()==4 && edge.getM()==6){
                    graph.add(new Edge(6,17));
                    graph.add(new Edge(16,17));
                    graph.add(new Edge(4,15));
                    graph.add(new Edge(15,16));
                }
                else if(edge.getN()==1 && edge.getM()==3){
                    graph.add(new Edge(1,9));
                    graph.add(new Edge(9,10));
                    graph.add(new Edge(10,11));
                    graph.add(new Edge(3,11));
                }
                else if(edge.getN()==1 && edge.getM()==5){
                    graph.add(new Edge(1,7));
                    graph.add(new Edge(7,18));
                    graph.add(new Edge(17,18));
                    graph.add(new Edge(5,17));
                }
                else if(edge.getN()==2 && edge.getM()==6){
                    graph.add(new Edge(2,9));
                    graph.add(new Edge(8,9));
                    graph.add(new Edge(7,8));
                    graph.add(new Edge(6,7));
                }
                else if(edge.getN()==2 && edge.getM()==5){
                    graph.add(new Edge(2,10));
                    graph.add(new Edge(10,19));
                    graph.add(new Edge(16,19));
                    graph.add(new Edge(5,16));
                }
                else if(edge.getN()==1 && edge.getM()==4){
                    graph.add(new Edge(1,8));
                    graph.add(new Edge(8,19));
                    graph.add(new Edge(14,19));
                    graph.add(new Edge(4,14));
                }
                else if(edge.getN()==3 && edge.getM()==6){
                    graph.add(new Edge(3,12));
                    graph.add(new Edge(12,19));
                    graph.add(new Edge(18,19));
                    graph.add(new Edge(6,18));
                }
                else if(edge.getN()==3 && edge.getM()==5){
                    graph.add(new Edge(3,13));
                    graph.add(new Edge(13,14));
                    graph.add(new Edge(14,16));
                    graph.add(new Edge(5,16));
                }
                else if(edge.getN()==2 && edge.getM()==4){
                    graph.add(new Edge(2,11));
                    graph.add(new Edge(11,12));
                    graph.add(new Edge(12,13));
                    graph.add(new Edge(4,13));
                }
                return true;
            }
        }
        return false;
    }

    public void shuffle() {
        Collections.shuffle(edgeList);
    }

    public Graph(ArrayList<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public void addEdge(Edge edge) {
        edgeList.add(edge);
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(ArrayList<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    /**
     * Clones given graph so we do not work with a reference
     *
     * @return the clone
     */
    public Graph clone() {
        Graph graph = new Graph();
        for (Edge edge : edgeList)
            graph.addEdge(edge);
        return graph;
    }

    @Override
    public String toString() {
        String toReturn = "Available edges are: ";
        for (Edge edge : edgeList)
            toReturn += edge + " ";
        return toReturn;
    }
}

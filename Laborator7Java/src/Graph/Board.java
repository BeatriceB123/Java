package Graph;

public class Board {

    private Graph complete = new Graph();
    private int nodeNr;

    /**
     * This method creates a complete graph then shuffles the edges.
     * @param nodeNr The number of nodes that the complete graph will have
     */
    public Board(int nodeNr) {
        for(int i=1; i<nodeNr; i++)
            for(int j=i+1; j<=nodeNr; j++)
            {
                Edge auxEdge = new Edge(i,j);
                complete.addEdge(auxEdge);
            }
        complete.shuffle();
        this.nodeNr = nodeNr;
    }

    public int getNodeNr(){
        return nodeNr;
    }

    /**
     * Synchronized method that ensures that each thread works correctly (by not picking the same edge as another thread)
     * @return an edge extracted from the graph
     */
    public synchronized Edge extract() {
        Edge edge = complete.pullFirst();
        return edge;
    }

    /**
     * Extracts a given edge from the graph
     * @param edge edge to extract
     * @return same edge
     */
    public synchronized boolean extract(Edge edge) {
        boolean toReturn = complete.pullEdge(edge);
        return toReturn;
    }

    /**
     * Extracts a given edge from the graph without printing any message.
     * @param edge edge to extract
     * @return same edge
     */
    public synchronized boolean extractSilently(Edge edge) {
        boolean toReturn = complete.pullEdgeSilently(edge);
        return toReturn;
    }

    public synchronized boolean extractClique(Edge edge, Graph graph, int turn){
        return complete.pullEdgeClique(edge,graph,turn);
    }

    /**
     * Checks if the graph is empty
     * @return true if it is, false if it is not
     */
    public boolean isEmpty() {
        if(complete.getEdgeList().size()==0) return true;
        return false;
    }

    public Graph getGraph() {
        return complete;
    }

    public void setGraph(Graph graph) {
        this.complete = graph;
    }

    /**
     * Clones given board so we do not work with a reference
     * @return the clone
     */
    public Board clone() {
        Board board = new Board(nodeNr);
        board.setGraph(complete);
        return board;
    }
}

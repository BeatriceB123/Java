import java.lang.reflect.Array;
import java.util.*;

public class TravelMap {
    ArrayList <Node> nodes = new ArrayList <> ();
    ArrayList <Edge> edges = new ArrayList <> ();
    ArrayList <String> nodesNames = new ArrayList <> ();



    public void addNode ( Node nodeU ) {
        if ( !nodes.contains (nodeU) ) {
            nodes.add (nodeU);
            nodesNames.add (nodeU.getName ());
            nodesNames.sort (String::compareTo);
        }
    }

    public void addEdge ( Edge edge ) {
        if ( !edges.contains (edge) ) {
            edges.add (edge);
        }
    }

    public void addEdge ( Node nodeU, Node nodeV, int edgeCost, boolean rightToLeft ) {
        addNode (nodeU);
        addNode (nodeV);
        Edge edge = new Edge (edgeCost, nodeU, nodeU, true, rightToLeft);
        addEdge (edge);
    }

    public void addEdge ( Node nodeU, Node nodeV, int edgeCost ) {
        addEdge (nodeU, nodeV, edgeCost, true);
    }


    public ArrayList <String> getNodesNames () {
        //all the locations sorted by their names
        //we can declare and sort the list here for every declaration, but it's not efficient

        return nodesNames;
    }

    @Override
    public String toString () {
        return "This map is formed by : \nedges :  " + edges + "\nnodes : " + nodes + '\n';
    }
}

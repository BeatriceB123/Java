package GraphPackage;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List <Node> listOfNodes;
    private List <Edge> listOfEdges;

    public Graph () {
        initializeGraphToNothing ();
    }

    /**
     * used for initialize or clear
     */
    public void initializeGraphToNothing () {
        setListOfEdges (new ArrayList <> ());
        setListOfNodes (new ArrayList <> ());
    }

    public List <Node> getListOfNodes () {
        return listOfNodes;
    }

    public List <Edge> getListOfEdges () {
        return listOfEdges;
    }

    public void setListOfNodes ( List <Node> list ) {
        this.listOfNodes = list;
    }

    public void setListOfEdges ( List <Edge> list ) {
        this.listOfEdges = list;
    }

    public void addNode ( Node coordinate ) {
        this.listOfNodes.add (coordinate);
    }

    public void revomeNode ( int orderInList ) {
        Node removedNode = this.listOfNodes.get (orderInList);
        //remove node
        this.listOfNodes.remove (orderInList);

        //remove adjacent edges
        boolean nodeFound = true;
        while (nodeFound) {
            nodeFound = false;
            for (int orderOfEdge = 0; orderOfEdge < listOfEdges.size (); orderOfEdge++) {
                if ( theSameNode (removedNode, listOfEdges.get (orderOfEdge).getU ()) || theSameNode (removedNode, listOfEdges.get (orderOfEdge).getV ()) ) {
                    this.removeEdge (orderOfEdge);
                    nodeFound = true;
                }
            }
        }
    }

    public void removeEdge(int orderInList)
    {
        this.listOfEdges.remove (orderInList);
    }

    public void addEdge ( Node coord1, Node coord2 ) {
        if ( listOfNodesContains (coord1.getX (), coord1.getY ()) &&
             listOfNodesContains (coord2.getX (), coord2.getY ()) )
            this.listOfEdges.add (new Edge (coord1, coord2));
        else
            System.out.println ("error");
    }

    private boolean listOfNodesContains ( int coordx, int coordy ) {
        for (int i = 0; i < getListOfNodes ().size (); i++)
            if ( getListOfNodes ().get (i).getX () == coordx &&
                 getListOfNodes ().get (i).getY () == coordy )
                return true;
        return false;
    }

    public boolean listOfEdgesContains ( Node u, Node v ) {
        for (int i = 0; i < getListOfEdges ().size (); ++i)
            if ( theSameNode (getListOfEdges ().get (i).getU (), u) &&
                 theSameNode (getListOfEdges ().get (i).getV (), v) )
                return true;
        return false;
    }

    public static boolean theSameNode ( Node u, Node v ) {
        return (u.getX () == v.getX () && u.getY () == v.getY ());
    }
}

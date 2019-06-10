import GraphPackage.Edge;
import GraphPackage.Graph;
import GraphPackage.Node;

import java.util.ArrayList;
import java.util.List;

public class PrettyGraph {
    private int numberOfNodes;
    private int[][] adjacencyMatrix;
    private List <Node> listOfNodes = new ArrayList <> ();
    private List <Edge> listOfEdges = new ArrayList<> ();

    public PrettyGraph ( Graph graph ) {
        setNumberOfNodes (graph.getListOfNodes ().size ());
        setListOfNodes (graph.getListOfNodes ());
        adjacencyMatrix = new int[numberOfNodes][numberOfNodes];

        for (int i = 0; i < numberOfNodes; ++i) {
            for (int j = 0; j < numberOfNodes; ++j) {
                //edge i-j ->
                if ( existEdgeinListOfEdges (graph.getListOfEdges (), new Edge (graph.getListOfNodes ().get (i), graph.getListOfNodes ().get (j))) ) {
                    adjacencyMatrix[i][j] = 1;
                    adjacencyMatrix[j][i] = 1;
                }
            }
        }
        printAdjacencyMatrix ();
        System.out.println ("Este arbore? " + isTree (adjacencyMatrix, numberOfNodes));
        System.out.println ("Este complet? " + isCompleteGraph (adjacencyMatrix, numberOfNodes));
    }

    public void drawPrettyGraph( PrettyGraph prettyGraph){
        //clear
        if(isTree (prettyGraph.adjacencyMatrix, prettyGraph.numberOfNodes))
            PrettyDraw.drawTree (prettyGraph);
        else if(isCompleteGraph (prettyGraph.adjacencyMatrix, prettyGraph.numberOfNodes))
            PrettyDraw.drawCompleteGraph (prettyGraph);
        else
            PrettyDraw.drawOrdinaryGraph (prettyGraph);
    }


    private void printAdjacencyMatrix () {
        //doua reprezentari pentru 0 si 1 in matricea de adiacenta
        char noduletChar = '\u2665'; //negru
        char liberChar = '\u2661';   //alb
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++)
                if ( adjacencyMatrix[i][j] == 1 )
                    System.out.print (noduletChar + " ");
                else
                    System.out.print (liberChar + " ");
            System.out.print ("\n");
        }
    }

    private boolean isCompleteGraph ( int[][] adjacencyMatrix, int numberOfNodes ) {
        for (int i = 0, sum; i < numberOfNodes; i++) {
            sum = 0;
            for (int j = 0; j < numberOfNodes; j++) {
                sum += adjacencyMatrix[i][j];
            }
            if ( sum + 1 != numberOfNodes )
                return false;
        }
        return true;
    }

    private boolean isTree ( int[][] adjacencyMatrix, int numberOfNodes ) {
        int sum = 0;
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                sum += adjacencyMatrix[i][j];
            }
        }
        //ca sa fie arbore trebuie sa aiba (n-1) muchii
        if ( sum/2 != numberOfNodes - 1 )
            return false;
        //si doar daca e conex este arbore.
        return (conex (numberOfNodes) == 1);
    }

    private int conex ( int numberOfNodes ) {
        boolean[] marcat = new boolean[numberOfNodes];
        int nrComponenteConexe = 0;
        for (int i = 0; i < numberOfNodes; i++)
            marcat[i] = false;

        int nod = alegeNodStart (marcat);
        while (nod != -1) {
            parcurgere (marcat, nod, numberOfNodes);
            nod = alegeNodStart (marcat);
            nrComponenteConexe++;
        }

        return nrComponenteConexe;
    }


    private void parcurgere ( boolean[] marcat, int nod_u, int numberOfNodes ) {
        marcat[nod_u] = true;

        //stabilim care sunt vecinii nodului u nevizitati
        int[] vecini = new int[numberOfNodes];
        int nrVecini = 0;
        for (int i = 0; i < numberOfNodes; i++)
            if ( !marcat[i] && adjacencyMatrix[nod_u][i] == 1 ) {
                vecini[nrVecini] = i;
                nrVecini++;
            }

        //si facem parcurgere a vecinilor nevizitati
        for (int i = 0; i < nrVecini; i++) {
            parcurgere (marcat, vecini[i], numberOfNodes);
        }
    }

    //o functie ce cauta un nod nevizitat in graf
    //daca returneaza -1 inseamna ca am parcurs toate componentele din graf
    public int alegeNodStart ( boolean marcat[] ) {
        for (int i = 0; i < numberOfNodes; i++)
            if ( !marcat[i] )
                return i;
        return -1;
    }

    public void setNumberOfNodes ( int numberOfNodes ) {
        this.numberOfNodes = numberOfNodes;
    }

    public void setListOfNodes ( List <Node> listOfNodes ) {
        this.listOfNodes = listOfNodes;
    }

    public boolean existEdgeinListOfEdges ( List <Edge> listOfEdges, Edge edge ) {
        for (int i = 0; i < listOfEdges.size (); ++i)
            if ( Graph.theSameNode (listOfEdges.get (i).getU (), edge.getU ()) && Graph.theSameNode (listOfEdges.get (i).getV (), edge.getV ()) )
                return true;
        return false;
    }

    public List <Node> getListOfNodes () {
        return listOfNodes;
    }

    public int getNumberOfNodes () {
        return numberOfNodes;
    }

    public int[][] getAdjacencyMatrix () {
        return adjacencyMatrix;
    }

    public void setListOfEdges ( List <Edge> listOfEdges ) {
        this.listOfEdges = listOfEdges;
    }

    public List <Edge> getListOfEdges () {
        return listOfEdges;
    }
}

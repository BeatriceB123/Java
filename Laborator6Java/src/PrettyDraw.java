import GraphPackage.Edge;
import GraphPackage.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PrettyDraw {

    public static void drawTree ( PrettyGraph prettyGraph ) {
        DrawingArea.g2.drawString ("This is a tree", 30, 600);
        putInTree(prettyGraph);
        draw(prettyGraph);
        System.out.println ("tree");
    }

    public static void drawCompleteGraph ( PrettyGraph prettyGraph ) {
        putInCircle (prettyGraph);
        DrawingArea.g2.drawString ("This is a complete graph", 30, 600);
        draw(prettyGraph);
        System.out.println ("complete");
    }

    public static void drawOrdinaryGraph ( PrettyGraph prettyGraph ) {
        putInCircle (prettyGraph);
        DrawingArea.g2.drawString ("This is an ordinary graph", 30, 600);
        draw(prettyGraph);
        System.out.println ("ordinary");
    }

    public static void putInTree(PrettyGraph prettyGraph){
        List <Node> listOfNodes = new ArrayList <> ();
        List <Node> listOfEdges = new ArrayList <> ();

        Node initial = new Node(10, 10 , Color.PINK);
        listOfNodes.add (initial);

        putInCircle (prettyGraph);
    }


    public static void draw(PrettyGraph prettyGraph){
        for(int i=0; i<prettyGraph.getListOfNodes ().size (); i++)
        {
            DrawComponentNode.drawNodeFront (prettyGraph.getListOfNodes ().get (i));
        }
        for(int i=0; i<prettyGraph.getListOfEdges ().size (); ++i)
        {
            DrawComponentEdge.drawEdgeFront (prettyGraph.getListOfEdges ().get (i).getU ().getX (),
                    prettyGraph.getListOfEdges ().get (i).getU ().getY (),
                    prettyGraph.getListOfEdges ().get (i).getV ().getX (),
                    prettyGraph.getListOfEdges ().get (i).getV ().getY ());
        }
    }

    private static void putInCircle ( PrettyGraph graph ) {
        //we have the page clear
        double initialX = 400;
        double initialY = 40;
        List <Node> listOfNodes = new ArrayList <> ();
        //drawCenteredCircle (DrawingArea.g2, (int) initialX, (int) initialY, 2);

        for (int contor = 0; contor < graph.getNumberOfNodes (); contor++) {
            double xcerc = initialX - 200 * Math.sin (contor * 360 / (double) (graph.getNumberOfNodes ()));
            double ycerc = initialY + 200 * (1 - Math.cos (contor * 360 / (double) (graph.getNumberOfNodes ())));
            listOfNodes.add (new Node ((int) xcerc, (int) ycerc, Color.PINK));
            //drawCenteredCircle (DrawingArea.g2, (int)xcerc, (int)ycerc, 5);
        }
        graph.setListOfNodes (listOfNodes);

        List <Edge> listOfEdges = new ArrayList <> ();
        for (int i = 0; i < graph.getNumberOfNodes (); ++i) {
            for (int j = i+1; j < graph.getNumberOfNodes (); j++) {
                if(graph.getAdjacencyMatrix ()[i][j] == 1){
                    listOfEdges.add (new Edge(graph.getListOfNodes ().get (i), graph.getListOfNodes ().get (j)));
                }
            }
        }
        graph.setListOfEdges (listOfEdges);
    }


    static private void drawCenteredCircle ( Graphics2D g, int x, int y, int r ) {
        x = x - (r / 2);
        y = y - (r / 2);
        g.fillOval (x, y, r, r);
        g.setColor (Color.black);
        g.drawString (". " + ToolsForDrawing.nameOfNode (x, y), x + r / 2, y + r / 2);
    }
}

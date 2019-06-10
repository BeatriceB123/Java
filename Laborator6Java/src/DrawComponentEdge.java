import GraphPackage.Node;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawComponentEdge {

    static void drawEdges ( MouseEvent e ) {
        if ( DrawingArea.existsFirstNodeInEdge ) {
            int nodeOrderInGraph = ToolsForDrawing.validationEndEdge (e.getX (), e.getY ());
            if ( nodeOrderInGraph == -1 )
                System.out.println ("Incorect point for node");
            else {
                DrawingArea.currentX = DrawingFrame.graph.getListOfNodes ().get (nodeOrderInGraph).getX ();
                DrawingArea.currentY = DrawingFrame.graph.getListOfNodes ().get (nodeOrderInGraph).getY ();
                drawEdgeFromTo (DrawingArea.oldX, DrawingArea.oldY, DrawingArea.currentX, DrawingArea.currentY);
                DrawingArea.existsFirstNodeInEdge = false;
            }
        } else {
            int nodeOrderInGraph = ToolsForDrawing.validationEndEdge (e.getX (), e.getY ());
            if ( nodeOrderInGraph == -1 )
                System.out.println ("Incorect point for node");
            else {
                //move the coordinates exactly in center of our node
                DrawingArea.existsFirstNodeInEdge = true;
                DrawingArea.oldX = DrawingFrame.graph.getListOfNodes ().get (nodeOrderInGraph).getX ();
                DrawingArea.oldY = DrawingFrame.graph.getListOfNodes ().get (nodeOrderInGraph).getY ();
            }
        }
    }

    static void drawEdgeFromTo( int x1, int y1, int x2, int y2 ) {
        //validare mai intai
        Node u = new Node (x1, y1, Color.black);
        Node v = new Node (x2, y2, Color.black);

        if ( DrawingFrame.graph.theSameNode (u, v) )
            System.out.println ("Cannot draw this edge because it's in the same node");
        else if ( DrawingFrame.graph.listOfEdgesContains (u, v) || DrawingFrame.graph.listOfEdgesContains (v, u) )
            System.out.println ("Cannot draw this edge beacause it was drawn");
        else {
            drawEdgeBack ( x1, y1, x2, y2 );
            drawEdgeFront ( x1, y1, x2, y2 );
        }
    }

    static private void drawEdgeBack ( int x1, int y1, int x2, int y2 ) {
        DrawingFrame.graph.addEdge (new Node (x1, y1, Color.black), new Node (x2, y2, Color.black));
    }

    static void drawEdgeFront( int x1, int y1, int x2, int y2 ) {
        DrawingArea.g2.setPaint (Color.black);
        DrawingArea.g2.drawLine (x1, y1, x2, y2);
        System.out.println ("drawing node from" + x1 + " " + y1 + "  " + x2 + " " + y2);
    }
}

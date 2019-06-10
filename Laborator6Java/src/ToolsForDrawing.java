import GraphPackage.Node;

import java.awt.*;

public class ToolsForDrawing {

    //-1 if there is no node; 0..size else
    static int orderInListOfNodeInProximity ( int x, int y) {
        for (int i = 0; i < DrawingFrame.graph.getListOfNodes ().size (); ++i) {
            if ( isInProximity (x, y, DrawingFrame.graph.getListOfNodes ().get (i).getX (), DrawingFrame.graph.getListOfNodes ().get (i).getY ()) )
                return i;
        }
        return -1;
    }


    static int orderInListOfEdge(int x1, int y1, int x2, int y2){
        for(int i=0; i<DrawingFrame.graph.getListOfEdges ().size (); ++i)
        {
            if( DrawingFrame.graph.theSameNode (new Node (x1, y1, Color.black), DrawingFrame.graph.getListOfEdges ().get (i).getU ()) && DrawingFrame.graph.theSameNode (new Node (x2, y2, Color.black), DrawingFrame.graph.getListOfEdges ().get (i).getV ()))
                return i;
            if(DrawingFrame.graph.theSameNode (new Node (x1, y1, Color.black), DrawingFrame.graph.getListOfEdges ().get (i).getV ()) && DrawingFrame.graph.theSameNode (new Node (x2, y2, Color.black), DrawingFrame.graph.getListOfEdges ().get (i).getU ()))
                return i;
        }
        return -1;
    }

    static boolean isInProximity ( int x1, int y1, int x2, int y2) {
        //se reduce la a verifica distanta dintre centrle celor doua cercuri
        double distance = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
        distance = (double) Math.sqrt (distance);
        return (distance < DrawingArea.circleRadius);
    }


    static String nameOfNode ( int x, int y ) {
        StringBuilder result = new StringBuilder ();
        result.append ("(")
                .append (x)
                .append (",")
                .append (y)
                .append (")");
        return result.toString ();
    }

    //vezi daca poate exista o muchie cu acest capat
    static int validationEndEdge ( int coordx, int coordy ) {
        for (int i = 0; i < DrawingFrame.graph.getListOfNodes ().size (); i++) {
            //if(DrawingFrame.graph.getListOfNodes ().get(i))
            boolean ok = coordInCircleWithCenter (coordx, coordy,
                    DrawingFrame.graph.getListOfNodes ().get (i).getX (),
                    DrawingFrame.graph.getListOfNodes ().get (i).getY (),
                    DrawingArea.circleRadius);
            if ( ok )
                return i; //the order of our node
        }
        return -1; //we don't found any node
    }

    static boolean coordInCircleWithCenter ( int coordx, int coordy, int circlex, int circley, int radius ) {
        //daca e in dreapta centrului cercului
        if ( circlex <= coordx && coordx <= circlex + radius ) {
            if ( circley <= coordy && coordy <= circley + radius ) {
                System.out.println ("dreapta jos");
                return true;
            }
            if ( circley >= coordy && coordy >= circley - radius ) {
                System.out.println ("dreapta sus");
                return true;
            }
        }

        if ( circlex - radius <= coordx && coordx <= circlex ) {
            if ( circley <= coordy && coordy <= circley + radius ) {
                System.out.println ("stanga jos");
                return true;
            }
            if ( circley >= coordy && coordy >= circley - radius ) {
                System.out.println ("stanga sus");
                return true;
            }
        }
        return false;
    }
}

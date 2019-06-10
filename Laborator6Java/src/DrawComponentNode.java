import GraphPackage.Node;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class DrawComponentNode {

    static void drawNodes ( MouseEvent e ) {
        int x = e.getX ();
        int y = e.getY ();
        if ( ToolsForDrawing.orderInListOfNodeInProximity (x, y) != -1 ) {
            System.out.println ("You have another node in that position. Select another position.");
        } else {
            Random rand = new Random ();
            Color color = new  Color (rand.nextInt (0xFFFFFF)); //you may use a random color or the one specified in the toolbar
            Node node = new Node (x, y, color);
            drawNodeFront (node);
            drawNodeBack (node);
        }
    }

    static private void drawNodeBack ( Node u) {
        DrawingFrame.graph.addNode (u);
    }

    static void drawNodeFront ( Node u ) {
        DrawingArea.g2.setColor (u.getColor());
        drawCenteredCircle (DrawingArea.g2, u.getX (), u.getY (), DrawingArea.circleRadius);
    }

    static private void drawCenteredCircle ( Graphics2D g, int x, int y, int r ) {
        x = x - (r / 2);
        y = y - (r / 2);
        g.fillOval (x, y, r, r);
        g.setColor (Color.black);
        g.drawString (". " + ToolsForDrawing.nameOfNode (x, y), x + r / 2, y + r / 2);
    }
}

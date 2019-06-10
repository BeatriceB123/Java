import java.awt.event.MouseEvent;

public class RemoveNode {
    static void removeNode ( MouseEvent e ) {
        int x = e.getX ();
        int y = e.getY ();
        int orderOfNode = ToolsForDrawing.orderInListOfNodeInProximity (x, y);
        System.out.println (orderOfNode + " este numarul lui ");
        if ( orderOfNode == -1 ) {
            System.out.println ("There is no node");
        } else {
            //back
            DrawingFrame.graph.revomeNode (orderOfNode);
        }
    }
}

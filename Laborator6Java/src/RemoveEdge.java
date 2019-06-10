import java.awt.event.MouseEvent;

public class RemoveEdge {

    static void removeEdges ( MouseEvent e ) {
        int x = e.getX ();
        int y = e.getY ();
        if ( DrawingArea.isExistsFirstNodeInEdgeToRemove ) {
            int nodeOrderInGraph = ToolsForDrawing.validationEndEdge (x, y);
            if ( nodeOrderInGraph == -1 )
                System.out.println ("Incorect point for node");
            else {
                DrawingArea.currentX = DrawingFrame.graph.getListOfNodes ().get (nodeOrderInGraph).getX ();
                DrawingArea.currentY = DrawingFrame.graph.getListOfNodes ().get (nodeOrderInGraph).getY ();
                RemoveEdge.removeEdge (DrawingArea.oldX, DrawingArea.oldY, DrawingArea.currentX, DrawingArea.currentY);
                DrawingArea.isExistsFirstNodeInEdgeToRemove = false;
            }
        } else {
            int nodeOrderInGraph = ToolsForDrawing.validationEndEdge (x, y);
            if ( nodeOrderInGraph == -1 )
                System.out.println ("Incorect point for node");
            else {
                //move the coordinates exactly in center of our node
                DrawingArea.isExistsFirstNodeInEdgeToRemove = true;
                DrawingArea.oldX = DrawingFrame.graph.getListOfNodes ().get (nodeOrderInGraph).getX ();
                DrawingArea.oldY = DrawingFrame.graph.getListOfNodes ().get (nodeOrderInGraph).getY ();
            }
        }
    }

    static void removeEdge ( int x1, int y1, int x2, int y2 ) {
        int orderOfEdge = ToolsForDrawing.orderInListOfEdge (x1, y1, x2, y2);
        if ( orderOfEdge == -1 ) {
            System.out.println ("There is no node");
        } else {
            //back
            DrawingFrame.graph.removeEdge (orderOfEdge);
            //front sync with back
        }
    }
}

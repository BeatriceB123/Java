import GraphPackage.Graph;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

/**
 * This is DrawingFrame: the main frame of the application
 */
public class DrawingFrame {

    static DrawingArea drawArea; //the canvas
    static Graph graph = new Graph ();   //a history of changes in drawArea

   // public enum actionType { drawNodes, drawEdges, removeNodes, removeEdges };
   // static Map <actionType, Boolean> actionMap; //if command will be executed now or not

    static boolean drawNodes = false;
    static boolean drawEdges = false;
    static boolean removeNodes = false;
    static boolean removeEdges = false;

    private ControlPanel controlPanel = new ControlPanel (); // the load, save and reset buttons
    private DrawingPanel drawingPanel = new DrawingPanel (); // the panel for specifying parameters

    public static void main ( String[] args ) {
        new DrawingFrame ().show ();
    }

    private void show () {

        // create main frame
        JFrame frame = new JFrame ("Visual representations of graphs");
        Container content = frame.getContentPane ();
        content.setLayout (new BorderLayout ());     // set layout on content pane
        drawArea = new DrawingArea ();               // create draw area
        content.add (drawArea, BorderLayout.CENTER); // add to content pane

        // add to content pane
        content.add (drawingPanel, BorderLayout.NORTH);
        content.add (controlPanel, BorderLayout.SOUTH);

        frame.setSize (800, 800);

        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);// can close frame

        frame.setVisible (true);// show the swing paint result
    }

}
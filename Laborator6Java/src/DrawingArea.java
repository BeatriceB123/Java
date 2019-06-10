import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Component for drawing!
 * This is our canvas (used to make items such as sails and tents and as a surface for oil painting.)
 */
public class DrawingArea extends JComponent {

    static private Image image; // Image in which we're going to draw
    static public Graphics2D g2; // Graphics2D object ==> used to draw on
    static int currentX, currentY, oldX, oldY;// Mouse coordinates
    static boolean existsFirstNodeInEdge = false;
    static boolean isExistsFirstNodeInEdgeToRemove = false;
    static int circleRadius = 30;

    DrawingArea () {
        setDoubleBuffered (true);
        addMouseListener (new MouseAdapter () {
            public void mousePressed ( MouseEvent e ) {
                if ( DrawingFrame.drawNodes ) {
                    drawNodes (e);
                } else if ( DrawingFrame.drawEdges ) {
                    drawEdges (e);
                } else if ( DrawingFrame.removeNodes ) {
                    removeNodes (e);
                } else if ( DrawingFrame.removeEdges ) {
                    removeEdges (e);
                }
            }
        });
    }

    private void drawNodes ( MouseEvent e ) {
        DrawComponentNode.drawNodes (e);
        repaint();
    }

    private void drawEdges ( MouseEvent e ) {
        DrawComponentEdge.drawEdges (e);
        repaint();
    }

    private void removeNodes ( MouseEvent e ) {
        RemoveNode.removeNode (e);
        redrawGraph ();
        repaint ();// refresh draw area to repaint
    }

    private void removeEdges ( MouseEvent e ) {
        RemoveEdge.removeEdges (e);
        redrawGraph ();
        repaint();
    }

    void prettyGraph(){
        clearFront ();
        repaint();

        PrettyGraph prettyGraph = new PrettyGraph (DrawingFrame.graph);
        prettyGraph.drawPrettyGraph (prettyGraph);

        repaint ();
        System.out.println ("Pretty");
    }

    void save () {
        try {
            BufferedImage bi = getMyImage ();
            File outputfile = new File ("E:\\JAVA - Programare avansata\\Laborator6Java\\saved.png");
            ImageIO.write (bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println ("Error. Image Not Saved. ");
        }
        repaint ();
    }

    void load () {
        try {
            clear();
            image = ImageIO.read (new File ("E:\\JAVA - Programare avansata\\Laborator6Java\\saved.png"));
            g2 = (Graphics2D) image.getGraphics ();                    // enable antialiasing
            g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage (image, 0, 0, null);
        } catch (IOException e) {
            System.out.println ("Error. Image Not Loaded. ");
        }
        repaint ();
    }

    void printGraph () {
        System.out.println ("List of nodes: ");
        for (int i = 0; i < DrawingFrame.graph.getListOfNodes ().size (); i++) {
            System.out.println (DrawingFrame.graph.getListOfNodes ().get (i));
        }
        System.out.println ("List of edges: ");
        for (int i = 0; i < DrawingFrame.graph.getListOfEdges ().size (); i++) {
            System.out.print (DrawingFrame.graph.getListOfEdges ().get (i));
        }
        System.out.println ();
    }

    void setStroke () {
        System.out.println ("Stroke? setted");
    }

    void setSizeOfEdge ( int size ) {
        int lineSize = size;
    }

    void setSizeOfNode ( int radius ) {
        circleRadius = radius;
    }

    private BufferedImage getMyImage () {
        return (BufferedImage) image;
    }

    private void redrawGraph () {
        clearFront();
        for(int i=0; i<DrawingFrame.graph.getListOfNodes ().size (); i++)
        {
            DrawComponentNode.drawNodeFront (DrawingFrame.graph.getListOfNodes ().get (i));
        }
        for(int i=0; i<DrawingFrame.graph.getListOfEdges ().size (); ++i)
        {
            DrawComponentEdge.drawEdgeFront (DrawingFrame.graph.getListOfEdges ().get (i).getU ().getX (),
                    DrawingFrame.graph.getListOfEdges ().get (i).getU ().getY (),
                    DrawingFrame.graph.getListOfEdges ().get (i).getV ().getX (),
                    DrawingFrame.graph.getListOfEdges ().get (i).getV ().getY ());
        }
    }

    protected void paintComponent ( Graphics g ) {
        if ( image == null ) {
            image = createImage (getSize ().width, getSize ().height); // image to draw null ==> we create
            g2 = (Graphics2D) image.getGraphics ();                    // enable antialiasing
            g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear ();// clear draw area
        }
        g.drawImage (image, 0, 0, null);
    }

    void clear()
    {
        clearBack ();
        clearFront ();
    }

    void clearFront () {
        g2.setPaint (Color.white);// draw white on entire draw area to clear
        g2.fillRect (0, 0, getSize ().width, getSize ().height);
        g2.setPaint (Color.black);
        repaint ();
    }

    private void clearBack() {
        DrawingFrame.graph.initializeGraphToNothing (); //and we will reset the graph
    }
}
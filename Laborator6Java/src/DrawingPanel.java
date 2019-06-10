import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Toolbar:
 * the panel for specifying parameters regarding the shapes that will be drawn
 */
public class DrawingPanel extends JPanel implements ChangeListener {

    private JSpinner sizeOfNode;
    private JSpinner sizeOfEdge;

    private JButton stroke;

    private JButton addEdge;
    private JButton addNode;

    private JButton removeEdge;
    private JButton removeNode;


    DrawingPanel () {
        sizeOfNode = new JSpinner (new SpinnerNumberModel (30, 20, 50, 5));
        sizeOfEdge = new JSpinner (new SpinnerNumberModel (4, 1, 10, 1));
        sizeOfNode.addChangeListener (this);
        sizeOfEdge.addChangeListener (this);

        stroke = new JButton ("Stroke");
        stroke.addActionListener (actionListener);

        addEdge=new JButton ("Add Edge");
        addEdge.addActionListener (actionListener);
        addNode=new JButton ("Add Node");
        addNode.addActionListener (actionListener);

        removeEdge=new JButton ("Remove Edge");
        removeEdge.addActionListener (actionListener);
        removeNode=new JButton ("Remove Node");
        removeNode.addActionListener (actionListener);

        this.add (new JLabel ("Node size:"));
        this.add (sizeOfNode);
        this.add (stroke);
        this.add(new JLabel ("Edge size:"));
        this.add (sizeOfEdge);
        this.add(addEdge);
        this.add(addNode);
        this.add(removeNode);
        this.add(removeEdge);
    }

    ActionListener actionListener = new ActionListener () {
        public void actionPerformed ( ActionEvent e ) {
            if ( e.getSource () == stroke ) {
                DrawingFrame.drawArea.setStroke ();
            }
            else if ( e.getSource () == addEdge ) {
                {
                    System.out.println ("Drawing edges.. ");
                    DrawingFrame.drawEdges = true;
                    DrawingFrame.drawNodes = false;
                    DrawingFrame.removeEdges = false;
                    DrawingFrame.removeNodes = false;
                }
            }
            else if ( e.getSource () == addNode ) {
                {
                    System.out.println ("Drawing nodes.. ");
                    DrawingFrame.drawNodes = true;
                    DrawingFrame.drawEdges = false;
                    DrawingFrame.removeNodes = false;
                    DrawingFrame.removeEdges = false;
                }
            }
            else if(e.getSource () == removeNode)
            {
                System.out.println ("Removing nodes.. ");
                DrawingFrame.removeNodes = true;
                DrawingFrame.removeEdges = false;
                DrawingFrame.drawEdges = false;
                DrawingFrame.drawNodes = false;
            }
            else if(e.getSource () == removeEdge)
            {
                System.out.println ("Removing edges.. ");
                DrawingFrame.removeEdges = true;
                DrawingFrame.removeNodes = false;
                DrawingFrame.drawNodes = false;
                DrawingFrame.drawEdges = false;
            }
        }
    };

    @Override
    public void stateChanged ( ChangeEvent e ) {
        DrawingFrame.drawArea.setSizeOfNode ((Integer) sizeOfNode.getValue ());
        DrawingFrame.drawArea.setSizeOfEdge ((Integer) sizeOfEdge.getValue ());
    }
}

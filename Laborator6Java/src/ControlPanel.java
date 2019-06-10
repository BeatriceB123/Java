import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ControlPanel extends JPanel {
    private JButton clearBtn;
    private JButton saveBtn;
    private JButton loadBtn;
    private JButton seeGraph;
    private JButton prettyGraph;

    ControlPanel () {
        clearBtn = new JButton ("Reset");
        saveBtn = new JButton ("Save");
        loadBtn = new JButton ("Load");
        seeGraph = new JButton ("See");
        prettyGraph = new JButton ("Pretty");
        clearBtn.addActionListener (actionListener);
        saveBtn.addActionListener (actionListener);
        loadBtn.addActionListener (actionListener);
        seeGraph.addActionListener (actionListener);
        prettyGraph.addActionListener (actionListener);
        this.add (clearBtn);
        this.add (saveBtn);
        this.add (loadBtn);
        this.add (seeGraph);
        this.add(prettyGraph);
    }

    ActionListener actionListener = new ActionListener () {
        public void actionPerformed ( ActionEvent e ) {
            if ( e.getSource () == clearBtn ) {
                DrawingFrame.drawArea.clear ();
            } else if ( e.getSource () == saveBtn ) {
                DrawingFrame.drawArea.save ();
            } else if ( e.getSource () == loadBtn ) {
                DrawingFrame.drawArea.load ();
            } else if ( e.getSource () == seeGraph ) {
                DrawingFrame.drawArea.printGraph ();
            }else if(e.getSource () == prettyGraph){
                DrawingFrame.drawArea.prettyGraph();
            }
        }
    };
}

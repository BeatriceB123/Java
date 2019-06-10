import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    ControlPanel controlPanel;
    DesignPanel designPanel;

    private MainFrame() {
        super("Swing Designer");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create and add to the frame, the controlPanel and designPanel objects
        controlPanel = new ControlPanel(this) ;
        designPanel = new DesignPanel(this);
        this.add (controlPanel, BorderLayout.NORTH);
        this.add (designPanel, BorderLayout.CENTER);

        this.setSize (800, 800);

        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);// can close frame

        this.setVisible (true);// show the swing paint result

        pack();
    }

    //create the main method
    public static void main ( String[] args ) {
        new MainFrame();
    }
}

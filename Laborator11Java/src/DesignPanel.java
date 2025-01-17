import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DesignPanel extends JPanel {
    public static final int W = 800, H = 600;
    private final MainFrame frame;

    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(W, H));
        setLayout(null);
    }

    /**
     * @param comp: a new component described in Control panel by user
     *            that should be added to the frame
     */
    public void addAtRandomLocation(JComponent comp) {

        int x = randNumber(0, W);//create a random integer between 0 and W-1
        int y = randNumber(0, H);//create a random integer between 0 and H-1
        int w = comp.getPreferredSize().width;
        int h = comp.getPreferredSize().height;
        comp.setBounds(x, y, w, h);
        comp.setToolTipText(comp.getClass().getName());
        this.add(comp);
        frame.setVisible(true);
        frame.repaint();
    }

    /**
     * @param from: the first edge of interval
     * @param to: the first number outside the edge
     * @return : a random number in between from (inclusive) and to (exclusive)
     */
    private int randNumber(int from, int to)
    {
        Random rand = new Random();
        return (rand.nextInt(to - from) + from);
    }
}

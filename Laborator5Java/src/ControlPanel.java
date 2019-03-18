import javax.swing.*;

public class ControlPanel extends JPanel {
    private final CatalogFrame frame;
    JButton loadBtn = new JButton("Load");
    public ControlPanel(CatalogFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {

        loadBtn.setHorizontalAlignment(SwingConstants.CENTER);
        loadBtn.setVerticalAlignment(SwingConstants.CENTER);
        loadBtn.setBounds(50, 50, 90, 50);
        //frame.add(loadBtn);
    }
}

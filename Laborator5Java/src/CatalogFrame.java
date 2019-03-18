import javax.swing.*;
import javax.naming.ldap.Control;

public class CatalogFrame extends JFrame {
    GraphForm form;
    CatalogList list;
    ControlPanel control;

    public CatalogFrame() {
        super("Visual Graph Manager");
        this.setSize(500, 500);
        init();
    }
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        form = new GraphForm(this);
        list = new CatalogList(this);
        control = new ControlPanel(this);
        this.add(form);
        this.add(list);
        this.add(control);
        this.pack();
        this.setVisible(true);
        this.setSize(800, 500);
        this.setResizable(false);
    }
}
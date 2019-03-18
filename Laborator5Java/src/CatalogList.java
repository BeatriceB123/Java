import javax.swing.*;

public class CatalogList extends JList {
    private final CatalogFrame frame;
    DefaultListModel model = new DefaultListModel<>();

    public CatalogList(CatalogFrame frame) {
        this.frame = frame;
        String title = "<html><i><font color='blue'>" +
                       "Catalog Graphs" + "</font></i></html>";
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setBounds(200, 200, 200, 200);
        addGraph("aa");
        addGraph("bb");
        this.setModel(model);
    }
    public void addGraph(String item) {
        model.addElement(item);
    }

    public void addGraph() {
    }
}

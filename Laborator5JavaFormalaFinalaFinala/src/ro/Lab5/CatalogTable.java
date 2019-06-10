package ro.Lab5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CatalogTable extends JPanel {
    private final CatalogFrame frame;



    private final JTable table;
    private final DefaultTableModel model;

    public CatalogTable(CatalogFrame frame) {
        super(new GridLayout(1,0));
        this.frame = frame;
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Name");
        model.addColumn("Type");
        model.addColumn("Nr Vertices");
        model.addColumn("Nr Edges");
        model.addColumn("Definition file Path");
        model.addColumn("Image file Path");
        table = new JTable(model);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);

        add(new JScrollPane(table));
    }
    public void addGraph(String[] item) {
        model.addRow(item);
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getTable () {
        return table;
    }
}

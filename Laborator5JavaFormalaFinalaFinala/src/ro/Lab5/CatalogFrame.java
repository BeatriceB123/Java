package ro.Lab5;

import ro.Commands.Catalog;
import javax.swing.*;
import java.awt.*;

public class CatalogFrame extends JFrame {
    private GraphForm form;
    private CatalogTable table;
    private ControlPanel control;
    private Catalog catalog;

    public CatalogFrame() {
        super("Visual Graph Manager");
        init();
    }
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        catalog = new Catalog();

        setForm(new GraphForm(this));
        setTable(new CatalogTable(this));
        setControl(new ControlPanel(this));
        this.add(getForm(), BorderLayout.NORTH);
        this.add(new JScrollPane(getTable()), BorderLayout.CENTER);
        this.add(getControl(), BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }


    public GraphForm getForm() {
        return form;
    }
    public ControlPanel getControl() {
        return control;
    }
    public CatalogTable getTable() {
        return table;
    }
    public Catalog getCatalog() { return catalog; }


    public void setTable(CatalogTable table) {
        this.table = table;
    }
    public void setForm(GraphForm form) {
        this.form = form;
    }
    public void setControl(ControlPanel control) {
        this.control = control;
    }
    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

}


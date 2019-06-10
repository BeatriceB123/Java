package ro.Lab5;

import ro.Commands.Catalog;
import ro.Commands.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ControlPanel extends JPanel {
    private final CatalogFrame frame;
    private Catalog catalog = new Catalog();
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton openBtn = new JButton("Open");
    JButton reportHtmlBtn = new JButton("Report Html");
    public ControlPanel(CatalogFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir")); //doar deschide folderul curent
                StringBuilder path = new StringBuilder();
                if (fc.showOpenDialog(new JButton()) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selectedFile = fc.getSelectedFile();
                        path.append(selectedFile.getParentFile()).append(File.separator).append(selectedFile.getName());
                        frame.getCatalog().load(path.toString());
                    } catch (Exception exc) {
                        System.out.println(exc.getMessage());
                    }
                }
                else return;

                frame.getTable().getModel().setRowCount(0);

                for (Graph it : frame.getCatalog().getList()) {
                    String[] result = new String[]{it.getName(), it.getPathTgf(), it.getPathFile(),
                            String.valueOf(it.isSimple()), String.valueOf(it.getN()), String.valueOf(it.getM())};
                    frame.getTable().addGraph(result);
                }

            }
        });

        this.add(loadBtn);

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getCatalog().save(System.getProperty("user.dir") + File.separator + "currentFileGraph.tgf");
            }
        });

        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // frame.getCatalog().save(System.getProperty("user.dir") + File.separator + "currentFileGraph.tgf");
                frame.getCatalog().open(frame.getTable ().getTable ().getValueAt (
                        frame.getTable ().getTable ().getSelectedRow(), 5).toString ());

            }
        });

        this.add(saveBtn);


        this.add(openBtn);

        reportHtmlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getCatalog().reportHtml();
            }
        });

        this.add(reportHtmlBtn);
    }
}

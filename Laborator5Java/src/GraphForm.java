import javax.swing.*;
import javax.swing.event.ChangeListener;


public class GraphForm extends JPanel {
    private final CatalogFrame frame;
    JLabel nameLabel = new JLabel("Name of the graph");
    JLabel typeLabel = new JLabel("Type of the graph");
    JLabel pathDefinitionLabel = new JLabel("Path of file of definition(txt)");
    JLabel pathImageLabel = new JLabel("Path of image");
    JLabel nrNodesLabel = new JLabel("Nr of nodes");
    JLabel nrVerticesLabel = new JLabel("Nr of vertices");
    JTextField nameField = new JTextField();
    JComboBox typeField = new JComboBox(new String[]{"simple", "directed"});
    JTextField pathDefinitionField = new JTextField();
    JTextField pathImageField = new JTextField();
    JSpinner nrNodesSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    JSpinner nrVerticesSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 10000, 1));
    JButton inputBtn = new JButton("Save Input");

    public GraphForm(CatalogFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        nameLabel.setBounds(10, 10, 150, 20);
        typeLabel.setBounds(10, 40, 150, 20);
        pathDefinitionLabel.setBounds(10, 70, 200, 20);
        pathImageLabel.setBounds(10, 100, 150, 20);
        nrNodesLabel.setBounds(10, 130, 150, 20);
        nrVerticesLabel.setBounds(10, 160, 150, 20);
        nameField.setBounds(235, 10, 100, 20);
        typeField.setBounds(235, 40, 100, 20);
        pathDefinitionField.setBounds(235, 70, 100, 20);
        pathImageField.setBounds(235, 100, 100, 20);
        nrNodesSpinner.setEditor(new JSpinner.NumberEditor(nrNodesSpinner));
        nrNodesSpinner.setBounds(235, 130, 100, 20);
        nrVerticesSpinner.setEditor(new JSpinner.NumberEditor(nrVerticesSpinner));
        nrVerticesSpinner.setBounds(235, 160, 100, 20);
        inputBtn.setBounds(10, 200, 100, 30);
        frame.add(nameLabel);
        frame.add(typeLabel);
        frame.add(pathDefinitionLabel);
        frame.add(pathImageLabel);
        frame.add(nrNodesLabel);
        frame.add(nrVerticesLabel);
        frame.add(nameField);
        frame.add(typeField);
        frame.add(pathDefinitionField);
        frame.add(pathImageField);
        frame.add(nrNodesSpinner);
        frame.add(nrVerticesSpinner);
        frame.add(inputBtn);

    }
    private void addGraph() {
        frame.list.addGraph();
    }
}
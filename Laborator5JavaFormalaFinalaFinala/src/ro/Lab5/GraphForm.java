package ro.Lab5;

import ro.Commands.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GraphForm extends JPanel {//un container care extinde mai multe chestii
    private final CatalogFrame frame; //folosit pentru a accesa frame - ul principal
    private JLabel nameLabel = new JLabel("Name of the graph");
    private JLabel typeLabel = new JLabel("Graph type");
    private JLabel pathDefinitionLabel = new JLabel("Path of file of definition(tgf)");
    private JLabel pathImageLabel = new JLabel("Path of image");
    private JLabel nrVerticesLabel = new JLabel("Nr of vertices");
    private JLabel nrEdgesLabel = new JLabel("Nr of edges");
    //Campurile de informatii(mai sus)
    private JTextField nameField = new JTextField(); //textul de la nume
    private JComboBox<String> typeField = new JComboBox<>(new String[]{"simple", "directed"});//optiuni graf
    private JTextField pathDefinitionField = new JTextField();//textul de la path definition(tgv, pdf, etc)
    private JButton selectDefinitionBtn = new JButton("Select file");//buton care selecteaza ce e mai sus
    private JTextField pathImageField = new JTextField();//textul de la imagine
    private JButton selectImageBtn = new JButton("Select image");//buton care selecteaza ce e mai sus
    private JSpinner nrVertivesSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));//selectorul de nr noduri
    private JSpinner nrEdgesSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 10000, 1));//selectorul de nr muchii
    private JButton inputBtn = new JButton("Add to repository");//butonul de ADD principal
    private JPanel insidePanel = new JPanel();//pentru cele 3 de mai sus si tip(combo Box)
    private JPanel definitionFilePanel = new JPanel();//pentru file definition buton + text field
    private JPanel imageFilePanel = new JPanel();//pentru image buton + text field

    public GraphForm(CatalogFrame frame) {
        this.frame = frame;
        this.setLayout(new GridLayout(0, 1));
        init();
    }
    private void init() {

        this.add(nameLabel);
        this.add(nameField);

        //campurile de nume
        this.add(pathDefinitionLabel);
        pathDefinitionField.setPreferredSize(new Dimension(300, 30));
        definitionFilePanel.add(pathDefinitionField);
        definitionFilePanel.add(selectDefinitionBtn);

        //(mai jos)iau fisierul deschis (selectat de utilizator) si returnez numele in text Panel - ul potrivit
        selectDefinitionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir")); //doar deschide folderul curent
                if(fc.showOpenDialog(new JButton()) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selectedFile = fc.getSelectedFile();
                        pathDefinitionField.setText(selectedFile.getParentFile() + File.separator + selectedFile.getName());
                    }
                    catch (Exception exc) {
                        System.out.println(exc.getMessage());
                    };
                }
            }
        });

        this.add(definitionFilePanel); // trebuie un Panel sa stea butonul si text field - ul unul langa celalalt
        this.add(pathImageLabel);
        pathImageField.setPreferredSize(new Dimension(300, 30));
        imageFilePanel.add(pathImageField);
        imageFilePanel.add(selectImageBtn);


        selectImageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir")); //incepem din directorul curent
                if(fc.showOpenDialog(new JButton()) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selectedFile = fc.getSelectedFile();
                        pathImageField.setText(selectedFile.getParentFile() + File.separator + selectedFile.getName());

                        System.out.println(selectedFile.getName());
                        System.out.println(selectedFile.getParentFile());
                    }
                    catch (Exception exc) {
                        System.out.println(exc.getMessage());
                    };
                }
            }
        });
        this.add(imageFilePanel);

        //mai jos , e nevoie de panel sa stea unul langa celalalat
        insidePanel.add(typeLabel);
        insidePanel.add(typeField);

        insidePanel.add(nrVerticesLabel);
        insidePanel.add(nrVertivesSpinner);

        insidePanel.add(nrEdgesLabel);
        insidePanel.add(nrEdgesSpinner);
        insidePanel.add(inputBtn);

        inputBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] result = new String[]{nameField.getText(), typeField.getSelectedItem().toString(), nrVertivesSpinner.getValue().toString(),
                        nrEdgesSpinner.getValue().toString(), pathDefinitionField.getText(), pathImageField.getText()};
                frame.getTable().addGraph(result);

                Graph newGraph = new Graph(nameField.getText(), pathDefinitionField.getText(), pathImageField.getText(),
                        typeField.getSelectedItem().toString().equals("simple"), (int)nrVertivesSpinner.getValue(), (int)nrEdgesSpinner.getValue());
                frame.getCatalog().add(newGraph);
            }
        });

        //mai sus, acelasi meta, adaugam textul de definire(label) si chestia imediat de alaturi in Panel - ul de jos

        this.add(insidePanel);//adaugam efectiv panel - ul

        this.setBorder(BorderFactory.createTitledBorder("Add Graph"));

    }
}
import javax.swing.*;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private final JLabel classNameLabel = new JLabel("Class name");
    private final JTextField classNameField = new JTextField(30);
    private final JLabel textLabel = new JLabel("Default text");
    private final JTextField textField = new JTextField(10);
    private final JButton createButton = new JButton("Add component");

    private final JButton saveButton = new JButton("Save");
    private final JButton loadButton = new JButton("Load");
    String[][] data = {
            { "John Doe", "4031", "CSE" },
            { "John Doe", "6014", "IT" }
    };
    String[] columnNames = { "Name", "Roll Number", "Department" };

    private JTable introspecterTable = new JTable(data, columnNames);

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        Introspector.flushCaches();
        add(classNameLabel);
        add(classNameField); //any class name of a Swing component
        add(textLabel);
        add(textField);     //a default text for that component (if applicable)
        add(createButton);  //a button for creating and adding an instance of the specified component to the DesignPanel
        add(saveButton);
        add(loadButton);
        add(introspecterTable);

        createButton.addActionListener(e -> {
            JComponent comp = null;
            try {
                comp = createDynamicComponent(classNameField.getText(), textField.getText());
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            setComponentText(comp, "JJJ");
            frame.designPanel.addAtRandomLocation(comp);
        });
        saveButton.addActionListener(e ->{
            System.out.println("SAVE press");
                }
        );
        loadButton.addActionListener(e ->{
                    System.out.println("LOAD press");
                }
        );
    }

    private JComponent createDynamicComponent(String className, String text) throws ClassNotFoundException {
        System.out.println("Nume clasa: " + className);
        /*switch (className) {
            case "JButton" : return new JButton(text);
            case "JLabel" : return new JLabel(text);
            case "JScrollPane" : return new JScrollPane();
            case "JPanel" : return new JPanel();
            case "JTable" : return new JTable();
            default : return new JButton(text);
        }*/
        Class clazz = Class.forName(className);
        try {
            Class[] signature = new Class[] {String.class};
            Method method = clazz.getMethod("setText", signature);

            JComponent component = (JComponent) clazz.newInstance();
            method.invoke(component, text);

            return component;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            //e.printStackTrace();
        }
        return new JLabel("comp");
    }

    private void setComponentText(JComponent comp, String text) {
	   comp.setToolTipText(text);
    }

}

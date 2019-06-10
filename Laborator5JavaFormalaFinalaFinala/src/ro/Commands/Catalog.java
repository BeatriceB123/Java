package ro.Commands;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Catalog implements Serializable {

    private ArrayList<Graph> list;

    public Catalog(ArrayList<Graph> list) {
        this.list = list;
    }

    public Catalog() {
        this(new ArrayList<>());
    }

    public Catalog(Catalog catalog) {
        this.list = catalog.getList();
    }

    public ArrayList<Graph> getList() {
        return list;
    }

    public void setList(ArrayList<Graph> list) {
        this.list = list;
    }

    public void add(Graph graph) {
        list.add(graph);
    }

    public void open(String fileName) {
        try {
            File file = new File (fileName);
            Desktop desktop = Desktop.getDesktop ();
            if ( file.exists () )
                desktop.open (file);
        }
        catch (Exception e) {}
    }

    public void save(String path) {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(path);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            oos.close();
            fout.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (oos != null)
                    oos.close();
                if (fout != null)
                    fout.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void load(String path) {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            File f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            this.list = ((Catalog) ois.readObject()).getList();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void reportHtml() {
        try {
            String fileName = "htmlDocument.html";
            StringBuilder string = new StringBuilder();
            string.append("<html>\n<head>\n</head>\n<body> <p>\n");
            for (Graph it : this.getList())
                string.append("Name : ").append(it.getName()).append(", type")
                        .append(it.isSimple()).append(", n = ").append(it.getN()).append(", m = ")
                        .append(it.getM()).append(", definition file : ").append(it.getPathTgf()).append(", image file : ")
                        .append(it.getPathFile()).append("<br>");
           // System.out.println(string.toString());

            File f = new File(System.getProperty("user.dir") + File.separator, fileName);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
            string.append("</p></body></html>");
            writer.write(string.toString());
            //System.out.println("FILE MADE YEY!! where??" + f.getAbsolutePath());
            writer.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

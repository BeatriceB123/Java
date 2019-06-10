package ro.Commands;

import java.io.Serializable;

public class Graph implements Serializable {
    private String name;
    private String pathTgf;
    private String pathFile;
    private boolean isSimple;
    private int n;
    private int m;

    public Graph(String name, String pathTgf, String pathFile, boolean isSimple, int nrEdges, int nrVertices) {
        this.name = name;
        this.pathTgf = pathTgf;
        this.pathFile = pathFile;
        this.isSimple = isSimple;
        this.n = nrEdges;
        this.m = nrVertices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathTgf() {
        return pathTgf;
    }

    public void setPathTgf(String pathTgf) {
        this.pathTgf = pathTgf;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public boolean isSimple() {
        return isSimple;
    }

    public void setDirected(boolean directed) {
        isSimple = directed;
    }

    public int getN() {
        return n;
    }

    public void setN(int nrEdges) {
        this.n = nrEdges;
    }

    public int getM() {
        return m;
    }

    public void setM(int nrVertices) {
        this.m = nrVertices;
    }
}

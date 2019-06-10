package GraphPackage;

public class Edge {
    private Node u;
    private Node v;

    public Node getU () {
        return u;
    }

    public void setU ( Node u ) {
        this.u = u;
    }

    public Node getV () {
        return v;
    }

    public void setV ( Node v ) {
        this.v = v;
    }

    public Edge ( Node u, Node v ) {
        this.u = u;
        this.v = v;
    }

    @Override
    public String toString () {
        return "Muchie: " + v.toString () + "  " + u.toString () + '\n';
    }
}

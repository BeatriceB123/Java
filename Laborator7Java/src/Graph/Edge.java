package Graph;

public class Edge {
    private int n, m;

    public Edge(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setM(int m) {
        this.m = m;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Edge)) return false;
        Edge other = (Edge) o;
        return (this.n == other.n && this.m == other.m);
    }

    @Override
    public int hashCode() {
        return n + m;
    }

    @Override
    public String toString() {
        return n + "-" + m;
    }
}

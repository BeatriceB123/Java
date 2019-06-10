public class Edge
{
    int cost; //distance in kilometers
              //or the time required to go from one location to the other
    Node v;
    Node u;

    boolean leftToRight = false;
    boolean rightToLeft = false;
//if an edge is bi-directional it'll have both seted to true


    public Edge ( int cost, Node v, Node u, boolean leftToRight, boolean rightToLeft )
    {
        this.cost = cost;
        this.v = v;
        this.u = u;
        this.leftToRight = leftToRight;
        this.rightToLeft = rightToLeft;
    }

    @Override
    public String toString () {
        return "Edge{" +
               "v = " + v +
               ", u = " + u +
               "}";
    }
}

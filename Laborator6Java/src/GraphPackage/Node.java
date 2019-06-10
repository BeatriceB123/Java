package GraphPackage;

import java.awt.*;
import java.util.Random;

public class Node {
    private int x;
    private int y;
    private Color color;

    public Node ( int x, int y, Color color){//}}, Color color) {
        this.x = x;
        this.y = y;
        setColor (color);
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public Color getColor(){return color; }

    private void setColor(Color color){
        this.color = color;
    }

    @Override
    public String toString () {
        return " [" + x + "." + y + "] ";
    }
}

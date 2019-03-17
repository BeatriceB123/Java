package Command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//collection of graphs
public class Catalog implements Serializable {

    String catalogPath;
    List <Graph> graphList;

    public Catalog () {
        this.graphList = new ArrayList <Graph> ();
        this.catalogPath = "";
    }

    @Override
    public String toString () {
        String result = "";
        for (int i = 0; i < graphList.size (); i++)
            result += graphList.get (i);
        return result + '\n';
    }

    public List<String> getGraphsNames()
    {
        List<String> names = new ArrayList <> ();
        for(int i=0; i<graphList.size(); i++)
        {
            names.add(graphList.get(i).name);
        }
        return names;
    }
}

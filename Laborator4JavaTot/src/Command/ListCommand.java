package Command;

import java.util.List;

public class ListCommand extends Command {

    public ListCommand(Catalog catalog)
    {
        super(catalog);
    }

    @Override
    public void runCommand ( List <String> arguments ){
        System.out.println ("Content of our catalog: ");
        for (Graph graph : catalog.graphList) {
            System.out.print (graph + " ");
        }
    }
}

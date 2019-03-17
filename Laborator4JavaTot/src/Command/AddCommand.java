package Command;

import java.util.List;
import Tools.*;
public class AddCommand extends Command {

    public AddCommand(Catalog catalog)
    {
        super(catalog);
    }

    /**
     * @param arguments: a list of 4 arguments(verified in parser)
     */
    @Override
    public void runCommand( List <String> arguments) throws WrongCommandArguments
    {
        if ( arguments.size () != 4 )
            throw new WrongCommandArguments ("Invalid number of arguments: " + arguments.size () + arguments);

        if ( !Tools.validateName (arguments.get (0)) )
            throw new WrongCommandArguments ("Invalid name: " + arguments.get (0));

        if ( !Tools.validateDescription (arguments.get (1)) )
            throw new WrongCommandArguments ("Invalid description: " + arguments.get (1));

        if ( !Tools.validatePath (arguments.get (2), ".tgf") )
            throw new WrongCommandArguments ("Invalid path to tgf: " + arguments.get (2));

        if ( !Tools.validatePath (arguments.get (3), ".png") )
            throw new WrongCommandArguments ("Invalid path to png: " + arguments.get (3));

        Graph graph = new Graph (arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3));
        int position = Tools.getPosition (graph.name, catalog.getGraphsNames ());
        if ( position == -1 )
            catalog.graphList.add (graph);
        else
            throw new IllegalArgumentException ("This graph name is already in the catalog");

        System.out.println ("Graph added.");
    }

}

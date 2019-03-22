package Command;
import Tools.Tools;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class OpenCommand extends Command {

    public OpenCommand(Catalog catalog){super(catalog);}

    @Override
    public void runCommand ( List <String> arguments ) throws WrongCommandArguments, IOException {
        if(arguments.size()!=1)
            throw new WrongCommandArguments ("Invalid number of arguments(" + arguments.size()+")");
        int position = Tools.getPosition (arguments.get(0), catalog.getGraphsNames ());
        if(position==-1)
            throw new WrongCommandArguments("Invalid graph name("+arguments.get(0)+")");
        openByPath (catalog.graphList.get(position).pathToImage);
    }

    public void openByPath(String path) throws IOException
    {
        //File file = new File ("E:\\JAVA - Programare avansata\\Laborator4JavaTot\\src\\resources\\images\\k4.png");
        File file = new File(path);
        Desktop desktop = Desktop.getDesktop ();
        if ( file.exists () )
            desktop.open (file);
        else
            throw new IOException ("Couldn't find the file or you don't have the rights " + path + '\n');
    }
}
package Command;

import java.io.*;
import java.util.List;

public class LoadCommand extends Command {
    public LoadCommand ( Catalog catalog ) {
        super (catalog);
    }

    @Override
    public void runCommand ( List <String> arguments )
            throws IOException, ClassNotFoundException, WrongCommandArguments
    {
        if(arguments.size()!=1)
            throw new WrongCommandArguments ("Load command accepts one argumend and only one");
        String path = arguments.get(0); //where we can find the file
        InputStream file = new FileInputStream (path);
        InputStream buffer = new BufferedInputStream (file);
        ObjectInputStream objStream =  new ObjectInputStream(buffer);
        Catalog managerFromFile = (Catalog) objStream.readObject();
        catalog = managerFromFile;
    }
}

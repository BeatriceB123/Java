package Command;

import java.io.*;
import java.util.List;

public class SaveCommand extends Command {

    public SaveCommand ( Catalog catalog ) {
        super (catalog);
    }

    @Override
    public void runCommand ( List <String> arguments )
            throws FileNotFoundException, IOException, WrongCommandArguments {
        if(arguments.size()!=1)
            throw new WrongCommandArguments ("Save command accepts one argumend and only one");
        String path = arguments.get(0); //path to our save location
        OutputStream file = new FileOutputStream (path);
        OutputStream buffer = new BufferedOutputStream (file);
        ObjectOutputStream objStream =  new ObjectOutputStream(buffer);
        objStream.writeObject(catalog);
        objStream.close();
        buffer.close();
        file.close();
    }
}

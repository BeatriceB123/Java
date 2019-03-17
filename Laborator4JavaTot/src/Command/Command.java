package Command;
import java.util.List;

//abstract class for generic command
public abstract class Command {

        protected Catalog catalog;

        //public Command(){}
        public Command(Catalog catalog){
                this.catalog = catalog;
        }

        //this method will be implemented in each command class
        public abstract void runCommand(List<String> arguments) throws Exception;

}

public class WrongCommand extends Exception {
    //signal the commands that are not valid using a custom exception.
    private String wrongCommand;

    WrongCommand(String wrongCommand){
        this.wrongCommand = wrongCommand;
    }

    @Override
    public String getMessage(){
        return "\"" + wrongCommand + "\" is a wrong command.";
    }

}

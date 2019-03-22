package Command;

public class WrongCommandArguments extends Exception {
    //signal the commands that are not valid using a custom exception.
    private String wrongCommand;

    WrongCommandArguments(String wrongCommand){
        this.wrongCommand = wrongCommand;
    }

    @Override
    public String getMessage(){
        return "Invalid arguments: " + "\"" + wrongCommand + "\" ";
    }

}
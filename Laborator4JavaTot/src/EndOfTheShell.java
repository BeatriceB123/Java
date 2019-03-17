public class EndOfTheShell extends Exception {
    //signal the commands that are not valid using a custom exception.
    private String message;

    EndOfTheShell(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){ return message ; }
}

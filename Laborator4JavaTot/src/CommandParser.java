import Command.*;
import Tools.*;
import com.itextpdf.text.DocumentException;
import javafx.util.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommandParser {

    enum CommandType { add, list, save, load, open, exit, report, svg };
    private Map <String, CommandType> commandMap; //name and type for commands
    private Scanner scanner;
    private Catalog catalog = new Catalog (); //managed catalog

    public CommandParser()
    {
        this.scanner = new Scanner(System.in);
        commandMap = new HashMap <> ();
        commandMap.put("add", CommandType.add);
        commandMap.put("open", CommandType.open);
        commandMap.put("save", CommandType.save);
        commandMap.put("list", CommandType.list);
        commandMap.put("load", CommandType.load);
        commandMap.put("exit", CommandType.exit);
        commandMap.put("report", CommandType.report);
        commandMap.put("svg", CommandType.svg);
    }

    /**
     *
     * @param line - the line from the keyboard that we want to split in two
     * @return a pair formed by the type(from enum) and a sting with the arguments
     * @throws WrongCommand if we can't recognize the exception
     */
    public Pair<CommandType, String> parseCommand(String line) throws WrongCommand{
        line = line.trim().replaceAll(" +", " ");//extra white spaces removed
        line = line.replaceAll (",", "");
        int pos = line.indexOf(' ');//the index of the first occurrence of character ' '

        String commandStr;
        String argsStr;
        if(pos != -1){
            commandStr = line.substring(0, pos); //pos -> exclusive index(so we have the string from 0 to pos-1)
            argsStr = line.substring(pos+1);     //(pos+1) -> inclusive (without white space)
        }
        else {
            commandStr = line;
            argsStr = "";
        }
        if(!commandMap.containsKey(commandStr))
            throw new WrongCommand(commandStr);
        else {
            return  new Pair<>(commandMap.get(commandStr), argsStr);
        }
    }

    /**
     *
     * @param command a pair formed by the type(from enum)
     * @param argumentString a sting with the arguments from the shell
     * @throws WrongCommandArguments if arguments aren't good for our command type
     */
    public void executeCommand(CommandType command, String argumentString)
            throws WrongCommandArguments, WrongCommand, IOException, DocumentException, ClassNotFoundException, EndOfTheShell
    {
        List <String> arguments = new ArrayList <String> (Tools.getArguments (argumentString));
        switch (command)
        {
            case add: {
                AddCommand actualCommand = new AddCommand (catalog);
                actualCommand.runCommand (arguments);
                break;
            }

            case list:{
                new ListCommand (catalog).runCommand (arguments);
                break;
            }

            case open:{
                new OpenCommand (catalog).runCommand (arguments);
                break;
            }

            case save: {
                new SaveCommand (catalog).runCommand (arguments);
                break;
            }

            case load: {
                new LoadCommand(catalog).runCommand(arguments);
                break;
            }

            case svg:{
                //System.out.println ("Filename: " + arguments.get(0));
                new SvgCommand(catalog).runCommand(arguments);
                break;
            }

            case report:{
                //System.out.println ("Arg type:" + arguments.get(0)+":");
                new ReportCommand (catalog).runCommand (arguments);
                break;
            }

            case exit:{
                throw new EndOfTheShell ("And they lived happily ever after.");
            }

            default: {
                throw new WrongCommand("No such command");
            }
        }
    }

    /**
     * we're reading commands from the keyboard,
     * parse them (for understanding)
     * and then execute them
     */
    public void run()
    {
        boolean isOver = false;
        while(!isOver)
        {
            try
            {
                System.out.print("$:");
                System.out.flush(); //clear the output buffer
                Pair <CommandType, String> parseResult = parseCommand(scanner.nextLine()); //command to be executed (tip si argumente)
                executeCommand(parseResult.getKey(), parseResult.getValue());
            }
            catch (WrongCommand e)
            {
                System.err.println(e.getMessage());
            }
            catch (WrongCommandArguments e)
            {
                System.err.println (e.getMessage ());
            }
            catch (IOException e)
            {
                System.out.println (e.getMessage ());
            }
            catch (EndOfTheShell e)
            {
                System.out.println (e.getMessage ());
                isOver = true;
            }
            catch (Exception e)
            {
                System.err.println("Another exception: " + e.getMessage());
            }
        }

    }


}

/*
asta am incercat..cateva ore..
(Arrays.asList(argumentString.split(" ")));
String patternString = " *\"[A-Za-z0-9 ]+\" *";
Pattern pattern = Pattern.compile(patternString); //a compiled representation of a regular expression
Matcher matcher = pattern.matcher(argumentString);//the engine that interprets the pattern and performs match operations against an input string
System.out.println (argumentString);
System.out.println (patternString);
System.out.println (Pattern.matches (patternString, argumentString));
if (!matcher.matches()) {
throw new WrongCommandArguments("add:"+argumentString+":");
}
int startIndex = 1;
arguments.add(matcher.group(0));
*/

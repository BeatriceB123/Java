import inbox.Message;
import modelOfNetwork.Person;
import modelOfNetwork.SocialNetwork;
import modelOfNetwork.StructuralCohesion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * An instance of this class will be responsible with communicating with a client Socket.
 */
public class ClientThread extends Thread {
    private Socket clientSocket;
    private boolean runningSesion = false;
    private String userLoggedName = null;
    private ConnectionTimer timer;

    ClientThread ( Socket clientSocket ) {
        this.clientSocket = clientSocket;
        setRunningSesion (true);
    }

    public void run () {
        if( SocialNetworkServer.running ) {
            while (runningSesion) {
                runCommunication (); //se va primi si trimite un mesaj
            }
            try {
                clientSocket.close ();
            } catch (IOException e) {
                System.out.println ("Nu am putut inchide socketul threadului. " + e.getMessage ());
            }
        }
        else
        {
            System.out.println ("Serverul nu mai accepta conexiuni");
        }
    }

    private void runCommunication () {
        //System.out.println (this.getName () + " " + clientSocket.toString ());
        Scanner in;
        String request;
        String response;

        try {
            in = new Scanner (clientSocket.getInputStream ());

            try {
                request = in.nextLine ();
                response = execute (request);

                try {
                    PrintWriter out = new PrintWriter (clientSocket.getOutputStream ());
                    out.println (response);
                    out.flush ();

                } catch (IOException e) {
                    e.printStackTrace ();
                }

            } catch (NoSuchElementException e) {
                System.out.println ("Client disconnected. Msg: " + e.getMessage ());
                setRunningSesion (false);
            }

        } catch (IOException e) {
            System.out.println (clientSocket.getPort () + " " + e.getMessage ());
        }
    }

    private String execute ( String request ) {
        String executeMessage = "Nu e o comanda valida";
        if ( ToolsForCommandsValidation.isExitCommand (request) )
            executeMessage = exitSession ();
        if ( ToolsForCommandsValidation.isRegisterCommand (request) )
            executeMessage = register (request);
        if ( ToolsForCommandsValidation.isLoginCommand (request) )
            executeMessage = login (request);
        if ( ToolsForCommandsValidation.isFriendCommand (request) )
            executeMessage = addFriends (request);
        if ( ToolsForCommandsValidation.isReadCommand (request) )
            executeMessage = readMessages (request, false);
        if ( ToolsForCommandsValidation.isReadNewCommand (request) )
            executeMessage = readMessages (request, true);
        if ( ToolsForCommandsValidation.isSendCommand (request) )
            executeMessage = sendMessageToAllFriends (request);
        if ( ToolsForCommandsValidation.isSeeNetworkStructure (request) )
            executeMessage = seeNetworkStructure (request);
        if ( ToolsForCommandsValidation.isUploadCommand (request) )
            executeMessage = upload (request);
        if(ToolsForCommandsValidation.isStopCommand (request))
            executeMessage = stopServer();
        if ( userLoggedName != null )
            Objects.requireNonNull (SocialNetwork.getPersonByName (userLoggedName)).commandSend ();

        System.out.println (SocialNetworkServer.socialNetwork);

        return executeMessage;
    }

    private String stopServer(){
        String response = "exit";
        for(int i=0; i<SocialNetwork.getPersons().size(); ++i)
        {
            Person person = SocialNetwork.getPersons ().get (i);
            //le trimitem lor mesajul
            try {
                PrintWriter out = new PrintWriter (person.getClientSocket().getOutputStream ());
                out.println (response);
                out.flush ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            //si ii delogam pe ceilalti
            person.setLogged (false);
        }
        //si il delogam pe cel de aici
        exitSession ();
        SocialNetworkServer.running = false;
        return "exit";
    }

    private String upload ( String request ) {
        return "Serverul a facut upload";
    }

    private String seeNetworkStructure ( String request ) {
        StructuralCohesion structuralCohesion = new StructuralCohesion (SocialNetworkServer.socialNetwork);
        return "Serverul a vazut";
    }

    private String sendMessageToAllFriends ( String request ) {
        if ( userLoggedName == null )
            return "Logheaza-te pentru a putea trimite mesaje prietenilor";
        String messageText = request.replace ("send ", "");

        Person person = SocialNetwork.getPersonByName (userLoggedName);
        assert person != null;

        person.sendMessageToAllFriends (messageText);

        return "Mesajul a fost trimis tuturor prietenilor din lista ta de prieteni";
    }

    private String readMessages ( String request, boolean justNewMessages ) {
        if ( userLoggedName == null )
            return "Logheaza-te pentru a-ti putea citi mesajele";

        Person person = SocialNetwork.getPersonByName (userLoggedName);
        assert person != null;

        return person.getMessages (justNewMessages);
    }

    private String addFriends ( String request ) {
        if ( userLoggedName == null )
            return ("Logheaza-te pentru a adauga prieteni in lista ta");

        String list = request.replace ("friend ", "");

        List <String> listOfNames = new ArrayList <String> (Arrays.asList (list.split (" ")));

        for (String name : listOfNames) {
            SocialNetwork.addFriendship (userLoggedName, name);
        }

        return "Prieteni adaugati cu succes in lista ta";
    }

    private String register ( String request ) {
        String name = request.replace ("register", "");
        name = name.replaceAll (" ", "");
        SocialNetwork.addPerson (new Person (name, clientSocket));
        return "Te-ai inregistrat. Pentru a continua, logheaza-te";
    }

    private String login ( String request ) {
        String auxUserLoggedName = request.replace ("login", "");
        auxUserLoggedName = auxUserLoggedName.replaceAll (" ", "");

        if ( !SocialNetwork.nameOfPersonInList (auxUserLoggedName) )
            return "S-a ajuns cu o persoana neinregistrata la logare";

        if ( userLoggedName != null )
            SocialNetwork.loggingOutByName (userLoggedName);//il delogam pe ultimul(daca era) in aceasta sesiune
        userLoggedName = auxUserLoggedName;

        SocialNetwork.loggingInByName (userLoggedName);

        timer = new ConnectionTimer (SocialNetwork.getPersonByName (userLoggedName));//pornim un cronometru petnru sesiunea curenta
        new Thread (timer).start ();

        return "Te-ai logat. Poti accesa mai multe functii ale aplicatiei";
    }

    private String exitSession () {
        setRunningSesion (false);
        if ( userLoggedName != null )
            SocialNetwork.loggingOutByName (userLoggedName);
        return "Se va parasi aplicatia";
        //si aici ar trebui sa vedem ce nume are threadul curent, si sa il delogam
    }

    private void setRunningSesion ( boolean value ) {
        this.runningSesion = value;
    }
}

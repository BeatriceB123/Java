import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocialNetworkClient {

    private final static String SERVER_ADDRESS = "127.0.0.1";
    private final static int PORT = 8101;
    private static Socket socket;

    public static void main ( String[] args ) throws IOException {
        //SocialNetworkClient client = new SocialNetworkClient ();
        boolean runningSesion = true;
        socket = new Socket (SERVER_ADDRESS, PORT);

       // Thread threadForRead = new Thread(new CevaRelevant (socket));
       //  threadForRead.start ();

        while (runningSesion) {
            String request = readFromKeyboard ();
            sendRequestToServer (request);

            String response = readResponseFromServer ();
            System.out.println ("Serverul spune:\"" + response + ":\"");

            if ( ToolsForValidation.isExitCommand (request) || ToolsForValidation.isExitCommand (response))
                runningSesion = false;
        }
    }

    /**
     * tells the command to server
     *
     * @param request = our command to server
     */
    private static void sendRequestToServer ( String request ) {
        try {
            PrintWriter out = new PrintWriter (socket.getOutputStream (), true);
            out.println (request);
        } catch (IOException e) {
            System.out.println ("Nu s-a putut trimite mesajul la server: " + e.getMessage ());
        }
    }

    /**
     * read the response from server
     *
     * @return the response
     */
    private static String readResponseFromServer () {
        Scanner in = null;
        String response = "";

        try {
            in = new Scanner (socket.getInputStream ());
        } catch (IOException e) {
            e.printStackTrace ();
        }

        assert in != null;
        response = in.nextLine ().replaceAll ("###4", "\n");

        return response;
    }

    /**
     * reads a right command from user(using keyboard). If the command isn't right, require another
     * @return the comand, validated.
     */
    private static String readFromKeyboard () {
        Scanner scanner = new Scanner (System.in);
        String response;

        boolean validated = false;
        do {
            System.out.println ("Introduceti o comanda. Pentru ajutor introduceti \"help\"");
            response = scanner.nextLine ();
            if ( ToolsForValidation.isValidCommand (response) ) {
                if ( !ToolsForValidation.isHelpCommand (response) )
                    validated = true;
                else
                    showHelp ();
            }
        } while (!validated);
        return response;
    }

    private static void showHelp () {
        System.out.println ("Commands:");
        System.out.println ("register name: adds a new person to the social network;\n" +
                            "login name: establishes a connection between the server and the client;\n" +
                            "friend name1 name2 ... namek: adds friendship relations between the person that sends the command and other persons;\n" +
                            "send message: sends a message to all friends.\n" +
                            "read: reads the messages from the server.\n" +
                            "help: shows this helper.\n" +
                            "exit: exit from app.");
    }
}

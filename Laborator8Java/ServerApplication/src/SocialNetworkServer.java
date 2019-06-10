import modelOfNetwork.SocialNetwork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * An instance of this class will create a ServerSocket running at a specified port.
 * The server will receive requests (commands) from clients and it will execute them
 */
public class SocialNetworkServer {
    private static final int PORT = 8101;
    private static ServerSocket serverSocket;
    static SocialNetwork socialNetwork;
    public static boolean running = false;
    Scanner keyboard;

    public static void main ( String[] args ) throws IOException {
        SocialNetworkServer server = new SocialNetworkServer ();
        server.init ();
        waitForClients ();
    }

    /**
     * create the serverSocket and set running to true
     */
    private void init () throws IOException {
        serverSocket = new ServerSocket (PORT);
        running = true;
        socialNetwork = new SocialNetwork ();
        keyboard = new Scanner(System.in);
        System.out.println ("Server is running...");
    }

    /**
     * while running is true, create a new socket for every
     * incoming client and start a ClientThread to execute its request.
     */
    private static void waitForClients () throws IOException {
        while (running) {
            Socket socket = serverSocket.accept (); //method blocks until a connection is made.
            ClientThread client = new ClientThread (socket);
            if(!running)
                break;
            client.start (); //we run the new client in another thread;
        }
        stop();
    }

    private static void stop () throws IOException {
        running = false;
        serverSocket.close ();
        System.out.println ("Serverul s-a inchis" +
                            "..ieei");
    }
}

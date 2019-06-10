import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CevaRelevant implements Runnable {
    private Socket socket;

    CevaRelevant ( Socket socket ) {
        this.socket = socket;
    }

    @Override
    public void run () {
        while (runCommunication ()) {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
    }

    private boolean runCommunication () {
        Scanner in = null;
        String response = "";

        try {
            in = new Scanner (socket.getInputStream ());
        } catch (IOException ignored) {
            ;
        }

        assert in != null;
        if(in.hasNext ())
            response = in.nextLine ().replaceAll ("###4", "\n");

        System.out.println (response);
        return true;
    }
}

package GameType;

import Graph.Board;
import ThreadType.Player;

public class ConnectivityGame extends Game {
    public ConnectivityGame(){
        super();
        this.setBoard(new Board(10));
        this.setGameName("ConnectivityGame");
    }

    /**
     * This method creates a thread for every player and a thread for the timekeeper
     */
    public void start() {
        Thread t = new Thread(this.getTimekeeper());
        t.setDaemon(true);
        t.start();

        for (Player player : this.getPlayers()) {
            new Thread(player).start();
        }
    }
}

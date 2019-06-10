package GameType;

import Graph.Board;
import ThreadType.Player;

public class CliqueGame extends Game {
    public CliqueGame(){
        this.setBoard(new Board(6));
        this.setGameName("CliqueGame");
    }

    /**
     * This method creates a thread for every player and a thread for the timekeeper
     */
    public void start() {
        if(this.getPlayers().size()!=2)
            throw new Error("There must be two players to play this game.");

        Thread t = new Thread(this.getTimekeeper());
        t.setDaemon(true);
        t.start();

        for (Player player : this.getPlayers()) {
            new Thread(player).start();
        }
    }
}

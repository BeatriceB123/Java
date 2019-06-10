import GameType.*;
import ThreadType.ManualPlayer;
import ThreadType.RandomPlayer;
import ThreadType.SmartPlayer;
import ThreadType.Timekeeper;

public class Main {
    public static void main(String args[]) {
        //Game game = new ConnectivityGame();
        Game game = new CliqueGame();
        game.addPlayer(new RandomPlayer("Player 1"));
        //game.addPlayer(new RandomPlayer("Player 2"));
        //game.addPlayer(new ManualPlayer("Player 2"));
        game.addPlayer(new SmartPlayer("Player 3"));
        game.addTimekeeper(new Timekeeper());
        game.start();
    }
}

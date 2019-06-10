package ThreadType;

import GameType.Game;
import Graph.Graph;

public abstract class Player implements Runnable {

    private int turn = 1;
    private String name;
    private Game game;
    private Graph graph = new Graph();
    private int score;
    static final int THINKING_TIME = 2000;
    private boolean hasWon = false;

    public void setTurn(int turn){
        this.turn = turn;
    }

    public int getTurn(){
        return turn;
    }

    public String getName() {
        return name;
    }

    public void setVictory() {
        this.hasWon = true;
    }

    public Game getGame() {
        return game;
    }

    public boolean getVictory() {
        return hasWon;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public Graph getGraph(){
        return graph;
    }

    public Player(String name) {
        this.name = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * The main method of the class player for the Connectivity game. Determines player type.
     * @return true to indicate that player may take another turn and false to indicate that the game is over
     * @throws InterruptedException
     */
    abstract boolean playConnectivity() throws InterruptedException;

    /**
     * The main method of the class player for the Clique Game. Determines player type.
     * @return true to indicate that player may take another turn and false to indicate that the game is over
     * @throws InterruptedException
     */
    abstract boolean playClique() throws InterruptedException;

    /**
     * This is the block of code that the thread runs. Ends once a player has won or there are no edges left to the graph.
     */
    public void run() {
        try {
            boolean gameStatus;
            do {
                if(getGame().getGameName().equals("ConnectivityGame"))
                    gameStatus = playConnectivity();
                else
                    gameStatus = playClique();
            } while (gameStatus);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return "The player " +
                "named " + name +
                " has a score equal to " + score;
    }
}
package GameType;

import Graph.Board;
import ThreadType.Player;
import ThreadType.Timekeeper;

import java.util.ArrayList;

public abstract class Game {
    private Board board;
    private boolean[] playerWaitStatus = new boolean[10];
    //private int turn = 1;
    private String gameName;
    private boolean gameOver = false;
    private ArrayList<Player> players = new ArrayList<>();
    private Timekeeper timekeeper;
    private boolean alreadyDisplayed = false;

    public Game() {
        playerWaitStatus[0] = true;
        for (int i = 1; i < 10; i++)
            playerWaitStatus[i] = false;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    /**
     * This method adds a player and sets their game. Will use an alternative game for the laboratory bonus.
     *
     * @param player the player that is added to the game
     */
    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    /**
     * This method adds a timekeeper to the game and sets its game to this.
     *
     * @param timekeeper the timekeeper that is added to the game
     */
    public void addTimekeeper(Timekeeper timekeeper) {
        this.timekeeper = timekeeper;
        timekeeper.setGame(this);
    }

    public Timekeeper getTimekeeper() {
        return timekeeper;
    }

    public Board getBoard() {
        return board;
    }

    void setBoard(Board board) {
        this.board = board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * If a player has a spanning tree graph, he/she will be selected as a winner.
     *
     * @param player the player that has a spanning tree
     */
    public synchronized void setWinner(Player player) {
        player.setScore(100000);
        boolean ok = true;
        for (Player auxPlayer : players)
            if (auxPlayer.getVictory())
                ok = false;
        if (ok) {
            timekeeper.elapsedTime();
            player.setVictory();
            System.out.println(player + " and has won the game.");
        }
        gameOver = true;
    }

    /**
     * If a player has formed a triangle clique in their graph, he/she will lose the game.
     *
     * @param player the player that has a spanning tree
     */
    public synchronized void setLoser(Player player) {
        for (Player auxPlayer : players)
            if (!auxPlayer.equals(player)) auxPlayer.setVictory();
        if (!player.getVictory()) {
            player.setScore(-100000);
            timekeeper.elapsedTime();
            player.setVictory();
            System.out.println(player + " and has lost the game.");
        }
        gameOver = true;
    }

//    public synchronized boolean getStatus(Player player) throws InterruptedException {
//        int index = players.indexOf(player);
//        if (playerWaitStatus[index]) {
//            playerWaitStatus[index] = false;
//            if(index!=players.size()-1)
//            playerWaitStatus[index+1] = true;
//            else
//                playerWaitStatus[0] = true;
//            notifyAll();
//            return true;
//        }
//        Thread.sleep(1000);
//        notifyAll();
//        return false;
//    }

    /**
     * If there are no edges left in the graph, each player will have a score calculated according to their largest partial tree.
     *
     * @param player the player that will receive a score
     */
    public synchronized void emptyBoard(Player player) {
        player.setScore(player.getGraph().emptyBoardScoring());
        //System.out.println(player);
        gameOver = true;
        if (!alreadyDisplayed) {
            timekeeper.elapsedTime();
            System.out.println("No edges left");
            showWinner();
            alreadyDisplayed = true;
        }
    }

    /**
     * This method is called once the timekeeper's timer runs out. It will end the game and calculate the score for each player. Then it will choose a winner.
     */
    public void timeRanOut() {
        if (!gameOver) {
            System.out.println("Time has ran out. " + timekeeper.getMaxRunningTime() + " milliseconds have passed.");
            gameOver = true;
            for (Player player : players) {
                player.setScore(player.getGraph().emptyBoardScoring());
                //System.out.println(player);
            }
            showWinner();
        }
    }

    /**
     * This method displays the winner in case the time ran out or there are no edges left.
     */
    public void showWinner() {
        int maxScore = 0, index = -1;
        for (Player player : players) {
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
                index = players.indexOf(player);
            }
        }
        Player player = players.get(index);
        player.setVictory();
        System.out.println(player + " and has won the game.");
    }

    /**
     * This method creates a thread for every player and a thread for the timekeeper
     */
    public abstract void start();
}
package ThreadType;

import Graph.Board;
import Graph.Edge;

import java.util.Scanner;

public class ManualPlayer extends Player {

    private Scanner scanner = new Scanner(System.in);

    public ManualPlayer(String name) {
        super(name);
    }

    /**
     * This method first checks if the game is over.
     * An edge corresponding to the two integer scanned will be extracted and then added to the player's graph.
     * Lastly, a check will be performed to see whether or not the player has lost.
     * @return true if the game is not over, false otherwise
     */
    synchronized boolean playClique() throws InterruptedException {
//        while (!this.getGame().getStatus(this)) {
//            wait();
//        }
        if (this.getGame().isGameOver()) return false;
        Board board = this.getGame().getBoard();
        if (board.isEmpty()) {
            this.getGame().emptyBoard(this);
            return false;
        }
        System.out.println(board.getGraph().toString());
        boolean ok = true;
        while (ok) {
            System.out.println("Player " + getName() + " must input two integers that represent an available and valid (connected to the rest of the graph) edge");
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            if (n > m) {
                int aux = n;
                n = m;
                m = aux;
            }
            Edge edge = new Edge(n, m);
            if (board.extractClique(edge, this.getGraph(), this.getTurn())) {
                this.setTurn(this.getTurn()+1);
                System.out.println(this.getName() + " has extracted the edge formed by the nodes " + edge.getN() + " and " + edge.getM());
                this.getGraph().add(edge);
                if (this.getGraph().isClique()) {
                    this.getGame().setLoser(this);
                    return false;
                }
                ok = false;
            }
        }
        //notify();
        return true;
    }

    /**
     * This method first checks if the game is over. If it isn't, it then checks if the board is empty.
     * If the board isn't empty, an edge corresponding to the two integer scanned will be extracted and then added to the player's graph.
     * Lastly, a check will be performed to see whether or not the player has won.
     *
     * @return true if the game is not over, false otherwise
     */
    synchronized boolean playConnectivity() throws InterruptedException {
//        while (!this.getGame().getStatus(this)) {
//            wait();
//        }
        if (this.getGame().isGameOver()) return false;
        Board board = this.getGame().getBoard();
        if (board.isEmpty()) {
            this.getGame().emptyBoard(this);
            return false;
        }
        System.out.println(board.getGraph().toString());
        boolean ok = true;
        while (ok) {
            System.out.println("Player " + getName() + " must input two integers that represent an available edge");
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            if (n > m) {
                int aux = n;
                n = m;
                m = aux;
            }
            Edge edge = new Edge(n, m);
            if (board.extract(edge)) {
                System.out.println(this.getName() + " has extracted the edge formed by the nodes " + edge.getN() + " and " + edge.getM());
                this.getGraph().add(edge);
                if (this.getGraph().isSpanningTree(board)) {
                    this.getGame().setWinner(this);
                    return false;
                }
                ok = false;
            }
        }
        //notify();
        return true;
    }
}
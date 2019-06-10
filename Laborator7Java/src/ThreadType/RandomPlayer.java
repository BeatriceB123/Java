package ThreadType;

import Graph.Board;
import Graph.Edge;
import Graph.Graph;

public class RandomPlayer extends Player {

    public RandomPlayer(String name) {
        super(name);
    }

    /**
     * This method first checks if the game is over.
     * An edge corresponding to the two integer randomly selected will be extracted and then added to the player's graph.
     * Lastly, a check will be performed to see whether or not the player has lost.
     * @return true if the game is not over, false otherwise
     * @throws InterruptedException
     */
    synchronized boolean playClique() throws InterruptedException {
//        while (!this.getGame().getStatus(this)) {
//            wait();
//        }
        int n,m;
        if (this.getGame().isGameOver()) return false;
        Board board = this.getGame().getBoard();
        if (board.isEmpty()) {
            this.getGame().emptyBoard(this);
            return false;
        }
        Edge auxEdge = new Edge(-1,-1);
        Graph auxGraph = this.getGraph().clone();
        do{
            n = (int)(Math.random() * 6) + 1;
            do{
                m = (int)(Math.random() * 6) + 1;
            }while(n==m);
            if(n>m){
                int aux = n;
                n = m;
                m = aux;
            }
            auxEdge.setN(n);
            auxEdge.setM(m);
        }while(!board.extractClique(auxEdge, auxGraph, this.getTurn()));
        System.out.println(this.getName() + " has extracted the edge formed by the nodes " + auxEdge.getN() + " and " + auxEdge.getM());
        this.getGraph().add(auxEdge);
        if (this.getGraph().isClique()) {
            this.getGame().setLoser(this);
            return false;
        }
        Thread.sleep(THINKING_TIME);
        //notify();
        return true;
    }

    /**
     * This method first checks if the game is over. If it isn't, it then checks if the board is empty.
     * If the board isn't empty, an edge will be extracted and then added to the player's graph.
     * Lastly, a check will be performed to see whether or not the player has won.
     * @return true if the game is not over, false otherwise
     * @throws InterruptedException
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
        Edge auxEdge = board.extract();
        System.out.println(this.getName() + " has extracted the edge formed by the nodes " + auxEdge.getN() + " and " + auxEdge.getM());
        this.getGraph().add(auxEdge);
        if (this.getGraph().isSpanningTree(board)) {
            this.getGame().setWinner(this);
            return false;
        }
        Thread.sleep(THINKING_TIME);
        //notify();
        return true;
    }
}

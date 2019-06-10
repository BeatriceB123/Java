package ThreadType;

import Graph.*;

public class SmartPlayer extends Player {
    public SmartPlayer(String name) {
        super(name);
    }

    /**
     * This method first checks if the game is over.
     * An edge corresponding to the two integer smartly selected will be extracted and then added to the player's graph.
     * Lastly, a check will be performed to see whether or not the player has lost.
     *
     * @return true if the game is not over, false otherwise
     * @throws InterruptedException
     */
    synchronized boolean playClique() throws InterruptedException {
//        while (!this.getGame().getStatus(this)) {
//            wait();
//        }
        int n, m;
        if (this.getGame().isGameOver()) return false;
        Board board = this.getGame().getBoard();
        if (board.isEmpty()) {
            this.getGame().emptyBoard(this);
            return false;
        }
        Edge auxEdge = new Edge(-1, -1);

        boolean ok = true;
        int limit = 20;
        while (ok && limit != 0) {
            limit--;
            Graph auxGraph = this.getGraph().clone();
            do {
                n = (int) (Math.random() * 6) + 1;
                do {
                    m = (int) (Math.random() * 6) + 1;
                } while (n == m);
                if (n > m) {
                    int aux = n;
                    n = m;
                    m = aux;
                }
                auxEdge.setN(n);
                auxEdge.setM(m);
            } while (!board.extractClique(auxEdge, auxGraph, this.getTurn()));
            auxGraph.add(auxEdge);
            if (!auxGraph.isClique()) ok = false;
        }
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
     * The edge will not be chosen randomly. If the spanning tree can be finished with an edge, it will be picked.
     * Otherwise, an edge from the border of the graph will be chosen as that will lead to the most difficulties to the other players.
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
        Edge auxEdge = new Edge(-1, -1);

        //checks if the smart player is one edge away from victory, in which case it picks a winning edge
        boolean ok = true;
        for (int i = 0; i < board.getGraph().getEdgeList().size() - 1 && ok; i++) {
            Graph auxGraph = getGraph().clone();
            auxGraph.addEdge(board.getGraph().getEdgeList().get(i));
            if (auxGraph.isSpanningTree(board)) {
                auxEdge = board.getGraph().getEdgeList().get(i);
                ok = false;
            }
        }

        //checks if any player is one edge away from victory, in which case it picks that edge
        if (auxEdge.getM() == -1) {
            ok = true;
            for (int i = 0; i < getGame().getPlayers().size() - 1 && ok; i++) {
                for (int j = 0; j < board.getGraph().getEdgeList().size() - 1 && ok; j++) {
                    Graph auxGraph = getGame().getPlayers().get(i).getGraph().clone();
                    auxGraph.addEdge(board.getGraph().getEdgeList().get(j));
                    if (auxGraph.isSpanningTree(board)) {
                        auxEdge = board.getGraph().getEdgeList().get(j);
                        ok = false;
                    }
                }
            }
        }

        //picks an edge from the border
        if (auxEdge.getM() == -1) {
            ok = true;
            for (int i = 1; i < board.getNodeNr() && ok; i++) {
                Edge newEdge = new Edge(i, i + 1);
                Board auxBoard = board.clone();
                if (auxBoard.extractSilently(newEdge)) {
                    ok = false;
                    auxEdge = newEdge;
                }
            }
        }

        if (auxEdge.getM() == -1)
            auxEdge = board.extract();
        else
            board.extractSilently(auxEdge); //picks a random edge
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

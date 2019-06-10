package ThreadType;

import GameType.Game;

public class Timekeeper implements Runnable {

    private Game game;
    private long startTime;
    private long maxRunningTime = 25000;

    public long getMaxRunningTime() {
        return maxRunningTime;
    }

    public Timekeeper(){
        this.startTime = System.nanoTime();
    }

    /**
     * This method displays the running time at the time it is called.
     */
    public void elapsedTime(){
        long currentTime = System.nanoTime();
        System.out.println("Current running time: " + (currentTime-startTime)/1000000 + " milliseconds");
    }

    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * This method will end the game once the specified time runs out.
     * @throws InterruptedException
     */
    public synchronized void timekeeping() throws InterruptedException{
        Thread.sleep(maxRunningTime);
        game.timeRanOut();
    }

    /**
     * This is the block of code that the thread runs.
     */
    @Override
    public void run() {
        try {
            timekeeping();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

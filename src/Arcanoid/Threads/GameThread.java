package Arcanoid.Threads;

import Arcanoid.Arcanoid;

/**
 * Created by 13_da on 16.03.2018.
 */
public class GameThread extends Thread{
    private Arcanoid game;
    public GameThread(Arcanoid game){
        this.game = game;
    }
    public void run(){
        game.isRunning = true;
        game.isPaused = false;
        game.lastUpdate = System.nanoTime();  // ???
        while (game.isRunning){
            try {
                if (game.isPaused){
                    game.lastUpdate = System.nanoTime();
                    Thread.sleep(1);
                } else {
                    game.tick();
                    game.lastUpdate = System.nanoTime();
                    Thread.sleep((long)(1000.0/30));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

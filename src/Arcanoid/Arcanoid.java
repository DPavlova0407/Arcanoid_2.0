package Arcanoid;

import Arcanoid.Threads.GameThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Set;

/**
 * Created by 13_da on 16.03.2018.
 */
public class Arcanoid  extends JPanel{
    public int width, height;
    Player player;
    //Set<Ball> ballsList;
    Ball ball;
    public int ballsCount = 2;

    //private Image background;

    public boolean isRunning = false;
    public boolean isPaused = false;
    public long lastUpdate;

    private GameThread gameThread;

    public Arcanoid(int width, int height){
        this.width = width;
        this.height = height;

    //    ImageIcon imgIcon = new ImageIcon("Arcanoid_2.0/src/resource/genre_overview.png");
    //    background = imgIcon.getImage();

        // create new player, balls
        init_game();
        this.setFocusable(true);
        // !!! переписать на нажатие правой кнопкой мыши
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                player.position.x = e.getX()-getWidth()/2;
                repaint();
            }
        });
        // добавить нажатие на кнопки для остановки или паузы
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !isRunning)
                    run();
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    pause_Play();
                if (e.getKeyCode() == KeyEvent.VK_Q)
                    stopGame();
            }
        });
    }
    public void init_game(){
        player = new Player(this);
        // balls
        ballsCount = 2;
        ball = new Ball(this);
    }
    public void run(){
        if (gameThread != null)
            if (gameThread.isAlive())
                gameThread.interrupt();
        init_game();
        gameThread = new GameThread(this);
        gameThread.start();
    }
    public void pause_Play(){
        isPaused = !isPaused;
    }
    public void stopGame(){
        isRunning = false;
    }
    public void lostTheBall(){
        ballsCount--;
        if (ballsCount <= 0)
            gameOver(false);
        else // !!!
            ball.position = new Point(0, 0);
    }
    public void gameOver(boolean won){
        stopGame();
    }
    public void tick(){
        double deltaTime = (System.nanoTime()-lastUpdate)/1000000.0;
        ball.tick(deltaTime);
        repaint();
    }
    public void paint(Graphics g){
        super.paint(g);
        g.translate((getWidth()-width)/2, (getHeight()-height)/2);

        g.setColor(Color.cyan);
        g.fillRect(0, 0, width, height);
        /*int bgScale = 3;
        for (int x = 0; x < width; x+=background.getWidth(null)*bgScale) {
            for (int y = 0; y < height; y+=background.getHeight(null)*bgScale) {
                g.drawImage(background, x, y, background.getWidth(null)*bgScale, background.getHeight(null)*bgScale, null);
            }
        }*/
        g.translate(width/2, height/2);

        player.render(g);
        ball.render(g);

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String messege = "";
        if (!isRunning)
            messege = "Press Space to continue the game";
        else if (isPaused)
            messege = "Game paused";
        FontMetrics fm = g.getFontMetrics();
        g.drawString(messege, -fm.stringWidth(messege)/2, fm.getHeight()/2);
    }
}

package Arcanoid;

import java.awt.*;

/**
 * Created by 13_da on 16.03.2018.
 */
public class Ball {
    private Arcanoid game;
    private int radius = 10;
    Point position = new Point(0, 0);
    Point move = new Point(1, 1);
    float speed = 0.3f;

    public Ball(Arcanoid game){
        this.game = game;
    }
    // on every tick move the balls
    public void tick(double deltaTime){
        position.translate((int)(move.x*(speed*deltaTime)),
                (int)(move.y*(speed*deltaTime)));
        if (Math.abs(position.x) >= Math.abs(game.width/2))
            move.x = -move.x;
        if (position.y <= -game.height/2)
            move.y = -move.y;
        if (position.y >= game.height/2)  //???
            game.lostTheBall();

        Rectangle hitBox = new Rectangle(position.x-radius, position.y-radius,radius*2,radius*2);
        Point pv = game.player.bounceVector(hitBox);
        move.x *= pv.x;
        move.y *= pv.y;
    }
    public void render(Graphics g){
        g.setColor(Color.green);
        g.fillOval(position.x-radius, position.y-radius, radius*2, radius*2);
    }
}

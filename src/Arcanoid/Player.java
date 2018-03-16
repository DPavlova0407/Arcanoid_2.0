package Arcanoid;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.*;

/**
 * Created by 13_da on 16.03.2018.
 */
public class Player {
    private Arcanoid game;
    int standartPlayerWidth = 100; // 30 ?
    int standartPlayerHeight = 10; // 8
    Point position = new Point(0, 0);

    public Player(Arcanoid game){
        position = new Point(0, game.height/2 - standartPlayerHeight - 20);
    }

    // !!! расчет направления
    public Point bounceVector(Rectangle hitBox){
        Point p = new Point(1, 1);
        Rectangle hitBox_top = new Rectangle(position.x-standartPlayerWidth/2, position.y-standartPlayerHeight/2, standartPlayerWidth, standartPlayerHeight/3);
        Rectangle hitBox_bottom = new Rectangle(position.x-standartPlayerWidth/2, position.y+standartPlayerHeight/2-standartPlayerHeight/3, standartPlayerWidth, standartPlayerHeight/3);
        Rectangle hitBox_left = new Rectangle(position.x-standartPlayerWidth/2,position.y-standartPlayerHeight/2, standartPlayerWidth/10, standartPlayerHeight);
        Rectangle hitBox_right = new Rectangle(position.x+standartPlayerWidth/2-standartPlayerWidth/10, position.y-standartPlayerHeight/2, standartPlayerWidth/10, standartPlayerHeight);
        if (hitBox_top.intersects(hitBox) || hitBox_bottom.intersects(hitBox))
            p.y = -1;
        if (hitBox_right.intersects(hitBox) || hitBox_left.intersects(hitBox))
            p.x = -1;
        return p;
    }

    public void render(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(position.x-standartPlayerWidth/2, position.y-standartPlayerHeight/2, standartPlayerWidth, standartPlayerHeight);
    }
}

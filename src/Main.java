import Arcanoid.Arcanoid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 13_da on 16.03.2018.
 */
public class Main {
    public static void main(String[] args) {
        // create new window
        JFrame frame = new JFrame("Arcanoid 2.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);

        Arcanoid game = new Arcanoid(1280, 720);
        frame.add(game, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

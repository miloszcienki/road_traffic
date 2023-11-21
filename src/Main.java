import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import javax.imageio.ImageIO;


public class Main {
    static JFrame frame = new JFrame("Road Traffic");

    public static void main(String[] args) {

        frame.setSize(1280, 1024);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add( new Game());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}



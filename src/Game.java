import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JPanel {
    Background background;

    public Game() {
        background = new Background();
    }


    public void paintComponent (Graphics g){
            background.paint(g);


    }
}






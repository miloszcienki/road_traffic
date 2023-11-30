import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;

public class Car {
    public BufferedImage carpic;

    public Car(){
        //this.setFocusable(true);
        //this.requestFocusInWindow();
        //this.setSize(1280, 1024);
        //this.setLocation(0,0);
        //this.setBackground(new Color(0,0,0,0));
        try{
            carpic= ImageIO.read(getClass().getResourceAsStream("carpictest.png"));
        } catch(IOException e){e.printStackTrace();}
    }


    public void paintcar(Graphics2D g2d) {

        g2d.drawImage(carpic, 512, 512,256,256, null);
        //g2d.dispose();
        System.out.println("draw car");
    }

}

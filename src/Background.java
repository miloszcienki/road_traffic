import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Background extends JPanel {

    public BufferedImage backgroundpic;

    public Background(){
        this.setFocusable(true);
        this.requestFocusInWindow();
        try{
            backgroundpic= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("backpic.png")));
        } catch(IOException e){e.printStackTrace();}


    }

    public void paint(Graphics g){
        //g.setColor(Color.BLACK);
        //g.fillRect(0,0,1280,1024);
        super.paint(g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.drawImage(backgroundpic,0,0,null);

        g2d.dispose();
    }
}

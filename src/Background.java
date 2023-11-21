import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public  class Background extends JPanel {

    public BufferedImage backgroundpic;


    public Background(){
        this.setFocusable(true);
        this.requestFocusInWindow();
        try{
            backgroundpic= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("backpic.png")));
        } catch(IOException e){e.printStackTrace();}


    }

    public void paint(Graphics g){
        super.paintComponent (g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.setColor(new Color(0,153,51));
        g2d.fillRect(0,0,200,1024); // lewa krawędź 200px
        g2d.fillRect(1080,0,200,1024);// prawa krawędź 200px
        g2d.setColor(Color.BLACK);
        g2d.fillRect(200,0,880,1024);// srodek 880 px
        g2d.setColor(Color.WHITE);
        for(int i=510;i<880;i+=310) {//pasy
            for(int j=0;j<=1024;j+=256)
            g2d.fillRect(i, j, 20 , 226);
        }
        g2d.dispose();

    }

}



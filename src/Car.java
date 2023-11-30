import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;

public class Car {
    public BufferedImage carpic;
    public int x=512,y=512,speed=4; //pierwotna pozycja samochodu

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

        g2d.drawImage(carpic, x, y,256,256, null);
        //g2d.dispose();

    }

    public void updatecar(KeyHandler keyHandler){

        if(keyHandler.upPressed== true){
            if(y>0)y-=speed;
        }
        if(keyHandler.downPressed==true){
            if(y<768)y+=speed;
        }
        if(keyHandler.leftPressed==true){
            if(x>128)x-=speed;
        }
        if(keyHandler.rightPressed==true){
            if(x<894)x+=speed;
        }
    }

}

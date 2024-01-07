import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Klasa formująca pojazd gracza
 * @author Miłosz Cienki
 */

public class Car {
    public BufferedImage carpic;
    public int x=582,y=512,speed=4; //pierwotna pozycja samochodu

    public Car(){


        try{
            carpic= ImageIO.read(getClass().getResourceAsStream("cartopview.png"));
        } catch(IOException e){e.printStackTrace();}
    }


    public void paintcar(Graphics2D g2d) {

        g2d.drawImage(carpic, x, y,100,256, null);


    }

    public void updatecar(KeyHandler keyHandler){

        if(keyHandler.upPressed){
            if(y>0)y-=speed;
        }
        if(keyHandler.downPressed){
            if(y<728)y+=speed;
        }
        if(keyHandler.leftPressed){
            if(x>0)x-=speed;
        }
        if(keyHandler.rightPressed){
            if(x<1163)x+=speed;
        }
    }

    public void carDeafult(){
        x=582;
        y=512;
    }


}

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class CarBot {
    public BufferedImage carpic;
    public int speed=4; //pierwotna pozycja samochodu
    private int number_car=0;
    public int[] x={232,516,816};
    public int[] y={-256,-256,-256};
    private Random rand = new Random();

    public CarBot(){

        //this.setFocusable(true);
        //this.requestFocusInWindow();
        //this.setSize(1280, 1024);
        //this.setLocation(0,0);
        //this.setBackground(new Color(0,0,0,0));
        try{
            carpic= ImageIO.read(getClass().getResourceAsStream("cartopviewbot.png"));
        } catch(IOException e){e.printStackTrace();}
    }


    public void paintbot(Graphics2D g2d) {
        for(int i=0;i<=2;i++) g2d.drawImage(carpic, x[i], y[i],256,256, null);
        //g2d.dispose();

    }

    public void updatebot(){
        if(y[number_car]<1300) y[number_car]+=2;
        else {
            y[number_car] = -256;
            number_car = rand.nextInt(3);
        }

    }

    public int getX() {
        return x[number_car];
    }
    public int getY() {
        return y[number_car];
    }
}

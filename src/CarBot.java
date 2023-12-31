import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Klasa formująca boty (pojazdy nadjeżdzające)
 * @author Miłosz Cienki
 */
public class CarBot {
    public BufferedImage carpic;
    public int speed=4; //pierwotna pozycja samochodu
    private int number_car=0;
    public int[] x={302,586,886};
    public int[] y={-350,-350,-350};
    private Random rand = new Random();

    public CarBot(){

        try{
            carpic= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("cartopviewbot.png")));
        } catch(IOException e){e.printStackTrace();}
    }


    public void paintbot(Graphics2D g2d) {
        for(int i=0;i<=2;i++) g2d.drawImage(carpic, x[i], y[i],130,334, null);


    }

    public void updatebot(){
        if(y[number_car]<1300) y[number_car]+=speed;
        else {
            y[number_car] = -350;
            number_car = rand.nextInt(3);
        }

    }

    public int getX() {
        return x[number_car];
    }
    public int getY() {
        return y[number_car];
    }

    public void carBotDeafult(){
        x[0]=302;
        x[1]=586;
        x[2]=886;
        Arrays.fill(y, -350);
    }
}

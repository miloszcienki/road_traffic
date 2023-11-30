import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DrawAndUpdate extends JPanel {
    Background background;
    Car car;
    CarBot carBot;
    int number;
    long currentTime;
    long lastTime;
    Random rand = new Random();

    public DrawAndUpdate(){

        this.requestFocusInWindow();
        this.setSize(1280, 1024);
        this.setLocation(0,0);
        this.setDoubleBuffered(true);

        this.setFocusable(true);
        //this.setBackground(new Color(0,0,0,0));
        background = new Background();
        car = new Car();
        carBot = new CarBot();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        background.paintback(g2d);
        car.paintcar(g2d);
        carBot.paintbot(g2d);
        g2d.dispose();
        //System.out.println("testpaint");
    }

    public void update_game(KeyHandler keyHandler)

    {
        currentTime=System.currentTimeMillis();
        if(currentTime-lastTime>3500){
            lastTime=System.currentTimeMillis();
             number = rand.nextInt(3);
        }
        background.move_road_lanes();
        car.updatecar(keyHandler);
        carBot.updatebot(number);
        //System.out.println("update");
    }
}

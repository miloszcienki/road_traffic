import javax.swing.*;
import java.awt.*;


public class DrawAndUpdate extends JPanel {
    Background background;
    Car car;
    CarBot carBot;
    CollisionDetector collisionDetector;
    int numberLife =2;





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
        collisionDetector = new CollisionDetector();
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

        background.move_road_lanes();
        car.updatecar(keyHandler);
        carBot.updatebot();
        collisionDetector.updateCarPosition(car.x, car.y,256,256);
        collisionDetector.updateBotPosition(carBot.getX(), carBot.getY(),256,256);
        if(collisionDetector.checkCollision()){
            background.life[numberLife]=false;
            numberLife--;
        }

        //System.out.println("update");
    }
}

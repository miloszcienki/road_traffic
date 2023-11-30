import javax.swing.*;
import java.awt.*;

public class DrawAndUpdate extends JPanel {
    Background background;
    Car car;
    public DrawAndUpdate(){
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setSize(1280, 1024);
        this.setLocation(0,0);
        //this.setBackground(new Color(0,0,0,0));
        background = new Background();
        car = new Car();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        background.paintback(g2d);
        car.paintcar(g2d);
        g2d.dispose();
        //System.out.println("testpaint");
    }

    public void update_game()

    {
        background.move_road_lanes();
        //System.out.println("update");
    }
}

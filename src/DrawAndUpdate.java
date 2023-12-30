import javax.swing.*;
import java.awt.*;


public class DrawAndUpdate extends JPanel {
    Background background;
    Car car;
    CarBot carBot;
    Quiz quiz;
    CollisionDetector collisionDetector;
    int numberLife =2;
    MouseHandler mouseHandler = new MouseHandler();
    boolean flag=true;//czy losowanć





    public DrawAndUpdate(){

        this.requestFocusInWindow();
        this.setSize(1280, 1024);
        this.setLocation(0,0);
        this.setDoubleBuffered(true);

        this.setFocusable(true);
        this.addMouseMotionListener(mouseHandler);
        //this.setBackground(new Color(0,0,0,0));
        background = new Background();
        car = new Car();
        carBot = new CarBot();
        collisionDetector = new CollisionDetector();
        quiz = new Quiz();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        background.paintback(g2d);
        car.paintcar(g2d);
        carBot.paintbot(g2d);
        if(!background.lifes[0]) quiz.paintQuiz(g2d);
        g2d.dispose();
    }

    public void update_game(KeyHandler keyHandler)

    {

        if(background.lifes[0]) {
            background.move_road_lanes();
            car.updatecar(keyHandler);
            carBot.updatebot();
            collisionDetector.updateCarPosition(car.x, car.y, 256, 256);
            collisionDetector.updateBotPosition(carBot.getX(), carBot.getY(), 256, 256);
            if (collisionDetector.checkCollision()) {
                background.lifes[numberLife] = false;
                numberLife--;
            }

        }else {
            quiz.updateQuiz(mouseHandler);
            quiz.drawNewQuestion();
            quiz.nextquest(flag);
            flag=false;
        }

        //System.out.println("update");
    }
}

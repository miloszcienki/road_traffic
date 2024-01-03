import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class DrawAndUpdate extends JPanel {
    Background background;
    Car car;
    CarBot carBot;
    Quiz quiz;
    CollisionDetector collisionDetector;
    Pause pause;
    int numberLife =2;
    MouseMotionHandler mouseMotionHandler = new MouseMotionHandler();
    MouseHandler mouseHandler = new MouseHandler();
    boolean flag=true;//czy losować
    boolean pauseFlag=false;





    public DrawAndUpdate(){

        this.requestFocusInWindow();
        this.setSize(1280, 1024);
        this.setLocation(0,0);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addMouseMotionListener(mouseMotionHandler);
        this.addMouseListener(mouseHandler);
        //this.setBackground(new Color(0,0,0,0));
        background = new Background();
        car = new Car();
        carBot = new CarBot();
        collisionDetector = new CollisionDetector();
        quiz = new Quiz();
        pause = new Pause();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if(background.lifes[0] && !pauseFlag) {
            background.paintback(g2d);
            car.paintcar(g2d);
            carBot.paintbot(g2d);
        }
        else if (!background.lifes[0] && !pauseFlag) quiz.paintQuiz(g2d);
        else pause.paintPause(g2d);
        g2d.dispose();
    }

    public void update_game(KeyHandler keyHandler)

    {

        if(background.lifes[0] && !keyHandler.escapePressed) {
            pauseFlag=false;
            background.move_road_lanes();
            car.updatecar(keyHandler);
            carBot.updatebot();
            collisionDetector.updateCarPosition(car.x, car.y, 100, 256);
            collisionDetector.updateBotPosition(carBot.getX(), carBot.getY(), 100, 256);
            if (collisionDetector.checkCollision()) {
                background.lifes[numberLife] = false;
                if(numberLife!=0)numberLife--;

            }

        }else if (!background.lifes[0] && !keyHandler.escapePressed) {
            pauseFlag=false;
            quiz.updateQuiz(mouseMotionHandler);
            quiz.drawNewQuestion();
            quiz.nextquest(flag);
            flag=false;
            if(quiz.checkAnswer(mouseHandler)){
                background.lifes[0]=true;
                mouseHandler.position=-1;
                flag=true;
                car.carDeafult();
                carBot.carBotDeafult();

            }
            else{

                Arrays.fill(background.lifes, true);
                mouseHandler.position=-1;
                flag=true;
                car.carDeafult();
                carBot.carBotDeafult();
            }
        }
        else pauseFlag=true;

        //System.out.println("update");
    }
}

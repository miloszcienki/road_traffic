import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;


public class DrawAndUpdate extends JPanel {
    Background background;
    Car car;
    CarBot carBot;
    Quiz quiz;
    CollisionDetector collisionDetector;
    Pause pause;
    Menu menu;
    int numberLife =2;
    AudioInputStream audioStream;
    Clip clip;
    MouseMotionHandler mouseMotionHandler = new MouseMotionHandler();
    MouseHandler mouseHandler = new MouseHandler();
    boolean flag=true;//czy losować
    boolean pauseFlag=false;
    boolean menuFlag=false;
    boolean drawGoodAnswer=false;





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
        menu = new Menu();
        try {
            audioStream = AudioSystem.getAudioInputStream(new File("res/engine_3.wav"));

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            //clip.loop(Clip.LOOP_CONTINUOUSLY);

        }catch (Exception e) {

        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if(background.lifes[0] && !pauseFlag && !menuFlag && !menu.howPlay && !drawGoodAnswer) {
            background.paintback(g2d);
            car.paintcar(g2d);
            carBot.paintbot(g2d);
        }
        else if (!background.lifes[0] && !pauseFlag && !menuFlag && !menu.howPlay && !drawGoodAnswer) quiz.paintQuiz(g2d);
        else if(pauseFlag) pause.paintPause(g2d);
        else if(menuFlag) menu.paintMenu(g2d);
        else if(menu.howPlay)menu.paintHowToPlay(g2d);
        else if(drawGoodAnswer)quiz.paintAnswer(g2d);

        g2d.dispose();
    }

    public void update_game(KeyHandler keyHandler)

    {

        pauseFlag=keyHandler.pPressed;
        menuFlag=keyHandler.escapePressed;
        //mouseHandler.wchichWindow=0;
        //mouseMotionHandler.wchichWindow=0;
        if(menuFlag){
            drawGoodAnswer=false;
            menu.howPlay=false;
        }
        if(background.lifes[0] && !pauseFlag && !menuFlag && !menu.howPlay && !drawGoodAnswer) {

           // clip.loop(Clip.LOOP_CONTINUOUSLY);
            background.move_road_lanes();
            car.updatecar(keyHandler);
            carBot.updatebot();
            collisionDetector.updateCarPosition(car.x, car.y, 100, 256);
            collisionDetector.updateBotPosition(carBot.getX(), carBot.getY(), 100, 256);
            if (collisionDetector.checkCollision()) {
                background.lifes[numberLife] = false;
                if(numberLife!=0)numberLife--;

            }

        }else if (!background.lifes[0] && !pauseFlag && !menuFlag && !menu.howPlay && !drawGoodAnswer) {
            clip.stop();
            quiz.updateQuiz(mouseMotionHandler);
            quiz.nextquest(flag);
            quiz.drawNewQuestion();
            flag=false;
            mouseHandler.wchichWindow=1;
            mouseMotionHandler.wchichWindow=1;
            if(quiz.checkAnswer(mouseHandler)){// dobra odpowiedź na quiz
                background.lifes[0]=true;
                flag=true;
                car.carDeafult();
                carBot.carBotDeafult();
                mouseHandler.position=-1;

            }
            else if(mouseHandler.position!=-1 && !quiz.checkAnswer(mouseHandler)){// zła odpowiedź na quiz

                Arrays.fill(background.lifes, true);
                mouseHandler.position=-1;
                flag=true;
                car.carDeafult();
                carBot.carBotDeafult();
                background.score=0;
                numberLife =2;
                drawGoodAnswer=true;
            }
        }
        else if(menuFlag){
            clip.stop();
            mouseHandler.wchichWindow=2;
            mouseMotionHandler.wchichWindow=2;
            int choice =menu.checkoption(mouseHandler);
            menu.hoverMenu(mouseMotionHandler);
            if(choice==0){

                keyHandler.escapePressed=false;
                menuFlag=false;
                mouseHandler.position=-1;

            }

            else if(choice==1){
                Arrays.fill(background.lifes, true);
                mouseHandler.position=-1;
                flag=true;
                car.carDeafult();
                carBot.carBotDeafult();
                background.score=0;
                numberLife =2;
                keyHandler.escapePressed=false;
                menuFlag=false;
            }
            else if(choice==2){
                menu.howPlay=true;
                mouseHandler.position=-1;
                keyHandler.escapePressed=false;
                menuFlag=false;
            }
            else if(choice==3)System.exit(0);

        }



    }
}

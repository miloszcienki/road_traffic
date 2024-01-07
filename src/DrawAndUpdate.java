import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

/**
 * Klasa odpowiadająca za wyświetlanie gry oraz odświeżanie mechaniki
 * @author Miłosz Cienki
 */

public class DrawAndUpdate extends JPanel {
    Background background;
    Car car;
    CarBot carBot;
    Quiz quiz;
    CollisionDetector collisionDetector;
    Pause pause;
    Menu menu;
    int numberLife =2;//numer indeks tablicy z życiamy, które aktualnie należy odjąć
    AudioInputStream audioStream;
    Clip clip;
    MouseMotionHandler mouseMotionHandler = new MouseMotionHandler();
    MouseHandler mouseHandler = new MouseHandler();
    boolean flag=true;//flaga czy losować pytanie
    boolean pauseFlag=false;//flaga czy wciśnieto pauze
    boolean menuFlag=false;// flaga czy włączono menu
    boolean drawGoodAnswer=false;// flaga aby wyświetlić prawidłową odpowiedź po wybraniu złej
    boolean infoGoodAnswer=false;// flaga aby wyświetlić informacje, że odpowiedź którą wybraliśmy jest prawidłowa
    int speedup=10;//wartość przy której prędkość gry jest zwiększana





    public DrawAndUpdate(){

        this.requestFocusInWindow();// Ustawienie fokusu na oknie, aby mogło reagować na zdarzenia klawiatury

        // Ustawienie rozmiarów okna
        this.setSize(1280, 1024);
        this.setLocation(0, 0);
        this.setDoubleBuffered(true); // Włączenie podwójnego buforowania dla płynniejszego rysowania

        // Ustawienie, dzięki któremu okno jest zdolne do obsługi zdarzeń związanych z klawiaturą
        this.setFocusable(true);

        // Dodanie obsługi zdarzeń związanych z ruchem myszy i kliknięciami myszy
        this.addMouseMotionListener(mouseMotionHandler);
        this.addMouseListener(mouseHandler);
        background = new Background();
        car = new Car();
        carBot = new CarBot();
        collisionDetector = new CollisionDetector();
        quiz = new Quiz();
        pause = new Pause();
        menu = new Menu();
        try {//Dodanie zapętlonego dźwięku samochodu
            audioStream = AudioSystem.getAudioInputStream(new File("res/engine_3.wav"));

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        }catch (Exception e) {

        }
    }

    /**
     * Główna funkcją odpowiadająca za wyświetlanie całej gry
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if(background.lifes[0] && !pauseFlag && !menuFlag && !menu.howPlay && !drawGoodAnswer && !infoGoodAnswer) {
            background.paintback(g2d);
            car.paintcar(g2d);
            carBot.paintbot(g2d);
        }
        else if (!background.lifes[0] && !pauseFlag && !menuFlag && !menu.howPlay && !drawGoodAnswer && !infoGoodAnswer) quiz.paintQuiz(g2d);
        else if(pauseFlag) pause.paintPause(g2d);
        else if(menuFlag) menu.paintMenu(g2d);
        else if(menu.howPlay)menu.paintHowToPlay(g2d);
        else if(drawGoodAnswer)quiz.paintAnswer(g2d);
        else if(infoGoodAnswer)quiz.paintInfoGoodAnswer(g2d);
        g2d.dispose();
    }

    /**
     * Główna funkcją odpowiadająca za odświeżanie całej gry oraz mechanikę
     * @param keyHandler przekazuję informację czy wciśnięto przycisk
     */
    public void update_game(KeyHandler keyHandler)

    {
        pauseFlag=keyHandler.pPressed;
        menuFlag=keyHandler.escapePressed;
        if(menuFlag){//włączenie menu
            drawGoodAnswer=false;
            menu.howPlay=false;
        }
        if(background.lifes[0] && !pauseFlag && !menuFlag && !menu.howPlay && !drawGoodAnswer && !infoGoodAnswer) {// aktualizowanie gry

           clip.loop(Clip.LOOP_CONTINUOUSLY);
            background.move_road_lanes();
            car.updatecar(keyHandler);
            carBot.updatebot();
            collisionDetector.updateCarPosition(car.x, car.y, 100, 256);
            collisionDetector.updateBotPosition(carBot.getX(), carBot.getY(), 100, 256);
            if(background.score>=speedup){
                car.speed++;
                carBot.speed++;
                speedup*=10;
                background.multiplier*=2;
                background.speed++;
            }
            if (collisionDetector.checkCollision()) {
                background.lifes[numberLife] = false;
                if(numberLife!=0)numberLife--;

            }

        }else if (!background.lifes[0] && !pauseFlag && !menuFlag && !menu.howPlay && !drawGoodAnswer && !infoGoodAnswer) {// włączenie quizu po straceniu wszystkich żyć
            clip.stop();
            quiz.updateQuiz(mouseMotionHandler);
            quiz.nextquest(flag);
            quiz.drawNewQuestion();
            flag=false;
            mouseHandler.wchichWindow=1;
            mouseMotionHandler.wchichWindow=1;
            if(quiz.checkAnswer(mouseHandler)){// udzielenie dobrej odpowiedzi na quiz
                background.lifes[0]=true;
                flag=true;
                car.carDeafult();
                carBot.carBotDeafult();
                mouseHandler.position=-1;
                infoGoodAnswer=true;

            }
            else if(mouseHandler.position!=-1 && !quiz.checkAnswer(mouseHandler)){//  udzielenie niepoprawnej odpowiedzi na quiz

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
        else if(menuFlag){//Włączenie menu
            clip.stop();
            mouseHandler.wchichWindow=2;
            mouseMotionHandler.wchichWindow=2;
            int choice =menu.checkoption(mouseHandler);
            menu.hoverMenu(mouseMotionHandler);
            if(choice==0){//Wznów grę

                keyHandler.escapePressed=false;
                menuFlag=false;
                mouseHandler.position=-1;

            }

            else if(choice==1){//Zacznij grę od nowa
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
            else if(choice==2){//Wyświetl informacje jak grać
                menu.howPlay=true;
                mouseHandler.position=-1;
                keyHandler.escapePressed=false;
                menuFlag=false;
            }
            else if(choice==3)System.exit(0);//Wyjdź gry

        }
        else if(drawGoodAnswer && keyHandler.enterPressed) drawGoodAnswer = false;//Wyjście z wyświetlania poprawnej odpowiedź po wybraniu błędnej
        else if(infoGoodAnswer && keyHandler.enterPressed) infoGoodAnswer = false;//Wyjście z wyświetlania komnukiatu, że udzielona odpowiedź jest poprawna
        else if(pauseFlag)clip.stop();//jeśli pauza jest włączona zatrzymaj dźwiek







    }
}

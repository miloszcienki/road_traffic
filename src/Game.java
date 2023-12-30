import javax.swing.*;
import java.awt.*;

public class Game extends JFrame implements Runnable {

    DrawAndUpdate drawAndUpdate;
    KeyHandler keyHandler = new KeyHandler();


    public Game() {
        this.setSize(1280, 1024);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);

        this.addKeyListener(keyHandler);

        drawAndUpdate = new DrawAndUpdate();
        this.add(drawAndUpdate);

    }

    public void run(){

        double drawInterval = 1000000000/144;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime = 0;
        long timer = 0;
        int drawCount = 0;



        while (true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta>-1){
                //update();//update klawiszy
                drawAndUpdate.update_game(keyHandler);
                drawAndUpdate.repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

}













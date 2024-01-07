import javax.swing.*;

/**
 * Klasa formująca Główne okno gry, dziedzicząca po klasie JFrame, implementująca interfejs Runnable
 * @author Miłosz Cienki
 */

public class Game extends JFrame implements Runnable {

    DrawAndUpdate drawAndUpdate;
    KeyHandler keyHandler = new KeyHandler();


    public Game() {
        /**Ustawienie rozmiarów okna*/
        this.setSize(1280, 1024);
        /**Zablokowanie zmiany rozmiarów okna*/
        this.setResizable(false);
        /**Ustawienie domyślnej operacji zamknięcia dla okna*/
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /**Wyłączenie "manager layour"- komponenty są rozmieszczane ręcznie*/
        this.setLayout(null);
        /**Ustawienie okna jako widoczne*/
        this.setVisible(true);
        /**Wyśrodkowanie okna aplikacji na ekranie*/
        this.setLocationRelativeTo(null);
        /**Ustawienie, dzięki któremy okno jest zdolne do obsługi zdarzeń związanych z klawiaturą*/
        this.setFocusable(true);
        /**Dodanie obiektu obsługującego zdarzenia klawiatury*/
        this.addKeyListener(keyHandler);
        /**Utworzenie nowego obiektu klasy drawAndUpdate*/
        drawAndUpdate = new DrawAndUpdate();
        /**Dodanie utworzonego obiektu do okna*/
        this.add(drawAndUpdate);

    }

    public void run(){

        double drawInterval = 1000000000/144;//interwał czasowy
        double delta = 0;
        long lastTime = System.nanoTime();// ostatni czas
        long currentTime = 0;//aktualny czas
        long timer = 0;
        int drawCount = 0;



        while (true) {
            currentTime = System.nanoTime();//pobieranie aktualnego czasu
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta>-1){
                //wywołanie funkcji rysowania oraz odświeżania gry 144 razy na sekundę
                drawAndUpdate.update_game(keyHandler);
                drawAndUpdate.repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                //Wypisywanie aktualnych klatek na sekundę
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

}













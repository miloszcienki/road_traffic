import javax.swing.*;

/**
 * Klasa formująca Główne okno gry, dziedzicząca po klasie JFrame, implementująca interfejs Runnable
 * @author Miłosz Cienki
 */

public class Game extends JFrame implements Runnable {

    DrawAndUpdate drawAndUpdate;
    KeyHandler keyHandler = new KeyHandler();


    public Game() {
        //Ustawienie rozmiarów okna
        this.setSize(1280, 1024);
        //Zablokowanie zmiany rozmiarów okna
        this.setResizable(false);
        //Ustawienie domyślnej operacji zamknięcia dla okna
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Wyłączenie "manager layour"- komponenty są rozmieszczane ręcznie
        this.setLayout(null);
        //Ustawienie okna jako widoczne
        this.setVisible(true);
        //Wyśrodkowanie okna aplikacji na ekranie
        this.setLocationRelativeTo(null);
        //Ustawienie, dzięki któremy okno jest zdolne do obsługi zdarzeń związanych z klawiaturą
        this.setFocusable(true);
        //Dodanie obiektu obsługującego zdarzenia klawiatury
        this.addKeyListener(keyHandler);
        //Utworzenie nowego obiektu klasy drawAndUpdate
        drawAndUpdate = new DrawAndUpdate();
        //Dodanie utworzonego obiektu do okna
        this.add(drawAndUpdate);

    }

    /**
     * Główna pętla gry
     */
    public void run(){

        double drawInterval = 1000000000/144;// Ustal interwał czasowy na 144 klatki na sekundę
        // Inicjalizacja zmiennych czasowych
        double delta = 0;
        long lastTime = System.nanoTime();// Czas ostatniego kroku pętli
        long currentTime = 0;//aktualny czas
        long timer = 0; // Licznik czasu dla FPS
        int drawCount = 0;  // Licznik klatek na sekundę


        // Główna pętla gry
        while (true) {
            currentTime = System.nanoTime();//pobieranie aktualnego czasu
            // Oblicz deltę czasową - czas od ostatniego kroku pętli podzielony przez interwał rysowania
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); // Zaktualizuj licznik czasu
            lastTime = currentTime; // Zapisz czas ostatniego kroku pętli
            // Sprawdź, czy czas na narysowanie klatki
            if(delta>-1){
                //wywołanie funkcji rysowania oraz odświeżania gry 144 razy na sekundę
                drawAndUpdate.update_game(keyHandler);
                drawAndUpdate.repaint();
                delta--; // Zmniejsz deltę czasową
                drawCount++; // Zwiększ licznik klatek na sekundę
            }
            // Sprawdź, czy minął jeden sekundowy cykl
            if(timer >= 1000000000){
                // Wypisz aktualne klatki na sekundę
                System.out.println("FPS:" + drawCount);
                drawCount = 0;  // Zresetuj licznik klatek
                timer = 0; // Zresetuj licznik czas
            }
        }
    }

}













import java.awt.*;

/**
 * Klasa odpowiadająca za wyświetlanie Pauzy w grze
 * @author Miłosz Cienki
 */
public class Pause {
    /**
     * Metoda odpowiedzialna za wyświetlenie informacji o pauzie po jej wciśnięciu
     * @param g2d obiekt klasy Graphics2D odpowiadającej za rysywanie
     */
    public void paintPause (Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 1280, 1024); // czarne tło
        g2d.setColor(Color.WHITE);
        Font czcionka = new Font("Courier New", Font.PLAIN, 100);
        g2d.setFont(czcionka);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        g2d.drawString("Pauza",640-fontMetrics.stringWidth("Pauza")/2,300);
        czcionka = new Font("Courier New", Font.PLAIN, 32);
        g2d.setFont(czcionka);
        fontMetrics = g2d.getFontMetrics();
        g2d.drawString("Aby wyjść z pauzy wciśnij 'P'",640-fontMetrics.stringWidth("Aby wyjść z pauzy wciśnij 'P'")/2,500);
    }
}

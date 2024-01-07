import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Klasa przechwytująca kliknięć myszki, impementująca MouseListener
 * @author Miłosz Cienki
 */
public class MouseHandler implements MouseListener {
    int x,y;//pozycja myszki
    int[] high={0,0,0,0,0,0,0,0};// tablica wysokości Stringów indeksy 0,1,2,3 odpowiedzi w quize ; indeksy 4,5,6,7 wybory w menu
    int[] width={0,0,0,0,0,0,0,0};// tablica szerokości Stringów indeksy 0,1,2,3 odpowiedzi w quize ; indeksy 4,5,6,7 wybory w menu
    int wchichWindow=0;// jeśli zmienna jest równa 1 odpalony jest quzi, jeśli 2 menu
    int position=-1;// jaka kordynaty pokrywają się z myszką

    /**
     * Obsługa zdarzenia gdy wciśnieto przycisk myszki, sprawdzenie jej pozycji i porównanie z rządanymi jeśli się pokrywają przypisanie odpowiedniej wartości do zmiennej position
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //pobranie pozycji myszki po przesunięciu
        x=e.getX();
        y=e.getY();


        if (wchichWindow == 1) {
            if (x >= 640 - width[0] / 2 && x <= 640 + width[0] / 2 && y >= 325 && y <= 325 + high[0]) position = 0;
            else if (x >= 640 - width[1] / 2 && x <= 640 + width[1] / 2 && y >= 395 && y <= 395 + high[1]) position = 1;
            else if (x >= 640 - width[2] / 2 && x <= 640 + width[2] / 2 && y >= 465 && y <= 465 + high[2]) position = 2;
            else if (x >= 640 - width[3] / 2 && x <= 640 + width[3] / 2 && y >= 535 && y <= 535 + high[3]) position = 3;
            else position = -1;
        }
        else if(wchichWindow == 2){
            if (x >= 640 - width[4] / 2 && x <= 640 + width[4] / 2 && y >= 345 && y <= 345+high[4]) position = 4;
            else if (x >= 640 - width[5] / 2 && x <= 640 + width[5] / 2 && y >=450  && y <=450+high[5]) position = 5;
            else if (x >= 640 - width[6] / 2 && x <= 640 + width[6] / 2 && y >=550  && y <=550+high[6]) position = 6;
            else if (x >= 640 - width[7] / 2 && x <= 640 + width[7] / 2 && y >=650  && y <= 650+high[7]) position = 7;
            else position = -1;

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

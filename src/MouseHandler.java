import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    int x,y;
    int[] high={0,0,0,0,0,0,0,0};// indeksy 0,1,2,3 odpowiedzi w quize ; indeksy 4,5,6,7 wybory w menu
    int[] width={0,0,0,0,0,0,0,0};
    int wchichWindow=0;
    int position=-1;
    @Override
    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();

        //System.out.println("X: " +x+" Y: "+y);
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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    int x,y;
    int position=-1;
    @Override
    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        //System.out.println("X: " +x+" Y: "+y);
        if(x>=400 && x<=850 && y>=325 && y<=350)position =1;
        else if(x>=400 && x<=850 && y>=395 && y<=420)position =2;
        else if(x>=400 && x<=870 && y>=465 && y<=490)position =3;
        else if(x>=400 && x<=820 && y>=535 && y<=560)position =4;
        else position = -1;
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

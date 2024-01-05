import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    int x,y;
    int[] high={0,0,0,0};
    int[] width={0,0,0,0};

    int position=-1;
    @Override
    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        //System.out.println("X: " +x+" Y: "+y);
        if(x>= 640-width[0]/2 && x<= 640+width[0]/2  && y>=325 && y<=325+high[0])position =1;
        else if(x>= 640-width[1]/2 && x<=  640+width[1]/2 && y>=395 && y<=395+high[1])position =2;
        else if(x>= 640-width[2]/2 && x<=  640+width[2]/2 && y>=465 && y<=465+high[2])position =3;
        else if(x>= 640-width[3]/2 && x<=  640+width[3]/2 && y>=535 && y<=535+high[3])position =4;
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

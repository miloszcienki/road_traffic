
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {
    int x,y;
    int position=-1;
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        //System.out.println("X: " +x+" Y: "+y);
        if(x>=400 && x<=850 && y>=325 && y<=350)position =0;
        else if(x>=400 && x<=850 && y>=395 && y<=420)position =1;
        else if(x>=400 && x<=870 && y>=465 && y<=490)position =2;
        else if(x>=400 && x<=820 && y>=535 && y<=560)position =3;
        else position = -1;
        //System.out.println(position);
        /*x od 400 do 850 850 870 820
        y od 350 385*/
    }
}

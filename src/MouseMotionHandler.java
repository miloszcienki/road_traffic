
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {
    int x,y;
    int position=-1;
    int[] high={0,0,0,0,0,0,0,0};// indeksy 0,1,2,3 odpowiedzi w quize ; indeksy 4,5,6,7 wybory w menu
    int[] width={0,0,0,0,0,0,0,0};
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        //System.out.println("X: " +x+" Y: "+y);

        if(x>= 640-width[0]/2 && x<= 640+width[0]/2  && y>=325 && y<=325+high[0])position =0;
        else if(x>= 640-width[1]/2 && x<=  640+width[1]/2 && y>=395 && y<=395+high[1])position =1;
        else if(x>= 640-width[2]/2 && x<=  640+width[2]/2 && y>=465 && y<=465+high[2])position =2;
        else if(x>= 640-width[3]/2 && x<=  640+width[3]/2 && y>=535 && y<=535+high[3])position =3;
        else position = -1;
        //System.out.println(position);
        /*x od 400 do 850 850 870 820
        y od 350 385*/
    }
}

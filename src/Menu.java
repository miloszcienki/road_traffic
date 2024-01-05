import java.awt.*;

public class Menu {
    int[] high={0,0,0,0,0};
    int[] width={0,0,0,0,0};
    String[] options={"Wznów","Zacznij od nowa","Jak Grać","Wyjdź"};
    public void paintMenu(Graphics2D g2d){

        Font czcionka = new Font("Courier New", Font.PLAIN, 100);

        g2d.setFont(czcionka);
        FontMetrics fontMetrics = g2d.getFontMetrics();
            width[4]=fontMetrics.stringWidth("Menu");
            high[4]=fontMetrics.getHeight();

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,1280,1024); // okno quizu
        g2d.setColor(Color.WHITE);


        g2d.drawString("Menu",640-width[4]/2,200);

        czcionka = new Font("Courier New", Font.PLAIN, 72);
        g2d.setFont(czcionka);
        fontMetrics = g2d.getFontMetrics();
        for(int i=0;i<=3;i++) {//pobieranie rozmiarów quziu
            width[i]=fontMetrics.stringWidth(options[i]);
            high[i]=fontMetrics.getHeight();
        }
        for(int i=0;i<=3;i++) g2d.drawString(options[i],640-width[i]/2,(400+(high[i]+20)*(i)));



    }

    public int  checkoption(MouseHandler mouseHandler){
        for(int i=0;i<=3;i++){
            mouseHandler.high[i]=high[i+1];
            mouseHandler.width[i]=width[i+1];

        }

        return 1;
    }
}

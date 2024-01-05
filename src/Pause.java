import java.awt.*;

public class Pause {

    public void paintPause (Graphics2D g2d) {

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 1280, 1024); // czarne tło
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Courier New", Font.PLAIN, 30));
        g2d.drawString("Pauza",500,500);
    }

   // public boolean checkPause(KeyHandler keyHandler){
    //    return
    //}

}

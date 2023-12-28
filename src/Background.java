import java.awt.*;
import javax.swing.*;

public  class Background {

    //public BufferedImage backgroundpic;
    int[] lenght_lanes={0,256,512,768};


public Background(){

        /*try{
            backgroundpic= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("backpic.png")));
        } catch(IOException e){e.printStackTrace();}
        */
    }

    public void paintback(Graphics2D g2d){

        g2d.setColor(new Color(0,153,51));
        g2d.fillRect(0,0,205,1024); // lewa krawędź 200px
        g2d.fillRect(1075,0,205,1024);// prawa krawędź 200px
        g2d.setColor(Color.BLACK);
        g2d.fillRect(205,0,870,1024);// srodek 880 px
        g2d.setColor(Color.WHITE);
        for(int i=485;i<880;i+=280) {//pasy
            for(int j=0;j<=3;j++) {
                g2d.fillRect(i,lenght_lanes[j] , 20 , 226);
                //System.out.println(lenght_lanes[j]);
            }
        }
        //System.out.println("4");
        //g2d.dispose();

    }

    public void move_road_lanes(){
        for(int i=0;i<=3;i++) {
            if (lenght_lanes[i] <= 768) lenght_lanes[i] += 1;
            else lenght_lanes[i] = -256;
        }
    }

}



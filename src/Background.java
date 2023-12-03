import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

public  class Background {

    public BufferedImage heart,white_heart;
    int[] lenght_lanes={0,256,512,768};
    boolean[] life={true,true,true};
    int score=0;
    Font font = new Font("Dialog", Font.PLAIN, 16);//czcionka

public Background(){

    getHeartImage();
    }
    public void getHeartImage(){
        heart = setup("/heart");
        white_heart = setup("/white_heart");
    }

    public void paintback(Graphics2D g2d){

        g2d.setColor(new Color(0,153,51)); // zmiana koloru na zielony
        g2d.fillRect(0,0,205,1024); // lewy pas zieleni 205px
        g2d.fillRect(1075,0,205,1024);// praw  pas zieleni 205px
        g2d.setColor(Color.BLACK); // zmiana koloru na czarny
        g2d.fillRect(205,0,870,1024);// pas jezdni 870 px
        g2d.setColor(Color.WHITE);// zmiana koloru na biały
        g2d.setFont(font);
        //g2d.setColor(new Color(0,0,0)); // zmiana koloru na zielony
        //g2d.fillRect(0,0,256,256); // lewy pas zieleni 205px
        g2d.drawString("Score: " + score, 10,20);// punkty gry
        for(int j=0;j<=120;j+=60) {
            if(life[j/60]) g2d.drawImage(heart,1085+j,0,52,40,null);
            else g2d.drawImage(white_heart,1085+j,0,null);
        }
        for(int i=485;i<880;i+=280) {// pętla rysująca linie oddzielające pasy ruchy
            for(int j=0;j<=3;j++) {
                g2d.fillRect(i,lenght_lanes[j] , 20 , 226);
            }
        }
    }

    public void move_road_lanes(){
        score++;
        for(int i=0;i<=3;i++) {
            if (lenght_lanes[i] <= 768) lenght_lanes[i] += 1;
            else lenght_lanes[i] = -256;
        }
    }

    public BufferedImage setup(String imagePath){
    BufferedImage image =null;
    System.out.println("check");
    try{
        image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
    }catch(IOException e){
        e.printStackTrace();
    }
    return image;
    }

}



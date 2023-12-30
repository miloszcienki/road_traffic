
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Quiz {
    boolean[] hover={false,false,false,false};
    String[] lines=new String[6];

    int lineNumberToRead=1;
    private Random rand = new Random();
    public void paintQuiz(Graphics2D g2d){

        //g2d.setColor(new Color(253, 5, 0,160));
        //g2d.fillRect(320,256,640,512); // okno quizu
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,1280,1024); // okno quizu
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Courier New", Font.PLAIN, 30));
        g2d.drawString(lines[0],0,200);
        for(int i=1;i<=4;i++) g2d.drawString(lines[i],400,(350+70*(i-1)));

        for(int i =0;i<=3;i++){


            if(hover[i]){
                g2d.setColor(Color.CYAN);
                g2d.fillRect(360,330+(70*i),20,20);

            }
        }
    }

    public void updateQuiz(MouseHandler mouseHandler){
        for(int i=0;i<=3;i++){

            hover[i]= mouseHandler.position == i;
        }
    }

    public void drawNewQuestion(){

    try
    {

        BufferedReader br = new BufferedReader(new FileReader("res/questions.txt"));
        // Zmienna do przechowywania odczytanej linii
        String line;

        // Zmienna do śledzenia numeru aktualnej linii
        int currentLineNumber = 0;

        // Odczytujemy linia po linii z pliku
        while ((line = br.readLine()) != null) {
            currentLineNumber++;

            // Sprawdzamy, czy aktualna linia jest tą, którą chcemy odczytać
            if (currentLineNumber == lineNumberToRead) {
                int j=0;//zmienna pomocnicza do iterowania tablicy
                for(int i=lineNumberToRead;i<=(lineNumberToRead+5);i++) {

                    //System.out.println("Linia " + i + ": " + line);
                    lines[j]=line;
                    line = br.readLine();
                    j++;
                }
                break; // Przerywamy pętlę po odczytaniu żądanej linii
            }
        }

        // Zamykamy BufferedReader po zakończeniu odczytu
        br.close();
    }  catch (IOException e) {
        // Obsługa błędów związanych z odczytem pliku
        e.printStackTrace();
    }
    }

    public void nextquest(boolean flag){
        if(flag)lineNumberToRead=1+(6*rand.nextInt(9));
    }

}

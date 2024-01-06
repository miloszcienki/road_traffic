
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Quiz {
    boolean[] hover={false,false,false,false};
    String[] lines=new String[6];
    int[] high={0,0,0,0,0};
    int[] width={0,0,0,0,0};

    int lineNumberToRead=1;
    private Random rand = new Random();

    int answer=-1;
    public void paintQuiz(Graphics2D g2d){
        Font czcionka = new Font("Courier New", Font.PLAIN, 30);

        g2d.setFont(czcionka);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        for(int i=0;i<=4;i++) {//pobieranie rozmiarów quziu
            width[i]=fontMetrics.stringWidth(lines[i]);
            high[i]=fontMetrics.getHeight();
        }
        //g2d.setColor(new Color(253, 5, 0,160));
        //g2d.fillRect(320,256,640,512); // okno quizu
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,1280,1024); // okno quizu
        g2d.setColor(Color.WHITE);

        g2d.drawString(lines[0],640-width[0]/2,200);
        for(int i=1;i<=4;i++) g2d.drawString(lines[i],640-width[i]/2,(350+70*(i-1)));

        for(int i =0;i<=3;i++){


            if(hover[i]){
                g2d.setColor(Color.CYAN);
                g2d.fillRect(640-30-width[i+1]/2,330+(70*i),20,20);

            }
        }
    }

    public void paintAnswer(Graphics2D g2d){

        //g2d.setColor(new Color(253, 5, 0,160));
        //g2d.fillRect(320,256,640,512); // okno quizu
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,1280,1024); // okno quizu
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Courier New", Font.PLAIN, 30));
        g2d.drawString("Poprawna odpowiedź:",0,200);
        for(int i=1;i<=4;i++) {
            if(answer==i)g2d.drawString(lines[i],400,(350+70*(i-1)));
        }

    }
    public void updateQuiz(MouseMotionHandler mouseMotionHandler){
        for(int i=0;i<=3;i++){
            mouseMotionHandler.high[i]=high[i+1];
            mouseMotionHandler.width[i]=width[i+1];
            hover[i]= mouseMotionHandler.position == i;
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
                for(int i=lineNumberToRead;i<=(lineNumberToRead+4);i++) {

                    //System.out.println("Linia " + i + ": " + line);

                        lines[j] = line;
                        line = br.readLine();
                        j++;

                        //line = br.readLine();
                        if((lineNumberToRead+4)==i){
                            answer= Integer.parseInt(line);
                            try {
                                answer= Integer.parseInt(line);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid integer input");
                            }
                        }



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

    public boolean  checkAnswer(MouseHandler mouseHandler){
        for(int i=0;i<=3;i++){
            mouseHandler.high[i]=high[i+1];
            mouseHandler.width[i]=width[i+1];

        }

        return answer == mouseHandler.position+1 ;
    }

}

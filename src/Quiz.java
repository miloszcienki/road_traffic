
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Klasa formująca Quiz gry
 * @author Miłosz Cienki
 */
public class Quiz {
    /** Tablica przechowująca czy wyświetlić niebieski kwadrat obok odpowiedź po najechaniu na nią*/
    boolean[] hover={false,false,false,false};
    /** Tablica Przechowująca odczytane z pliku: pytanie, 4 odpowiedzi, i cyfrę mówiącą, która odpowiedź jest poprawna*/
    String[] lines=new String[6];
    /** Wysokość kolejno wyświetlanego pytania i odpowiedźi*/
    int[] high={0,0,0,0,0};
    /** Szerokość kolejno wyświetlanego pytania i odpowiedźi*/
    int[] width={0,0,0,0,0};
    /** Zmienna od której lini ma być odczytany plik z pytaniami i odpowiedźiami*/
    int lineNumberToRead=1;
    /** Zmienna do losowania liczy*/
    private Random rand = new Random();
    /** Zmienna przechowująca numer, która odpowiedź jest poprawna*/
    int answer=-1;

    /**
     * Metoda odpowiadająca za wyświetlanie quizu
     * @param g2d obiekt klasy Graphics2D odpowiadającej za rysywanie
     */
    public void paintQuiz(Graphics2D g2d){
        Font czcionka = new Font("Courier New", Font.PLAIN, 26);

        g2d.setFont(czcionka);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        for(int i=0;i<=4;i++) {//pobieranie rozmiarów quziu
            width[i]=fontMetrics.stringWidth(lines[i]);
            high[i]=fontMetrics.getHeight();
        }

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

    /**
     * Metoda odpowiadająca za wyświetylanie okna z poprawną odpowiedzią po udzeleniu błędnej odpowiedź na quiz
     * @param g2d obiekt klasy Graphics2D odpowiadającej za rysywanie
     */
    public void paintAnswer(Graphics2D g2d){


        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,1280,1024); // okno quizu
        g2d.setColor(Color.WHITE);
        Font czcionka = new Font("Courier New", Font.PLAIN, 26);
        g2d.setFont(czcionka);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        g2d.drawString(lines[0],640-width[0]/2,200);
        g2d.setColor(Color.RED);
        g2d.drawString("Twoja odpowiedź jest błędna!",640-fontMetrics.stringWidth("Twoja odpowiedź jest błędna!")/2,300);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Poprawna odpowiedź to:",640-fontMetrics.stringWidth("Poprawna odpowiedź to:")/2,400);
        g2d.setColor(Color.GREEN);
        for(int i=1;i<=4;i++) {

            if(answer==i)g2d.drawString(lines[i],640-width[i]/2,500);
        }
        g2d.setColor(Color.WHITE);
        g2d.drawString("Aby zacząć grę od nowa wciśnij Enter",640-fontMetrics.stringWidth("Aby zacząć grę od nowa wciśnij Enter")/2,600);

    }

    /**
     * Metoda wyświetlająca informacje o poprawnej odpowiedźi
     * @param g2d obiekt klasy Graphics2D odpowiadającej za rysywanie
     */
    public void paintInfoGoodAnswer(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,1280,1024); // okno quizu
        g2d.setColor(Color.WHITE);
        Font czcionka = new Font("Courier New", Font.PLAIN, 32);
        g2d.setFont(czcionka);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        //g2d.drawString(lines[0],640-width[0]/2,200);
        g2d.drawString("Gratulacje!",640-fontMetrics.stringWidth("Gratulacje!")/2,400);
        g2d.setColor(Color.GREEN);
        g2d.drawString("Twoja odpowiedź jest poprawna otrzymałeś dodatkowe życie",640-fontMetrics.stringWidth("Twoja odpowiedź jest poprawna otrzymałeś dodatkowe życie")/2,500);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Aby kontynować grę naciśnij ENTER",640-fontMetrics.stringWidth("Aby kontynować grę naciśnij ENTER!")/2,600);
    }

    /**
     * Metoda odpowiadająca z wyświetlenie niebieskiego kwadracika po najechaniu na daną odpowiedź
     * @param mouseMotionHandler przekazuje koło której odpowiedź należy wyświetlić niebieski kwadracik na podstawie pozycji myszki
     */
    public void updateQuiz(MouseMotionHandler mouseMotionHandler){
        for(int i=0;i<=3;i++){
            mouseMotionHandler.high[i]=high[i+1];
            mouseMotionHandler.width[i]=width[i+1];
            hover[i]= mouseMotionHandler.position == i;
        }
    }

    /**
     * Metoda odpowiadająca za odczytanie Pytania i odpowiedź z pliku questions.txt
     */
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

    /**
     * Metoda losująca kolejne pytanie do odczytania
     * @param flag przekazuje informacje czy losować kolejne pytanie
     */
    public void nextquest(boolean flag){
        if(flag)lineNumberToRead=1+(6*rand.nextInt(9));//mnożymy liczbe od 0 do 0 przez 6 ponieważ odczytyjemy Pytanie, 4 odpowiedzi oraz numer poprawnej odpowiedzi czyli musimy zaczynać wczytywanie od co szóstej linii
    }

    /**
     * Metoda zwraca po kliknięciu czy odpowiedź jest poprawna oraz przekazuje wymiary Stringów
     * @param mouseHandler Przekazuje informacje w którą odpowiedź klikneliśmy
     * @return zwraca true jeśli odpowiedź jest poprawna, w innym wypadku false
     */
    public boolean  checkAnswer(MouseHandler mouseHandler){
        for(int i=0;i<=3;i++){
            mouseHandler.high[i]=high[i+1];
            mouseHandler.width[i]=width[i+1];
        }
        return answer == mouseHandler.position+1 ;
    }

}

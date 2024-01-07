import java.awt.*;

/**
 * Klasa formująca menu gry
 * @author Miłosz Cienki
 */
public class Menu {
    /** Wysokość kolejno wyświetlanych napisów w Menu*/
    int[] high={0,0,0,0,0};
    /** Szerokość kolejno wyświetlanych napisów w Menu*/
    int[] width={0,0,0,0,0};
    /** Czy wybrano opcję "Jak Grać?" */
    boolean howPlay=false;
    /** Tablica przechowujące czy wyświetlić niebieski kwadrat obok odpowiedź po najechaniu na nią*/
    boolean[] hover={false,false,false,false};
    /**Tablica przechowująca kolejne opcje Menu */
    String[] options={"Wznów","Zacznij od nowa","Jak Grać","Wyjdź"};

    /**
     * Metoda odpowiadająca za wyświetlenie menu
     * @param g2d obiekt klasy Graphics2D odpowiadającej za rysywanie
     */
    public void paintMenu(Graphics2D g2d){

        Font czcionka = new Font("Courier New", Font.PLAIN, 100);//deklaracja czcionki

        g2d.setFont(czcionka);//ustawienie czcionki
        FontMetrics fontMetrics = g2d.getFontMetrics();//utworzenie obiektu który zawiera informacje na temat rozmiarów czcionki
            width[4]=fontMetrics.stringWidth("Menu");//pobranie szerokości napisu
            high[4]=fontMetrics.getHeight();//pobranie wysokości napisu

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,1280,1024); // wyświetlenie czarnego tła
        g2d.setColor(Color.WHITE);


        g2d.drawString("Menu",640-width[4]/2,200);// wyświetlenie napisu

        czcionka = new Font("Courier New", Font.PLAIN, 72);
        g2d.setFont(czcionka);
        fontMetrics = g2d.getFontMetrics();
        for(int i=0;i<=3;i++) {//pobieranie wymiarów napisów
            width[i]=fontMetrics.stringWidth(options[i]);
            high[i]=fontMetrics.getHeight();
        }
        for(int i=0;i<=3;i++) {
            //wyświetlenie opcji
            g2d.setColor(Color.WHITE);
            g2d.drawString(options[i],640-width[i]/2,(400+(high[i]+20)*(i)));
            if(hover[i]){
                //wyświetlenie niebieskiego kwadracika koło opcji, na którą najechaliśmy kursorem
                g2d.setColor(Color.CYAN);
                g2d.fillRect(640-40-width[i]/2,(370+(100)*(i)),30,30);
            }
        }



    }

    /**
     * Wyświetlenie informacji jak Grać w grę
     * @param g2d obiekt klasy Graphics2D odpowiadającej za rysywanie
     */
    public void paintHowToPlay(Graphics2D g2d){

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,1280,1024); // okno
        g2d.setColor(Color.WHITE);
        Font czcionka = new Font("Courier New", Font.PLAIN, 100);

        g2d.setFont(czcionka);
        FontMetrics fontMetrics = g2d.getFontMetrics();

        g2d.drawString("Jak Grać?",640-fontMetrics.stringWidth("Jak Grać?")/2,100);
        czcionka = new Font("Courier New", Font.PLAIN, 26);
        g2d.setFont(czcionka);
        g2d.drawString("- Sterowanie odbywa się poprzez przyciski W,A,S,D",0,200+fontMetrics.getHeight());
        g2d.drawString("- Aby nie stracić żyć należy unikac nadjężdzających pojazdów",0,200+2*fontMetrics.getHeight());
        g2d.drawString("- Wjechanie na trawnik również odejmuje życia",0,200+3*fontMetrics.getHeight());
        g2d.drawString("- Jeśli stracimy wszystkie życia za każdym razem dostajemy dodatkową szanse,",0,200+4*fontMetrics.getHeight());
        g2d.drawString(" jeśli poprawnie odpowiemy na pytanie dostaniemy dodatkowe życie",0,200+5*fontMetrics.getHeight());
        g2d.drawString(" - Powodzenia!",0,200+6*fontMetrics.getHeight());


    }

    /**
     * Metoda zwracająca którą opcje z menu wybraliśmy poprzez kliknięcie myszki
     * @param mouseHandler -- przekazuje opcje którą wybraliśmy poprzez kliknięcie po sprawdzeniu czy pozycja myszki zgadza się z obrębem opcji
     * @return zwraca pozycję w menu którą wybraliśmy poprzez kliknięcie
     */
    public int  checkoption(MouseHandler mouseHandler){
        for(int i=0;i<=3;i++){
            mouseHandler.high[i+4]=high[i+1];
            mouseHandler.width[i+4]=width[i+1];
        }
        return mouseHandler.position-4;
    }

    /**
     * Metoda odpowiadająca za zmiennienie w tablicy wartości na true jeśli mamy wyrysować dany kwadracik, po najechaniu na konkretną opcją
     * @param mouseMotionHandler -- przekazuje infromacje o położeniu myszki po jej przemieszczeniu
     */
    public void hoverMenu(MouseMotionHandler mouseMotionHandler){
        for(int i=0;i<=3;i++){
            mouseMotionHandler.high[i+4]=high[i+1];
            mouseMotionHandler.width[i+4]=width[i+1];
            hover[i]= mouseMotionHandler.position-4 == i;
        }
    }
}

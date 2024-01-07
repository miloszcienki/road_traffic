import java.awt.*;

/**
 * Klasa formująca hitboxy w grze
 * @author Miłosz Cienki
 */
class GameObject {
    private int x, y;//zmienne przechowujące pozycje obiektu
    private int width, height;//zmienne przechowujące wymiary obiektu

    /**
     *Konstruktor klasy formującej hitboxy w grze
     * @param x przekazuje położenie na osi X
     * @param y przekazuje położenie na osi Y
     * @param width przekazuje szerokość obiektu
     * @param height przekazuje wysokośc obiektu
     */
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Metoda ustawia aktualne parametry obiektu
     * @param x przekazuje położenie na osi X
     * @param y przekazuje położenie na osi Y
     * @param width przekazuje szerokość obiektu
     * @param height przekazuje wysokośc obiektu
     */
    public void setCords(int x,int y,int width,int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Metoda tworzy nowy obiekt Rectangle
     * @return zwraca nowy obiekt
     */
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }

    /**
     *Metoda zwraca informacje czy dwa obiekty nachodza na siebie
     * @param other przekazanie jednego obiektu
     * @return zwraca true lub false w zależności czy warunek został spełniony
     */
    public boolean collidesWith(GameObject other) {
        Rectangle thisHitbox = this.getHitbox();
        Rectangle otherHitbox = other.getHitbox();

        return thisHitbox.intersects(otherHitbox);
    }
}
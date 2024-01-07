import java.util.ArrayList;
import java.util.List;

/**
 * Klasa formująca kolizję w grze
 * @author Miłosz Cienki
 */

public class CollisionDetector {
    // Tworzymy listę obiektów gry
    List<GameObject> gameObjects = new ArrayList<>();
    GameObject car;

    long lastTime;
    CollisionDetector(){

        lastTime=System.nanoTime();

        // Dodajemy przykładowe obiekty do listy
        gameObjects.add(new GameObject(0,0,205,1024));//lewy pas zieleni
        gameObjects.add(new GameObject(1075,0,205,1024));//prawy pas zieleni
        gameObjects.add(new GameObject(0,0,100,256));//bot
        car=new GameObject(512, 512, 100, 256);//samochód


        }

    /**
     * Metoda sprawdzająca czy nie nastąpiła kolizja między graczem a innym obiektem
     * @return zwraca true jeśli nastąpiła
     */
    public boolean checkCollision(){
        // Sprawdzamy kolizje między wszystkimi obiektami
        for (int i = 0; i < gameObjects.size(); i++) {
            if (car.collidesWith(gameObjects.get(i))  ) {
                if((System.nanoTime()-lastTime)>=1000000000) {
                    lastTime =System.nanoTime();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metoda odpowiadająca za aktualizowanie aktualnych współrzędnych gracza
     * @param x przekazywanie pozycji na osi x
     * @param y przekazywanie pozycji na osi y
     * @param width przekazywanie szerokości pojazdu
     * @param height przekazywanie wysokości pojazdu
     */
        public void updateCarPosition(int x,int y,int width,int height){
        car.setCords( x, y, width,height);
        }

    /**
     * Metoda odpowiadająca za aktualizowanie aktualnych współrzędnych Bota
     * @param x przekazywanie pozycji na osi x
     * @param y przekazywanie pozycji na osi y
     * @param width przekazywanie szerokości pojazdu
     * @param height przekazywanie wysokości pojazdu
     */
    public void updateBotPosition(int x,int y,int width,int height){
        gameObjects.get(2).setCords( x, y, width,height);
    }

}



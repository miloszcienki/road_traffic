
import java.util.ArrayList;
import java.util.List;

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
        gameObjects.add(new GameObject(0,0,256,256));//bot
        car=new GameObject(512, 512, 256, 256);//samochód


        }
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
        public void updateCarPosition(int x,int y,int width,int height){
        car.setCords( x, y, width,height);
        }
    public void updateBotPosition(int x,int y,int width,int height){
        gameObjects.get(2).setCords( x, y, width,height);
    }

}



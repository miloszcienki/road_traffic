import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Klasa przechwytująca kliknięć klawiatury, impementująca KeyListener
 * @author Miłosz Cienki
 */
public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, escapePressed, pPressed,enterPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Obsułga zdarzeń klawiatury, gdy przycisk zostaje kliknięty
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed=true;
        }

        if(code == KeyEvent.VK_A){
            leftPressed=true;
        }

        if(code == KeyEvent.VK_S){
            downPressed=true;
        }

        if(code == KeyEvent.VK_D){
            rightPressed=true;
        }
        if(code == KeyEvent.VK_P){
            pPressed=!pPressed;
        }

        if(code == KeyEvent.VK_ESCAPE){
            escapePressed=!escapePressed;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed=true;
        }
    }

    /**
     * Obsługa zdarzeń klawiatury a dokładniej kiedy przycisk zostaje zwolniony
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed=false;
        }

        if(code == KeyEvent.VK_A){
            leftPressed=false;
        }

        if(code == KeyEvent.VK_S){
            downPressed=false;
        }

        if(code == KeyEvent.VK_D){
            rightPressed=false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed=false;
        }






    }
}

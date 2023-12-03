import java.awt.*;

class GameObject {
    private int x, y;
    private int width, height;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setCords(int x,int y,int width,int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }

    public boolean collidesWith(GameObject other) {
        Rectangle thisHitbox = this.getHitbox();
        Rectangle otherHitbox = other.getHitbox();

        return thisHitbox.intersects(otherHitbox);
    }
}
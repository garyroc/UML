import javax.swing.*;
import java.awt.*;

public class Drawable extends JComponent {
//    protected int xPos = 0;
//    protected int yPos = 0;
    protected Point startPoint = new Point(0,0);
    protected String text;

    public Drawable() {
        this.startPoint.x = 0;
        this.startPoint.y = 0;
    }

    public Drawable(int x, int y) {
        this.startPoint.x = x;
        this.startPoint.y = y;
    }

    public Drawable(Point givenPoint) {
        this.startPoint.x = givenPoint.x;
        this.startPoint.y = givenPoint.y;
    }

    public int getX() { return startPoint.x; }
    public int getY() { return startPoint.y; }

    protected void paintObject(Graphics g){    }
}

import javax.swing.*;
import java.awt.*;

public class Drawable extends CompositeProtocol {
    protected Point startPoint = new Point(0,0);
    protected String text;

    public Drawable(Point givenPoint) {
        this.startPoint.x = givenPoint.x;
        this.startPoint.y = givenPoint.y;
    }

    public int getX() { return startPoint.x; }
    public int getY() { return startPoint.y; }

    protected void paintObject(Graphics g){    }
}
